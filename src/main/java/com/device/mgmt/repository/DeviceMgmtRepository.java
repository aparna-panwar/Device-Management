package com.device.mgmt.repository;

import com.device.mgmt.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceMgmtRepository extends JpaRepository<Device, Long> {

    List<Device> findByBrand(String brand);
}
