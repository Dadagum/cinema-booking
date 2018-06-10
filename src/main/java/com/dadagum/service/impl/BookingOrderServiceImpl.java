package com.dadagum.service.impl;

import com.dadagum.bean.BookingOrder;
import com.dadagum.dao.BookingOrderDao;
import com.dadagum.dao.FilmInfoDao;
import com.dadagum.dto.BookingOrderInfoDto;
import com.dadagum.exception.FilmPeriodNotFoundException;
import com.dadagum.exception.HasSoldSeatException;
import com.dadagum.service.BookingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookingOrderServiceImpl implements BookingOrderService {

    @Autowired
    private BookingOrderDao bookingOrderDao;

    @Autowired
    private FilmInfoDao filmInfoDao;

    @Override
    @Transactional
    public void generateBookingOrder(BookingOrder bookingOrder) {
        // 查找此电影时间段是否存在
        if (!filmInfoDao.hasFilmPeriod(bookingOrder.getPid())) throw new FilmPeriodNotFoundException("电影不存在!");
        // 电影存在，记录在order表中，返回oid
        int oid = bookingOrderDao.addOrder(bookingOrder.getPid());
        bookingOrder.setOid(oid);
        // 记录座位
        int[] seatNumber = bookingOrder.getSeat_number();
        for (int i = 0; i < seatNumber.length; i++){
            // 检查座位是否已经被卖出
            if (bookingOrderDao.hasSoldSeat(bookingOrder.getPid(), seatNumber[i])) throw new HasSoldSeatException("该座位已经出售了！");
            // TODO
            bookingOrderDao.addOrderSeat(oid, seatNumber[i]);
        }
    }

    @Override
    public List<Map<String, Object>> getAllOrdersInfo() {
        List<Map<String, Object>> result = new ArrayList<>();
        // 得到所有的oid
        List<Integer> oids = bookingOrderDao.getAllOid();
        for(int oid : oids){
            // 得到此订单的电影信息
            BookingOrderInfoDto infoDto = bookingOrderDao.getBookingOrderInfo(oid);
            // 得到此订单的位置信息
            List<Integer> seatNumber = bookingOrderDao.getOrderSeatNumber(oid);
            // 加入到结果集中
            Map<String, Object> orderInfo = new HashMap<>();
            orderInfo.put("FilmAndPeriod", infoDto);
            orderInfo.put("seatNumber", seatNumber);
            result.add(orderInfo);
        }
        return result;
    }

    @Override
    public Map<String, Object> getSpecificOrderInfo(int oid) {
        Map<String, Object> orderInfo = new HashMap<>();
        orderInfo.put("FilmAndPeriod", bookingOrderDao.getBookingOrderInfo(oid));
        return orderInfo;
    }


}
