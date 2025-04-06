package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Loan;
import com.biblioteca.biblioteca.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Request related to loans, as in BookController, create one or more loans, returns one or more specidifc,
// Replaces a loan with data certain fields ore completely and deletes one or all
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public void createLoan(@RequestBody Loan loan) {
        loanService.createLoan(loan);
    }

    @PostMapping("/batch")
    public void createMultipleLoans(@RequestBody List<Loan> loans) {
        for (Loan loan : loans) {
            loanService.createLoan(loan);
        }
    }

    @GetMapping("/{id}")
    public Loan getLoan(@PathVariable String id) {
        return loanService.getLoan(id);
    }

    @GetMapping
    public Map<String, Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PutMapping("/{id}")
    public void updateLoan(@PathVariable String id, @RequestBody Loan loan) {
        loan.setId(id);
        loanService.updateLoan(id, loan);
    }

     @PatchMapping("/{id}")
    public void patchLoan(@PathVariable String id, @RequestBody Loan loan) {
        loanService.patchLoan(id, loan);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable String id) {
        loanService.deleteLoan(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllLoans() {
        loanService.deleteAllLoans();
    }
}
