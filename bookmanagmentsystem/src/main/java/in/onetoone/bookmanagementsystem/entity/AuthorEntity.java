package in.onetoone.bookmanagementsystem.entity;

import in.onetoone.bookmanagementsystem.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name="author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID" ,updatable = false,nullable = false)
    private Integer id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="AUTHOR_EXPERIENCE")
    private Integer authorExperience;

}
