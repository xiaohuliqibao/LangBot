package top.kagerou.lang.Bot.messages.groupMessages;

import okhttp3.OkHttpClient;

import com.alibaba.fastjson.JSONObject;

import kotlinx.serialization.json.JsonObject;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class GoodLang {
    private static final String HITOKOTOAPI = "https://v1.hitokoto.cn";

    public static String getHitokoto(String c) {
        OkHttpClient client = new OkHttpClient();
        String s = null;
        HttpUrl httpUrl = HttpUrl.parse(HITOKOTOAPI).newBuilder().addQueryParameter("c", c)
                .addQueryParameter("encode", "json").addQueryParameter("charset", "utf-8").build();
        Request request = new Request.Builder().url(httpUrl.toString()).build();

        try {
            Response response = client.newCall(request).execute();
            String responseStr = response.body().string();
            JSONObject jsonObject = JSONObject.parseObject(responseStr);
            s = jsonObject.getString("hitokoto");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
