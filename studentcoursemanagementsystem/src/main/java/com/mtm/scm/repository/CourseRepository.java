package in.manytomany.studentcoursemanagementsystem.repository;

import in.manytomany.studentcoursemanagementsystem.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
}
