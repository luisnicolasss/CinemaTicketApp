package com.example.cinematicketapp.Activity

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.cinematicketapp.R
import com.example.cinematicketapp.adapter.CastListAdapter
import com.example.cinematicketapp.adapter.CategoryEachFilmAdapter
import com.example.cinematicketapp.databinding.ActivityFilmDetailBinding
import com.example.cinematicketapp.models.Film
import eightbitlab.com.blurview.RenderScriptBlur

class FilmDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityFilmDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFilmDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )

        setVariable()
    }

    private fun setVariable() {
        val item: Film =intent.getParcelableExtra("object")!!
        val requestOptions = RequestOptions().transform(CenterCrop(), GranularRoundedCorners(0f, 0f, 50f, 50f))

        Glide.with(this).load(item.Poster).apply(requestOptions).into(binding.filmPic)

        binding.titleTxt.text = item.Title
        binding.movieTimeTxt.text = "${item.Year} - ${item.Time}"
        binding.movieSummaryTxt.text = item.Description
        binding.imdbTxt.text = "IMDB ${item.Imdb}"

        binding.backBtn.setOnClickListener {
            finish()
        }

        val radius = 10f
        val decorView = window.decorView
        val rootView = decorView.findViewById<ViewGroup>(android.R.id.content)
        val windowsBackground = decorView.background

        binding.blurView.setupWith(rootView, RenderScriptBlur(this)).setFrameClearDrawable(windowsBackground).setBlurRadius(radius)
        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.blurView.clipToOutline = true

        item.Genre?.let {
            binding.genreView.adapter = CategoryEachFilmAdapter(it)
            binding.genreView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }

        item.Casts?.let {
            binding.castListView.adapter = CastListAdapter(it)
            binding.castListView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }


    }
}