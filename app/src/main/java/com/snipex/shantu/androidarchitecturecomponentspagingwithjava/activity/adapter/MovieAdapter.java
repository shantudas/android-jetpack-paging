package com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.snipex.shantu.androidarchitecturecomponentspagingwithjava.R;
import com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.model.Article;

public class MovieAdapter extends PagedListAdapter<Article, MovieAdapter.ViewHolder> {

    private Context mCtx;

    public MovieAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.list_each_row_movies, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder viewHolder, int i) {
        Article article = getItem(i);
        if (article != null) {
            viewHolder.tvTitle.setText(article.getTitle());
            viewHolder.tvAuthor.setText("-" + article.getAuthor());
            viewHolder.tvDescription.setText(article.getDescription());
            Glide.with(mCtx)
                    .load(article.getUrlToImage())
                    .into(viewHolder.imgViewMoviesCover);

        } else {
            Toast.makeText(mCtx, "article is null", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<Article> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Article>() {
                @Override
                public boolean areItemsTheSame(Article oldArticle, Article newArticle) {
                    return oldArticle.getTitle() == newArticle.getTitle();
                }

                @Override
                public boolean areContentsTheSame(Article oldArticle, Article newArticle) {
                    return oldArticle.equals(newArticle);
                }
            };


    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvDescription;
        private final TextView tvAuthor;
        private final ImageView imgViewMoviesCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            imgViewMoviesCover = (ImageView) itemView.findViewById(R.id.imgViewMoviesCover);
        }
    }


}
