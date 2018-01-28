package xyz.hsuyeemon.news.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.hsuyeemon.news.MMNewsApp;
import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.adapters.NewsAdapter;
import xyz.hsuyeemon.news.data.models.NewsModel;
import xyz.hsuyeemon.news.data.vo.NewsVO;
import xyz.hsuyeemon.news.delegates.NewsActionDelegate;
import xyz.hsuyeemon.news.events.LoadedNewsEvent;

/**
 * Created by Dell on 1/7/2018.
 */

public class NewsByCategoryFragment extends Fragment implements NewsActionDelegate{

    @BindView(R.id.rv_news_by_category)
    RecyclerView rvNewsByCategory;

    private NewsAdapter mNewsByCategoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news_by_category,container,false);
        ButterKnife.bind(this,view);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        rvNewsByCategory.setLayoutManager(linearLayoutManager);

        mNewsByCategoryAdapter=new NewsAdapter(this);
        rvNewsByCategory.setAdapter(mNewsByCategoryAdapter);

        NewsModel.getsObjInstance().loadNews();

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapNewsItem(NewsVO tappedNews) {

    }

    @Override
    public void onTapCommentButton() {

    }

    @Override
    public void onTapSendToButton(NewsVO tappedNews) {

    }

    @Override
    public void onTapFavoriteButton() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsLoaded(LoadedNewsEvent event){
        Log.d(MMNewsApp.LOG_TAG,"onNewsLoaded" + event.getNewsList().size());
        mNewsByCategoryAdapter.setNews(event.getNewsList());
    }
}
