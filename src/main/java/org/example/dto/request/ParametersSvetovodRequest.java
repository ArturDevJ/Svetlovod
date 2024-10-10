package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametersSvetovodRequest {
    private String parametrsSvetlovod;
    private float diameter;
    private float reflectionCoefMirrorSurface;
    private float coefTransmissionDome;
    private float coefTransmissionDiffuser ;
    private float coefReserve;
}
