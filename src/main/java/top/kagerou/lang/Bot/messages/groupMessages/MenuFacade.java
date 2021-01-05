package top.kagerou.lang.Bot.messages.groupMessages;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;

import org.springframework.stereotype.Component;

@Component
public class MenuFacade implements MessageFacade {

    @Override
    public void execute(Contact sender, Group group, String message) {
        // TODO Auto-generated method stub
        if (message.equals("菜单")) {
            String sendMsg = Menu.getMenu();
            group.sendMessage(sendMsg);
        }
    }

    @Override
    public EnumKeyWord get() {
        // TODO Auto-generated method stub
        return EnumKeyWord.GROUP_MENU;
    }

}