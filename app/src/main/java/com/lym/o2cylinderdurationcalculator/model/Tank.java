package com.lym.o2cylinderdurationcalculator.model;

public class Tank {
    String tank_type;
    float tank_value;

    public String getTank_type() {
        return tank_type;
    }

    public void setTank_type(String tank_type) {
        this.tank_type = tank_type;
    }

    public Tank(String tank_type, float tank_value) {
        this.tank_type = tank_type;
        this.tank_value = tank_value;
    }

    public float getTank_value() {
        return tank_value;
    }

    public void setTank_value(float tank_value) {
        this.tank_value = tank_value;
    }
}
