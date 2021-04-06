package top.kagerou.lang.Bot.messages.groupMessages;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import top.kagerou.lang.Bot.messages.MessageFacade;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.entity.CustomizeMessage;
import top.kagerou.lang.repository.CustomizeMessageRepository;

@Component
public class CustomizeMessageFacade implements MessageFacade {

    @Resource
    CustomizeMessageRepository customizeMessageRepository;

    @Override
    public void execute(Member sender, Group group, String message) {
        if (message.substring(0, 3).equals("有人发") && message.contains("你回")) {
            String[] split = message.substring(3, message.length()).split("你回");
            String qKey = split[0];
            String qMessage = "";
            // String[] split = str1.split("你回");
            qMessage = split[1];
            CustomizeMessage customizeMessage = CustomizeMessage.builder().qkey(qKey).qmessage(qMessage)
                    .groupnumber(group.getId()).build();
            if (!customizeMessageRepository.existsByQkeyAndGroupnumber(qKey, group.getId())) {
                customizeMessageRepository.save(customizeMessage);
            } else {
                customizeMessageRepository.updateQMessage(qMessage, qKey, group.getId());
            }
            group.sendMessage("好的");
        }

    }

    @Override
    public EnumKeyWord get() {
        return EnumKeyWord.GROUP_CUSTOMISEMESSAGE;
    }

}