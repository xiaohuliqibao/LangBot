package top.kagerou.lang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "qq_member")
public class QQMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nickname", nullable = true, length = 12)
    private String nickname;
    @Column(name = "authority", nullable = true, length = 12)
    private Integer authority;
    @Column(name = "identity", nullable = true, length = 12)
    private String identity;
    @Column(name = "number", nullable = true, length = 20)
    private Long number;
    @Column(name = "register_date", nullable = true, length = 50)
    private Date register_date;
    @Column(name = "money", nullable = true, length = 12)
    private Integer money;
    @Column(name = "morning_clock_in", length = 2)
    private String morning_clock_in;
    @Column(name = "night_clock_in", length = 2)
    private String night_clock_in;

}
