package top.kagerou.lang.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormateUtil {

    /**
     * @author Qibao
     * @param date
     * @return
     * @see 将固定格式的string类型yyyy-MM-dd时间转化为Date类型
     */
    public static Date simlpeDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try {
            d = simpleDateFormat.parse(date);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return d;
    }

    /**
     * @author Qibao
     * @param date
     * @return
     * @see 将固定格式的string类型yyyy-MM-dd HH:mm:ss时间转化为Date类型
     */
    public static Date simlpeDateTime(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        try {
            d = simpleDateFormat.parse(date);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return d;
    }

    /**
     * @author Qibao
     * @return
     * @see
     */
    public static Date simlpeNowDateTime() {
        Date date = new Date();
        Date time = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String format = simpleDateFormat.format(date);
            time = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return time;
    }

    public static String dateToString(Date d) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMdd");
        return simpleDateFormat.format(d);
    }

    /**
     * 时间戳转换成日期格式字符串
     * 
     * @param seconds   精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }
}