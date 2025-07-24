package com.counsellors.service;

import com.counsellors.binding.CounsellorDTO;

public class CounsellorServiceImpl implements CounsellorService {
    @Override
    public CounsellorDTO login(CounsellorDTO counsellorDTO) {
        return null;
    }

    @Override
    public boolean uniqueEmailChacke(String email) {
        return false;
    }

    @Override
    public boolean register(CounsellorDTO counsellorDTO) {
        return false;
    }
}
