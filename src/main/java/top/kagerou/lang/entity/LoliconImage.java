package top.kagerou.lang.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name = "lolicon_images")
public class LoliconImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pid", nullable = true, length = 128)
    private Long pid;

    @Column(name = "p", nullable = true, length = 128)
    private Long p;

    @Column(name = "uid", nullable = true, length = 128)
    private Long uid;

    @Column(name = "title", nullable = true, length = 128)
    private String title;

    @Column(name = "author", nullable = true, length = 128)
    private String author;

    @Column(name = "url", nullable = true, length = 128)
    private String url;

    @Column(name = "r18", nullable = true, length = 128)
    private boolean r18;

    @Column(name = "width", nullable = true, length = 128)
    private Integer width;

    @Column(name = "height", nullable = true, length = 128)
    private Integer height;

    @ElementCollection(targetClass = String.class)
    private List<String> tags;
}