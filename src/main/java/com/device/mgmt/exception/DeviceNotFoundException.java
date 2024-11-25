package com.device.mgmt.exception;

public class DeviceNotFoundException extends RuntimeException{

    public DeviceNotFoundException(String msg) {
        super(msg);
    }
}
