package com.hello.boot.springboot.domain.posts;

import com.hello.boot.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // 테이블과 링크될 클래스임을 나타낸다
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK필드를 나타낸다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙을 나타낸다
    private Long id;

    @Column(length = 500, nullable = false)
    /*
    * 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다
    * 사용 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    * 문자열의 경우 VARCHAR(255)가 기본인데, 사이즈를 500으로 늘리고 싶거나, 타입을 TEXT로 변경하 싶거나 등의 경우에 사용
    * */
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
