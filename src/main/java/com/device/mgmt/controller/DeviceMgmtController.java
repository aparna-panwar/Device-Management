package com.device.mgmt.controller;

import com.device.mgmt.model.Device;
import com.device.mgmt.model.DeviceDto;
import com.device.mgmt.service.DeviceMgmtService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeviceMgmtController {

    private final DeviceMgmtService deviceMgmtService;

    //Get device by identifier
    @GetMapping("/devices/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable @Min(1) @NotNull Long id) {

        return ResponseEntity.ok(deviceMgmtService.getDeviceById(id));
    }

    //List all devices
    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getDevices(@RequestParam(required = false) String brand) {

        if(StringUtils.hasLength(brand)) {
            return ResponseEntity.ok(deviceMgmtService.getDevicesByBrand(brand));
        }
        return ResponseEntity.ok(deviceMgmtService.getDevices());
    }

    //Delete a device
    @DeleteMapping("/devices/{id}")
    public ResponseEntity<Void> deleteDeviceById(@PathVariable @Min(1) @NotNull Long id) {
        deviceMgmtService.deleteDeviceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Add device
    @PostMapping("/devices")
    public ResponseEntity<Device> addDevice(@Valid @RequestBody DeviceDto deviceDto) {

        return new ResponseEntity<>(deviceMgmtService.addDevice(deviceDto), HttpStatus.CREATED);
    }

    //Update device (full)
    @PutMapping("/devices/{id}")
    public ResponseEntity<String> updateDevice(
            @PathVariable @Min(1) @NotNull Long id,
            @RequestBody @Valid DeviceDto deviceDto) {
        boolean isUpdated = deviceMgmtService.updateDevice(id, deviceDto);
        if (isUpdated) {
            return ResponseEntity.ok("Device updated successfully.");
        } else {
            return ResponseEntity.status(404).body("Device not found.");
        }
    }

    //Update device (partial)
    @PatchMapping("/devices/{id}")
    public ResponseEntity<String> updateDevicePartial(
            @PathVariable @Min(1) @NotNull Long id,
            @RequestBody DeviceDto deviceDto) {
        boolean isUpdated = deviceMgmtService.patchDevice(id, deviceDto);
        if (isUpdated) {
            return ResponseEntity.ok("Device partially updated.");
        } else {
            return ResponseEntity.status(404).body("Device not found.");
        }
    }
}
