package xyz.hsuyeemon.news.data.models;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import xyz.hsuyeemon.news.data.vo.NewsVO;
import xyz.hsuyeemon.news.events.LoadedNewsEvent;
import xyz.hsuyeemon.news.network.HttpUrlConnectionDataAgent;
import xyz.hsuyeemon.news.network.NewsDataAgent;
import xyz.hsuyeemon.news.network.OkHttpDataAgent;
import xyz.hsuyeemon.news.network.RetrofitDataAgent;

/**
 * Created by Dell on 12/23/2017.
 */

public class NewsModel {

    private static NewsModel sObjInstance;  //s=static attribute ,m =member attribute

    private NewsDataAgent mDataAgent;

    private Map<String,NewsVO> mNews;

    private NewsModel(){
       //mDataAgent= HttpUrlConnectionDataAgent.getNewsDataAgent();
       //mDataAgent= OkHttpDataAgent.getObjInstance();
        mDataAgent= RetrofitDataAgent.getsObjInstance();
         mNews = new HashMap<>();

        EventBus.getDefault().register(this);
    }

    public static NewsModel getsObjInstance(){
        if(sObjInstance ==null){
            sObjInstance=new NewsModel();
        }
        return sObjInstance;
    }
    /**
     * loads news from Network layer
     */
    public void loadNews(){
        mDataAgent.loadNews();
    }

    /**
     * Get news object by id.
     * @param newsId
     * @return
     */
    public NewsVO getNewsById(String newsId){

        return mNews.get(newsId);

    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
        public void onNewsLoaded(LoadedNewsEvent event){
            for(NewsVO news :event.getNewsList()){
                mNews.put(news.getNewsId(),news);
            }
        }

}
