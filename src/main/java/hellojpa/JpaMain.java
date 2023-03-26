package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

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

            /* JPQL 작성, Member 객체를 대상으로 조회하는 Query을 작성 */
            /*
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();

            result.stream().forEach(n -> {
                    System.out.println("id : " + n.getId() + "\nname : " + n.getName());
                }
            );
            */

            /* 변경 감지 */
            /*
            Member member = em.find(Member.class, 1L);
            member.setName("그만해~ 그러다 다 죽어~~!!");


            Member memberA = new Member();
            memberA.setId(2L);
            memberA.setName("김A");

            Member memberB = new Member();
            memberB.setId(3L);
            memberB.setName("김B");

            Member memberC = new Member();
            memberC.setId(4L);
            memberC.setName("김C");

            em.persist(memberA);
            em.persist(memberB);
            em.persist(memberC);

            //em.flush();  // 영속성에서 변경분을 반영

            List<Member> result = em.createQuery("select m from Member m" ,Member.class).getResultList();

            result.stream().forEach(n -> {
                        System.out.println("id : " + n.getId() + "\nname : " + n.getName());
                    }
            );
            */

            Member member = em.find(Member.class, 1L);

            System.out.println("memberId : " + member.getId());
            System.out.println("memberName : " + member.getName());

            member.setName("그만할까?");

            em.detach(member);  // 영속성 컨텍스트에서 제외되기 때문에 변경분이 저장되지 않는다.

            tx.commit();        // commit시 Entity의 수정내용이 있으면 데이터베이스에 반영하여 Commit 진행

            member = em.find(Member.class, 1L);
            System.out.println("memberId : " + member.getId());
            System.out.println("memberName : " + member.getName());
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
