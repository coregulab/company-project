package com.companyproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="company_name")
    private String companyName;

    @Column(name = "email", unique = true)
    private String email;


    @Column(name = "strength")
    private Integer strength;

    @Column(name = "web_site_url")
    private String webSiteURL;

    @Column(name = "company_code",unique = true)
    private String companyCode;




    // Constructors, getters, and setters
}
