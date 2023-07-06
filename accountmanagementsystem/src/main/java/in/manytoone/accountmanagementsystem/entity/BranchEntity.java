package in.manytoone.accountmanagementsystem.entity;

import in.manytoone.accountmanagementsystem.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DynamicUpdate
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false,nullable = false)
    private Integer id;

    private String code;

    private String name;


    private Status status;

    private LocalDateTime createdOn;

    private  LocalDateTime updatedOn;


    @PrePersist
    public void prePersist(){
      this.createdOn=LocalDateTime.now();
      this.status=Status.ACTIVE;
    }

    @PostUpdate
    public void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }



}
