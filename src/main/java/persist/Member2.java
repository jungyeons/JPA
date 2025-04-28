package persist;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member2 {

    //id가 pk임을 알려줌.
    @Id
    private Long id;
    private String name;

    //jpa는 기본적으로 reflection
    public Member2() {

    }
    public Member2(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
