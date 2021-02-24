package top.kagerou.lang.util;

import java.io.File;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

public class VoicdFromateUtil {

    private static final String VOICE_CUSTOMIZE_MP3_PATH_LINUX = "/home/qibao/file/miraibot/voices/customize/mp3/";
    private static final String VOICE_CUSTOMIZE_AMR_PATH_LINUX = "/home/qibao/file/miraibot/voices/customize/amr/";
    private static final String VOICE_CUSTOMIZE_WAV_PATH_LINUX = "/home/qibao/file/miraibot/voices/customize/wav/";

    public static Boolean mp3ToAmr(String fileName) {
        try {

            Encoder encoder = new Encoder();
            EncodingAttributes attributes = new EncodingAttributes();

            AudioAttributes audio = new AudioAttributes();
            audio.setBitRate(new Integer(6400));
            audio.setChannels(new Integer(1));
            audio.setSamplingRate(new Integer(8000));
            audio.setCodec("libopencore_amrnb");
            attributes.setFormat("amr");
            attributes.setAudioAttributes(audio);
            System.out.println(fileName);
            File source = new File(VOICE_CUSTOMIZE_MP3_PATH_LINUX + fileName + ".mp3");

            File target = new File(VOICE_CUSTOMIZE_AMR_PATH_LINUX + fileName + ".amr");
            if (!target.exists()) {
                target.createNewFile();
            }
            encoder.encode(new MultimediaObject(source), target, attributes);
            System.out.println("mp3ToAmr Convert OK!");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static Boolean wavToAmr(String fileName) {
        try {

            Encoder encoder = new Encoder();
            EncodingAttributes attributes = new EncodingAttributes();

            AudioAttributes audio = new AudioAttributes();
            // 查看现有编码集和解码集 对应setcodec
            // String[] audioDecoders = encoder.getAudioDecoders();
            // String[] audioEncoders = encoder.getAudioEncoders();

            // for (int i = 0; i < audioEncoders.length; i++) {
            // System.out.println(audioEncoders[i].toString());
            // }
            // for (String string : audioDecoders) {
            // System.out.println(string);
            // }
            // audio.setCodec("libamr");
            audio.setBitRate(new Integer(6400));
            audio.setChannels(new Integer(1));
            audio.setSamplingRate(new Integer(8000));
            audio.setCodec("libopencore_amrnb");
            attributes.setFormat("amr");
            attributes.setAudioAttributes(audio);
            System.out.println(fileName);
            File source = new File(VOICE_CUSTOMIZE_WAV_PATH_LINUX + fileName + ".wav");

            File target = new File(VOICE_CUSTOMIZE_AMR_PATH_LINUX + fileName + ".amr");
            if (!target.exists()) {
                target.createNewFile();
            }
            encoder.encode(new MultimediaObject(source), target, attributes);
            System.out.println("wavToAmr Convert OK!");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
