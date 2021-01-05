package top.kagerou.lang.Bot.messages;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import top.kagerou.lang.Bot.enums.EnumKeyWord;

public interface MessageFacade {

    public EnumKeyWord get();

    public void execute(Contact sender, Group group, String message);
}
