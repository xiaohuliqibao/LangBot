package top.kagerou.lang.Bot.messages.groupMessages;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.LightApp;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import top.kagerou.lang.Bot.enums.EnumKeyWord;
import top.kagerou.lang.Bot.messages.MessageFacade;
import top.kagerou.lang.entity.City;
import top.kagerou.lang.entity.Weather;
import top.kagerou.lang.repository.CityRespository;

@Slf4j
@Component
public class WeatherFacade implements MessageFacade {

    /**
     * [mirai:app:{ { "app": "com.tencent.weather", "desc": "天气", "view":
     * "RichInfoView", "ver": "0.0.0.1", "prompt": "[应用]天气", "meta": { "richinfo": {
     * "adcode": "", "air": "5", "city": "落雪家", "date": "114514", "max": "-273",
     * "min": "-273", "ts": "15158613", "type": "205", "wind": "" } } } }]
     */
    // [mirai:app:{"app":"com.tencent.weather","desc":"天气","view":"RichInfoView","ver":"0.0.0.1","prompt":"[应用]天气","meta":{"richinfo":{"adcode":"","air":"5","city":"落雪家","date":"114514","max":"-273","min":"-273","ts":"15158613","type":"205","wind":""}}}]
    // [mirai:app:{"app":"com.tencent.weather","desc":"天气","view":"RichInfoView","ver":"0.0.0.1","prompt":"[应用]天气","meta":{"richinfo":{"adcode":"","air":"5","city":"眉山","date":"2021-01-17","max":"10","min":"0","ts":"15158613","type":"205","wind":""}}}]

    @Resource
    CityRespository cityRespository;

    @Override
    public void execute(Member sender, Group group, String message) {
        if (message.contains("查询")) {
            String cityname = message.substring(2, message.length() - 2);

            log.info(cityname);

            if (cityRespository.existsByCityCn(cityname)) {
                City city = cityRespository.findByCityCn(cityname);
                String getweather = WeatherApi.getWeatherByCityId(city.getId());
                Weather weather = WeatherApi.formatJson(getweather);

                // String json =
                // "{\"app\":\"com.tencent.weather\",\"desc\":\"天气\",\"view\":\"RichInfoView\",\"ver\":\"0.0.0.1\",\"prompt\":\"[应用]天气\",\"meta\":{\"richinfo\":{\"adcode\":\"\",\"air\":\"151\",\"city\":\""
                // + weather.getCity() + "\",\"date\":\"" +
                // DateFormateUtil.dateToString(weather.getDate())
                // + "\",\"max\":\"" + weather.getTem1() + "\",\"min\":\"" + weather.getTem2()
                // + "\",\"ts\":\"15158613\",\"type\":\"201"\",\"wind\":\"\"}}}";

                String json1 = WeatherApi.getWeatherJsonContent(weather);
                MessageChain ms = MessageUtils.newChain(new LightApp(json1));
                group.sendMessage(ms);
            } else {
                group.sendMessage("没有找到该城市!");
            }

        }
    }

    @Override
    public EnumKeyWord get() {
        return EnumKeyWord.GROUP_WEATHERS;
    }

}
