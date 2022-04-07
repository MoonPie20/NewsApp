package com.comviva.mobiquity.newapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_artilce_details.*
import android.content.Intent
import android.net.Uri
import android.widget.*
import com.comviva.mobiquity.newapp.Router.selectedArticleurl


class ArtilceDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artilce_details)
        displayStuff()
    }

    private fun displayStuff() {
        val atricledetailstitle = findViewById<TextView>(R.id.atricledetailstitle)
        val atricledetailsdescription = findViewById<TextView>(R.id.atricledetailsdescription)
        val imageArticleDetail = findViewById<ImageView>(R.id.imageArticleDetail)
        val publishedBy = findViewById<TextView>(R.id.publishedBy)
        val datepublished = findViewById<TextView>(R.id.datepublished)
        val readfullbutton = findViewById<Button>(R.id.readfullbutton)
        atricledetailstitle.setText(Router.selectedArticleTitle)
        atricledetailsdescription.setText(Router.selectedArticleDescription)
        publishedBy.setText(Router.selectedArticleAuthor)
        datepublished.setText(Router.selectedArticledate)
        Glide
            .with(this)
            .load(Router.selectedArticleurlImage)
            .fallback(R.drawable.img)
            .into(imageArticleDetail)

        readfullbutton.setOnClickListener {
            if(selectedArticleurl.equals("not known"))
            {
                Toast.makeText(this,"No Article Found",Toast.LENGTH_SHORT)
            }
            else
            {
                val uri: Uri = Uri.parse(Router.selectedArticleurl) // missing 'http://' will cause crashed
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

        }

    }
}