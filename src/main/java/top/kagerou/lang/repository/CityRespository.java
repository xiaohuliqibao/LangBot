package top.kagerou.lang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import top.kagerou.lang.entity.City;

public interface CityRespository extends JpaRepository<City, Integer> {

    City findByCityCn(String cityname);

    boolean existsByCityCn(String cityname);
}
