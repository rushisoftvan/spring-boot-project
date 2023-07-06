package in.manytoone.accountmanagementsystem.Repository;

import in.manytoone.accountmanagementsystem.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;

public interface BranchRepository  extends JpaRepository<BranchEntity,Integer> {
}
