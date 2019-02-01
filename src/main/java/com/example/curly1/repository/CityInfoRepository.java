package com.example.curly1.repository;

import com.example.curly1.model.CityInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityInfoRepository extends JpaRepository<CityInfoModel, Long> {
}
