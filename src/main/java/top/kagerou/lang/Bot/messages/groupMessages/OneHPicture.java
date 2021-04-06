package top.kagerou.lang.Bot.messages.groupMessages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.elasticsearch.action.search.SearchResponse;

import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.kagerou.lang.entity.LoliconImage;

@Slf4j
public class OneHPicture {

    private static final String LOLICONAPI_URL = "https://api.lolicon.app/setu/";
    private static final String LOLICONAPI_KEY = "*";
    private static final String IMAGE_PATH = "/home/qibao/file/miraibot/images/";
    // private static final String IMAGE_PATH = "D://";
    private static final String IMAGE_R18 = "0";

    private static final String IMAGE_FORMAT = ".jpg";

    public static LoliconImage getLoliconImage() {
        String s = null;
        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = HttpUrl.parse(LOLICONAPI_URL).newBuilder().addQueryParameter("apikey", LOLICONAPI_KEY)
                .addQueryParameter("r18", IMAGE_R18).build();
        Request request = new Request.Builder().url(httpUrl.toString()).build();
        try {
            Response response = client.newCall(request).execute();
            s = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject jsonObjectImage = jsonObject.getJSONArray("data").getJSONObject(0);

        LoliconImage loliconImage = new LoliconImage();
        loliconImage = JSON.parseObject(jsonObjectImage.toJSONString(), LoliconImage.class);
        return loliconImage;
    }

    /**
     * 
     * @param imageUrl
     * @param imageTitle
     * @return
     * @throws IOException
     */
    public static File saveImageFile(String imageUrl, String imageTitle) {
        File imageFile = new File(IMAGE_PATH + imageTitle + IMAGE_FORMAT);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(imageUrl).build();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imageFile);
            Response response = client.newCall(request).execute();
            fos.write(response.body().bytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageFile;
    }

    // ElasticSearch上拉的lasepi的库，没清洗，有黄图，暂时不用这一块
    public static File saveImageFile(SearchResponse response) {
        JSONObject responseJson = JSON.parseObject(response.toString());
        JSONArray setuArray = responseJson.getJSONObject("hits").getJSONArray("hits");
        JSONObject setuObject = setuArray.getJSONObject(0).getJSONObject("_source");
        String originalUrl = setuObject.getString("original");
        String title = setuObject.getString("title");
        log.info(originalUrl);
        File imageFile = new File(IMAGE_PATH + title + IMAGE_FORMAT);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(originalUrl).addHeader("Referer", "https://www.pixiv.net/").build();

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(imageFile);
            Response fileResponse = client.newCall(request).execute();
            fos.write(fileResponse.body().bytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageFile;
    }

}
