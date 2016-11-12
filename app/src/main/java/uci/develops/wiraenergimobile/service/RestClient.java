package uci.develops.wiraenergimobile.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 10/22/2016.
 */
public class RestClient {
    public static RestInterface RestClient;
    public static String URL = "http://app.wiraenergi.co.id/api/mobile/";
//    public static String URL = "http://app.wira.dev/api/mobile/";

    static {
        setRestClient();
    }

    private RestClient(){}
    public static RestInterface getRestClient(){ return RestClient; }

    private static void setRestClient(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        RestClient = retrofit.create(RestInterface.class);
    }
}
