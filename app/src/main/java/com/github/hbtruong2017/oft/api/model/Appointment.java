package com.github.hbtruong2017.oft.api.model;

import java.io.Serializable;

public class Appointment implements Serializable {
    private String address;
    private String date;
    private String time;
    private String barberName;
    private int barberImage;
    private String serviceName;
    private String serviceDescription;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBarberName() {
        return barberName;
    }

    public void setBarberName(String barberName) {
        this.barberName = barberName;
    }

    public int getBarberImage() {
        return barberImage;
    }

    public void setBarberImage(int barberImage) {
        this.barberImage = barberImage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}
