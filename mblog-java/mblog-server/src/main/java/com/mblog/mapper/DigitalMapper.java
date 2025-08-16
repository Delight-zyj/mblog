package com.mblog.mapper;

import com.mblog.entry.Digital;
import com.mblog.entry.DigitalBrand;
import com.mblog.entry.DigitalType;
import list.DigitalList;
import list.DigitalQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DigitalMapper {

    List<DigitalList> selectAll(DigitalQueryParam digitalQueryParam);

    //    @Select("select digital.*,digital_type.typename,digital_brand.brandname from digital a,digital_type b,digital_brand c where digitalId = #{digitalId}")
    DigitalList getById(Integer digitalId);


    List<DigitalType> getType();

    @Select("select brandid,brandname from digital_brand where typeid = #{typeid}")
    List<DigitalBrand> getBrand(Integer typeid);
}
