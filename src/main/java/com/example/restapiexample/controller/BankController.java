package com.example.restapiexample.controller;

import com.example.restapiexample.dto.ApiResponse;
import com.example.restapiexample.dto.BankDTO;
import com.example.restapiexample.entity.Bank;
import com.example.restapiexample.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody BankDTO dto) {
        ApiResponse response = bankService.add(dto);
        return ResponseEntity.status(response.isSuccess() ?
                        HttpStatus.CREATED : HttpStatus.CONFLICT).
                body(response);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        List<Bank> all = bankService.getAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Bank> optional = bankService.getOne(id);
        return ResponseEntity.status(!optional.isEmpty() ?
                HttpStatus.OK :
                HttpStatus.NOT_FOUND).body(optional.get());
    }
}
