package in.onetoone.bookmanagementsystem.repository;

import in.onetoone.bookmanagementsystem.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Integer> {

}
