package top.kagerou.lang.Bot.messages.groupMessages;

public class Menu {

    public static String getMenu() {
        String menu = null;
        menu = "---功能---\n" + "[来张涩图]\t 随机一张涩图(花费2积分)\n" + "[网抑云]\t随机抑郁(花费1积分)\n"
                + "[点歌+歌名]\t网易云点歌/格式不对可能会有奇怪的东西(花费2积分)\n" + "[有人发*你回*]\t自定义机器人回复消息(部分关键词不支持自定义,花费1积分)\n"
                + "[不再回复]\t停止自定义回复\n" + "[扔漂流瓶]\t扔一个漂流瓶(花费2积分)\n" + "[捡漂流瓶]\t随机捡一个漂流瓶(关闭了跨群漂流,花费1积分)\n"
                + "[查询+地名+天气]\t查询天气(花费1积分)\n" + "[早安狼宝]\t早安一下(获得5积分)\n" + "[菜单]\t查看功能菜单\n" + "[Mirai功能]\t查看未来的功能\n";
        return menu;
    }

    public static String getFeature() {
        String feature;
        feature = "计划中的功能：\na.报时功能\n b.签到功能\n";
        return feature;
    }
}