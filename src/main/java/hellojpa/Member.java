package hellojpa;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
public class Member {

    /**
     * GeneratedValue Strategy
     *   - GenerationType.AUTO : 데이터베이스 방언에 적합한 내용으로 설정
     *   - GenerationType.IDENTITY : 기본 키 생성을 데이터베이스에 위임 (MySQL : Auto Increment)
     *     - 사용 데이터베이스 : MySQL, PostgreSQL, SQL Server, DB2
     *     - JPA는 일반적으로 트랜잭션 커밋 시점에 Insert SQL 실행
     *     - AUTO_INCREMENT는 데이터베이스에 Insert SQL 실행한 이후 ID값 확인 가능
     *     - EntityManager.persist() 시점에 즉시 Insert SQL 실행하며, DB에서 식별자를 조회
     *   - GenerationType.SEQUENCE : 숫자형 데이터로 로우의 개수에 맞게 증가되는 형태를 취함
     *     - 사용 데이터베이스 : ORACLE
     *     - 테이블마다 시퀀스 별도로 관리하고 싶은 경우 Entity Class과 컬럼 필드에 명시
     *       - Entity Class : @SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ(매핑할 데이터베이스 시퀀스 명)", initialValue = 1, allocationSize = 1)
     *       - Column Field : @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
     *   - GenerationType.TABLE
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;

    @Column(name = "name")
    private String username;

    public Member() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

