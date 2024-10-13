package com.example.cinematicketapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.cinematicketapp.databinding.ViewholderSliderBinding
import com.example.cinematicketapp.models.SliderItems

class SliderAdapter(private val sliderItems: MutableList<SliderItems>,
                    private val viewPager2: ViewPager2):
    RecyclerView.Adapter<SliderAdapter.SilderViewHolder>() {
    private var context: Context?= null
    private val runnable= Runnable {
        sliderItems.addAll(sliderItems)
        notifyDataSetChanged()
    }
    inner class SilderViewHolder(private val binding: ViewholderSliderBinding): RecyclerView.ViewHolder(binding.root) {
       fun bind(sliderItems: SliderItems){
        val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(60))
           context?.let {
               Glide.with(it).load(sliderItems.image).apply(requestOptions).into(binding.imageSlide)
           }
       }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SilderViewHolder {
        context=parent.context
        val binding=
            ViewholderSliderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SilderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderAdapter.SilderViewHolder, position: Int) {
       holder.bind(sliderItems[position])
        if (position==sliderItems.size-2){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int = sliderItems.size
}