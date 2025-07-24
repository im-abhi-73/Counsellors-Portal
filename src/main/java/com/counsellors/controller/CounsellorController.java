package com.counsellors.controller;


import com.counsellors.service.CounsellorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CounsellorController {

    @Autowired
     private CounsellorService counsellorService;


  

}
