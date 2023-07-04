package in.onetomany.employeemanagmentsystem.entity;

import in.onetomany.employeemanagmentsystem.StatusEnum.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="employee")
@ToString
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EMPLOYEE_ID",updatable = false,nullable = false)
    private Integer id;

    @Column(name="NAME")
    private String name;

    @Column(name="AGE")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private StatusEnum status;

    @OneToMany(mappedBy = "employeeEntity",fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    private List<AddressEntity> addressEntityList=new ArrayList<>();

    @Column(name="CREATED_ON")
    private LocalDateTime createdDateTime;

    @Column(name="UPDATED_ON")
    private LocalDateTime updatedDateTime;

    public void addAddress(AddressEntity address){
        this.addressEntityList.add(address);
        address.setEmployeeEntity(this);
    }

    @PrePersist
    public void beforePersist() {
        this.createdDateTime = LocalDateTime.now();
        this.updatedDateTime = LocalDateTime.now();
        this.status = StatusEnum.ACTIVE;
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedDateTime = LocalDateTime.now();
    }


}
