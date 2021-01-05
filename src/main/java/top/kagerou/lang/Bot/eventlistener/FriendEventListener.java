package top.kagerou.lang.Bot.eventlistener;

import org.springframework.stereotype.Service;

import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;

@Slf4j
@Service
public class FriendEventListener extends SimpleListenerHost {

    @EventHandler
    public void onMessage(FriendMessageEvent event) {

    }

    @Override
    public void handleException(CoroutineContext context, Throwable exception) {
        super.handleException(context, exception);
        log.error("群消息错误", exception.getMessage());
    }

}
