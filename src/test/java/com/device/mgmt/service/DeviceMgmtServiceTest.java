package com.device.mgmt.service;

import com.device.mgmt.exception.DeviceNotFoundException;
import com.device.mgmt.model.Device;
import com.device.mgmt.model.DeviceDto;
import com.device.mgmt.repository.DeviceMgmtRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DeviceMgmtServiceTest {

    @Mock
    private DeviceMgmtRepository deviceMgmtRepository;

    @InjectMocks
    private DeviceMgmtService deviceMgmtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDeviceByIdSuccessScenario() {
        var existingDevice = new Device("Device Name", "Device Brand");
        existingDevice.setId(1L);

        when(deviceMgmtRepository.findById(1L)).thenReturn(Optional.of(existingDevice));

        var result = deviceMgmtService.getDeviceById(1L);

        // Assert
        assertNotNull(result);

        assertEquals(1L, result.getId());
        assertEquals("Device Name", result.getName());
        assertEquals("Device Brand", result.getBrand());
    }

    @Test
    void getDeviceByIdNotFoundScenario() {
        when(deviceMgmtRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> deviceMgmtService.getDeviceById(1L));
    }

    @Test
    void getDevicesSuccessScenario() {
        var deviceList = new ArrayList<Device>();

        var device1 = new Device("Device Name 101", "Device Brand 101");
        device1.setId(1L);

        var device2 = new Device("Device Name 102", "Device Brand 101");
        device2.setId(2L);

        deviceList.add(device1);
        deviceList.add(device2);

        when(deviceMgmtRepository.findAll()).thenReturn(deviceList);

        var result = deviceMgmtService.getDevices();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getDevicesNotFoundScenario() {
        when(deviceMgmtRepository.findAll()).thenReturn(new ArrayList<>());

        var result = deviceMgmtService.getDevices();

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void deleteDeviceByIdSuccessScenario() {
        doNothing().when(deviceMgmtRepository).deleteById(1L);
        deviceMgmtService.deleteDeviceById(1L);
        Mockito.verify(deviceMgmtRepository,times(1)).deleteById(1L);
    }

    @Test
    void addDeviceSuccessScenario() {
        // Arrange
        var deviceDto = new DeviceDto();
        deviceDto.setName("Name 101");
        deviceDto.setBrand("Brand 101");

        var device = new Device("Name 101" , "Brand 101");
        device.setId(1L);

        when(deviceMgmtRepository.save(any(Device.class))).thenReturn(device);

        // Act
       Device savedDevice = deviceMgmtService.addDevice(deviceDto);

        // Assert
        verify(deviceMgmtRepository, times(1)).save(any(Device.class));

        assertEquals(1l, savedDevice.getId());
        assertEquals("Name 101", savedDevice.getName());
        assertEquals("Brand 101", savedDevice.getBrand());
    }

    @Test
    void updateDeviceSuccessScenario() {
        // Arrange
        Long deviceId = 1L;
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setName("New Name");
        deviceDto.setBrand("New Brand");

        Device existingDevice = new Device("Old Name", "Old Brand");
        existingDevice.setId(deviceId);

        when(deviceMgmtRepository.findById(deviceId)).thenReturn(Optional.of(existingDevice));
        when(deviceMgmtRepository.save(any(Device.class))).thenReturn(existingDevice);

        // Act
        boolean result = deviceMgmtService.updateDevice(deviceId, deviceDto);

        // Assert
        assertTrue(result);
        verify(deviceMgmtRepository, times(1)).findById(deviceId);
        verify(deviceMgmtRepository, times(1)).save(existingDevice);

        assertEquals("New Name", existingDevice.getName());
        assertEquals("New Brand", existingDevice.getBrand());
    }

    @Test
    void updateDeviceNotFoundScenario() {
        // Arrange
        Long deviceId = 1L;
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setName("New Name");
        deviceDto.setBrand("New Brand");

        when(deviceMgmtRepository.findById(deviceId)).thenReturn(Optional.empty());

        // Act
        boolean result = deviceMgmtService.updateDevice(deviceId, deviceDto);

        // Assert
        assertFalse(result);
        verify(deviceMgmtRepository, times(1)).findById(deviceId);
        verify(deviceMgmtRepository, times(0)).save(any(Device.class));
    }

    @Test
    void patchDeviceSuccessScenario() {
        // Arrange
        Long deviceId = 1L;
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setBrand("New Brand");

        Device existingDevice = new Device("Old Name", "Old Brand");
        existingDevice.setId(deviceId);

        when(deviceMgmtRepository.findById(deviceId)).thenReturn(Optional.of(existingDevice));
        when(deviceMgmtRepository.save(any(Device.class))).thenReturn(existingDevice);

        // Act
        boolean result = deviceMgmtService.patchDevice(deviceId, deviceDto);

        // Assert
        assertTrue(result);
        verify(deviceMgmtRepository, times(1)).findById(deviceId);
        verify(deviceMgmtRepository, times(1)).save(existingDevice);

        assertEquals("Old Name", existingDevice.getName());
        assertEquals("New Brand", existingDevice.getBrand());
    }

    @Test
    void patchDeviceNotFoundScenario() {
        // Arrange
        Long deviceId = 1L;
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setName("New Name");

        when(deviceMgmtRepository.findById(deviceId)).thenReturn(Optional.empty());

        // Act
        boolean result = deviceMgmtService.patchDevice(deviceId, deviceDto);

        // Assert
        assertFalse(result);
        verify(deviceMgmtRepository, times(1)).findById(deviceId);
        verify(deviceMgmtRepository, times(0)).save(any(Device.class));
    }
}
