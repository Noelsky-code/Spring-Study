

# 스프링 5 프로그래밍 입문 최범균 책으로 공부
책 내용 정리 , 예제 프로그램 작성해보기 

## Maven VS Gradle

책을 펴자마자 Maven과 Gradle에 대한 얘기가 나왔다.  
둘다 빌드 관리를 자동으로 해주는 툴이라는 건 알고 있었지만 어떤 차이점이 있는지 몰랐고 
어떤 걸 사용해야할지 고민됐다.  
두개 전부 배우기에는 배울게 너무 많다. 그래서 스프링 공부하면서 같이 병행해서 숙련도를 높일 
툴을 선택하려고 조사를 했다. 

---------
### 공통점

* Maven, Gradle 모두 빌드 자동 관리 툴.
*  라이브러리를 직접 다운받아 추가하는 방법 대신 서버를 통해 자동으로 다운 받아줌.
프로젝트가 일관된 디렉토리 구조를 가지게 해줌

------------
### 차이점

* xml을 편집해야하는 Maven보다 Gradle이 훨씬 간결한 코드사용가능

* Gradle은 작업 의존성 그래프 기반,Maven은 고정적이고 선형적 모델 
-> Gradle 성능 부분에서 빌드시간이 훨씬 단축됨. 규모가 커질수록 빋드시간 차이가 많이 남

* Gradle은 concurrent에 안전한 캐시를 허용

----
### 결론
속도, 안정성, 코드 간결등등 Gradle을 사용하는게 맞는거 같다.  

게다가 현재 Gradle을 주로 사용하는 추세라고 하니 굳이 Maven을 사용할 필요는 없는 것 같다.  

그래도 기초적인 작성법은 배워놓자.  



----
## vscode

구글링하다 알게된건데 스프링부트는 자동으로 dependency 관리를 해준다.. 자세한 내용은 다음에 알아보자..  

어쨋든 Vscode 스프링 부트엔 좋은 툴이 있었고 그걸 사용해봤다..   

* SPRING BOOT DASHBOARD ... 뭔진 모르겠지만 새로고침 하면 자동으로 build 해주고 자동으로 dependency가 추가되었다.    
     
### vscode 에서 추가 프로젝트 생성할 떄  
고생하고 알게 된 사실 .. 순서대로 
* F1 
* gradle 검색
* 현재 폴더에 new gradle 프로젝트 
* 엔터 계속 .. (나중에 폴더명 등등 고치면 됨) 
* build.gradle 수정 후 ctrl + s  
* 업데이트 할거냐고 물어봄 -> yes 
* 하면 자동으로 모든 과정 build 해줌 ...  
* dependency 추가할 거 있으면 새로고침 진행 


이 방법을 고생하면서 알게 됐음 .. 무려 시작한지 2주정도 지나고 나서 .. (그 동안은 어쩌다 성공함)   
이런건 왜 구글링하면 안뜨는거지 ..  
보람차서 제목 크게 설정해야지 ~  

----
## 객체 컨테이너

* ApplicationContext(BeanFactory)는 빈 객체의 생성,  초기화, 보관, 제거 등을 관리하고 있어서 컨테이너라고 부름.

* BeanFactory 인터페이스는 객체 생성과 검색에 대한 기능을 정의. 
getBean()메서드가 BeanFactory에 정의 되어 있음.
싱글톤/프로토타입 빈인지 확인하는 기능도 제공.

* ApplicationContext 인터페이스는 메시지,프로필/환경 변수 등을 처리할 수 있는 기능을 추가로 정의 , BeanFactory의 하위 인터페이스임 

* AnnotationConfigApplicationContext, GenericXmlApplicationContext, GenericGroovyApplicationContext 클래스는 위의 인터페이스들을 구현한 클래스임
각 클래스는 모두 설정 정보로 부터 빈이라는 객체를 생성하고 그 객체를 내부에 보관함 . 그리고 getBean() 메서드로 빈 객체 제공 . 

* AnnotationConfigApplicationContext은 자바 어노테이션을 이용해 클래스로부터 객체 설정 정보를 가져옴.
GenericXmlApplicationContext는 XML로 부터 객체 설정 정보를 가져옴
GenericGroovyApplicationContext 는 그루비 코드로 부터 객체 설정 정보를 가져옴

* 컨테이너는 내부적으로 빈 객체와 빈 이름을 연결하는 정보를 가짐. 

* 컨테이너는 추가적으로 생성, 초기화, 의존주입 등 객체 관리를 위한 다양한 기능을 제공함.   

---
## 싱글톤 객체 

* 싱글톤 객체는 단일객체를 의미한다.
* 스프링에서는 별도의 설정을 하지 않으면 한개의 빈 객체만을 생성한다 
* 즉 기본적으로 @Bean 어노테이션에 대해 한개의 빈 객체를 생성.

