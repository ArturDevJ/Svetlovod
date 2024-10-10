package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {
    private String name;
    private float sizeX;
    private float sizeY;
    private float heightUr;
    private int countSupport;
    private float heightRoom;
    private float widthRoom;
    private float reflectionCoef;
    private int designPointsX;
    private int designPointsY;
}