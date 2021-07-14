package com.example.economic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_news.*
import kotlinx.android.synthetic.main.activity_main_news.view.*

class DetailNews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)
        Picasso.get().load(intent.getStringExtra("title_image")).fit()
            .centerCrop()
            .error(R.drawable.ic_baseline_bathtub_24)
            .placeholder(R.drawable.ic_baseline_person_24)
            .into(image_news_detail)
    }
}
