package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        //emf는 로딩시점에 딱 하나만 !!
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //일반적인 일을 할때마다 em탄생
        EntityManager em = emf.createEntityManager();

        try {
            // CREATE
            createMember(em);

            // READ
            readMember(em, 1L);

            // UPDATE
            updateMember(em, 1L, "HelloB");

            // DELETE
            deleteMember(em, 1L);

        } finally {
            em.close();
            emf.close();
        }
    }

    // CREATE
    private static void createMember(EntityManager em) {
        //JPA에서는 꼭 트렌젝션을 해야함.
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setId(1L); // ID는 수동 설정. 자동화 가능.
            member.setName("HelloA");

            em.persist(member);
            tx.commit();
            System.out.println("✅ CREATE 완료");

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    // READ
    private static void readMember(EntityManager em, Long id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member findMember = em.find(Member.class, id);
            if (findMember != null) {
                System.out.println("✅ READ 결과");
                System.out.println("이름: " + findMember.getName());
                System.out.println("ID: " + findMember.getId());
            } else {
                System.out.println("❌ 멤버를 찾을 수 없습니다.");
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    // UPDATE
    private static void updateMember(EntityManager em, Long id, String newName) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member memberToUpdate = em.find(Member.class, id);
            if (memberToUpdate != null) {
                memberToUpdate.setName(newName); // JPA는 변경 감지를 통해 자동 update
                tx.commit();
                System.out.println("✅ UPDATE 완료: " + newName);
            } else {
                System.out.println("❌ 수정할 멤버가 없습니다.");
                tx.rollback();
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    // DELETE
    private static void deleteMember(EntityManager em, Long id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member memberToDelete = em.find(Member.class, id);
            if (memberToDelete != null) {
                em.remove(memberToDelete); // 삭제
                tx.commit();
                System.out.println("✅ DELETE 완료");
            } else {
                System.out.println("❌ 삭제할 멤버가 없습니다.");
                tx.rollback();
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
}
