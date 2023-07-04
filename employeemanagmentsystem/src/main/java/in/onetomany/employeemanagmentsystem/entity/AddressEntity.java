package in.onetomany.employeemanagmentsystem.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name="address")
@ToString
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID" , updatable = false)
    private Integer id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "HOUSE_NO")
    private Integer houseNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;
}
