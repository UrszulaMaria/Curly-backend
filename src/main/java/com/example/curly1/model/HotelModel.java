package com.example.curly1.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotel")
public class HotelModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "hotel_street")
    private String hotelStreet;

    @Column(name = "hotel_street_number")
    private int hotelStreetNumber;

    @Column(name = "hotel_cost")
    private int hotelCost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_info_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("city_info_id")
    private CityInfoModel cityInfoModel;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "hotels")
    private Set<UserModel> users = new HashSet<>();


    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelStreet() {
        return hotelStreet;
    }

    public void setHotelStreet(String hotelStreet) {
        this.hotelStreet = hotelStreet;
    }

    public int getHotelStreetNumber() {
        return hotelStreetNumber;
    }

    public void setHotelStreetNumber(int hotelStreetNumber) {
        this.hotelStreetNumber = hotelStreetNumber;
    }

    public int getHotelCost() {
        return hotelCost;
    }

    public void setHotelCost(int hotelCost) {
        this.hotelCost = hotelCost;
    }

    public CityInfoModel getCityInfoModel() {
        return cityInfoModel;
    }

    public void setCityInfoModel(CityInfoModel cityInfoModel) {
        this.cityInfoModel = cityInfoModel;
    }
}
