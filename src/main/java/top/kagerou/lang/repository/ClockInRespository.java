package top.kagerou.lang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import top.kagerou.lang.entity.ClockInHistory;

public interface ClockInRespository extends JpaRepository<ClockInHistory, Integer> {

}
