package top.kagerou.lang.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import top.kagerou.lang.entity.CustomizeMessage;

public interface CustomizeMessageRepository extends JpaRepository<CustomizeMessage, Integer> {

    CustomizeMessage findByQkeyAndGroupnumber(String groupMsg, long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE customize_message SET qmessage = ?1 WHERE qkey = ?2 AND groupnumber = ?3", nativeQuery = true)
    int updateQMessage(String string, String string2, long id);

    boolean existsByQkeyAndGroupnumber(String string, long id);

    @Transactional
    int deleteByQkeyAndGroupnumber(String qkey, long groupid);
}