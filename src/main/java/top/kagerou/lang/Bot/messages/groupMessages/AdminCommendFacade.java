package top.kagerou.lang.Bot.messages.groupMessages;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;
import top.kagerou.lang.repository.QQMemberRepository;

@Slf4j
@Component
public class AdminCommendFacade implements MessageFacade {
    private static final String MUTE = "不理";
    private static final String UNMUTE = "理理";
    private static final Integer MUTE_AUTH = 1;
    private static final Integer UNMUTE_AUTH = 3;

    @Resource
    QQMemberRepository qqMemberRepository;

    @Override
    public void execute(Contact sender, Group group, String message) {
        // #不理[mirai:at:3452223487614]
        if (qqMemberRepository.findByNumber(sender.getId()).getAuthority() > 3) {
            String commend = message.substring(1, 3);
            String atmsg = message.substring(4, message.length()).trim();
            Long number = Long
                    .parseLong(atmsg.split("mirai:at:")[1].substring(0, atmsg.split("mirai:at:")[1].length() - 1));
            log.info(number.toString());
            switch (commend) {
                case MUTE:
                    if (qqMemberRepository.findByNumber(number).getAuthority() == 1) {
                        group.sendMessage("这个人已经是笨蛋了！");
                    } else {
                        qqMemberRepository.updateAuthority(MUTE_AUTH, number);
                        group.sendMessage("不再理你了，笨蛋！");
                    }

                    break;
                case UNMUTE:
                    qqMemberRepository.updateAuthority(UNMUTE_AUTH, number);
                    group.sendMessage("好吧，这次就原谅你了。");
                    break;
                default:
                    break;
            }
        } else {
            group.sendMessage("坏蛋你想干什么！");
        }

    }

    @Override
    public EnumKeyWord get() {
        // TODO Auto-generated method stub
        return EnumKeyWord.GROUP_ADMIN;
    }
}
