package xyz.hsuyeemon.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.data.vo.NewsVO;
import xyz.hsuyeemon.news.delegates.NewsActionDelegate;
import xyz.hsuyeemon.news.viewholders.ItemNewsViewHolder;

/**
 * Created by Dell on 12/5/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<ItemNewsViewHolder> {   //<INV> = will bind with ItemNewsViewHolder

    private NewsActionDelegate mNewsActionDelegate;
    private List<NewsVO> mNewsList; //for news

    public NewsAdapter(NewsActionDelegate newsActionDelegate) {
        mNewsActionDelegate=newsActionDelegate;
        mNewsList=new ArrayList<>(); //for null pointer exception
    }

    @Override
    public ItemNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View newsItemView=inflater.inflate(R.layout.item_news,parent,false);
        ItemNewsViewHolder itemNewsViewHolder=new ItemNewsViewHolder(newsItemView,mNewsActionDelegate);
        return itemNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemNewsViewHolder holder, int position) {
        holder.setNews(mNewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setNews(List<NewsVO> newsList){
        mNewsList=newsList;
        notifyDataSetChanged();
    }
}
