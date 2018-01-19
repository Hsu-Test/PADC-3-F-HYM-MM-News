package xyz.hsuyeemon.news.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.hsuyeemon.news.MMNewsApp;
import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.adapters.NewsAdapter;
import xyz.hsuyeemon.news.data.models.NewsModel;
import xyz.hsuyeemon.news.data.vo.NewsVO;
import xyz.hsuyeemon.news.delegates.NewsActionDelegate;
import xyz.hsuyeemon.news.events.LoadedNewsEvent;

public class MainActivity extends AppCompatActivity
        implements NewsActionDelegate{

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;


    private NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this,this);
        setSupportActionBar(toolbar);

        mNewsAdapter=new NewsAdapter(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false);
        rvNews.setLayoutManager(linearLayoutManager);

        rvNews.setAdapter(mNewsAdapter);

        NewsModel.getsObjInstance().loadNews();

            }

   @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.fab)
    public void onTabFab(View view){
        Snackbar.make(view, "Replace with your own action - Butterknife", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    @Override
    public void onTapNewsItem(NewsVO tappedNews) {
        Intent intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
        //get key from activity
        intent.putExtra("news_id",tappedNews.getNewsId());
        startActivity(intent);   //launch
    }

    @Override
    public void onTapCommentButton() {

    }

    @Override
    public void onTapSendToButton() {

    }

    @Override
    public void onTapFavoriteButton() {

    }
    /**
     * event listen
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsLoaded(LoadedNewsEvent event){
        Log.d(MMNewsApp.LOG_TAG,"onNewsLoaded" + event.getNewsList().size());
        mNewsAdapter.setNews(event.getNewsList());
    }
}