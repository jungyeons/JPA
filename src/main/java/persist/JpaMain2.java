package persist;

import hellojpa.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain2 {

    public static void main(String[] args) {

        //emf는 로딩시점에 딱 하나만 !!
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //일반적인 일을 할때마다 em탄생
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member2 member1 = new Member2(150L,"A");
            Member2 member2 = new Member2(151L,"B");
            //이렇게 하면 버퍼를 모았다가 디비에 한번에 저장
            em.persist(member1);
            em.persist(member2);

            //flush하면 바로 sql문 실행됨.
            em.flush();

            //** 플러시 세팅 **//
            //FlushmodeType.auto -> 커밋이나 쿼리를 실행할 때 플러시
            //FlushmodeType.COMMIT -> 커밋할 떄만 플러시

            //이때 저장함.
            tx.commit();
            System.out.println("==========================");
            //update때는 persist 절대 하지말기
            member1 = em.find(Member2.class, 150L);
            member2.setName("ZZZZ");

            //찾아내서 삭제 (아예삭제)
            member2= em.find(Member2.class, 151L);
            em.remove(member2);

          //준영속 상태 -> 영속이 끊어진 상태
            //특정 엔티티만 끊기
            //em.detach(member2);
            //전체 끊기
            //em.clear();
            //영속성 컨텍스트를 아예 닫기
            //em.close();



        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

}
