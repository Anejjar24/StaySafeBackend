package ma.ensaj.StaySafe.dto;

import lombok.Data;

@Data
public class LocationDTO {
    private Double latitude;
    private Double longitude;
    private Boolean isEmergency;
    private String message;
}