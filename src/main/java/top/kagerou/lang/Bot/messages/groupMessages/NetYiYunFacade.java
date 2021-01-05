package top.kagerou.lang.Bot.messages.groupMessages;

import org.springframework.stereotype.Component;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;

@Component
public class NetYiYunFacade implements MessageFacade {

    @Override
    public void execute(Contact sender, Group group, String message) {
        // TODO Auto-generated method stub
        if (message.equals("网抑云")) {
            String sendMsg = NetYiYun.getWangyiyunComment();
            group.sendMessage(sendMsg);
        }
    }

    @Override
    public EnumKeyWord get() {
        // TODO Auto-generated method stub
        return EnumKeyWord.GROUP_NETYIYUN;
    }

}