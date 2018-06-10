package com.dadagum.dao;

import com.dadagum.dto.BookingOrderInfoDto;

import java.util.List;

public interface BookingOrderDao {

    /**
     * 添加订单
     * @param pid
     */
    public int addOrder(int pid);

    /**
     * 记录某一个订单的座位号
     * @param oid
     * @param seatNumber
     */
    public void addOrderSeat(int oid, int seatNumber);

    /**
     * 得到所有的订单id
     * @return
     */
    public List<Integer> getAllOid();

    /**
     * 根据订单id获取此订单的详细信息(选择的电影信息和选择的时间段)
     * @param oid 订单id
     * @return 此订单的详细信息(选择的电影信息和选择的时间段)
     */
    public BookingOrderInfoDto getBookingOrderInfo(int oid);

    /**
     * 根据订单id获取此订单选择的座位信息
     * @param oid 订单id
     * @return 此订单选择的座位信息
     */
    public List<Integer> getOrderSeatNumber(int oid);

    /**
     * 判断某一个电影的时间段的某一个座位是否已经卖出
     * @param pid 某一个电影的时间段
     * @param seatNum 座位号
     * @return
     */
    public boolean hasSoldSeat(int pid, int seatNum);
}
