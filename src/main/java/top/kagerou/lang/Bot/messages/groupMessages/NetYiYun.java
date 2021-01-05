package top.kagerou.lang.Bot.messages.groupMessages;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetYiYun {

    private static final String NEMUSICAPI = "http://api.heerdev.top/nemusic/random";
    private static final String CLOUDMUSICAPI = "http://music.163.com/api/search/pc";
    private static final String JUMPURL = "http://music.163.com/song/";
    private static final String MUSICURL = "http://music.163.com/song/media/outer/url?id=";
    private static final String JSONCONTENT = "{\"app\": \"com.tencent.structmsg\", 	\"desc\": \"音乐\", 	\"meta\": { 		\"music\": { 			\"app_type\": 1, 			\"desc\": \"\", 			\"jumpUrl\": \"\", 			\"musicUrl\": \"\", 			\"preview\": \"\", 			\"sourceMsgId\": \"0\", 			\"tag\": \"网易云音乐\", 			\"title\": \"\" 		} 	}, 	\"prompt\": \"\", 	\"ver\": \"0.0.0.1\", 	\"view\": \"music\" }";

    // { "app": "com.tencent.structmsg", "desc": "音乐",
    // "meta": {
    // "music": {
    // "app_type": 1,
    // "desc": "", 此处填写歌手名
    // "jumpUrl": "", 此处用JUMPURL+歌曲ID
    // "musicUrl": "", 此处用MUSICURL+歌曲ID
    // "preview": "", 此处填专辑图片
    // "sourceMsgId": "0",
    // "tag": "网易云音乐",
    // "title": "" 此处是歌曲名
    // } },
    // "prompt": "", 此处是"[分享]：歌曲名" 似乎可以不填，
    // "ver":"0.0.0.1",
    // "view": "music" }
    // 备注过的是必填项，其他的建议不要去掉了。

    public static String getCloudMusicJsonContent(String songName) {

        // 通过歌名和api搜索返回相应的Json信息，提取关键的歌曲名(name);歌曲ID(id);歌手名(artisName);歌曲封面Url(albumPicUrl)
        // 关键信息。
        // String result = HttpRequest.get(CLOUDMUSICAPI).query("s",
        // songName).query("type", 1).execute().asString();
        String result = null;
        OkHttpClient client = new OkHttpClient();
        HttpUrl httpUrl = HttpUrl.parse(CLOUDMUSICAPI).newBuilder()//
                .addQueryParameter("s", songName)//
                .addQueryParameter("type", "1").build();
        Request request = new Request.Builder().url(httpUrl.toString()).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONArray songsJsonArray = JSON.parseObject(result).getJSONObject("result").getJSONArray("songs");
        JSONObject songJsonObject = songsJsonArray.getJSONObject(0);
        JSONObject artisJsonObject = songJsonObject.getJSONArray("artists").getJSONObject(0);
        JSONObject albumJsonObject = songJsonObject.getJSONObject("album");

        String name = songJsonObject.getString("name");
        String id = songJsonObject.getLong("id").toString();
        String artisName = artisJsonObject.getString("name");
        String albumPicUrl = albumJsonObject.getString("picUrl");
        String prompt = "[分享]:" + name;

        // 将查询到的信息填写到Json信息小卡片中，完善Json信息
        JSONObject jsonContent = JSON.parseObject(JSONCONTENT);
        JSONObject musicJsonObject = jsonContent.getJSONObject("meta").getJSONObject("music");
        musicJsonObject.put("desc", artisName);
        musicJsonObject.put("jumpUrl", JUMPURL + id);
        musicJsonObject.put("musicUrl", MUSICURL + id);
        musicJsonObject.put("preview", albumPicUrl);
        musicJsonObject.put("title", name);
        jsonContent.put("prompt", prompt);
        return jsonContent.toString();
    }

    public static String getWangyiyunComment() {
        String comment = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(NEMUSICAPI).build();
        try {
            Response response = client.newCall(request).execute();
            comment = response.body().string();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(comment);
        return jsonObject.getString("text");
    }
}