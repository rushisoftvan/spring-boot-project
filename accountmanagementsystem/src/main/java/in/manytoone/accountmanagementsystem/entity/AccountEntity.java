package in.manytoone.accountmanagementsystem.entity;

import in.manytoone.accountmanagementsystem.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@Getter
@Setter
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(updatable = false,nullable = false)
    private Integer id;
    private String accountNumber;
    private String fullName;
    private BigDecimal balance;



    private Status status;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="branch_id")
    private BranchEntity branch;

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
