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
@Table(name = "drifting_bottle")
public class DriftingBottle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    // 列名与数据库关键字冲突了
    @Column
    private String bottlemessage;
    @Column
    private Long groupnumber;
    @Column
    private Long sendernumber;
    @Column
    private Date sendtime;
}