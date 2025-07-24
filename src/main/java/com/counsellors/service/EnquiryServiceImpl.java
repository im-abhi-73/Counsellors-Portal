package com.counsellors.service;

import com.counsellors.binding.DashbordDTO;
import com.counsellors.binding.EnqFilterDTO;
import com.counsellors.binding.EnquiryDTO;
import com.counsellors.entity.CounsellorEntity;
import com.counsellors.entity.EnquiryEntity;
import com.counsellors.repo.CounsellorRepo;
import com.counsellors.repo.EnquiryRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnquiryServiceImpl implements  EnquiryService {

    @Autowired
    private EnquiryRepo enquiryRepo;
    @Autowired
    private CounsellorRepo counsellorRepo;


    @Override
    public DashbordDTO getDashbordInfo(Integer counsellorId) {

       List<EnquiryEntity> enqsList = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
       DashbordDTO dto = new DashbordDTO();

       dto.setTotalEnqCount(enqsList.size());

        int openCnt = enqsList.stream()
                                      .filter(enq -> enq.getStatus()
                                      .equals("OPEN"))
                                      .collect(Collectors
                                      .toList())
                                      .size();
    dto.setOpenEnqCount(openCnt);

     int entrollCnt = enqsList.stream()
                                      .filter(enq -> enq
                                      .getStatus()
                                      .equals("ENTROLL"))
                                      .collect(Collectors
                                      .toList())
                                      .size();

     dto.setEntrolledEnqCount(entrollCnt);

    int lostCnt =  enqsList.stream()
                                  .filter(enq -> enq
                                  .getStatus()
                                  .equals("LOST"))
                                  .collect(Collectors
                                  .toList())
                                  .size();

     dto.setLostEnqCount(lostCnt);
        return null;
    }

    @Override
    public boolean addEnquiry(EnquiryDTO enquiryDTO, Integer counsellorId) {

        EnquiryEntity entity = new EnquiryEntity();
        BeanUtils.copyProperties(enquiryDTO, entity);

        //setting Fk (counsellor_id) to enquiry object
       Optional<CounsellorEntity> byId = counsellorRepo.findById(counsellorId);
       byId.ifPresent(counsellorEntity -> {
           CounsellorEntity counsellor = byId.get();
           entity.setCounsellor(counsellor);
       });



        EnquiryEntity save = enquiryRepo.save(entity);

        return save.getEnquiryId() != null;
    }

    @Override
    public List<EnquiryDTO> getEnquiry(Integer counsellorDTO) {

        List<EnquiryDTO> enquiryDTO =  new ArrayList<>();

        List<EnquiryEntity> enqList = enquiryRepo.findByCounsellorCounsellorId(counsellorDTO);
        for (EnquiryEntity entity : enqList) {
            EnquiryDTO dto = new EnquiryDTO();
            BeanUtils.copyProperties(entity, dto);

            enquiryDTO.add(dto);
        }

        return enquiryDTO;
    }

    @Override
    public List<EnquiryDTO> getEnquiry(EnqFilterDTO filterDTO, Integer counsellorId) {

        EnquiryEntity entity = new EnquiryEntity();
        if (filterDTO.getClassMode() != null && filterDTO.getClassMode().equals("")) {
            entity.setClassMode(filterDTO.getClassMode());
        }

        if (filterDTO.getCourse() != null && filterDTO.getCourse().equals("")) {
            entity.setCourse(filterDTO.getCourse());
        }

        if (filterDTO.getStatus() != null && filterDTO.getStatus().equals("")) {
            entity.setStatus(filterDTO.getStatus());
        }

        CounsellorEntity counsellor = new CounsellorEntity();
        counsellor.setCounsellorId(counsellorId);
        entity.setCounsellor(counsellor);

        Example<EnquiryEntity> of = Example.of(entity);
        List<EnquiryEntity> enqList = enquiryRepo.findAll(of);


        List<EnquiryDTO> enquiryDTO =  new ArrayList<>();

        for (EnquiryEntity enq : enqList) {
            EnquiryDTO dto = new EnquiryDTO();
            BeanUtils.copyProperties(entity, dto);

            enquiryDTO.add(dto);
        }
        
        return List.of();
    }

    @Override
    public EnquiryDTO getEnquiryById(Integer enquiryId) {

        Optional<EnquiryEntity> byId = enquiryRepo.findById(enquiryId);
       if (byId.isPresent()) {

           EnquiryEntity entity = byId.get();
           EnquiryDTO dto = new EnquiryDTO();
           BeanUtils.copyProperties(entity, dto);
           return dto;

       }

        return null;
    }
}
