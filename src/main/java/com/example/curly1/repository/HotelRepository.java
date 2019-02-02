package com.example.curly1.repository;

import com.example.curly1.model.HotelModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Long> {
    Page<HotelModel> findByCityInfoModelCityId(Long cityId,Pageable pageable);
}
