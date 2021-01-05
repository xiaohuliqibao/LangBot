package top.kagerou.lang.Bot.messages;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.kagerou.lang.Bot.enums.EnumKeyWord;

@Component
public class MessageFactory {

    @Autowired
    List<MessageFacade> messageFacades;

    Logger logger = LoggerFactory.getLogger(getClass());

    public MessageFacade get(EnumKeyWord keyWord) {
        for (MessageFacade messageFacade : messageFacades) {
            if (messageFacade.get().equals(keyWord)) {
                return messageFacade;
            }
            continue;
        }
        return null;
    }
}