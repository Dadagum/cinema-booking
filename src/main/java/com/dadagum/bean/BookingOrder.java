package com.dadagum.bean;

import java.util.Arrays;

public class BookingOrder {
    private int oid; // 订单id
    private int pid; // 电影时间选择id
    private int[] seat_number; // 选择的座位号，可以多选

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int[] getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(int[] seat_number) {
        this.seat_number = seat_number;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Override
    public String toString() {
        return "BookingOrder{" +
                "oid=" + oid +
                ", pid=" + pid +
                ", seat_number=" + Arrays.toString(seat_number) +
                '}';
    }
}
