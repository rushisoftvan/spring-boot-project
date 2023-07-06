package in.manytoone.accountmanagementsystem.controller;

import in.manytoone.accountmanagementsystem.dto.request.BranchPageRequest;
import in.manytoone.accountmanagementsystem.dto.request.CreateBranchRequest;
import in.manytoone.accountmanagementsystem.dto.request.UpdateBranchRequest;
import in.manytoone.accountmanagementsystem.dto.response.ApiResponse;
import in.manytoone.accountmanagementsystem.dto.response.BranchPagedResponse;
import in.manytoone.accountmanagementsystem.dto.response.BranchResponse;
import in.manytoone.accountmanagementsystem.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/branchapi")
@Slf4j
@RequiredArgsConstructor
@Validated
public class BranchController {

     public final BranchService branchService;

    @PostMapping("/branches")
    public ApiResponse<BranchResponse> createBranch(@Valid @RequestBody CreateBranchRequest createBranchRequest){
        log.debug("<<<<<<<<< createBranch()");
        BranchResponse branch = this.branchService.createBranch(createBranchRequest);
        ApiResponse.ApiResponseBuilder<BranchResponse> builder = ApiResponse.builder();
        log.debug("createBranch() >>>>>>>");
        return builder.data(branch).statusCode(HttpStatus.CREATED.value()).build();
    }




    @GetMapping("/branches/{id}")
    public ApiResponse<BranchResponse> fetchBranchById(@PathVariable("id") @Positive(message= "id should be greater than zero") @NotNull(message="id should not be null or empty") Integer id)
    {  log.debug("<<<<<<<<< fetchBranchById()");
        BranchResponse branchResponse = this.branchService.fetchBranchById(id);
        log.debug("fetchBranchById() >>>>>>>");
        ApiResponse.ApiResponseBuilder<BranchResponse> builder = ApiResponse.builder();
        return  builder.data(branchResponse).statusCode(HttpStatus.OK.value()).build();
    }

    @DeleteMapping("/branches/{id}")
      public ApiResponse<Boolean> deleteById( @PathVariable("id") @Positive(message= "id should be greater than zero") @NotNull(message="id should not be null or empty") Integer id){
          log.debug("<<<<<<<<< deleteById()");
          this.branchService.deleteById(id);
          log.debug("deleteById() >>>>>>>");
          ApiResponse.ApiResponseBuilder<Boolean> builder = ApiResponse.builder();
          return builder.data(true).statusCode(HttpStatus.OK.value()).build();
    }

    @PostMapping("/pagebranches")
     public ApiResponse<BranchPagedResponse> getAllBranch(@RequestBody BranchPageRequest branchPageRequest)
     {      log.debug("<<<<<<<<< getAllBranch()");
         BranchPagedResponse allBranch = this.branchService.getAllBranch(branchPageRequest);
         ApiResponse.ApiResponseBuilder<BranchPagedResponse> builder = ApiResponse.builder();
         log.debug("getAllBranch() >>>>>>>");
         return builder.data(allBranch).statusCode(HttpStatus.OK.value()).build();
     }

     @PutMapping("/branches/{id}")
      public ApiResponse<BranchResponse> updateBranch(@PathVariable("id") Integer id, @Valid @RequestBody UpdateBranchRequest updateBranchRequest)
      {    log.debug("<<<<<<<<< updateBranch()");
          BranchResponse branchResponse = this.branchService.updateBranch(id, updateBranchRequest);
          ApiResponse.ApiResponseBuilder<BranchResponse> builder = ApiResponse.builder();
          log.debug("updateBranch() >>>>>>>");
          return builder.data(branchResponse).statusCode(HttpStatus.OK.value()).build();
      }
}
