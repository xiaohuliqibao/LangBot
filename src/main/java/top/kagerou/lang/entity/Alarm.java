package top.kagerou.lang.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Alarm {
    private String alarm_type;
    private String alarm_level;
    private String alarm_content;
}