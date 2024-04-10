# Springmvc
리포지토리 설명 : 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술 스터디 정리

# MVC 패턴이란
MVC 패턴은 하나의 서블릿이나, JSP로 처리하던 것을 컨트롤러(Controller)와 뷰(View)라는 영역으로 서로 역할을 나눈 것으로, 웹 애플리케이션은 보통 이 MVC 패턴을 사용

- 컨트롤러(Controller): HTTP 요청을 받아서 파라미터를 검증하고, 비즈니스 로직을 실행. 그리고 뷰에 전달할 결과 데이터를 조회해서 모델에 담음
- 모델(Model): 뷰에 출력할 데이터를 담아둠. 뷰가 필요한 데이터를 모두 모델에 담아서 전달해주는 덕분에 뷰는 비즈니스 로직이나 데이터 접근을 몰라도 되고, 화면을 렌더링 하는 일에 집중할 수 있음
- 뷰(View): 모델에 담겨있는 데이터를 사용해서 화면을 그리는 일에 집중

# SpringMVC 구조
1. 클라이언트 → HTTP 요청 → Servlet Dispatcher
2. 핸들러 매핑을 통해 요청 URL이 스프링 빈의 이름으로 등록된 핸들러(컨트롤러)를 조회
3. 핸들러를 실행할 수 있는 핸들러 어댑터를 조회
4. 핸들러 어탭터를 통해 핸들러(컨트롤러) 실행 후 ModelAndView 반환
5. ViewResolver를 호출하여 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체를 반환
6. 뷰 렌더링

# 컨트롤러(Controller)
- @Controller : 스프링이 자동으로 스프링 빈으로 등록(내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨)
- @RequestMapping : : 요청 정보를 매핑하며, 해당 URL이 호출되면 이 애노테이션이 선언된 메서드가 호출됨

※ 스프링 부트 3.0(스프링 프레임워크 6.0)부터는 클래스 레벨에 @RequestMapping 이 있어도 스프링 컨트롤러로 인식하지 않고, 오직 @Controller 가 있어야 스프링 컨트롤러로 인식

예시)

    @Controller
    public class MyController {
    
        @RequestMapping("/springmvc/new-form")
        public ModelAndView process() {
            return new ModelAndView("new-form");
        }
    }