예제에서 아래 코드의 결과가 true로 나온다는 점에서 확인 가능하다. 
~~~
Greeter g1 = ctx.getBean("greeter",Greeter.class);
Greeter g2 = ctx.getBean("greeter",Greeter.class);
System.out.println(g1==g2); 
~~~
아래 처럼 작성하면 "greeter"에 대한 객체 하나, "greeter1"에 대한 객체 하나를 얻을 수 있다.
```java    
    @Bean
    public Greeter greeter(){
        Greeter g= new Greeter();
        g.setFormat("%s,안녕하세요!");
        return g;
    }
        @Bean
    public Greeter greeter10(){
        Greeter g= new Greeter();
        g.setFormat("%s,안녕하세요!");
        return g;
    }
```

---
## 의존 

* 의존은 변경에 의해 영향을 받는 관계 .
* 만약 한 클래스가 다른 클래스의 메서드를 실행할 때 이를 '의존' 한다고 함
* 의존 하는 대상의 객체를 get하는 방법에는 해당 클래스에서 직접 그 객체를 생성하는 법이 있음  
 하지만 이 방법은 유지보수 측면에서 문제가 존재.  
 * 의존 객체를 구하는 다른 방법엔 DI와 서비스 로케이터가 있음 
 * 스프링에서는 DI를 이용. 

 ---
## DI (Dependency Injection) , 의존 주입  

의존하는 객체를 직접 생성하는 대신 의존 객체를 전달받는 방식.
```java
    public MemberRegisterService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
```
위 코드처럼 객체를 직접 생성하는게 아닌 생성자를 통해 의존 객체를 주입받음.   
위 코드는 DI 패턴을 따름.  
```java
    MemberDao dao = new MemberDao();
    MemberRegisterService svc = new MemberRegisterService(dao);
```
이렇게 객체 생성할 때 객체를 주입 시켜줌.   
굳이 이렇게 DI를 하는 이유는 어려운 개념이라고 함 ... 나중에 찾아보자  
일단은 주된 이유중 하나는 변경의 유연함떄문 . 

---
### <string> 변경의 유연함
의존 객체를 직접 생성하는 방식은 만약 해당 의존 객체를 사용하는 class가 여러개일 경우 불편함이 발생함  
만약 의존 객체가 dao 이고 dao 클래스를 extends 하는 클래스가 ex_dao일 경우 ex_dao를 사용하고 싶을 떄 모든 class의 객체 생성 파트에 수정을 해줘야함 
```java 
     <Class 1>
     private dao = new dao  ->  private dao = new ex_dao 
     <Class 2>
     private dao = new dao  ->  private dao = new ex_dao 
    
    //하지만 DI를 사용할 경우 
    new dao = new ex_dao
    class1.make(dao);
    class1.make(dao); 
```
너무 대충 작성했지만 어쩃든 그저 생성자를 통해 객체를 생성하고 주입 하기만 하면 해당 의존객체를 사용하는 모든 class 코드를 수정할 필요가 사라지는게 핵심. 

---
##  예제 프로젝트 
예제 프로젝트는 DI를 이용하여 <Email, password> 를 등록, 등록된 비밀번호를 변경하는 프로젝트. 

* 회원 데이터 관련 클래스 - Member , WrongIdPasswordException, MemberDao

* 회원 가입 처리 관련 클래스 - DuplicateMemberException , RegisterRequest , MemberRegisterService 

* 암호 변경 관련 클래스 - MemberNotFoundException , ChangePasswordService

* main 클래스 - MainForAssembler
* 
* 객체 조립기 - Assembler
---
## 객체 조립기 
DI를 사용하면 객체 생성에 사용할 클래스를 변경하기 위해 객체를 주입하는 코드 한 곳만 변경하면 된다.  
이 때 객체를 생성하는 코드는 쉽게 메인 메서드에서 생성하는 방법이 있을 수 있다.  
더 나은 방법은 객체를 생성하고 의존 객체를 주입해주는 클래스를 따로 작성하는 방법.   
의존 객체를 주입하는 것을 서로 다른 두 객체를 조립한다고 생각할 수 있어서 이 클래스를 조립기라고 표현함.  
* 조립기는 객체를 생성하고 의존 객체를 주입하는 기능 제공 
* 특정 객체가 필요한 곳에 get 메서드로 제공해줌.
* 만약 의존 객체를 변경하려면 조립기의 코드만 변경하면 됨

예제 프로젝트에서는 Assembler.java 가 객체 조립기로서 MemberDao 클래스를 ChangePasswordService에 의존 주입, MemberRegisterService에 의존주입한 후 (조립) get 메서드를 통해 각각 객체를 반환해줌. 

