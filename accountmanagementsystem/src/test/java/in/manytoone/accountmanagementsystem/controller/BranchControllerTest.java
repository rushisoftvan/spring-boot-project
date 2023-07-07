package in.manytoone.accountmanagementsystem.controller;

import in.manytoone.accountmanagementsystem.Exception.CustomException;
import in.manytoone.accountmanagementsystem.Exception.RecordNotFoundException;
import in.manytoone.accountmanagementsystem.dto.request.BranchPageRequest;
import in.manytoone.accountmanagementsystem.dto.request.CreateBranchRequest;
import in.manytoone.accountmanagementsystem.dto.request.UpdateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.request.UpdateBranchRequest;
import in.manytoone.accountmanagementsystem.dto.response.ApiResponse;
import in.manytoone.accountmanagementsystem.dto.response.BranchPagedResponse;
import in.manytoone.accountmanagementsystem.dto.response.BranchResponse;
import in.manytoone.accountmanagementsystem.enums.Status;
import in.manytoone.accountmanagementsystem.service.BranchService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@SpringBootTest
class BranchControllerTest {

    @MockBean
    private BranchService branchService;

    @Autowired
    private BranchController branchController;

    @Test
    public void  testcreateBranch()
    {
        CreateBranchRequest createBranchRequest = new CreateBranchRequest();
        createBranchRequest.setBranchName("odhav");
        createBranchRequest.setBranchCode("23455645454");

        BranchResponse branchResponse = new BranchResponse();
        branchResponse.setBranchName("odhav");
        branchResponse.setStatus(Status.ACTIVE);
        branchResponse.setBranchCode("23455645454");

        Mockito.when(branchService.createBranch(createBranchRequest)).thenReturn(branchResponse);
        ApiResponse<BranchResponse> branch = this.branchController.createBranch(createBranchRequest);
        assertEquals(branchResponse,branch.getData());
        assertEquals(HttpStatus.CREATED.value(),branch.getStatusCode());

    }

    @Test
     public void  testCreateBranchIfBranchNameNull(){

         CreateBranchRequest createBranchRequest = new CreateBranchRequest();
         createBranchRequest.setBranchName(null);
         createBranchRequest.setBranchCode("23455645454");

         assertThrows(ConstraintViolationException.class,()->this.branchController.createBranch(createBranchRequest));
    }

    @Test
    public void  fetchBranchById(){
        Integer id=1;
        BranchResponse branchResponse = new BranchResponse();
        branchResponse.setBranchId(id);
        branchResponse.setBranchCode("23455");
        branchResponse.setBranchName("odhav");

        Mockito.when(this.branchService.fetchBranchById(id)).thenReturn(branchResponse);
        ApiResponse<BranchResponse> branchResponseApiResponse = this.branchController.fetchBranchById(id);
        assertEquals("odhav",branchResponseApiResponse.getData().getBranchName());
        assertEquals(200,branchResponseApiResponse.getStatusCode());
    }

    @Test
    public void fetchBranchByIdIfIdNull(){
        Integer id= null;

        assertThrows(ConstraintViolationException.class,()->this.branchController.fetchBranchById(id));

    }
    @Test
    public void fetchBranchByIdIfIdZero(){
        Integer id= 0;
        ConstraintViolationException constraintViolationException = assertThrows(ConstraintViolationException.class, () -> this.branchController.fetchBranchById(id));

    }

    @Test
    public void fetchBranchByIdIfIdNotAvailable(){
        Integer id= 50;
         Mockito.when(this.branchService.fetchBranchById(id)).thenThrow(RecordNotFoundException.class);
         assertThrows(RecordNotFoundException.class,()->this.branchController.fetchBranchById(id));
    }

    @Test
    public void testdeleteById(){
      Integer id=1;
        BranchResponse branchResponse = new BranchResponse();
        branchResponse.setBranchId(id);
        branchResponse.setStatus(Status.IN_ACTIVE);


        Mockito.when(this.branchService.deleteById(id)).thenReturn(branchResponse);
        ApiResponse<Boolean> booleanApiResponse = this.branchController.deleteById(id);
        assertEquals(true,booleanApiResponse.getData());
    }

    @Test
    public void testdeleteByIdIfIdNull(){
        Integer id = null;
        assertThrows(ConstraintViolationException.class,
                ()->this.branchController.deleteById(id));
    }

    @Test
    public void testdeleteByIdIfIdZero(){
        Integer id=0;
          assertThrows(ConstraintViolationException.class,()->this.branchController.deleteById(id));
    }

    @Test
  public void testupdateBranch(){
        Integer id=1;
        UpdateBranchRequest updateBranchRequest = new UpdateBranchRequest();
        updateBranchRequest.setBranchName("odhav");
        updateBranchRequest.setBranchCode("233456");

        BranchResponse branchResponse = new BranchResponse();
        branchResponse.setStatus(Status.ACTIVE);
        branchResponse.setBranchName("odhav");
        branchResponse.setBranchCode("233456");

        Mockito.when(this.branchService.updateBranch(id,updateBranchRequest)).thenReturn(branchResponse);
        ApiResponse<BranchResponse> branchResponseApiResponse = this.branchController.updateBranch(id, updateBranchRequest);
        assertEquals("odhav",branchResponseApiResponse.getData().getBranchName());

    }

    @Test
    public void testupdateBranchIfUpdateRequestBranchNameNull(){
        Integer id =1;

        UpdateBranchRequest updateBranchRequest = new UpdateBranchRequest();
        updateBranchRequest.setBranchCode("32333");
        updateBranchRequest.setBranchName(null);
        assertThrows(ConstraintViolationException.class,()->this.branchController.updateBranch(id,updateBranchRequest));
    }

    @Test
    public void testupdateBranchIfUpdateRequestnull(){
        Integer id=1;
        UpdateBranchRequest updateBranchRequest=null;
         Mockito.when(this.branchService.updateBranch(id,updateBranchRequest)).thenThrow(CustomException.class);
         assertThrows(CustomException.class,()->this.branchController.updateBranch(id,updateBranchRequest));
    }

    @Test
    public void getAllBranch(){

        BranchPageRequest branchPageRequest = new BranchPageRequest();
        branchPageRequest.setPageNo(1);
        branchPageRequest.setPageSize(2);



        List<BranchResponse> listOfBranch = new ArrayList<>();

        BranchResponse branchResponse1 = new BranchResponse();
        branchResponse1.setBranchId(1);
        branchResponse1.setBranchName("odhav");
        branchResponse1.setBranchCode("23455");

         BranchResponse branchResponse2= new BranchResponse();
        branchResponse1.setBranchId(2);
        branchResponse1.setBranchName("pune");
        branchResponse1.setBranchCode("23456");

        listOfBranch.add(branchResponse1);
        listOfBranch.add(branchResponse2);
        BranchPagedResponse branchPagedResponse = new BranchPagedResponse();
        branchPagedResponse.setBranchResponseList(listOfBranch);

        Mockito.when(this.branchService.getAllBranch(branchPageRequest)).thenReturn(branchPagedResponse);
        ApiResponse<BranchPagedResponse> allBranch = this.branchController.getAllBranch(branchPageRequest);
        assertEquals(2,allBranch.getData().getBranchResponseList().size());

    }

    @Test
    public void getAllBranchIfPageNoNull(){
        BranchPageRequest branchPageRequest = new BranchPageRequest();
        branchPageRequest.setPageNo(null);
        branchPageRequest.setPageSize(1);
      assertThrows(ConstraintViolationException.class,()->this.branchController.getAllBranch(branchPageRequest));

    }













}