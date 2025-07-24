package com.counsellors.repo;


import com.counsellors.entity.EnquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Enquiry extends JpaRepository<EnquiryEntity, Integer> {


}
