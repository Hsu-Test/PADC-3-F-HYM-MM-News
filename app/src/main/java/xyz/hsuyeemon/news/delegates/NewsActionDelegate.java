package xyz.hsuyeemon.news.delegates;

import xyz.hsuyeemon.news.data.vo.NewsVO;

/**
 * Created by Dell on 12/17/2017.
 */

public interface NewsActionDelegate {

    void onTapNewsItem(NewsVO tappedNews);
    void onTapCommentButton();
    void onTapSendToButton(NewsVO tappedNews);
    void onTapFavoriteButton();

}
