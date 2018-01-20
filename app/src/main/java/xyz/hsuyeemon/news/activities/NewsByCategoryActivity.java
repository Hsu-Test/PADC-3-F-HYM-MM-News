package xyz.hsuyeemon.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.adapters.NewsByCategoryAdapter;
import xyz.hsuyeemon.news.fragments.InternationalNewsFragment;
import xyz.hsuyeemon.news.fragments.NewsByCategoryFragment;
import xyz.hsuyeemon.news.fragments.SportNewsFragment;

/**
 * Created by Dell on 1/7/2018.
 */

public class NewsByCategoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tb_news_by_category)
    TabLayout tbNewsByCategory;

    @BindView(R.id.vp_news_by_categories)
    ViewPager vpNewsByCategories;


    private NewsByCategoryAdapter mNewsByCategoryAdapter;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,NewsByCategoryActivity.class);
        return intent;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_by_category);
        ButterKnife.bind(this,this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(R.string.title_news_by_category);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mNewsByCategoryAdapter=new NewsByCategoryAdapter(getSupportFragmentManager());  //parameter as FragmentManager
        vpNewsByCategories.setAdapter(mNewsByCategoryAdapter);

        mNewsByCategoryAdapter.addTab("Local News",new NewsByCategoryFragment());
        mNewsByCategoryAdapter.addTab("International News",new InternationalNewsFragment());
        mNewsByCategoryAdapter.addTab("Sport News",new SportNewsFragment());

        tbNewsByCategory.setupWithViewPager(vpNewsByCategories);
        vpNewsByCategories.setOffscreenPageLimit(mNewsByCategoryAdapter.getCount());


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
