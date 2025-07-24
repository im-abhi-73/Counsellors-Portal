package com.counsellors.service;

import com.counsellors.binding.CounsellorDTO;
import com.counsellors.entity.CounsellorEntity;
import com.counsellors.repo.CounsellorRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounsellorServiceImpl implements CounsellorService {



    @Autowired
   private CounsellorRepo counsellorRepo;


    @Override
    public CounsellorDTO login(CounsellorDTO counsellorDTO) {

        CounsellorEntity entity = counsellorRepo.findByEmailAndPassword(counsellorDTO.getEmail(), counsellorDTO.getPassword());

        if (entity != null)
        {

            //copy enttity object data into dto and return dto obj  // this is good for big data
            CounsellorDTO dto = new CounsellorDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;

            // this is not good for big data
         /*   CounsellorDTO dto = new CounsellorDTO();
            dto.setEmail(entity.getEmail());
            dto.setPassword(entity.getPassword());  */
        }

        return null;
    }

    @Override
    public boolean uniqueEmailChacke(String email) {

        CounsellorEntity  entity = counsellorRepo.findByEmail(email);
        return entity == null;
    }

    @Override
    public boolean register(CounsellorDTO counsellorDTO) {

        CounsellorEntity entity = new CounsellorEntity();
        BeanUtils.copyProperties(counsellorDTO, entity);
        CounsellorEntity save = counsellorRepo.save(entity);

        return null != save.getCounsellorId();
    }
}
