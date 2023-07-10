package in.manytomany.studentcoursemanagementsystem.repository;

import in.manytomany.studentcoursemanagementsystem.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
}
