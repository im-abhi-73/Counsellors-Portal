package com.counsellors.repo;

import com.counsellors.entity.CounsellorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Counsellor extends JpaRepository<CounsellorEntity, Integer> {
}
