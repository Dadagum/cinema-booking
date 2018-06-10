package com.dadagum.controller;

import com.dadagum.bean.BookingOrder;
import com.dadagum.dto.JsonResult;
import com.dadagum.bean.Film;
import com.dadagum.bean.FilmPeriod;
import com.dadagum.exception.FilmNotFoundException;
import com.dadagum.exception.FilmPeriodNotFoundException;
import com.dadagum.exception.HasSoldSeatException;
import com.dadagum.service.BookingOrderService;
import com.dadagum.service.FilmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmInfoService filmInfoService;

    @Autowired
    private BookingOrderService bookingOrderService;

    /**
     * @return 所有电影的详细数据
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult<?> getFilmList(){
        List<Film> list = filmInfoService.getFilmList();
        return new JsonResult<>(list, "成功", true);
    }

    /**
     * @param fid 电影id
     * @return 某一个电影的详细信息，这个电影可以选择的电影场次
     */
    @RequestMapping(value = "/{fid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult<?> chooseFilmPeriod(@PathVariable int fid){
        Film film;
//        try{
//            film = filmInfoService.getSingleFilm(fid);
//        } catch (FilmNotFoundException e){
//            return new JsonResult<>(null, e.getMessage(), false);
//        }
        List<FilmPeriod> periods = filmInfoService.getFilmPeriod(fid);
        Map<String, Object> result = new HashMap<>();
//        result.put("film", film);
        result.put("period", periods);
        return new JsonResult<>(result, "成功", true);
    }

    /**
     * @param bookingOrder 订单(pid, seatNumber)
     * @return 此电影的详细信息(名称，时间场次，座位号)
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult<?> generateBookingOrder(@RequestBody BookingOrder bookingOrder){
        System.out.println("booking order :" + bookingOrder);
        // 生成订单，记录在订单和座位信息
        if (bookingOrder.getSeat_number() == null || bookingOrder.getSeat_number().length == 0) return new JsonResult<>(null, "请填写座位号", false);
        try {
            bookingOrderService.generateBookingOrder(bookingOrder);
        }catch (HasSoldSeatException e){
            return new JsonResult<>(null, e.getMessage(), false);
        } catch (Exception e) {
            return new JsonResult<>(null, "生成订单失败", false);
        }
        Map<String, Object> result = bookingOrderService.getSpecificOrderInfo(bookingOrder.getOid());
        result.put("seatNumber", bookingOrder.getSeat_number());
        return new JsonResult<>(result, "生成订单成功", true);
    }

    /**
     * @return 所有订单信息:电影详细信息，时间段，座位信息
     */
    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult<?> getBookingOrderList(){
        return new JsonResult<>(bookingOrderService.getAllOrdersInfo(), "成功", true);
    }
}
