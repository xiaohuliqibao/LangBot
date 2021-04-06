package top.kagerou.lang.Bot.messages.groupMessages;

import org.springframework.stereotype.Component;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;

@Component
public class NetYiYunFacade implements MessageFacade {

    @Override
    public void execute(Member sender, Group group, String message) {
        if (message.equals("网抑云")) {
            String sendMsg = NetYiYun.getWangyiyunComment();
            group.sendMessage(sendMsg);
        }
    }

    @Override
    public EnumKeyWord get() {
        return EnumKeyWord.GROUP_NETYIYUN;
    }

}