package kg.aloha.pet.service;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by altynbek.kochkonbaev on 19.05.2020.
 */

public class OkHttpUtils {
    public  OkHttpClient okHttpClient = new OkHttpClient();

    public  OkHttpClient getInstance() {
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        return okHttpClient;
    }
}