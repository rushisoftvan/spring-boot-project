package in.manytoone.accountmanagementsystem.Repository;

import in.manytoone.accountmanagementsystem.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {


}
