package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        /**
         * EntityManagerFactory
         * persistence.xml 설정 정보 확인 후 persistence-unit name 에 맞는 EntityManagerFactory 생성
         * Web Server 가 생성되는 시점에 하나만 생성해서 애플리케이션 전체에서 공유
         */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        insertExample(emf);
        updateExample(emf);

        emf.close();
    }

    private static void updateExample(EntityManagerFactory emf) {
        /**
         * EntityManager
         * 요청 건마다 생성
         * 쓰레드간 공유하면 안 되고, 사용 후 종료
         */
        EntityManager em = emf.createEntityManager();

        /**
         * EntityTransaction
         * JPA의 모든 데이터 변경은 트랜젝션 안에서 실행
         */
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 1L);
            member.setName("AaronNew");
            // JPA 가 commit 시점에 변경사항을 체크하고 변경 시 update query 생성
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void insertExample(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("Aaron");

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
