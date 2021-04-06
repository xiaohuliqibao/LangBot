package top.kagerou.lang.Bot.messages.groupMessages;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;

import org.springframework.stereotype.Component;

@Component
public class GoodLang implements MessageFacade {

    @Override
    public void execute(Member sender, Group group, String message) {

        if (message.equals("早安狼宝")) {

            String sendMsg = "早安" + sender.getNick();
            group.sendMessage(sendMsg);
        }
    }

    @Override
    public EnumKeyWord get() {
        return EnumKeyWord.GROUP_GOOD;
    }

}