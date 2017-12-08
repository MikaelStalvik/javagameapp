package com.imploded.javagameapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imploded.javagameapp.R;
import com.imploded.javagameapp.models.Game;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    private List<Game> games;
    private Context context;
    private static ClickListener clickListener;

    public GamesAdapter(Context context, List<Game> games) {
        this.games = games;
        this.context = context;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        GamesAdapter.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_game, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Game game = games.get(position);
        holder.textViewTitle.setText(game.getName());
        holder.textViewInfo.setText(game.getReleaseYear() + ", " + game.getPublisher());
        Picasso.with(context).load(game.getPicture()).into(holder.imageViewPicture);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewPicture;
        TextView textViewTitle;
        TextView textViewInfo;
        View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            v.setOnClickListener(this);
            imageViewPicture = v.findViewById(R.id.imageViewPicture);
            textViewInfo = v.findViewById(R.id.textViewInfo);
            textViewTitle = v.findViewById(R.id.textViewTitle);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }

    }
}
