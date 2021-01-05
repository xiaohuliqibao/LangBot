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
@Table(name = "group_message")
public class GroupMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nikename", nullable = true, length = 32)
    private String nikename;
    @Column(name = "groupname", nullable = true, length = 32)
    private String groupname;
    @Column(name = "number", nullable = true, length = 16)
    private Long number;
    @Column(name = "group_number", nullable = true, length = 16)
    private Long group_number;
    @Column(name = "message", nullable = true, length = 128)
    private String message;
    @Column(name = "message_date", nullable = true, length = 64)
    private Date message_date;
}
