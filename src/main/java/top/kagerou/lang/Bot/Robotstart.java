package top.kagerou.lang.Bot;

import org.springframework.stereotype.Component;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import top.kagerou.lang.Bot.eventlistener.FriendEventListener;
import top.kagerou.lang.Bot.eventlistener.GroupEventListener;

@Component
public class Robotstart {

    public static Bot bot;

    static {
        bot = BotFactory.INSTANCE.newBot(qq号, "密码", new BotConfiguration() {
            {
                fileBasedDeviceInfo("deviceInfo.json");
                setProtocol(MiraiProtocol.ANDROID_PAD);
            }
        });

        bot.login();
    }

    public static void start(GroupEventListener groupEventListener, FriendEventListener friendEventListener) {
        bot.getFriends().forEach(friend -> System.out.println(friend.getNick()));
        bot.getGroups().forEach(group -> System.out.println(group.getName()));

        // net.mamoe.mirai.event.Events.registerEvents(bot, friendEventListener);
        // net.mamoe.mirai.event.Events.registerEvents(bot, groupEventListener);
        bot.getEventChannel().registerListenerHost(friendEventListener);
        bot.getEventChannel().registerListenerHost(groupEventListener);

        bot.join();
    }
}
