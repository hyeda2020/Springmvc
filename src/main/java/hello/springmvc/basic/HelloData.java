package hello.springmvc.basic;

import lombok.Data;

@Data // lombok의 @Getter, @Setter, @ToString 모두 포함
public class HelloData {
    private String username;
    private int age;
}
