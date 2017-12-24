package xyz.hsuyeemon.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.delegates.NewsActionDelegate;
import xyz.hsuyeemon.news.viewholders.ItemNewsViewHolder;

/**
 * Created by Dell on 12/5/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private NewsActionDelegate mNewsActionDelegate;
    public NewsAdapter(NewsActionDelegate newsActionDelegate) {
        mNewsActionDelegate=newsActionDelegate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View newsItemView=inflater.inflate(R.layout.item_news,parent,false);
        ItemNewsViewHolder itemNewsViewHolder=new ItemNewsViewHolder(newsItemView,mNewsActionDelegate);
        return itemNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 16;
    }
}
