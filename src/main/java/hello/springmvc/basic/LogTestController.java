package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // 반환 값으로 뷰를 찾는 것이 아닌, HTTP 메시지 바디에 값을 바로 입력하여 응답
public class LogTestController {

    /* @Slf4j 선언해주면 Logger 클래스 없이 바로 로깅 기능 사용 가능 */
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {

        String name = "LogTest";

        // log.trace("trace log=" + name); => 로그레벨을 debug로 했음에도 의미없는 문자 더하기 연산이 발생하기 때문에 이렇게 사용하지 말자!
        log.trace("trace log={}", name);

        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
