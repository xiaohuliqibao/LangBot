package top.kagerou.lang.Bot.messages.groupMessages;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetYiYun {

    private static final String NEMUSICAPI = "https://api.lo-li.icu/wyy/";

    public static String getWangyiyunComment() {
        String comment = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(NEMUSICAPI).build();
        try {
            Response response = client.newCall(request).execute();
            comment = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // JSONObject jsonObject = JSON.parseObject(comment);
        return comment;
    }
}