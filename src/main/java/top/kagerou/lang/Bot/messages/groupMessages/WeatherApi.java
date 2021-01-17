package top.kagerou.lang.Bot.messages.groupMessages;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.kagerou.lang.entity.Weather;
import top.kagerou.lang.util.DateFormateUtil;

public class WeatherApi {

    private static final String WEATHERAPI = "https://tianqiapi.com/api";
    private static final String WEATHERCONNENT = "{\"app\": \"com.tencent.weather\", 	\"desc\": \"天气\", 	\"view\": \"RichInfoView\", 	\"ver\": \"0.0.0.1\", 	\"prompt\": \"[应用]天气\", 	\"meta\": { 		\"richinfo\": { 			\"adcode\": \"\", 			\"air\": \"\", 			\"city\": \"\", 			\"date\": \"\", 			\"max\": \"\", 			\"min\": \"\", 			\"ts\": \"\", 			\"type\": \"\", 			\"wind\": \"\" 		} 	} }";

    public static String getWeatherByCityId(String cityid) {
        OkHttpClient client = new OkHttpClient();
        // 每一个城市对应一个id，百度城市id编码，默认0为你当前ip所在的城市
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
        return s;
    }

    public static Weather formatJson(String str) {
        JSONObject jsonObject = JSONObject.parseObject(str);
        Weather weather = Weather.builder().air(jsonObject.getString("air"))
                .air_level(jsonObject.getString("air_level")).air_pm25(jsonObject.getString("air_pm25"))
                .air_tips(jsonObject.getString("air_tips")).city(jsonObject.getString("city"))
                .cityEn(jsonObject.getString("cityEn")).cityid(jsonObject.getString("cityid"))
                .country(jsonObject.getString("country")).countryEn(jsonObject.getString("countryEn"))
                .date(DateFormateUtil.simlpeDate(jsonObject.getString("date")))
                .humidity(jsonObject.getString("humidity")).pressure(jsonObject.getString("pressure"))
                .tem(jsonObject.getString("tem")).tem1(jsonObject.getString("tem1")).tem2(jsonObject.getString("tem2"))
                .update_time(DateFormateUtil.simlpeDateTime(jsonObject.getString("update_time")))
                .visibility(jsonObject.getString("visibility")).wea(jsonObject.getString("wea"))
                .wea_img(jsonObject.getString("wea_img")).week(jsonObject.getString("week"))
                .win(jsonObject.getString("win")).win_meter(jsonObject.getString("win_meter"))
                .win_speed(jsonObject.getString("win_speed"))
                // alarm暂定
                .build();
        return weather;
    }

    /**
     * [mirai:app:{ { "app": "com.tencent.weather", "desc": "天气", "view":
     * "RichInfoView", "ver": "0.0.0.1", "prompt": "[应用]天气", "meta": { "richinfo": {
     * "adcode": "", "air": "5", "city": "落雪家", "date": "114514", "max": "-273",
     * "min": "-273", "ts": "15158613", "type": "205", "wind": "" } } } }]
     */

    public static String getWeatherJsonContent(Weather weather) {
        String adcode = "0";
        String air = weather.getAir();
        String city = weather.getCity();
        String date = DateFormateUtil.dateToString(weather.getDate());
        String max = weather.getTem1();
        String min = weather.getTem2();
        String ts = weather.getCityid();
        String type = "203";
        // 209 error
        // 208 霾
        // 207 沙尘
        // 206 雾
        // 205 雪
        // 204 雨
        // 203 阴
        // 202 多云
        // 201 晴
        // 200 error
        switch (weather.getWea()) {
            case "晴":
                type = "201";
                break;
            case "多云":
                type = "202";
                break;
            case "阴":
                type = "203";
                break;
            case "雨":
                type = "204";
                break;
            case "雪":
                type = "205";
                break;
            case "雾":
                type = "206";
                break;
            case "沙尘":
                type = "207";
                break;
            case "霾":
                type = "208";
                break;
            default:
                break;
        }
        String wind = "";

        JSONObject weatherObject = JSON.parseObject(WEATHERCONNENT);
        JSONObject parseObject = weatherObject.getJSONObject("meta").getJSONObject("richinfo");
        parseObject.put("adcode", adcode);
        parseObject.put("air", air);
        parseObject.put("city", city);
        parseObject.put("date", date);
        parseObject.put("max", max);
        parseObject.put("min", min);
        parseObject.put("ts", ts);
        parseObject.put("type", type);
        parseObject.put("wind", wind);
        return weatherObject.toString();
    }
}
