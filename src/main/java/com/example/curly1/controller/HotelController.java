package com.example.curly1.controller;

import com.example.curly1.exception.ResourceNotFoundException;
import com.example.curly1.model.HotelModel;
import com.example.curly1.repository.CityInfoRepository;
import com.example.curly1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    CityInfoRepository cityInfoRepository;

    @GetMapping("/getHotel/{id}/hotels")
    public Page<HotelModel> getAllHotels(@PathVariable(value = "id") Long cityId, Pageable pageable) {
        return hotelRepository.findByCityInfoModelCityId(cityId, pageable);
    }

    @PostMapping("/addNewHotel/{id}")
    public HotelModel addNewHotel(@PathVariable(value = "id") Long cityId,
                                  @RequestBody HotelModel hotelModel) {
        return cityInfoRepository.findById(cityId).map(city -> {
            hotelModel.setCityInfoModel(city);
            return hotelRepository.save(hotelModel);
        }).orElseThrow(() -> new ResourceNotFoundException("City" + "id" + cityId));
    }

    @PutMapping("/updateHotel/{cityId}/hotels/{hotelId}")
    public HotelModel updateHotel(@PathVariable(value = "cityId") Long cityId,
                                  @PathVariable(value = "hotelId") Long hotelId,
                                  @Valid @RequestBody HotelModel hotelModel) {

        if (!cityInfoRepository.existsById(cityId)) {
            throw new ResourceNotFoundException("City" + "id" + cityId);
        }

        return hotelRepository.findById(hotelId).map(hotel -> {
            hotel.setHotelStreet(hotelModel.getHotelStreet());
            hotel.setHotelCost(hotelModel.getHotelCost());
            hotel.setHotelStreetNumber(hotelModel.getHotelStreetNumber());
            return hotelRepository.save(hotel);
        }).orElseThrow(() -> new ResourceNotFoundException("Hotel" + "id" + hotelId));
    }

    @DeleteMapping("/deleteHotel/{cityId}/hotels/{hotelId}")
    public ResponseEntity<?> deleteHotel(@PathVariable(value = "cityId") Long cityId,
                                         @PathVariable(value = "hotelId") Long hotelId) {

        if (!cityInfoRepository.existsById(cityId)) {
            throw new ResourceNotFoundException("City" + "id" + cityId);
        }
        return hotelRepository.findById(hotelId).map(hotel -> {
            hotelRepository.delete(hotel);
            return ResponseEntity.ok().body("Succes!");
        }).orElseThrow(() -> new ResourceNotFoundException("Hotel" + "id" + hotelId));
    }
}
