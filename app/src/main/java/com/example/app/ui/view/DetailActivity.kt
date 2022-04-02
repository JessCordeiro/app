package com.example.app.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private init var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent?.extras?.let{

            val match = it.getParcelable(Extras.MATCH) as Match?

            if(match != null){
                glide.with(this).load(match.place.image).into(binding.ivPlace)
                supportActionBar?.title = match.place.title

                binding.tvDescription.text = match.description

                Glide.with(this).load(match.homeTeam.image).into(binding.ivHomeTeam)
                binding.tvHomeTeamName.text = match.homeTeam.name

                binding.rbHomeTeamStars.rating = match.homeTeam.stars.toFloat()
                if(match.homeTeam.score != null){
                    binding.tvHomeTeamScore.text = match.homeTeam.score.toString()
                }
            }
        }
    }
}