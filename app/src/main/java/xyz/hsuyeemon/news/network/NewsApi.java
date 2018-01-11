package xyz.hsuyeemon.news.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.hsuyeemon.news.network.responses.GetNewsResponse;

/**
 * Created by Dell on 1/7/2018.
 */

public interface NewsApi {
    @FormUrlEncoded
    @POST("getMMNews.php")
    Call<GetNewsResponse> loadNews(@Field("page") int page,
                                   @Field("access_token") String accessToken);
}
