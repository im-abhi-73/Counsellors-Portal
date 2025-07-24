package com.counsellors.repo;

import com.counsellors.entity.CounsellorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounsellorRepo extends JpaRepository<CounsellorEntity, Integer> {

    public CounsellorEntity findByEmailAndPassword(String email, String password);
    public CounsellorEntity findByEmail(String email);
}