---
## 스프링과 DI 
스프링은 DI를 지원하는 조립기 즉, 필요한 객체를 생성하고 생성한 객체에 의존을 주입해줌,  객체를 제공하는 기능을 정의해놓은 범용 조립기임.  
=> 스프링의 객체 조립은 어떤 객체를 생성하고 어떻게 주입할지를 정의한 설정 정보를 미리 작성해야함.   
* @Configuration 애노테이션은 스프링 설정 클래스를 의미함 -> 이걸 붙여야지 스프링 설정 클래스로 사용가능
* @Bean 애노테이션은 해당 메서드가 생성한 객체를 스프링 빈이라고 설정 -> 각각 메서드가 빈 객체를 생성 -> 메서드가 생성한 빈 객체가 메서드 이름으로 스프링에 등록됨 
* @Configuration 애노테이션을 붙인 설정 클래스 안에 @Bean 애노테이션을 붙인 메서드들을 작성하면 됨   
--- 
###  생성자를 통한 DI  

    ```java
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

=> 이렇게 설정 클래스를 작성했다면 해당 설정 클래스를 이용하기 위해 컨테이너를 생성해야함.  
```java
    //AppCtx.java 가 설정 클래스 라면
    //AppCtx로부터 생성할 객체와 의존 주입 대상에 대한 설정을 가져와 컨테이너를 생성함  
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
    
    //컨테이너에서 이름이 memberRegSvc인 빈 객체 구함
    MemberRegisterService regSvc = ctx.getBean("memberRegSvc",MemberRegisterService.class);

    //앞서 설정 클래스에서 memberRegSvc 메서드가 MemberDao 객체를 생성자를 통해 MemberRegisterService에 주입했었음. 따라서 regSvc 객체는 내부에서 memberDao 객체를 사용함.  
```
* 에러 : src/main 폴더에 config 폴더 만들어서 import 하면 인식을 못함//이유는 모르겠음
* -> 책 내용과 다르게 src/main/java 폴더에 config 폴더 만들었음. 

이렇게 생성자를 통해 의존 객체를 전달하는 방식은 생성자에 전달할 의존 객체를 2개 이상이어도 같은 방식으로 사용 가능함. 

``` java
    //AppCtx 파일 
    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }
    @Bean 
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }
    @Bean
    public MemberListPrinter listPrinter(){
        return new MemberListPrinter(memberDao(), memberPrinter());
    }

    //main
    MemberListPrinter listPrinter = ctx.getBean("listPrinter",MemberListPrinter.class);
```
이렇게 2개의 객체를 생성자를 통해 주입하는것이 가능함. 

---
###  세터 메서드 방식을 통한 DI
* 자바빈에서는 게터와 세터를 이용해 프로퍼티를 정의 
* get과 set 뒤에 사용할 프로퍼티 이름의 첫글자를 대문자로 바꿔 사용

```java
    //AppCtx 파일
    @Bean
    public MemberInfoPrinter infoPrinter(){
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setMemberDao(memberDao());
        infoPrinter.setPrinter(memberPrinter());
        return infoPrinter;
    }
    //main
        MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter",MemberInfoPrinter.class);
        infoPrinter.printMemberInfo(arg[1]);
