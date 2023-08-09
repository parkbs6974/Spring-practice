package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private Long id;
    private String username;
    private int age;

    public Member() { //기본 생성자 왜 만드나?
    }

    public Member(String username, int age) {  // 왜 id는 넣지 않는 건가?
        this.username = username;
        this.age = age;
    }
}
