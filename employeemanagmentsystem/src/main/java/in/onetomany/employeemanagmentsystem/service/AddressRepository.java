package in.onetomany.employeemanagmentsystem.service;

import in.onetomany.employeemanagmentsystem.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity,Integer> {
}
