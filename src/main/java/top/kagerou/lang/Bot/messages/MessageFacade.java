package top.kagerou.lang.Bot.messages;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import top.kagerou.lang.Bot.enums.EnumKeyWord;

public interface MessageFacade {

    public EnumKeyWord get();

    public void execute(Member sender, Group group, String message);
}
