package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.Loan;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoanService {
    private final Map<String, Loan> loanMap = new HashMap<>();
    private static int nextId = 1;

    public void createLoan(Loan loan) {
        String generatedId = String.valueOf(nextId++);
        loan.setId(generatedId);
        loanMap.put(generatedId, loan);
    }

    public Loan getLoan(String id) {
        return loanMap.get(id);
    }

    public void updateLoan(String id, Loan loan) {
        loanMap.put(id, loan); 
    }

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

    public void deleteLoan(String id) {
        loanMap.remove(id);
    }

    public void deleteAllLoans() {
        loanMap.clear();
    }

    public Map<String, Loan> getAllLoans() {
        return loanMap;
    }
}
