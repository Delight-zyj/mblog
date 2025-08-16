package com.mblog.service.impl;

import com.mblog.entry.Digital;
import com.mblog.entry.DigitalBrand;
import com.mblog.entry.DigitalType;
import com.mblog.mapper.DigitalMapper;
import com.mblog.service.DigitalService;
import list.DigitalList;
import list.DigitalQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;

@Service
public class DigitalServiceImpl implements DigitalService {

    @Autowired
    private DigitalMapper digitalMapper;
    @Autowired
    private DispatcherServlet dispatcherServlet;

    @Override
    public List<DigitalList> selectAll(DigitalQueryParam digitalQueryParam) {

        return digitalMapper.selectAll(digitalQueryParam);
    }

    @Override
    public DigitalList getById(Integer digitalId) {
        return digitalMapper.getById(digitalId);
    }

    @Override
    public List<DigitalType> getType() {
        return digitalMapper.getType();
    }

    @Override
    public List<DigitalBrand> getBrand(Integer typeid) {
        return digitalMapper.getBrand(typeid);
    }
}
