package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Loan;
import com.biblioteca.biblioteca.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanRestController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        return loanService.findById(id).orElseThrow();
    }

    
    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.save(loan);
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Long id, @RequestBody Loan loan) {
        return loanService.update(id, loan);
    }

    @PatchMapping("/{id}")
    public Loan patchLoan(@PathVariable Long id, @RequestBody Loan loan) {
        return loanService.patch(id, loan);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loanService.delete(id);
    }
}
