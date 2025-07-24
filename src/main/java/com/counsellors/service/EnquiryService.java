package com.counsellors.service;

import com.counsellors.binding.DashbordDTO;
import com.counsellors.binding.EnqFilterDTO;
import com.counsellors.binding.EnquiryDTO;

import java.util.List;

public interface EnquiryService {




    public DashbordDTO getDashbordInfo(Integer counsellorDTO);

    public boolean addEnquiry(EnquiryDTO enquiryDTO, Integer counsellorDTO);

    public List<EnquiryDTO> getEnquiry(Integer counsellorDTO);

    public List<EnquiryDTO> getEnquiry(EnqFilterDTO filterDTO, Integer counsellorDTO);

    public EnquiryDTO getEnquiryById(Integer enquiryId);

}
