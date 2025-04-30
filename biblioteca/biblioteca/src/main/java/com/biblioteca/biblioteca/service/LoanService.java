package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.Loan;
import com.biblioteca.biblioteca.repository.BookRepository;
import com.biblioteca.biblioteca.repository.LoanRepository;
import com.biblioteca.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Optional<Loan> findById(Long id) {
        return loanRepository.findById(id);
    }

    public Loan save(Loan loan) {
        if (loan.getUser() != null) {
            loan.setUser(userRepository.findById(loan.getUser().getId()).orElseThrow());
        }

        if (loan.getBook() != null) {
            loan.setBook(bookRepository.findById(loan.getBook().getId()).orElseThrow());
        }

        return loanRepository.save(loan);
    }

    public void delete(Long id) {
        loanRepository.deleteById(id);
    }

    public Loan update(Long id, Loan newLoan) {
        return loanRepository.findById(id).map(loan -> {
            loan.setLoanDate(newLoan.getLoanDate());
            loan.setReturnDate(newLoan.getReturnDate());

            if (newLoan.getUser() != null) {
                loan.setUser(userRepository.findById(newLoan.getUser().getId()).orElseThrow());
            }

            if (newLoan.getBook() != null) {
                loan.setBook(bookRepository.findById(newLoan.getBook().getId()).orElseThrow());
            }

            return loanRepository.save(loan);
        }).orElseThrow();
    }

    public Loan patch(Long id, Loan patchLoan) {
        return loanRepository.findById(id).map(loan -> {
            if (patchLoan.getLoanDate() != null) loan.setLoanDate(patchLoan.getLoanDate());
            if (patchLoan.getReturnDate() != null) loan.setReturnDate(patchLoan.getReturnDate());

            if (patchLoan.getUser() != null) {
                loan.setUser(userRepository.findById(patchLoan.getUser().getId()).orElseThrow());
            }

            if (patchLoan.getBook() != null) {
                loan.setBook(bookRepository.findById(patchLoan.getBook().getId()).orElseThrow());
            }

            return loanRepository.save(loan);
        }).orElseThrow();
    }
}
