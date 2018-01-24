package xyz.hsuyeemon.news.network;

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
     * @param phoneNo
     * @param password
     */

    void loadLoginUser(String phoneNo, String password);

    //void registerUser(String name,String phoneNo,String password);


}
