package top.kagerou.lang.Bot.messages.groupMessages;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.PlainText;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;
import top.kagerou.lang.util.DateFormateUtil;

import org.springframework.stereotype.Component;

@Component
public class GoodLangFacade implements MessageFacade {

    @Override
    public void execute(Member sender, Group group, String message) {
        int now24Hour = DateFormateUtil.getNow24Hour();
        if (message.equals("晚安狼宝")) {
            if (now24Hour > 20) {
                String sendMsg = GoodLang.getHitokoto("k") + "\n晚安，祝好梦";
                group.sendMessage(new PlainText(sendMsg).plus(new At(sender.getId())));
            } else if (now24Hour >= 0 && 4 >= now24Hour) {
                group.sendMessage(new PlainText("狼宝太困了，已经不能和你说晚安了哦。").plus(new At(sender.getId())));
            } else {
                group.sendMessage(new PlainText("拜托看看现在几点啊，你就要睡觉了嘛？").plus(new At(sender.getId())));
            }

        } else if (message.equals("早安狼宝")) {
            if (now24Hour > 5 && 10 > now24Hour) {
                String sendMsg = GoodLang.getHitokoto("k") + "\n早上好";
                group.sendMessage(new PlainText(sendMsg).plus(new At(sender.getId())));
            } else if (now24Hour > 9 && 12 > now24Hour) {
                group.sendMessage(new PlainText("懒虫说的就是你吧，今天的奖励已经没有了哦。").plus(new At(sender.getId())));
            } else {
                group.sendMessage(new PlainText("？？？，你是时差党嘛？").plus(new At(sender.getId())));
            }
        }
    }

    @Override
    public EnumKeyWord get() {
        return EnumKeyWord.GROUP_GOOD;
    }

}