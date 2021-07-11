package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberReigsterService;

@Configuration
public class AppCtx{
    
    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }
    
    @Bean 
    public MemberReigsterService memberRegSvc(){
        return new MemberReigsterService(memberDao());
    }
    
    @Bean
    public ChangePasswordService changerPwdSvc(){
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao());
        return pwdSvc;
    }
    @Bean 
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }
    @Bean
    public MemberListPrinter listPrinter(){
        return new MemberListPrinter(memberDao(), memberPrinter());
    }
    @Bean
    public MemberInfoPrinter infoPrinter(){
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setMemberDao(memberDao());
        infoPrinter.setPrinter(memberPrinter());
        return infoPrinter;
    }
}