package com.counsellors.repo;


import com.counsellors.entity.EnquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiryRepo extends JpaRepository<EnquiryEntity, Integer> {

     // select * from Enquiry where counsellor_id =: id;   // thi line aslo work same
       public List<EnquiryEntity> findByCounsellorCounsellorId(Integer counsellorId);

}
