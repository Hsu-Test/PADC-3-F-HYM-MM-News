package xyz.hsuyeemon.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.viewholders.ItemNewsViewHolder;
import xyz.hsuyeemon.news.viewholders.ItemSportViewHolder;

/**
 * Created by Dell on 1/11/2018.
 */

public class NewsSportAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View newsSportItemView=inflater.inflate(R.layout.item_sport_news,parent,false);
        ItemSportViewHolder itemNewsViewHolder=new ItemSportViewHolder(newsSportItemView);
        return itemNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
