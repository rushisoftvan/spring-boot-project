package in.manytoone.accountmanagementsystem.service;

import in.manytoone.accountmanagementsystem.Exception.CustomException;
import in.manytoone.accountmanagementsystem.Exception.RecordNotFoundException;
import in.manytoone.accountmanagementsystem.Repository.BranchRepository;
import in.manytoone.accountmanagementsystem.dto.request.BranchPageRequest;
import in.manytoone.accountmanagementsystem.dto.request.CreateBranchRequest;
import in.manytoone.accountmanagementsystem.dto.request.UpdateBranchRequest;
import in.manytoone.accountmanagementsystem.dto.response.BranchPagedResponse;
import in.manytoone.accountmanagementsystem.dto.response.BranchResponse;
import in.manytoone.accountmanagementsystem.entity.BranchEntity;
import in.manytoone.accountmanagementsystem.enums.Status;
import in.manytoone.accountmanagementsystem.mapper.BranchMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        assertEquals("odhav", branch1.getBranchName());
//        Mockito.when(branchRepository.save(Mockito.any(BranchEntity.class))).thenReturn(branchEntity);
//        BranchResponse branch = this.branchService.createBranch(createBranchRequest);
//        assertEquals("odhav",branch.getBranchName());
    }

    @Test
    public void testCreateBranchIfCreateBranchNull() {
        BranchRepository branchMock = Mockito.mock(BranchRepository.class);
        BranchMapperImpl branchMapper = new BranchMapperImpl();
        BranchServiceImp branchServiceImp = new BranchServiceImp(branchMock, branchMapper);
        CustomException customException = assertThrows(CustomException.class, () -> branchServiceImp.createBranch(null));
        assertEquals("creatbranchRequest should not be null", customException.getMessage());
    }

    @Test
    public void testFetchBranchById() {
        Integer id = 1;
        BranchRepository branchMock = Mockito.mock(BranchRepository.class);
        BranchMapperImpl branchMapper = new BranchMapperImpl();
        BranchServiceImp branchServiceImp = new BranchServiceImp(branchMock, branchMapper);

        BranchResponse branchResponse = new BranchResponse();
        branchResponse.setAccountId(id);
        branchResponse.setBranchName("odhav");
        branchResponse.setStatus(Status.ACTIVE);

        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setId(1);
        branchEntity.setName("odhav");
        branchEntity.setCode("23455645454");
        branchEntity.setStatus(Status.ACTIVE);
        Mockito.when(branchMock.findById(id)).thenReturn(Optional.of(branchEntity));
        BranchResponse branch = branchServiceImp.fetchBranchById(id);
        assertEquals("odhav", branch.getBranchName());
    }

    @Test
    public void testFetchBranchByIdIfBranchNotAvailable() {
        Integer id = 50;
        BranchRepository branchMock = Mockito.mock(BranchRepository.class);
        BranchMapperImpl branchMapper = new BranchMapperImpl();
        BranchServiceImp branchServiceImp = new BranchServiceImp(branchMock, branchMapper);
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class, () -> branchServiceImp.getBranchById(id));
    }

    @Test
    public void testFetchBranchByIdIfIdNull() {
        Integer id = null;
        BranchRepository branchMock = Mockito.mock(BranchRepository.class);
        BranchMapperImpl branchMapper = new BranchMapperImpl();
        BranchServiceImp branchServiceImp = new BranchServiceImp(branchMock, branchMapper);
        assertThrows(CustomException.class, () -> branchServiceImp.fetchBranchById(id));

    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setId(id);
        branchEntity.setName("odhav");
        branchEntity.setCode("23455645454");
        branchEntity.setStatus(Status.ACTIVE);

        BranchRepository branchMock = Mockito.mock(BranchRepository.class);
        BranchMapperImpl branchMapper = new BranchMapperImpl();
        BranchServiceImp branchServiceImp = new BranchServiceImp(branchMock, branchMapper);

        Mockito.when(branchMock.findById(id)).thenReturn(Optional.of(branchEntity));
        branchEntity.setStatus(Status.IN_ACTIVE);
        Mockito.when(branchMock.save(branchEntity)).thenReturn(branchEntity);
        BranchResponse branch = branchServiceImp.deleteById(id);
        assertEquals(Status.IN_ACTIVE, branch.getStatus());
    }

    @Test
    public void testDeleteByIdIfIdNull() {
        Integer id = null;
        BranchRepository branchMock = Mockito.mock(BranchRepository.class);
        BranchMapperImpl branchMapper = new BranchMapperImpl();
        BranchServiceImp branchServiceImp = new BranchServiceImp(branchMock, branchMapper);
        assertThrows(CustomException.class, () -> branchServiceImp.deleteById(id));
    }

    @Test
    public void testUpdateBranch() {
        Integer id = 1;

        UpdateBranchRequest updateBranchRequest = new UpdateBranchRequest();
        updateBranchRequest.setBranchCode("345666");
        updateBranchRequest.setBranchName("odhav");

        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setId(id);
        branchEntity.setName("pune");
        branchEntity.setCode("23455645454");
        branchEntity.setStatus(Status.ACTIVE);

        BranchRepository branchMock = Mockito.mock(BranchRepository.class);
        BranchMapperImpl branchMapper = new BranchMapperImpl();
        BranchServiceImp branchServiceImp = new BranchServiceImp(branchMock, branchMapper);

        Mockito.when(branchMock.findById(id)).thenReturn(Optional.of(branchEntity));

        branchEntity.setName(updateBranchRequest.getBranchName());
        branchEntity.setCode(updateBranchRequest.getBranchCode());
        Mockito.when(branchMock.save(branchEntity)).thenReturn(branchEntity);
        BranchResponse branchResponse = branchServiceImp.updateBranch(id, updateBranchRequest);
        assertEquals("odhav", branchResponse.getBranchName());
    }

    @Test
    public void testUpdateBranchIfUpdateRequest() {
        Integer id=1;
        BranchRepository branchMock = Mockito.mock(BranchRepository.class);
        BranchMapperImpl branchMapper = new BranchMapperImpl();
        BranchServiceImp branchServiceImp = new BranchServiceImp(branchMock, branchMapper);
        assertThrows(CustomException.class,()->branchServiceImp.updateBranch(id,null));
    }

    @Test
    public void getBranchList(){
        BranchPageRequest branchPageRequest = new BranchPageRequest();
        branchPageRequest.setPageNo(1);
        branchPageRequest.setPageSize(2);

        List<BranchEntity> branches = new ArrayList<>();
        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setId(1);
        branchEntity.setName("odhav");
        branchEntity.setStatus(Status.ACTIVE);
        branchEntity.setCode("23456");

        branches.add(branchEntity);


        BranchRepository branchMock = Mockito.mock(BranchRepository.class);
        BranchMapperImpl branchMapper = new BranchMapperImpl();
        BranchServiceImp branchServiceImp = new BranchServiceImp(branchMock, branchMapper);


        Integer pageNo=branchPageRequest.getPageNo()-1;
        Integer pageSize=branchPageRequest.getPageSize();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page page = new PageImpl(branches, pageable, branches.size());

        Mockito.when(branchMock.findAll(pageable)).thenReturn(page);
        BranchPagedResponse allBranch = branchServiceImp.getAllBranch(branchPageRequest);
        assertEquals(1,allBranch.getBranchResponseList().size());
    }
}