```
이렇게 설정 파일에서 세터를 이용해 빈객체를 주입시켜줌.  
메인에서는 저렇게 getBean 메서드로 빈객체 받아오고 쓰면됨.

---
###  [DI 방법] 생성자 vs 세터
* 생성자 방식 : 빈 객체를 생성하는 시점에 모든 의존 객체가 주입됨
* 세터 방식 :  세터 메서드 이름을 통해 어떤 의존 객체가 주입되는지 알 수 있음  

따라서 생성자 파라미터 개수가 많을 경우 어떤 의존 객체가 주입되는지 알려면 세터방식이 유리  
         
생성자 방식은 빈 객체를 생성하는 시점에 모든 의존 객체를 주입받으므로 완전한 상태로 사용가능. 하지만 세터 방식은 필요한 의존 객체가 전달되지 않아도 빈 객체가 생성되기 때문에  
         NullPointerException  발생 가능.

---
### 기본 데이터 타입 값 설정
Bean 객체의 기본 데이터 타입, String 타입 값은 일반 코드처럼 값을 설정하면 됨 

예제 코드 
```java
    //세터 메서드 
    public void setMajorVersion(int majorVersion){
        this.majorVersion = majorVersion;
    }

    public void setMinorVersion(int minorVersion){
        this.minorVersion = minorVersion;
    }
    //설정 파일 
    public VersionPrinter VersionPrinter(){
    VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
    //main
     VersionPrinter versionPrinter = ctx.getBean("VersionPrinter",VersionPrinter.class); 
```

---
## @Configure 설정 클래스, @Bean 싱글톤 

```java
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
```
설정 파일에서 meberRegSvc()와 changePwdSvc()가 호출할때 마다 매번 새로운 MemberDao 객체가 생성되서 반환되는 거 같음  
         
하지만 스프링 컨테이너가 생성한 빈은 싱글톤 객체이므로 컨테이너는 @Bean 이 붙은 메서드에 대해 한 개의 객체만 생성함.  
         
=> 따라서 메서드를 몇 번을 호출하더라도 같은 객체가 리턴됨.  
         
* 스프링은 설정 클래스를 그대로 사용하지 않고 설정 클래스를 상속한 새로운 클래스를 만들어 사용함 
* 스프링이 런타임에 생성한 클래스는 한번 생성한 객체를 보관했다가 이후에 동일한 객체를 리턴함.
* 따라서 두 메서드가 동일한 MemberDao객체를 사용하게 됨. 

---
## 두 개 이상의 설정 파일 사용
많은 수의 빈을 사용할 떄 영역 별로 설정 파일을 나누면 관리하기 편해짐 
스프링은 한 개 이상의 설정 파일을 이용해 컨테이너를 생성할 수 있음

AppConf1.java, AppConf2.java 설정 파일이 존재한다고 할 때 아래와 같이 짜면 
2개의 설정 파일로 컨테이너 생성 가능. 
즉, 콤마로 설정 파일들을 연결해주면 됨. 
```java
ctx = new AnnotationConfigApplicationContext(AppConf1.class,AppConf2.class);
```
다른 방법에는 @import 애노테이션을 사용하는 방법이 있음.

---
## @Autowired 애노테이션
@Autowired 애노테이션은 스프링 빈에 의존하는 다른 빈을 자동으로 주입하고 싶을 때 사용
```java
@Autowired
private MemberDao memDao;
@Autowired
private MemberPrinter printer;
```
이렇게하면 설정 파일의 @Bean 메서드에서 memDao, printer에 의존 주입 코드를  짤 필요없이 자동으로 빈 객체를 의존주입 해줌.   

스프링은 내부적으로 @Configuration 애노테이션이 붙은 설정 클래스도 스프링 빈으로 등록함.  
         
 그리고 설정 클래스 내부의 @Autowired 애노테이션이 붙은 대상에 알맞은 빈을 자동으로 주입함.  
         
 따라서 아래 코드 실행 결과 true를 반환함  
         
```java
AppConf1 appConf1 = ctx.getBean(AppConf1.class);
System.out.println(appConf!=null);
```
---
## @import 애노테이션
 두 개 이상의 설정파일을 사용하는 방법중 하나임.
 @import 애노티에션으로 함께 사용할 설정 클래스를 지정함.
```java
@Configuration
@import(AppConf2.class)
public class AppConf1{
    ...
}
```
```java
//main
ApplicationContext ctx = new AnnotationConfigApplication(AppConf1.class); 
``` 
이렇게 하면 AppConf2 클래스의 설정도 함께 사용해서 컨테이너를 생성하게 됨.  
         
즉 따로 AppConf2클래스를 따로 지정할 필요가 없어짐.  
         
두 개이상의 클래스를 import 할 때는 배열형식으로..  
         
```java
@import({AppConf1.class,AppConf2.class})
~~~~
``` 
---
## getBean() 메서드 
빈 객체를 구하는 메서드. 
* 만약 존재하지 않는 빈 이름을 사용하면 Exception 발생
* 빈 객체의 타입과 지정한 타입이 달라도 Exception 발생  
  
만약 해당 타입의 빈 객체가 한개만 존재하면 다음과 같이도 사용 가능. 
```java
VersionPrinter versionPrinter = ctx.getBean(MemberPrinter.class);
```
하지만 해당 타입의 빈 객체가 2개 이상이라면 역시 Exception 발생 시킴

---
## 주입 대상 객체 와 빈 
주입할 객체가 꼭 스프링 빈이어야할 필요가 없음.  
         
즉, 객체를 빈으로 등록하지 않고 일반 객체로 생성해서 주입할 수 있음.  
         
단, 빈으로 등록하지 않는다면 스프링 컨테이너에서 관리하지 않기 떄문에 컨테이너에서 해당 객체를 구할 수 없음.  
         
따라서 스프링 컨테이너의 자동 주입, 라이프사이클 관리 등의 기능을 사용할 수 없음.  
         
<strong>최근에는 의존 자동 주입 기능을 프로젝트 전반에서 사용하기 때문에 의존 주입 대상은 스프링 빈으로 등록하는 것이 보통임.</strong> 

