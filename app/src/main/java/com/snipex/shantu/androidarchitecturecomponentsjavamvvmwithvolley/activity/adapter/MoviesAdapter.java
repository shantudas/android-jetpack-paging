package com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.R;
import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.model.Article;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private Context _context;
    ArrayList<Article> articleArrayList;

    public MoviesAdapter(Context _context, ArrayList<Article> articleArrayList) {
        this._context = _context;
        this.articleArrayList = articleArrayList;
    }


    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_each_row_movies, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder viewHolder, int position) {
        Article article = articleArrayList.get(position);
        viewHolder.tvTitle.setText(article.getTitle());
        viewHolder.tvAuthor.setText("-"+article.getAuthor());
        viewHolder.tvDescription.setText(article.getDescription());
        Glide.with(_context)
                .load(article.getUrlToImage())
                .into(viewHolder.imgViewMoviesCover);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvDescription;
        private final TextView tvAuthor;
        private final ImageView imgViewMoviesCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            imgViewMoviesCover=(ImageView) itemView.findViewById(R.id.imgViewMoviesCover);
        }
    }
}
