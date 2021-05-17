

# 스프링 5 프로그래밍 입문 최범균 책으로 공부


## <strong>Maven VS Gradle

책을 펴자마자 Maven과 Gradle에 대한 얘기가 나왔다.  
둘다 빌드 관리를 자동으로 해주는 툴이라는 건 알고 있었지만 어떤 차이점이 있는지 몰랐고 
어떤 걸 사용해야할지 고민됐다.  
두개 전부 배우기에는 배울게 너무 많다. 그래서 스프링 공부하면서 같이 병행해서 숙련도를 높일 
툴을 선택하려고 조사를 했다. 

---------
### <strong>공통점

* Maven, Gradle 모두 빌드 자동 관리 툴.
*  라이브러리를 직접 다운받아 추가하는 방법 대신 서버를 통해 자동으로 다운 받아줌.
프로젝트가 일관된 디렉토리 구조를 가지게 해줌

------------
### <strong>차이점

* xml을 편집해야하는 Maven보다 Gradle이 훨씬 간결한 코드사용가능

* Gradle은 작업 의존성 그래프 기반,Maven은 고정적이고 선형적 모델 
-> Gradle 성능 부분에서 빌드시간이 훨씬 단축됨. 규모가 커질수록 빋드시간 차이가 많이 남

* Gradle은 concurrent에 안전한 캐시를 허용

----
### <strong>결론
속도, 안정성, 코드 간결등등 Gradle을 사용하는게 맞는거 같다.  
게다가 현재 Gradle을 주로 사용하는 추세라고 하니 굳이 Maven을 사용할 필요는 없는 것 같다.  
그래도 기초적인 작성법은 배워놓자.  

---
## <strong>Spring.. SpringBoot.. Eclipse.. Vscode

노트북에 이클립스 안깔림 -> 어쩔 수 없이 Vscode 사용 결정 . -> 구글링 결과 대부분 Vscode + 스프링 부트  
이클립스와 Vscode의 그레이들 프로젝트 생성 방법이 달라서 책 내용과 너무 달라짐
-> 스프링 부트를 쓰지않는 방법을 최대한 찾아봤지만 결국 못찾음   

그냥 예제에 있는 코드를 src/main/java에 작성을 했더니
org.springframework: ~~~ dependency 때문에 제대로 안돌아감 -> 책에있는 방법대로 comPile 'org.~~ ' 했는데 안돌아감   
-> 이제 compile말고 implementation 써야한다고 함 ->   썻는데도 오류뜸 -> 하아 ..

----
### <strong>결론

구글링하다 알게된건데 스프링부트는 자동으로 dependency 관리를 해준다.. 자세한 내용은 다음에 알아보자..  
어쨋든 Vscode 스프링 부트엔 좋은 툴이 있었고 그걸 사용해봤다..   

* SPRING BOOT DASHBOARD ... 뭔진 모르겠지만 새로고침 하면 자동으로 build 해주고 자동으로 dependency가 추가되었다.    
---      
# <strong>vscode 에서 추가 프로젝트 생성할 떄  

고생하고 알게 된 사실 .. 순서대로 
* F1 
* gradle 검색
* 현재 폴더에 new gradle 프로젝트 
* 엔터 계속 .. (나중에 폴더명 등등 고치면 됨) 
* build.gradle 수정 후 ctrl + s  
* 업데이트 할거냐고 물어봄 -> yes 
* 하면 자동으로 모든 과정 build 해줌 ...  

이 방법을 고생하면서 알게 됐음 .. 무려 시작한지 2주정도 지나고 나서 .. (그 동안은 어쩌다 성공함)   
이런건 왜 구글링하면 안뜨는거지 ..  
보람차서 제목 크게 설정해야지 ~  


----
## <strong>객체 컨테이너

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
## <strong>싱글톤 객체 

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
---
## <strong>의존 

* 의존은 변경에 의해 영향을 받는 관계 .
* 만약 한 클래스가 다른 클래스의 메서드를 실행할 때 이를 '의존' 한다고 함
* 의존 하는 대상의 객체를 get하는 방법에는 해당 클래스에서 직접 그 객체를 생성하는 법이 있음  
 하지만 이 방법은 유지보수 측면에서 문제가 존재.  
 * 의존 객체를 구하는 다른 방법엔 DI와 서비스 로케이터가 있음 
 * 스프링에서는 DI를 이용. 

 ---
## <strong>DI (Dependency Injection) , 의존 주입  

의존하는 객체를 직접 생성하는 대신 의존 객체를 전달받는 방식.

    public MemberRegisterService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
위 코드처럼 객체를 직접 생성하는게 아닌 생성자를 통해 의존 객체를 주입받음.   
위 코드는 DI 패턴을 따름.  

    MemberDao dao = new MemberDao();
    MemberRegisterService svc = new MemberRegisterService(dao);

이렇게 객체 생성할 때 객체를 주입 시켜줌.   
굳이 이렇게 DI를 하는 이유는 어려운 개념이라고 함 ... 나중에 찾아보자  
일단은 주된 이유중 하나는 변경의 유연함떄문 . 

---
### 변경의 유연함
의존 객체를 직접 생성하는 방식은 만약 해당 의존 객체를 사용하는 class가 여러개일 경우 불편함이 발생함  
만약 의존 객체가 dao 이고 dao 클래스를 extends 하는 클래스가 ex_dao일 경우 ex_dao를 사용하고 싶을 떄 모든 class의 객체 생성 파트에 수정을 해줘야함 
     
     <Class 1>
     private dao = new dao  ->  private dao = new ex_dao 
     <Class 2>
     private dao = new dao  ->  private dao = new ex_dao 
하지만 DI를 사용할 경우 
    
    new dao = new ex_dao
    class1.make(dao);
    class1.make(dao); 
너무 대충 작성했지만 어쩃든 그저 생성자를 통해 객체를 생성하고 주입 하기만 하면 해당 의존객체를 사용하는 모든 class 코드를 수정할 필요가 사라지는게 핵심. 

---
## 예제 프로젝트 
