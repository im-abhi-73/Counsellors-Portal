package com.counsellors.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table
public class CounsellorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer counsellorId;
    private String name;
    private String email;
    private String password;
    private String phone;


@OneToMany(mappedBy = "counsellor", cascade = CascadeType.ALL)
   private List<EnquiryEntity>enquiryEntities;


}
