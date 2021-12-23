package com.practice.book.springboot.config.auth.dto;

import com.practice.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    /**
     * [User 클래스를 사용하지 않는 이유]
     * User 클래스를 사용하면 직렬화를 구현하지 않았다는 에러가 뜨는데, 이는 엔티티이기 때문이다.
     * @OneToMany, @ManyToMany 등 자식 엔티티를 갖고있다면 직렬화 대상에 자식들까지 포함함. -> 성능 이슈, 부수 효과발생
     * 직렬화 기능을 가진 세션 Dto를 추가로 만드는 것이 운영 및 유지보수에 도움이 됨.
     */
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
