package com.companyproject.service;


import com.companyproject.entity.Company;
import com.companyproject.payload.CompanyDto;
import com.companyproject.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyDto saveCompany(CompanyDto companyDto) {
        Company company = mapToCompany(companyDto);
        Company company1 = companyRepository.save(company);
        return mapToDto(company1);
    }

    public CompanyDto mapToDto(Company company) {
        CompanyDto dto = new CompanyDto();
        dto.setId(company.getId());
        dto.setCompanyName(company.getCompanyName());
        dto.setEmail(company.getEmail());
        dto.setStrength(company.getStrength());
        dto.setCompanyCode(company.getCompanyCode());
        dto.setWebSiteURL(company.getWebSiteURL());
        return dto;
    }
    public Company mapToCompany(CompanyDto companyDto){
        Company company=new Company();
        company.setCompanyName(companyDto.getCompanyName());
        company.setEmail(companyDto.getEmail());
        company.setCompanyCode(companyDto.getCompanyCode());
        company.setStrength(companyDto.getStrength());
        company.setWebSiteURL(companyDto.getWebSiteURL());
        return company;
    }


    public CompanyDto getCompanyById(Long id) {
        Optional<Company> byId = companyRepository.findById(id);
        Company company = byId.get();
        return mapToDto(company);

    }

    public CompanyDto getCompanyByCode(String companyCode) {
        Company byCompanyCode = companyRepository.findByCompanyCode(companyCode);
       return  mapToDto(byCompanyCode);
    }

    public ResponseEntity<CompanyDto> partialUpdateCompany(Long id, Map<String, Object> updates) {
        Company company = companyRepository.findById(id).get();
        if (company != null) {
            // Apply partial updates
            updates.forEach((key, value) -> {
                switch (key) {
                    case "companyName":
                        company.setCompanyName((String) value);
                        break;
                    case "email":
                        company.setEmail((String) value);
                        break;
                    case "strength":
                        company.setStrength((Integer) value);
                        break;
                    case "webSiteURL":
                        company.setWebSiteURL((String) value);
                        break;
                    case "companyCode":
                        company.setCompanyCode((String) value);
                        break;
                    default:
                        // Ignore unknown fields
                }
            });
            Company updatedCompany = companyRepository.save(company);
            return new ResponseEntity<>(mapToDto(updatedCompany), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<CompanyDto> getAllCompany() {
        List<Company> company = companyRepository.findAll();
       return  company.stream().map(company1->mapToDto(company1)).collect(Collectors.toList());

    }
}

