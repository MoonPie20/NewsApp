package com.comviva.mobiquity.newapp.news

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comviva.mobiquity.newapp.ArtilceDetails
import com.comviva.mobiquity.newapp.R
import com.comviva.mobiquity.newapp.Router
import kotlinx.android.synthetic.main.card_view.view.*

class NewsAdapter(val context: Context, val articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.title.text = article.title
        val imageUrl = article.urlToImage
        Glide
            .with(context)
            .load(imageUrl)
            .fallback(R.drawable.img)
            .into(holder.image)
        holder.description.text = article.description
        holder.cardParentBg.setOnClickListener{
        Router.selectedArticleTitle=articles[position].title
        Router.selectedArticleDescription=articles[position].description
            if( articles[position].author==null)
            {
                Router.selectedArticleAuthor="Anonymous"
            }
            else
            {
                Router.selectedArticleAuthor=articles[position].author
            }
            if( articles[position].publishedAt==null)
            {
                Router.selectedArticledate="Unknown"
            }
            else
            {
                Router.selectedArticledate=articles[position].publishedAt
            }
            if(articles[position].url.equals("") || articles[position].url.equals(null))
            {
                Router.selectedArticleurl="not known"
            }
            else
            {
                Router.selectedArticleurl=articles[position].url
            }

        Router.selectedArticleurlImage=articles[position].urlToImage
            val intent = Intent(context, ArtilceDetails::class.java)
            context.startActivity(intent)

        }


    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.articleImage)
        var title = itemView.findViewById<TextView>(R.id.articleTitle)
        var description = itemView.findViewById<TextView>(R.id.articleDescription)
        val cardParentBg: LinearLayout = itemView.card_parent_bg

        
    }

}