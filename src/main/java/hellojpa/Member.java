package hellojpa;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//이걸 넣어줘야 JPA가 이걸 사용해야겠구나 인식함.
@Entity
//만약 멤버가 아니라 유저테이블에 넣어줘야 한다면
//@Table (name ="USER") 이렇게 명시하면 됨.
public class Member {
    //id가 pk임을 알려줌.
    @Id
    private Long id;
    //만약 여기에는 name이라고 해놨지만 컬럼은 username이다.
    //@Column(name = "username") 이렇게 명시하면 됨.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
