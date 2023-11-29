package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
// @RestController = @Controller + 메서드에 @ResponseBody 모두 적용
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * HttpEntity, ResponseEntity(Http Status 추가)
     * @return
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("Wonho");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /***
     * 이런 경우에는 HTTP 상태코드를 리턴할 수 없으므로,
     * @ResponseStatus 어노테이션 사용하여 응답코드 전달
     * 단, 만약 응답코드를 동적으로 전달해야 한다면 @ResponseStatus 어노테이션 대신
     * responseBodyJsonV1 메서드처럼 ResponseEntity에 상태코드를 담아서 리턴해야 함
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("Wonho");
        helloData.setAge(20);

        return helloData;
    }
}
