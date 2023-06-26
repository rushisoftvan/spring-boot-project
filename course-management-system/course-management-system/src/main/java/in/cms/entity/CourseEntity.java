package in.cms.entity;

import in.cms.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="course")
@Getter
@Setter
@ToString
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",updatable = false,unique = true)
    private Integer id;

    @Column(name="TITLE")
    private  String title;

    @Column(name="PRICE")
    private BigDecimal price;

    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="CREATED_ON")
    private LocalDateTime createdOn;

    @Column(name="UPDATE_ON")
    private LocalDateTime updatedOn;

    @PrePersist
    public void prePresist(){
        this.status=Status.ACTIVE;
        this.createdOn=LocalDateTime.now();
        this.updatedOn=LocalDateTime.now();
    }
       @PostUpdate
     public void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }

}
