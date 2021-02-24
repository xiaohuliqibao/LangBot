package top.kagerou.lang;

import com.alibaba.fastjson.JSONObject;

import okhttp3.*;

public class LangTest {

    /**
     * { "date": "2021-01-17", "countryEn": "China", "tem2": "0", "country": "中国",
     * "win_meter": "小于12km/h", "air_level": "良", "week": "星期日", "tem1": "10",
     * "visibility": "25.9km", "city": "眉山", "cityid": "101271501", "pressure":
     * "976", "air": "51", "air_pm25": "51", "update_time": "2021-01-17 14:36:11",
     * "wea": "多云", "air_tips": "空气好，可以外出活动，除极少数对污染物特别敏感的人群以外，对公众没有危害！", "wea_img":
     * "yun", "alarm": { "alarm_type": "", "alarm_content": "", "alarm_level": "" },
     * "cityEn": "meishan", "win_speed": "1级", "humidity": "31%", "tem": "10",
     * "win": "西北风" }
     */

    public static final String WEATHERAPI = "https://tianqiapi.com/api";

    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();
        // 每一个城市对应一个id，百度城市id编码，默认0为你当前ip所在的城市
        String cityid = "0";
        String s = null;

        HttpUrl httpUrl = HttpUrl.parse(WEATHERAPI).newBuilder().addQueryParameter("version", "v6")
                .addQueryParameter("appid", "99836373").addQueryParameter("appsecret", "NyAv8EAW")
                .addQueryParameter("cityid", cityid).build();
        Request request = new Request.Builder().url(httpUrl.toString()).build();

        try {
            Response response = client.newCall(request).execute();
            s = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject.toString());
    }
}
