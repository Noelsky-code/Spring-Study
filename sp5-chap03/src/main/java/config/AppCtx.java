package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
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
}