package com.device.mgmt.service;

import com.device.mgmt.exception.DeviceNotFoundException;
import com.device.mgmt.model.Device;
import com.device.mgmt.model.DeviceDto;
import com.device.mgmt.repository.DeviceMgmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceMgmtService {

    private final DeviceMgmtRepository deviceMgmtRepository;

    public Device getDeviceById(Long id) {
        return deviceMgmtRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("No device found for given ID."));
    }

    public List<Device> getDevices() {
        return deviceMgmtRepository.findAll();
    }


    public List<Device> getDevicesByBrand(String brand) {
        return deviceMgmtRepository.findByBrand(brand);
    }

    public void deleteDeviceById(Long id) {
        deviceMgmtRepository.deleteById(id);
    }


    public Device addDevice(DeviceDto deviceDto) {

        var device = new Device(deviceDto.getName(), deviceDto.getBrand());
        return deviceMgmtRepository.save(device);
    }

    public Boolean updateDevice(Long id, DeviceDto deviceDto) {
        return deviceMgmtRepository.findById(id)
                .map(device -> {
                    device.setName(deviceDto.getName());
                    device.setBrand(deviceDto.getBrand());
                    deviceMgmtRepository.save(device);
                    return true;
                }).orElse(false);
    }

    public Boolean patchDevice(Long id, DeviceDto deviceDto) {
        return deviceMgmtRepository.findById(id)
                .map(device -> {
                    if(StringUtils.hasText(deviceDto.getName())) {
                        device.setName(deviceDto.getName());
                    }
                    if(StringUtils.hasText(deviceDto.getBrand())) {
                        device.setBrand(deviceDto.getBrand());
                    }
                    deviceMgmtRepository.save(device);
                    return true;
                }).orElse(false);
    }

}
