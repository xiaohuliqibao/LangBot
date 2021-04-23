package top.kagerou.lang.Bot.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EnumKeyWord {

    GROUP_ADMIN(1, 700, "#"), GROUP_MENU(1, 701, "菜单"), GROUP_WEATHERS(1, 702, "天气"), GROUP_GOOD(1, 703, "狼宝"),
    GROUP_PRAISEME(1, 704, "-夸我"), GROUP_GAIVEMEONEHPICTURE(1, 705, "来张涩图"), GROUP_NETYIYUN(1, 706, "网抑云"),
    GROUP_SONG(1, 706, "点歌"), GROUP_CUSTOMISEMESSAGE(1, 707, "有人发"), GROUP_DELCUSTOMISEMESSAGE(1, 708, "不再回复"),
    GROUP_DRIFTINGBOTTLE(1, 709, "漂流瓶"), GROUP_VOICE(1, 710, "!语音"), GROUP_PCR(1, 711, "-PCR"),
    GROUP_QIERU(1, 712, "-切噜");

    public Integer keytype;
    public Integer code;
    public String keyword;

    EnumKeyWord(Integer keytype, Integer code, String keyword) {
        this.keytype = keytype;
        this.code = code;
        this.keyword = keyword;
    }

    public static EnumKeyWord groupFind(String groupmessage) {

        List<EnumKeyWord> collect = Arrays.stream(EnumKeyWord.values()).filter(e -> e.keytype.equals(1))
                .collect(Collectors.toList());
        for (EnumKeyWord enumKeyWord : collect) {
            if (groupmessage.contains(enumKeyWord.keyword)) {
                return enumKeyWord;
            }
            continue;
        }
        return null;
    }

    public static EnumKeyWord privateFind(String privatemessage) {

        List<EnumKeyWord> collect = Arrays.stream(EnumKeyWord.values()).filter(e -> e.keytype.equals(2))
                .collect(Collectors.toList());
        for (EnumKeyWord enumKeyWord : collect) {
            if (privatemessage.contains(enumKeyWord.keyword)) {
                return enumKeyWord;
            }
            continue;
        }
        return null;
    }
}
