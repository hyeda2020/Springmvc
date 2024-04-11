# Springmvc
리포지토리 설명 : 인프런 김영한님의 강의 '스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술' 스터디 정리

https://www.inflearn.com/course/mvc-1


# 1. MVC 패턴이란
MVC 패턴은 하나의 서블릿이나, JSP로 처리하던 것을 컨트롤러(Controller)와 뷰(View)라는 영역으로 서로 역할을 나눈 것으로, 웹 애플리케이션은 보통 이 MVC 패턴을 사용
- 컨트롤러(Controller): HTTP 요청을 받아서 파라미터를 검증하고, 비즈니스 로직을 실행. 그리고 뷰에 전달할 결과 데이터를 조회해서 모델에 담음
- 모델(Model): 뷰에 출력할 데이터를 담아둠. 뷰가 필요한 데이터를 모두 모델에 담아서 전달해주는 덕분에 뷰는 비즈니스 로직이나 데이터 접근을 몰라도 되고, 화면을 렌더링 하는 일에 집중할 수 있음
- 뷰(View): 모델에 담겨있는 데이터를 사용해서 화면을 그리는 일에 집중


# 2. SpringMVC 구조

![img](https://github.com/hyeda2020/Springmvc/assets/139141270/4414c614-e079-4d16-9eb8-1d94fd12ffcc)

1) 클라이언트 → HTTP 요청 → Servlet Dispatcher
2) 핸들러 매핑을 통해 요청 URL이 스프링 빈의 이름으로 등록된 핸들러(컨트롤러)를 조회
3) 핸들러를 실행할 수 있는 핸들러 어댑터를 조회
4) 핸들러 어탭터를 통해 핸들러(컨트롤러) 실행 후 ModelAndView 반환
5) ViewResolver를 호출하여 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체를 반환
6) 뷰 렌더링


# 3. 컨트롤러(Controller)
@Controller : 스프링이 자동으로 스프링 빈으로 등록(내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨)
@RequestMapping : 요청 정보를 매핑하며, 해당 URL이 호출되면 이 애노테이션이 선언된 메서드가 호출됨

※ 스프링 부트 3.0(스프링 프레임워크 6.0)부터는 클래스 레벨에 @RequestMapping 이 있어도 스프링 컨트롤러로 인식하지 않고, @Controller 또는 @RestController가 있어야 스프링 컨트롤러로 인식

예시)

    @Controller
    public class MyController {
    
        @RequestMapping("/springmvc/new-form")
        public ModelAndView process() {
            return new ModelAndView("new-form");
        }
    }


# 4. 뷰 리졸버(ViewResolver)
컨트롤러는 특정 뷰를 식별하기 위해 문자열 형태의 뷰 이름을 반환하며, 이때 뷰 리졸버는 이 뷰의 이름을 실제 뷰 템플릿 파일로 매핑함
@Controller는 반환 값이 String 타입이면 View 이름으로 인식되어 해당 View를 찾고 View가 렌더링 됨
예를 들어 컨트롤러가 "home"이라는 뷰 이름을 리턴하면, 뷰 리졸버는 이를 "home.jsp"나 "home.html"같은 실제파일로 매핑해줌

※ 스프링 MVC는 JSP, Thymeleaf, FreeMarker 등과 관련된 다양한 뷰 기술을 지원하며 이를 위해 여러 뷰 리졸버를 제공함

예시)

application.properties

    # ViewResolver가 View를 찾기 위한 경로 설정 
    spring.mvc.view.prefix=/WEB-INF/views/
    spring.mvc.view.suffix=.jsp

SpringMemberController.java

    @Controller
    @RequestMapping("/springmvc/members")
    public class SpringMemberController {
    
        private MemberRepository memberRepository = MemberRepository.getInstance();

        @PostMapping("/save") // HTTP Method도 함께 구분 가능(@PostMapping으로 Post, @GetMapping으로 Get 메서드 매핑 가능)
        public String save(
                @RequestParam("username") String username, // 스프링은 HTTP 요청 파라미터를 @RequestParam으로 받을 수 있음
                @RequestParam("age") int age,
                Model model) {
            
            Member member = new Member(username, age);
            memberRepository.save(member);
            model.addAttribute("member", member); // 뷰에서 렌더링할 데이터를 Model 객체에 담음
            return "save-result"; // "save-result" 라는 뷰의 논리 이름 반환하면 뷰 리졸버가 save-result.html 뷰 파일과 매핑해줌
        }
    }
