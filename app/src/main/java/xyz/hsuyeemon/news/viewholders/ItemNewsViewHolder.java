package xyz.hsuyeemon.news.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.data.vo.NewsVO;
import xyz.hsuyeemon.news.delegates.NewsActionDelegate;

/**
 * Created by Dell on 12/5/2017.
 */

public class ItemNewsViewHolder extends RecyclerView.ViewHolder {

    private NewsActionDelegate mNewsActionDelegete;

    private NewsVO mNews;

    @BindView(R.id.tv_publication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.tv_posteded_date)
    TextView tvPostedDate;

    @BindView(R.id.tv_news_brief)
    TextView tvNewsBrief;

    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;

    @BindView(R.id.iv_news)
    ImageView ivNews;

    public ItemNewsViewHolder(View itemView, NewsActionDelegate newsActionDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView); //initialize

        mNewsActionDelegete=newsActionDelegate;
    }
    @OnClick(R.id.cv_news_items_root)
    public void onNewsItemTap(View view){
       // Toast.makeText(view.getContext(),"News item clicked",Toast.LENGTH_SHORT).show();
        mNewsActionDelegete.onTapNewsItem(mNews);  //relay

    }

    public void setNews(NewsVO news){
        mNews=news;
        tvPublicationTitle.setText(news.getPublication().getTitle());
        tvPostedDate.setText(news.getPostedDate());
        tvNewsBrief.setText(news.getBrief());

        //Use Glide to load image from network
        Glide.with(ivPublicationLogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivPublicationLogo);

        if(news.getImages() !=null){
            ivNews.setVisibility(View.VISIBLE);
            Glide.with(ivNews.getContext())
                    .load(news.getImages().get(0))
                    .into(ivNews);
        }
        else{
            ivNews.setVisibility(View.GONE);
        }
    }

}
