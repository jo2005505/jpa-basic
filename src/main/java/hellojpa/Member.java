package hellojpa;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity

public class Member {
    @Id
    private Long id;

    @Column(name = "name", nullable = true)
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /*
     * @Temporal은 과거 버전용이며, 현재는 LocalDate와 LocalDateTome을 사용
     */
    /*
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    */

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Lob
    private String description;

    public Member() {}
}
