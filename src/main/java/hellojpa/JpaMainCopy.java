package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMainCopy  {

    public static void main(String[] args) {

        //emf는 로딩시점에 딱 하나만 !!
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //일반적인 일을 할때마다 em탄생
        EntityManager em = emf.createEntityManager();

        //JPA에서는 꼭 트렌젝션을 해야함.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //CREATE 멤버 만들기
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            //만든 멤버 저장하기
            em.persist(member);

//            //트렌젝션 저장
//            tx.commit();

            //멤버 찾기 find를 이용해서 찾으면 됨.
            Member findMember = em.find(Member.class, 1L);
            System.out.println(findMember.getName());
            System.out.println(findMember.getId());
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}

