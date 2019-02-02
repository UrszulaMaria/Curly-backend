package com.example.curly1.controller;

import com.example.curly1.exception.ResourceNotFoundException;
import com.example.curly1.model.CityInfoModel;
import com.example.curly1.repository.CityInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityInfoController {

    @Autowired
    CityInfoRepository cityInfoRepository;

    @GetMapping("/showAllCity")
    public List<CityInfoModel> findAll(){
        return cityInfoRepository.findAll();
    }

    @GetMapping("/fingOne/{id}")
    public CityInfoModel findOneById(@PathVariable(value = "id") Long cityId){
        return cityInfoRepository.findById(cityId)
                .orElseThrow(()->new ResourceNotFoundException("City"+"id"+cityId));
    }

    @PostMapping("/addNewCity")
    public CityInfoModel addCity(@Valid @RequestBody CityInfoModel cityInfoModel){
        return cityInfoRepository.save(cityInfoModel);
    }

    @PutMapping("/updateCity/{id}")
    public CityInfoModel updateCity(@PathVariable(value = "id") Long cityId,
                                    @Valid @RequestBody CityInfoModel cityInfoModel){
        CityInfoModel newCity =cityInfoRepository.findById(cityId)
                .orElseThrow(()->new ResourceNotFoundException("City"+"id"+cityId));

        newCity.setCityName(cityInfoModel.getCityName());
        newCity.setCountryName(cityInfoModel.getCountryName());

        return cityInfoRepository.save(newCity);
    }

    public ResponseEntity<?> deleteCity(@PathVariable(value = "id")Long cityId){
        CityInfoModel cityInfoModel=cityInfoRepository.findById(cityId)
                .orElseThrow(()->new ResourceNotFoundException("City"+"id"+cityId));

        cityInfoRepository.delete(cityInfoModel);

        return ResponseEntity.ok().body("Succes!");
    }

}
