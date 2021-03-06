package top.kagerou.lang.Bot.messages.groupMessages;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;

import org.springframework.stereotype.Component;

@Component
public class MenuFacade implements MessageFacade {

    @Override
    public void execute(Member sender, Group group, String message) {
        if (message.equals("菜单")) {
            String sendMsg = Menu.getMenu();
            group.sendMessage(sendMsg);
        }
    }

    @Override
    public EnumKeyWord get() {
        return EnumKeyWord.GROUP_MENU;
    }

}