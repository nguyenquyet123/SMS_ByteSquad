package com.poly.sms.controller.app;

import com.poly.sms.entity.Branch;
import com.poly.sms.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branches = branchService.findAll();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Integer id) {
        Optional<Branch> branch = branchService.findById(id);
        return branch.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
        Branch savedBranch = branchService.save(branch);
        return new ResponseEntity<>(savedBranch, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable Integer id, @RequestBody Branch branchDetails) {
        Optional<Branch> branch = branchService.findById(id);
        if (branch.isPresent()) {
            Branch existingBranch = branch.get();
            existingBranch.setBranchType(branchDetails.getBranchType());
            existingBranch.setBranchName(branchDetails.getBranchName());
            existingBranch.setManagerName(branchDetails.getManagerName());
            existingBranch.setAddress(branchDetails.getAddress());
            existingBranch.setPhone(branchDetails.getPhone());
            existingBranch.setEmail(branchDetails.getEmail());
            existingBranch.setBranchState(branchDetails.getBranchState());
            existingBranch.setEmployee(branchDetails.getEmployee());
            Branch updatedBranch = branchService.save(existingBranch);
            return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBranch(@PathVariable Integer id) {
        try {
            branchService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
