package com.example.demo.util;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;


public class DeviceUtils {
    public static String getDevice(Device device){
            if (device.isMobile()) {
                System.out.println("========请求来源设备是手机！========");
                return "phone";
            } else if (device.isTablet()) {
                System.out.println("========请求来源设备是平板！========");
                return "tablet";
            } else if(device.isNormal()){
                System.out.println("========请求来源设备是PC！========");
                return "PC";
            }else {
                System.out.println("========请求来源设备是其它！========");
                return "other";
            }
    }
}
