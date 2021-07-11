package spring;

import java.util.Collection;

public class MemberListPrinter{
    private MemberDao memberDao;
    private MemberPrinter printer;

    public MemberListPrinter(MemberDao memberDao,MemberPrinter printer){
        this.memberDao = memberDao;
        this.printer = printer;
    }

    public void printAll(){
        Collection<Member> members = memberDao.selectAll();
        members.forEach(m->printer.print(m));//각 멤버에 대해 print함수 실행해줌 

    }
}