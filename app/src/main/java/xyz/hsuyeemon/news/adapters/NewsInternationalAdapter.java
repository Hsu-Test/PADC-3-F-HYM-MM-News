package xyz.hsuyeemon.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.viewholders.ItemInternationalNewsViewHolder;

/**
 * Created by Dell on 1/12/2018.
 */

public class NewsInternationalAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.item_international_news,parent,false);
        ItemInternationalNewsViewHolder itemInternationalNewsViewHolder=new ItemInternationalNewsViewHolder(view);
        return itemInternationalNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
