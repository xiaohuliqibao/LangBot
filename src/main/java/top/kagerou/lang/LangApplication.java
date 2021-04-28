package top.kagerou.lang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;
import top.kagerou.lang.Bot.Robotstart;
import top.kagerou.lang.Bot.eventlistener.FriendEventListener;
import top.kagerou.lang.Bot.eventlistener.GroupEventListener;
import top.kagerou.lang.helper.ApplicationContextHelper;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class LangApplication {

	public static void main(String[] args) {
		SpringApplication.run(LangApplication.class, args);

		log.info("启动QQ机器人");
		Robotstart.start(ApplicationContextHelper.getBean(GroupEventListener.class),
				ApplicationContextHelper.getBean(FriendEventListener.class));
	}

}
