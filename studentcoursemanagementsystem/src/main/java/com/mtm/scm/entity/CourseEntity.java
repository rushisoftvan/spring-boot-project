package in.manytomany.studentcoursemanagementsystem.entity;

import in.manytomany.studentcoursemanagementsystem.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status;


    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    @PrePersist
    public void beforePersist() {
        this.createdDateTime = LocalDateTime.now();
        this.updatedDateTime = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedDateTime = LocalDateTime.now();
    }

}
