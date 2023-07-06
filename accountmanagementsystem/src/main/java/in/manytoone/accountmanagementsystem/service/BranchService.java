package in.manytoone.accountmanagementsystem.service;

import in.manytoone.accountmanagementsystem.dto.request.BranchPageRequest;
import in.manytoone.accountmanagementsystem.dto.request.CreateBranchRequest;
import in.manytoone.accountmanagementsystem.dto.request.UpdateBranchRequest;
import in.manytoone.accountmanagementsystem.dto.response.BranchPagedResponse;
import in.manytoone.accountmanagementsystem.dto.response.BranchResponse;
import in.manytoone.accountmanagementsystem.entity.BranchEntity;

public interface BranchService {

    public BranchResponse createBranch(CreateBranchRequest createBranchRequest);
    public BranchResponse fetchBranchById(Integer id);

    public BranchResponse deleteById(Integer id);

    public BranchPagedResponse getAllBranch(BranchPageRequest branchPageRequest);
    public BranchResponse updateBranch(Integer id, UpdateBranchRequest updateBranchRequest);

    public BranchEntity getBranchById(Integer id);

}
