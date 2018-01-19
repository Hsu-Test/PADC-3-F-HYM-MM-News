package xyz.hsuyeemon.news.activities;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.adapters.ImagesInNewsDetailsAdapter;
import xyz.hsuyeemon.news.data.models.NewsModel;
import xyz.hsuyeemon.news.data.vo.NewsVO;

/**
 * Created by Dell on 12/9/2017.
 */

public class NewsDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tb_toolbar)
    Toolbar toolbar;

    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;

    @BindView(R.id.tv_publication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.tv_news_details)
    TextView tvNewsDetails;

    private ImagesInNewsDetailsAdapter mImagesInNewsDetailsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this,this);

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        mImagesInNewsDetailsAdapter=new ImagesInNewsDetailsAdapter();
        vpNewsDetailsImages.setAdapter(mImagesInNewsDetailsAdapter);

        String newsId = getIntent().getStringExtra("news_id");  //getIntExtra if the inserted data is integer
        NewsVO news = NewsModel.getsObjInstance().getNewsById(newsId);
        bindData(news);

    }
    private void bindData(NewsVO news){
        tvNewsDetails.setText(news.getDetails());
        tvPublicationTitle.setText(news.getPublication().getTitle());

        Glide.with(ivPublicationLogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivPublicationLogo);

    }
}
