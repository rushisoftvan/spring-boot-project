package in.onetomany.employeemanagmentsystem.repository;

import in.onetomany.employeemanagmentsystem.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {


}
