package top.kagerou.lang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import top.kagerou.lang.entity.GroupMessageEntity;

public interface GroupMessageEntityRepository extends JpaRepository<GroupMessageEntity, Integer> {

}
