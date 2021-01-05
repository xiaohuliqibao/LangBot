package top.kagerou.lang.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import top.kagerou.lang.entity.QQMember;

public interface QQMemberRepository extends JpaRepository<QQMember, Integer> {

    boolean existsByNumber(long id);

    QQMember findByNumber(long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE qq_member SET money = ?1 WHERE number = ?2", nativeQuery = true)
    int updateMoney(Integer nowMoney, Long number);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE qq_member SET authority = ?1 WHERE number = ?2", nativeQuery = true)
    // private Integer authority;
    int updateAuthority(Integer authority, Long number);

}