package xyz.hsuyeemon.news.data.models;

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
    private NewsModel(){
      // mDataAgent= HttpUrlConnectionDataAgent.getNewsDataAgent();
      // mDataAgent= OkHttpDataAgent.getObjInstance();
        mDataAgent= RetrofitDataAgent.getsObjInstance();
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
}
