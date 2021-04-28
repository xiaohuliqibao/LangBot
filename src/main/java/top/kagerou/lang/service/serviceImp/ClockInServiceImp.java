package top.kagerou.lang.service.serviceImp;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.kagerou.lang.entity.ClockInHistory;
import top.kagerou.lang.repository.ClockInRespository;
import top.kagerou.lang.service.ClockInService;
import top.kagerou.lang.util.DateFormateUtil;

@Service
public class ClockInServiceImp implements ClockInService {

    @Autowired
    ClockInRespository clockInRespository;

    @Override
    public void addClockIn(String eventName, Long number) {
        String clockintimekey = DateFormateUtil
                .timeStamp2Date(String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000), "yyyyMMddHHmmss");
        ClockInHistory clockInHistory = ClockInHistory.builder().clockeventname(eventName)
                .clockintimekey(clockintimekey).number(number).build();
        clockInRespository.save(clockInHistory);

    }

}
