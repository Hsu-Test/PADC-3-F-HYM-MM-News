package xyz.hsuyeemon.news.network;

/**
 * Created by Dell on 1/11/2018.
 */

        import com.google.gson.Gson;
        import org.greenrobot.eventbus.EventBus;

        import java.util.concurrent.TimeUnit;

        import okhttp3.OkHttpClient;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;
        import xyz.hsuyeemon.news.events.LoadedNewsEvent;
        import xyz.hsuyeemon.news.network.responses.GetNewsResponse;


public class RetrofitDataAgent implements NewsDataAgent {

    private static RetrofitDataAgent sObjInstance;

    public static RetrofitDataAgent getsObjInstance() {
        if (sObjInstance==null){
            sObjInstance=new RetrofitDataAgent();
        }
        return sObjInstance;
    }

    private RetrofitDataAgent(){

    }

    @Override
    public void loadNews() {
        OkHttpClient httpClient = new OkHttpClient.Builder() //1.
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/mm-news/apis/v1/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(httpClient)
                .build();

        NewsApi newsApi=retrofit.create(NewsApi.class);

        Call<GetNewsResponse> getNewsResponseCall=newsApi.loadNews(1,"http://padcmyanmar.com/padc-3/mm-news/apis/v1/");
        getNewsResponseCall.enqueue(new Callback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                GetNewsResponse getNewsResponse=response.body();
                if (getNewsResponse.getMmNews()==null) {
                    LoadedNewsEvent event = new LoadedNewsEvent(getNewsResponse.getMmNews());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<GetNewsResponse> call, Throwable t) {

            }
        });



    }
}

