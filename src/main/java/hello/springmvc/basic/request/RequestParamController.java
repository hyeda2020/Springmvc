package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * @RequestParam 사용
     * - 파라미터 이름으로 바인딩
     * @ResponseBody 추가
     * - View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
     */
    @ResponseBody
    @RequestMapping("/request-param-v1")
    public String requestParamV1(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    /***
     * HTTP 파라미터 이름이 변수명과 같다면 
     * @RequestParam("파라미터명") 에서 괄호 안의 "파라미터명" 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /***
     * String , int , Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
     * 단, 이렇게 @ReqeustParam을 생략하면 스프링에서는
     * 파라미터들에 대해 required=false를 적용함.
     * 또한, 이렇게 @RequestParam까지 생략해버리면 너무 많은 내용이 축약되어 버리니
     * 가급적이면은 @RequestParam 애노테이션을 사용해서
     * 요청 파라미터에서 데이터를 가져온다는 것을 명시하는 것이 좋음.
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /***
     * @param username -> required=true 이므로 생략시 오류 리턴
     * @param age -> 파라미터 생략시 해당 값에 null이 들어오는데, int 등의 기본형 타입에는 null 대입이 불가능하므로 Integer로 대체
     * 한편, username= 처럼 파라미터 이름만 있고, 값이 없을 경우 빈 문자가 들어옴
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required=true) String username,
            @RequestParam(required=false) Integer age) { //
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /***
     * defaultValue를 통해 해당 파라미터 값이 빈 문자가 들어오거나
     * 생략되었을 경우 설정한 기본 값이 들어오게끔 설정 가능
     * @param age -> 생략시 null이 아닌 default 값으로 0이 들어오기 때문에 기본형 타입인 int형 사용 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required=true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "0") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /***
     * @param paramMap : 파라미터를 Map으로 조회 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /***
     * @ModelAttribute 애노테이션을 통해
     * 요청 파라미터 값을 받아서 객체를 생성하고
     * 그 객체에 파라미터 값을 바인딩 하는 과정을 자동화 가능
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /***
     * @ModelAttribute 생략 가능
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
