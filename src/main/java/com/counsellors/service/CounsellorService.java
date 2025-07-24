package com.counsellors.service;

import com.counsellors.binding.CounsellorDTO;

public interface CounsellorService {

    //for valid login return id,for invalid return 0.
    public CounsellorDTO login(CounsellorDTO counsellorDTO);

    //if mail are unique them true else false.
    public boolean uniqueEmailChacke(String email);

    //if registration success true else false.
    public boolean register(CounsellorDTO counsellorDTO);

}
