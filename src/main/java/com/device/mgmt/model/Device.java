package com.device.mgmt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity(name = "device")
public class Device {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String brand;
        private Timestamp creationTime;

        public Device(String name, String brand) {
                this.name = name;
                this.brand = brand;
                this.creationTime = new Timestamp(System.currentTimeMillis());
        }
}
