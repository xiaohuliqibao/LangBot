package top.kagerou.lang.Bot.messages.groupMessages;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;
import top.kagerou.lang.entity.LoliconImage;
import top.kagerou.lang.repository.LoliconImageRepository;
import top.kagerou.lang.service.QQMemberService;
import top.kagerou.lang.service.elasticSearch.ElaSetuServer;

import java.io.File;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OneHPictureFacade implements MessageFacade {

    @Autowired
    LoliconImageRepository loliconImageRepository;

    @Autowired
    QQMemberService qqMemberService;

    private ElaSetuServer elaSetuServer;

    @Autowired
    public OneHPictureFacade(ElaSetuServer elaSetuServer) {

        this.elaSetuServer = elaSetuServer;
    }

    @Override
    public void execute(Member sender, Group group, String message) {
        if (message.equals("来张涩图")) {
            // String r18 = "0";
            // 获取imageBean的基本信息
            if (qqMemberService.spendpoints(2, sender.getId())) {
                LoliconImage loliconImage = OneHPicture.getLoliconImage();
                loliconImageRepository.save(loliconImage);
                String imageTitle = loliconImage.getTitle();
                String imageUrl = loliconImage.getUrl();
                File imageFile = OneHPicture.saveImageFile(imageUrl, imageTitle);
                // ExternalImage externalImage = ExternalImageJvmKt.toExternalImage(imageFile,
                // false);
                ExternalResource imageResource = ExternalResource.create(imageFile);
                Image image = group.uploadImage(imageResource);
                String imageId = image.getImageId();
                log.info(imageId);
                group.sendMessage(image);
            } else {
                group.sendMessage("积分不够啦，今天的涩图就到此为止吧！");
            }

        } else if (message.contains("来张") && message.contains("色图")) {
            String tags = message.substring(2, message.length() - 2);
            log.info(tags);
            SearchResponse response = elaSetuServer.searchImagByTags(tags);
            File imageFile = OneHPicture.saveImageFile(response);
            ExternalResource imageResource = ExternalResource.create(imageFile);
            Image image = group.uploadImage(imageResource);
            String imageId = image.getImageId();
            log.info(imageId);
            group.sendMessage(image);
        }
    }

    @Override
    public EnumKeyWord get() {
        return EnumKeyWord.GROUP_GAIVEMEONEHPICTURE;
    }

}