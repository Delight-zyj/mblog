package com.mblog.mapper;

import com.mblog.entry.Digital;
import list.DigitalQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DigitalMapper {

    List<Digital> selectAll(DigitalQueryParam digitalQueryParam);
}
