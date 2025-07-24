package com.counsellors.service;

import com.counsellors.binding.DashbordDTO;
import com.counsellors.binding.EnqFilterDTO;
import com.counsellors.binding.EnquiryDTO;

import java.util.List;

public class EnquiryServiceImpl implements  EnquiryService {
private DashbordDTO dashbordDTO;

    @Override
    public DashbordDTO getDashbordInfo(Integer counsellorDTO) {
        return null;
    }

    @Override
    public boolean addEnquiry(EnquiryDTO enquiryDTO, Integer counsellorDTO) {
        return false;
    }

    @Override
    public List<EnquiryDTO> getEnquiry(Integer counsellorDTO) {
        return List.of();
    }

    @Override
    public List<EnquiryDTO> getEnquiry(EnqFilterDTO filterDTO, Integer counsellorDTO) {
        return List.of();
    }

    @Override
    public EnquiryDTO getEnquiryById(Integer enquiryId) {
        return null;
    }
}
