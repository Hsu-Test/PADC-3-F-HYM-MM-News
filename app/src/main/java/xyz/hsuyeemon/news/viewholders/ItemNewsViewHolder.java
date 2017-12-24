package xyz.hsuyeemon.news.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.delegates.NewsActionDelegate;

/**
 * Created by Dell on 12/5/2017.
 */

public class ItemNewsViewHolder extends RecyclerView.ViewHolder {

    private NewsActionDelegate mNewsActionDelegete;
    public ItemNewsViewHolder(View itemView, NewsActionDelegate newsActionDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView); //initialize

        mNewsActionDelegete=newsActionDelegate;
    }
    @OnClick(R.id.cv_news_items_root)
    public void onNewsItemTap(View view){
       // Toast.makeText(view.getContext(),"News item clicked",Toast.LENGTH_SHORT).show();
        mNewsActionDelegete.onTapNewsItem();  //relay
    }

}
