package top.kagerou.lang.Bot.eventlistener;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;
import top.kagerou.lang.Bot.messages.MessageFactory;
import top.kagerou.lang.entity.CustomizeMessage;
import top.kagerou.lang.entity.GroupMessageEntity;
import top.kagerou.lang.entity.QQMember;
import top.kagerou.lang.repository.CustomizeMessageRepository;
import top.kagerou.lang.repository.GroupMessageEntityRepository;
import top.kagerou.lang.repository.QQMemberRepository;
import top.kagerou.lang.util.DateFormateUtil;

@Slf4j
@Service
public class GroupEventListener extends SimpleListenerHost {

    @Resource
    MessageFactory messageFactory;

    @Resource
    QQMemberRepository qqMemberRepository;

    @Resource
    CustomizeMessageRepository customizeMessageRepository;

    @Resource
    GroupMessageEntityRepository groupMessageEntityRepository;

    @EventHandler
    public void onMessage(GroupMessageEvent event) {
        String groupMsg = "";

        MessageChain originalMessage = event.getSource().getOriginalMessage();
        for (int i = 1; i < originalMessage.getSize(); i++) {
            groupMsg = groupMsg + originalMessage.get(i);
        }
        log.info(groupMsg);
        Member sender = event.getSender();
        Long sendNumber = sender.getId();
        GroupMessageEntity groupMessage = GroupMessageEntity.builder().group_number(event.getGroup().getId())
                .message(groupMsg).message_date(DateFormateUtil.simlpeNowDateTime()).nikename(sender.getNick())
                .number(sendNumber).groupname(event.getGroup().getName()).build();

        groupMessageEntityRepository.save(groupMessage);

        if (qqMemberRepository.existsByNumber(sendNumber)) {
            if (qqMemberRepository.findByNumber(sendNumber).getAuthority() > 1) {

                EnumKeyWord enumKeyWord = EnumKeyWord.groupFind(groupMsg);
                if (enumKeyWord == null) {
                    if (customizeMessageRepository.existsByQkeyAndGroupnumber(groupMsg, event.getGroup().getId())) {
                        CustomizeMessage customizeMessage = customizeMessageRepository
                                .findByQkeyAndGroupnumber(groupMsg, event.getGroup().getId());
                        String qmessage = customizeMessage.getQmessage();
                        if (qmessage.contains("mirai:image:")) {
                            // [mirai:image:{3E742ED0-C05A-EF01-8DA5-E0CAE4E6E756}.png]
                            String str1 = qmessage.split("mirai:image:")[1];
                            event.getGroup().sendMessage(Image.fromId(str1.substring(0, str1.length() - 1)));
                        } else {
                            event.getGroup().sendMessage(qmessage);
                        }
                    } else {
                        return;
                    }

                } else {
                    MessageFacade messageFacade = messageFactory.get(enumKeyWord);
                    messageFacade.execute(event.getSender(), event.getGroup(), groupMsg);
                }
            }
        } else if (groupMsg.equals("#签订契约")) {
            QQMember member = QQMember.builder().nickname(sender.getNick()).number(sender.getId())
                    .register_date(DateFormateUtil.simlpeNowDateTime()).money(0).authority(3).identity("mumber")
                    .build();
            qqMemberRepository.save(member);
            event.getGroup().sendMessage("你想成为魔法少女吧！");
        }

    }

    @Override
    public void handleException(CoroutineContext context, Throwable exception) {
        super.handleException(context, exception);
        log.error("群消息错误", exception.getMessage());
    }

}
