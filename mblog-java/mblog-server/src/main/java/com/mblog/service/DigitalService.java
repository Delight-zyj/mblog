package com.mblog.service;

import com.mblog.entry.Digital;
import list.DigitalQueryParam;

import java.util.List;

public interface DigitalService {
    List<Digital> selectAll(DigitalQueryParam digitalQueryParam);
}
