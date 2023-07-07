package in.manytoone.accountmanagementsystem.service;

import in.manytoone.accountmanagementsystem.Exception.CustomException;
import in.manytoone.accountmanagementsystem.Repository.BranchRepository;
import in.manytoone.accountmanagementsystem.dto.request.CreateBranchRequest;
import in.manytoone.accountmanagementsystem.dto.response.BranchResponse;
import in.manytoone.accountmanagementsystem.entity.BranchEntity;
import in.manytoone.accountmanagementsystem.mapper.BranchMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class BranchServiceImpTest {

   // @Mock
    //BranchRepository branchRepository;

   //@Autowired
   // BranchService branchService;

    @Test
    public void testCreateBranch() {
        CreateBranchRequest createBranchRequest = new CreateBranchRequest();
        createBranchRequest.setBranchName("odhav");
        createBranchRequest.setBranchCode("23455645454");

        BranchRepository branchMock = Mockito.mock(BranchRepository.class);
        BranchMapperImpl branchMapper = new BranchMapperImpl();


        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setId(1);
        branchEntity.setName("odhav");
        branchEntity.setCode("23455645454");

         Mockito.when(branchMock.save(Mockito.any(BranchEntity.class))).thenReturn(branchEntity);
        BranchServiceImp branchServiceImp = new BranchServiceImp(branchMock, branchMapper);
        BranchResponse branch1 = branchServiceImp.createBranch(createBranchRequest);
        assertEquals("odhav",branch1.getBranchName());


//        Mockito.when(branchRepository.save(Mockito.any(BranchEntity.class))).thenReturn(branchEntity);
//        BranchResponse branch = this.branchService.createBranch(createBranchRequest);
//        assertEquals("odhav",branch.getBranchName());


    }

    @Test
    public void testCreateBranchIfCreateBranchNull(){
        BranchRepository branchMock = Mockito.mock(BranchRepository.class);
        BranchMapperImpl branchMapper = new BranchMapperImpl();
        BranchServiceImp branchServiceImp = new BranchServiceImp(branchMock, branchMapper);
        CustomException customException = assertThrows(CustomException.class, () -> branchServiceImp.createBranch(null));
        assertEquals("creatbranchRequest should not be null",customException.getMessage());
    }



}