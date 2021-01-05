package top.kagerou.lang.entity;

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
@Table(name = "customize_message")
public class CustomizeMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    // 列名与数据库关键字冲突了
    @Column(name = "qkey", nullable = true, length = 64)
    private String qkey;
    @Column(name = "qmessage", nullable = true, length = 64)
    private String qmessage;
    @Column(name = "groupnumber", nullable = true, length = 16)
    private Long groupnumber;
}