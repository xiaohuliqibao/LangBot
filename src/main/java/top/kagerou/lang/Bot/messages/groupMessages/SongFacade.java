package top.kagerou.lang.Bot.messages.groupMessages;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.LightApp;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.MessageUtils;
import top.kagerou.lang.Bot.messages.MessageFacade;
import top.kagerou.lang.Bot.enums.EnumKeyWord;

import org.springframework.stereotype.Component;

@Component
public class SongFacade implements MessageFacade {

    @Override
    public void execute(Contact sender, Group group, String message) {
        // TODO Auto-generated method stub
        if (message.contains("点歌")) {
            if (message.substring(0, 2).equals("点歌")) {
                String songName = message.substring(2, message.length());
                MessageChain ms = MessageUtils.newChain(new LightApp(Song.getCloudMusicJsonContent(songName)));
                group.sendMessage(ms);
            }
        }
    }

    @Override
    public EnumKeyWord get() {
        // TODO Auto-generated method stub
        return EnumKeyWord.GROUP_SONG;
    }

}