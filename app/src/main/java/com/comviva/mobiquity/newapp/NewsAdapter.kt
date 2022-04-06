package com.comviva.mobiquity.newapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comviva.mobiquity.newapp.news.Article
import kotlinx.android.synthetic.main.card_view.view.*

class NewsAdapter(val context: Context, val articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsAdapter.ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.title.text = article.title
        val imageUrl = article.urlToImage
        Glide
            .with(context)
            .load(imageUrl)
            .fallback(R.drawable.img)
            .into(holder.image)
        holder.description.text = article.description

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