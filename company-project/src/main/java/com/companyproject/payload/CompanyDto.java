package com.companyproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    @NotEmpty
    @Size(min = 5,message = "should be at least five characters")
    private String companyName;

    @NotEmpty
    @Email
    private String email;

    @PositiveOrZero
    private Integer strength;

    private String webSiteURL;


    @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[EN]$", message = "Company code must have 2 alphabets, 2 numbers, and end with E or N")
    private String companyCode;

}
