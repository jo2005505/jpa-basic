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

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("memeber1");
            member.setTeam(team);
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            tx.commit();

            System.out.println("FIND TEAM : "  + findTeam.getName());
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
