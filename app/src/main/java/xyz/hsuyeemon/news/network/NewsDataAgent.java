package xyz.hsuyeemon.news.network;

import android.content.Context;

/**
 * Created by Dell on 12/23/2017.
 */

public interface NewsDataAgent {

    /**
     * load news from network api
     */
    void loadNews();

    /**
     * load loginUser
     * @param context
     * @param phoneNo
     * @param password
     */

    void loadLoginUser(Context context, String phoneNo, String password);

    void registerUser(String name,String phoneNo,String password);


}
