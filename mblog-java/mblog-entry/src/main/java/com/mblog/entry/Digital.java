package com.mblog.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Digital {

    private int digitalId;

    private String digitalname;

    private String digitalrand;

    private String digitalimg;

    private String digitalsoc;

    private LocalDate releaseTime;

    private double digitalprice;

    private String digitalscreen;

    private int digitalbattery;

    private String digitalcamera;

    private int type;
}
