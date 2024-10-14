package com.example.cinematicketapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.cinematicketapp.databinding.ViewholderFilmBinding
import com.example.cinematicketapp.models.Film

class FilmListAdapter(private val items: ArrayList<Film>): RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>() {
    private var context: Context?= null

    inner class FilmViewHolder(private val binding: ViewholderFilmBinding): RecyclerView.ViewHolder(binding.root) {
         fun bind(film: Film){
             binding.nameTxt.text=film.Title
             val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(30))

             Glide.with(context!!).load(film.Poster).apply(requestOptions).into(binding.pic)

             binding.root.setOnClickListener {
                 // Handle item click
             }
         }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmListAdapter.FilmViewHolder {
        context=parent.context
        val binding=
            ViewholderFilmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmListAdapter.FilmViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}