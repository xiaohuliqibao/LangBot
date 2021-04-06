package top.kagerou.lang.Bot.messages.groupMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;
import top.kagerou.lang.repository.CustomizeMessageRepository;

@Component
public class DelCustomizeMessage implements MessageFacade {

    @Autowired
    CustomizeMessageRepository customizeMessageRepository;

    @Override
    public void execute(Member sender, Group group, String message) {
        if (message.substring(0, 4).equals("不再回复")) {
            String qkey = message.substring(4, message.length());
            String sendMsg = null;
            long groupid = group.getId();
            if (customizeMessageRepository.existsByQkeyAndGroupnumber(qkey, groupid)) {
                customizeMessageRepository.deleteByQkeyAndGroupnumber(qkey, groupid);
                sendMsg = "已经没有[" + qkey + "]了。";
                group.sendMessage(sendMsg);
            } else {
                sendMsg = "还没有设置[" + qkey + "]哦。";
                group.sendMessage(sendMsg);
            }
        }

    }

    @Override
    public EnumKeyWord get() {
        return EnumKeyWord.GROUP_DELCUSTOMISEMESSAGE;
    }

}