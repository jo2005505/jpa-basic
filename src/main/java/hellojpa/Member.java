package hellojpa;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR"
        , table = "MY_SEQUENCES"            // Table Name
        , pkColumnValue = "MEMBER_SEQ"      // PK Column Name
        , allocationSize = 1
)
public class Member {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
            , generator = "MEMBER_SEQ_GENERATOR"
    )
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

