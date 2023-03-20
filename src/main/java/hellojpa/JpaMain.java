package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        // 데이터베이스와 연결되어있는 상태가 되며, 하나만을 생성 (싱글패턴 형태)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 하나의 Transaction 단위를 실행할 때마다 생성/삭제하며, 스레드간 공유하면 않됨
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 필수적으로 Transaction 내에서 동작
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /* insert Ex */
            /*
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");

            em.persist(member);     // 데이터베이스에 저장 수행
            */

            /* select Ex */
            /*
            Member findMember = em.find(Member.class, 1L);
            System.out.println("find Member.id : " + findMember.getId());
            System.out.println("find Member.name : " + findMember.getName());
             */

            /* update Ex */
            /*
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
            */
            
            tx.commit();        // commit시 Entity의 수정내용이 있으면 데이터베이스에 반영하여 Commit 진행
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
