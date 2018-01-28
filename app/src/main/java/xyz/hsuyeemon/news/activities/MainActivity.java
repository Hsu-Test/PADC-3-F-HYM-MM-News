package xyz.hsuyeemon.news.activities;

import android.Manifest;
import android.accounts.Account;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.hsuyeemon.news.MMNewsApp;
import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.adapters.NewsAdapter;
import xyz.hsuyeemon.news.data.models.LoginUserModel;
import xyz.hsuyeemon.news.data.models.NewsModel;
import xyz.hsuyeemon.news.data.vo.NewsVO;
import xyz.hsuyeemon.news.delegates.BeforeLoginDelegate;
import xyz.hsuyeemon.news.delegates.LoginUserDelegate;
import xyz.hsuyeemon.news.delegates.NewsActionDelegate;
import xyz.hsuyeemon.news.events.LoadedNewsEvent;
import xyz.hsuyeemon.news.viewpods.AccountControlViewPod;
import xyz.hsuyeemon.news.viewpods.BeforeLoginViewPod;

public class MainActivity extends AppCompatActivity
        implements NewsActionDelegate, BeforeLoginDelegate, LoginUserDelegate {

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private NewsAdapter mNewsAdapter;

    private AccountControlViewPod vpAccountConrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_all_news);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mNewsAdapter = new NewsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        rvNews.setLayoutManager(linearLayoutManager);

        rvNews.setAdapter(mNewsAdapter);

        NewsModel.getsObjInstance().loadNews();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_news_by_categories) {
                    Intent intent = NewsByCategoryActivity.newIntent(getApplicationContext());
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return false;
            }
        });

        //BeforeLoginViewPod vpBeforeLogin = (BeforeLoginViewPod) navigationView.getHeaderView(0);
        //vpBeforeLogin.setmDelegate(this);
        vpAccountConrol = (AccountControlViewPod) navigationView.getHeaderView(0);
        vpAccountConrol.setDelegate((BeforeLoginDelegate) this);
        vpAccountConrol.setDelegate((LoginUserDelegate) this);

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
        } else if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onTabFab(View view) {

        /*
        Snackbar.make(view, "Replace with your own action - Butterknife", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
                */

        String numberToCall = "+959448500348";
        callToNumber(numberToCall);
        //intentToCall.putExtra()   if the data we send is string or others


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            //request call phone permission
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                String numberToCall = "+959448500348";
                callToNumber(numberToCall);
            }
        }
    }


    private void callToNumber(String numberToCall){
        Uri numberToCallUri = Uri.parse("tel:" + numberToCall);
        Intent intentToCall = new Intent(Intent.ACTION_CALL, numberToCallUri);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    100);

            return;
        }
        startActivity(intentToCall);
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
    public void onTapSendToButton(NewsVO tappedNews) {

        //Intent intent = ShareCompat.IntentBuilder.from(this).getIntent();

        Intent shareIntent = ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setText(tappedNews.getBrief())
                .getIntent();

        //check whether the application exits
        if(shareIntent.resolveActivity(getPackageManager()) != null){
            startActivity(shareIntent);
        }
        else{
            Snackbar.make(rvNews,"No appTo Handle Share Action",Snackbar.LENGTH_INDEFINITE).show();
        }

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

    @Override
    public void onTapToLogin() {

        Intent intent = AccountControlActivity.newIntentLogin(getApplicationContext());

        startActivity(intent);
    }

    @Override
    public void onTapToRegister() {
        Intent intent = AccountControlActivity.newIntentRegister(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTapLogout() {
        LoginUserModel.getObjInstance(getApplicationContext()).logout();
    }
}