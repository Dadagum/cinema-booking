package com.dadagum.service;

import com.dadagum.bean.BookingOrder;

import java.util.List;
import java.util.Map;

public interface BookingOrderService {

    /**
     * 生成一个订单
     * @param bookingOrder
     */
    public void generateBookingOrder(BookingOrder bookingOrder);


    /**
     * 得到所有预定的订单的详细信息(电影的名称，时间段，对应的座位)
     * @return
     */
    public List<Map<String, Object>> getAllOrdersInfo();

    /**
     * 得到一个预定订单的详细信息(电影的名称，时间段，除了对应的座位，因为在controller中已经有记录，虽然这样子写不好)
     * @param oid 订单id
     * @return
     */
    public Map<String, Object> getSpecificOrderInfo(int oid);
}
