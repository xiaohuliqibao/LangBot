package top.kagerou.lang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import top.kagerou.lang.entity.DriftingBottle;

public interface DriftingBottleRepository extends JpaRepository<DriftingBottle, Integer> {

	List<DriftingBottle> findByGroupnumber(long id);

}