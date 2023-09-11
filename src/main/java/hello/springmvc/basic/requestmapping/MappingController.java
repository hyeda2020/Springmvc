package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Slf4j
@RestController
public class MappingController {

    /**
     * 편리한 축약 애노테이션 (코드보기)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    //@RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    @GetMapping(value = {"/hello-basic", "/hello"}) // 여러 개의 URI에 대해 다중으로 매핑 설정 가능
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * PathVariable 사용
     */
    @GetMapping("/mapping/{userId}")
    //public String mappingPath(@PathVariable("userId") String data) // PathVariable명과 파라미터 변수명이 같으면 아래처럼 생략 가능
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug") // 쿼리 파라미터로 "mode=debug"가 들어가야만 메서드 실행
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE) // 서버에서 "application/json" 타입 요청만 처리
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * 요청 클라이언트의 Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "mapping-produces", produces = MediaType.TEXT_HTML_VALUE) // 요청 클라이언트 헤더의 Accept 타입이 "text/html"이어야만 응답 가능
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
