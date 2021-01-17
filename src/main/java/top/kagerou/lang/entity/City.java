package top.kagerou.lang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "city_backup")
public class City {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "cityEn")
    private String cityEn;
    @Column(name = "cityCn")
    private String cityCn;
    // private String Country_code;
    @Column(name = "countryEn")
    private String countryEn;
    @Column(name = "countryCn")
    private String countryCn;
    @Column(name = "provinceEn")
    private String provinceEn;
    @Column(name = "provinceCn")
    private String provinceCn;
    @Column(name = "leaderEn")
    private String leaderEn;
    @Column(name = "leaderCn")
    private String leaderCn;
    @Column(name = "lat")
    private String Latitude;
    @Column(name = "lon")
    private String Longitude;
    // private String AD_code;
}