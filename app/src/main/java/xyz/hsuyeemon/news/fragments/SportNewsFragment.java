package xyz.hsuyeemon.news.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.adapters.NewsSportAdapter;

/**
 * Created by Dell on 1/7/2018.
 */

public class SportNewsFragment extends Fragment{

    @BindView(R.id.rv_sport_news)
    RecyclerView rvSportNews;

    private NewsSportAdapter mNewsSportAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sport_news,container,false);

        ButterKnife.bind(this,view);
        LinearLayoutManager lm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvSportNews.setLayoutManager(lm);
        mNewsSportAdapter=new NewsSportAdapter();
        rvSportNews.setAdapter(mNewsSportAdapter);

        return view;


    }


}
