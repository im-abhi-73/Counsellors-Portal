package com.counsellors.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Setter
@Getter
public class EnquiryEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer enquiryId;
   private String stdName;
   private String phone;
   private String classMode;
   private String course;
   private String status;


   @ManyToOne
   @JoinColumn(name = "counsellor_id")
   private CounsellorEntity counsellor;



}
