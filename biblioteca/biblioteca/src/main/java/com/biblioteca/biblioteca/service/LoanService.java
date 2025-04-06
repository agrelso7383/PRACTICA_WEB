package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.Loan;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoanService {
    private final Map<String, Loan> loanMap = new HashMap<>();
    private static int nextId = 1;

    // new loan with a generated ID
    public void createLoan(Loan loan) {
        String generatedId = String.valueOf(nextId++);
        loan.setId(generatedId);
        loanMap.put(generatedId, loan);
    }
// returns a loan by the ID
    public Loan getLoan(String id) {
        return loanMap.get(id);
    }
// replaces a loan completely
    public void updateLoan(String id, Loan loan) {
        loanMap.put(id, loan); 
    }
// updates only fields provided
    public void patchLoan(String id, Loan updatedFields) {
        Loan existingLoan = loanMap.get(id);
        if (existingLoan != null) {
            if (updatedFields.getBook() != null) {
                existingLoan.setBook(updatedFields.getBook());
            }
            if (updatedFields.getUser() != null) {
                existingLoan.setUser(updatedFields.getUser());
            }
            if (updatedFields.getLoanDate() != null) {
                existingLoan.setLoanDate(updatedFields.getLoanDate());
            }
            if (updatedFields.getReturnDate() != null) {
                existingLoan.setReturnDate(updatedFields.getReturnDate());
            }
            loanMap.put(id, existingLoan);
        }
    }
// deletes a loan by ID
    public void deleteLoan(String id) {
        loanMap.remove(id);
    }
// deletes all loans
    public void deleteAllLoans() {
        loanMap.clear();
    }
// returns all loans
    public Map<String, Loan> getAllLoans() {
        return loanMap;
    }
}
