package com.mblog.service.impl;

import com.mblog.entry.Digital;
import com.mblog.mapper.DigitalMapper;
import com.mblog.service.DigitalService;
import list.DigitalQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigitalServiceImpl implements DigitalService {

    @Autowired
    private DigitalMapper digitalMapper;
    @Override
    public List<Digital> selectAll(DigitalQueryParam digitalQueryParam) {

        return digitalMapper.selectAll(digitalQueryParam);
    }
}
