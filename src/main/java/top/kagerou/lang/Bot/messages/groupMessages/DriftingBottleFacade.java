package top.kagerou.lang.Bot.messages.groupMessages;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;

import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;
import top.kagerou.lang.entity.DriftingBottle;
import top.kagerou.lang.repository.DriftingBottleRepository;
import top.kagerou.lang.util.DateFormateUtil;

@Component
public class DriftingBottleFacade implements MessageFacade {

    @Autowired
    DriftingBottleRepository driftingBottleRepository;

    @Override
    public void execute(Member sender, Group group, String message) {
        String keyWord = message.substring(0, 4);
        String sendMsg = null;
        // 是否飘洋过海,
        // true 开启跨群漂流
        // false 关闭跨群漂流
        Boolean span = false;
        if (keyWord.equals("捡漂流瓶")) {
            // TODO 解耦
            DriftingBottle driftingBottle = new DriftingBottle();
            if (span) {
                Long count = driftingBottleRepository.count();
                int randomInt = new Random().nextInt(count.intValue()) + 1;
                Optional<DriftingBottle> optionalDriftingBottle = driftingBottleRepository.findById(randomInt);
                driftingBottle = optionalDriftingBottle.get();
            } else {
                List<DriftingBottle> driftingBottles = driftingBottleRepository.findByGroupnumber(group.getId());
                int randomInt = new Random().nextInt(driftingBottles.size());
                driftingBottle = driftingBottles.get(randomInt);
            }
            sendMsg = driftingBottle.getBottlemessage();
            // TODO 拼接消息增加AT功能
            group.sendMessage(sendMsg);
        } else if (keyWord.equals("扔漂流瓶")) {
            String bottleMessage = message.substring(4, message.length());
            if (!bottleMessage.isEmpty()) {
                DriftingBottle driftingBottle = DriftingBottle.builder().bottlemessage(bottleMessage)
                        .sendernumber(sender.getId()).groupnumber(group.getId())
                        .sendtime(DateFormateUtil.simlpeNowDateTime()).build();
                driftingBottleRepository.save(driftingBottle);
                sendMsg = "好的";
            } else {
                sendMsg = "漂流瓶信息不能为空哦！";
            }
            // TODO 拼接消息增加AT功能
            group.sendMessage(sendMsg);
        }
    }

    @Override
    public EnumKeyWord get() {
        return EnumKeyWord.GROUP_DRIFTINGBOTTLE;
    }

}