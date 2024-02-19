package com.companyproject.controller;

import com.companyproject.entity.Company;
import com.companyproject.payload.CompanyDto;
import com.companyproject.service.CompanyService;
import com.zaxxer.hikari.util.FastList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

//http://localhost:8080/companies/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        return new ResponseEntity<>(companyService.getCompanyById(id),HttpStatus.OK);
    }
//http://localhost:8080/companies/byCode/{companyCode}
    @GetMapping("/byCode/{companyCode}")
    public ResponseEntity<CompanyDto> getCompanyByCode(@PathVariable String companyCode) {
        return new ResponseEntity<>( companyService.getCompanyByCode(companyCode), HttpStatus.OK);
    }
    //http://localhost:8080/companies/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyDto> partialUpdateCompany(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return companyService.partialUpdateCompany(id, updates);

    }
    ////http://localhost:8080/companies
    @PostMapping
    public ResponseEntity<Object> saveCompany(@Valid @RequestBody CompanyDto companyDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(companyService.saveCompany(companyDto), HttpStatus.CREATED);
    }
    //http://localhost:8080/companies
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompany() {
        return new ResponseEntity<>(companyService.getAllCompany(),HttpStatus.OK);
    }
}