package com.mblog.service;

import com.mblog.entry.Digital;
import com.mblog.entry.DigitalBrand;
import com.mblog.entry.DigitalType;
import list.DigitalList;
import list.DigitalQueryParam;

import java.util.List;

public interface DigitalService {
    List<DigitalList> selectAll(DigitalQueryParam digitalQueryParam);

    DigitalList getById(Integer digitalId);

    List<DigitalType> getType();

    List<DigitalBrand> getBrand(Integer typeid);
}
