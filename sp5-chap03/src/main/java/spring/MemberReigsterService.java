package spring;

import java.time.LocalDateTime;

public class MemberReigsterService {
    
    private MemberDao memberDao;
    
    public MemberReigsterService(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req){
        Member member = memberDao.selectByEmail(req.getEmail());
        if(member != null){
            throw new DuplicateMemberException("dup email "+ req.getEmail());
        }
        Member newMember = new Member(req.getEmail(),req.getPassword(),req.getName(),LocalDateTime.now());
        return newMember.getId();
    }
}
