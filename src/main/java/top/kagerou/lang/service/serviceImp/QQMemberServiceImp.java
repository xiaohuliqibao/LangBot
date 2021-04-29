package top.kagerou.lang.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.kagerou.lang.entity.QQMember;
import top.kagerou.lang.repository.QQMemberRepository;
import top.kagerou.lang.service.QQMemberService;

@Service
public class QQMemberServiceImp implements QQMemberService {

    @Autowired
    QQMemberRepository qqMemberRepository;

    @Override
    public boolean spendpoints(Integer points, Long number) {
        QQMember qqMember = qqMemberRepository.findByNumber(number);
        if (qqMember.getMoney() < points) {
            return false;
        } else {
            qqMemberRepository.updateMoney(qqMember.getMoney() - points, number);
            return true;
        }
    }

}
