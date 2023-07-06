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
import in.manytoone.accountmanagementsystem.mapper.BranchMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchServiceImp implements  BranchService{

    private final BranchRepository branchRepository;
    private  final BranchMapper branchMapper;

    @Override
    public BranchResponse createBranch(CreateBranchRequest createBranchRequest) {
        log.debug("<<<<<<<<< createBranch()");
        checkBranchRequestNull(createBranchRequest);
        BranchEntity branchEntity = this.branchMapper.toEntity(createBranchRequest);
        BranchEntity savedEntity = this.branchRepository.save(branchEntity);
        log.debug("createBranch() >>>>>>>");
        return  this.branchMapper.toDto(savedEntity);
    }


    @Override
    public BranchResponse fetchBranchById(Integer id) {
        log.debug("<<<<<<<<< fetchBranchById() {}",id);
        BranchEntity branchById = getBranchById(id);
        log.debug("<<<<<<<<< fetchBranchById() {}", id);
       return  this.branchMapper.toDto(branchById);
    }

    @Override
    public BranchResponse deleteById(Integer id) {
        log.debug("<<<<<<<<< deleteById()");
        BranchEntity dbBranch = getBranchById(id);
        dbBranch.setStatus(Status.IN_ACTIVE);
        BranchEntity updatedBranch = this.branchRepository.save(dbBranch);
        log.debug("deleteById() >>>>>>>");
         return this.branchMapper.toDto(updatedBranch);
    }



    @Override
    public BranchPagedResponse getAllBranch(BranchPageRequest branchPageRequest) {

        return getBranchList(branchPageRequest);

    }

    
    @Override
    public BranchResponse updateBranch(Integer id, UpdateBranchRequest updateBranchRequest) {
        log.debug("<<<<<<<<< deleteById()");
        BranchEntity dbBranch = getBranchById(id);
        dbBranch.setName(updateBranchRequest.getBranchName());
        dbBranch.setCode(updateBranchRequest.getBranchCode());
        BranchEntity updatedBranch = this.branchRepository.save(dbBranch);
        BranchResponse dto = this.branchMapper.toDto(updatedBranch);
        log.debug("deleteById() >>>>>>>");
        return  dto ;
    }

    private BranchPagedResponse getBranchList(BranchPageRequest branchPageRequest) {
        log.debug("<<<<<<<<< getBranchList()");
        Integer pageNo= branchPageRequest.getPageNo()-1;
        Integer pageSize= branchPageRequest.getPageSize();
        Pageable pageable =PageRequest.of(pageNo,pageSize);
        Page<BranchEntity> branchPage = this.branchRepository.findAll(pageable);
        BranchPagedResponse branchPagedResponse = new BranchPagedResponse();
        List<BranchResponse> branchList = this.branchMapper.toBranchList(branchPage.getContent());
        branchPagedResponse.setBranchResponseList(branchList);
        branchPagedResponse.setTotalRecords(branchPage.getTotalElements());
        branchPagedResponse.setFirstPage(branchPage.isFirst());
        log.debug("getBranchList() >>>>>>>");
        return branchPagedResponse ;
    }


    private static void checkBranchRequestNull(CreateBranchRequest createBranchRequest) {
        if(createBranchRequest ==null)
        {
            throw new CustomException("creatbranchRequest should not be null");
        }
    }

    public BranchEntity getBranchById(Integer id) {
        Optional<BranchEntity> dbBranch = this.branchRepository.findById(id);
        if(dbBranch.isPresent()){
            return dbBranch.get();
        }
        else{
            throw new RecordNotFoundException("Branch is not available for id :" + id);
        }
    }
}
