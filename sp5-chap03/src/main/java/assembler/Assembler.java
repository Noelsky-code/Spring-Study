package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberReigsterService;

public class Assembler {

    private MemberDao memberDao;
    private MemberReigsterService regSvc;
    private ChangePasswordService pwdSvc;

    public Assembler(){
        memberDao = new MemberDao();
        regSvc = new MemberReigsterService(memberDao);
        pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);
    }

    public MemberDao getMemberDao(){
        return memberDao;
    }

    public MemberReigsterService getMemberReigsterService(){
        return regSvc;
    }
    public ChangePasswordService getChangePasswordService(){
        return pwdSvc;
    }
}
