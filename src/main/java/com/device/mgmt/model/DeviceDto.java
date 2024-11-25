package com.device.mgmt.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DeviceDto {

    @NotEmpty(message = "Device name cannot be empty")
    private String name;

    @NotEmpty(message = "Device brand cannot be empty")
    private String brand;
}
