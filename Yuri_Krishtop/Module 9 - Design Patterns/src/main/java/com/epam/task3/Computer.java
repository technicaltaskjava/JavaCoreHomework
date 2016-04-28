package com.epam.task3;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class Computer {

    private String cpu;
    private String motherboard;
    private String ram;
    private String hdd;
    private String video;

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", motherboard='" + motherboard + '\'' +
                ", ram='" + ram + '\'' +
                ", hdd='" + hdd + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
