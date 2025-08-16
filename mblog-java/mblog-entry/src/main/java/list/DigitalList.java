package list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalList {

    private int digitalId;

    private String digitalname;

    private String digitalimg;

    private String digitalsoc;

    private LocalDate releaseTime;

    private String digitalprice;

    private String digitalscreen;

    private int digitalbattery;

    private String digitalcamera;

    private String brandname;

    private String typename;

    private String digitalDimensionsAndWeight;

    private String digitalcharge;

    private String digitalInternet;

    private String digitalscreencharacteristics;
}
