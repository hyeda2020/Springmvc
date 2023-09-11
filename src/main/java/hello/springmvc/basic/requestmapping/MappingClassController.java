package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/***
 * 회원 관리 API 예시
 * * 회원 목록 조회: GET /users
 * * 회원 등록: POST /users
 * * 회원 조회: GET /users/{userId}
 * * 회원 수정: PATCH /users/{userId}
 * * 회원 삭제: DELETE /users/{userId}
 */
@Slf4j
@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String getUsers() {
        log.info("getUsers");
        return "get users";
    }

    @PostMapping
    public String addUser() {
        log.info("addUser");
        return "add user";
    }

    @GetMapping("/{userId}")
    public String findUserById(@PathVariable String userId) {
        log.info("findUserById={}", userId);
        return "findUserById " + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUserById(@PathVariable String userId) {
        log.info("updateUserById", userId);
        return "updateUserById " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUserById(@PathVariable String userId) {
        log.info("deleteUserById", userId);
        return "deleteUserById " + userId;
    }
}
