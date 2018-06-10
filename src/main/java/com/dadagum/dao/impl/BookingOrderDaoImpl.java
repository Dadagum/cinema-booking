package com.dadagum.dao.impl;

import com.dadagum.dao.BookingOrderDao;
import com.dadagum.dto.BookingOrderInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingOrderDaoImpl implements BookingOrderDao{

    static final String ORDER_TABLE = "order_";

    static final String ORDER_SEAT_TABLE = "order_seat";

    private static final String ADD_ORDER = "INSERT INTO " + ORDER_TABLE + " VALUES(null, ?)";

    private static final String ADD_ORDER_SEAT = "INSERT INTO " + ORDER_SEAT_TABLE + " VALUES(?, ?)";

    private static final String GET_ALL_OID = "SELECT oid FROM " + ORDER_TABLE;

    private static final String GET_BOOKING_ORDER_INFO = "SELECT * FROM " + ORDER_TABLE + " NATURAL JOIN " + FilmInfoDaoImpl.FILM_PERIOD_TABLE + " NATURAL JOIN " + FilmInfoDaoImpl.FILM_TABLE + " WHERE oid = ?";

    private static final String GET_ORDRE_SEAT_NUMBER = "SELECT num FROM " + ORDER_TABLE + " NATURAL JOIN " + ORDER_SEAT_TABLE + " WHERE oid = ?";

    private static final String HAS_SOLD_SEAT = "SELECT count(*) FROM " + ORDER_TABLE + " NATURAL JOIN " + ORDER_SEAT_TABLE + " WHERE pid = ? AND num=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addOrder(final int pid) {
        System.out.println("pid : " + pid);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, pid);
            return preparedStatement;
        }, keyHolder);
        System.out.println("return key : " + keyHolder.getKey().intValue());
        return keyHolder.getKey().intValue();
    }

    @Override
    public void addOrderSeat(int oid, int seatNumber) {
        jdbcTemplate.update(ADD_ORDER_SEAT, oid, seatNumber);
    }

    @Override
    public List<Integer> getAllOid() {
        List<Integer> list = new ArrayList<>();
        jdbcTemplate.query(GET_ALL_OID, resultSet -> {
            list.add(resultSet.getInt("oid"));
        });
        return list;
    }

    @Override
    public BookingOrderInfoDto getBookingOrderInfo(int oid) {
        BookingOrderInfoDto info = new BookingOrderInfoDto();
        jdbcTemplate.query(GET_BOOKING_ORDER_INFO, new Object[]{oid}, resultSet -> {
            info.setName(resultSet.getString("name"));
            info.setDirector(resultSet.getString("director"));
            info.setActors(resultSet.getString("actors"));
            info.setPrice(resultSet.getDouble("price"));
            info.setLast_len(resultSet.getDouble("last_len"));
            info.setType(resultSet.getString("type"));
            info.setIntroduction(resultSet.getString("introduction"));
            info.setStart_time(resultSet.getString("start_time"));
            info.setVideo_hall(resultSet.getInt("video_hall"));
        });
        return info;
    }

    @Override
    public List<Integer> getOrderSeatNumber(int oid) {
        List<Integer> seats = new ArrayList<>();
        jdbcTemplate.query(GET_ORDRE_SEAT_NUMBER, new Object[]{oid}, resultSet -> {
            seats.add(resultSet.getInt("num"));
        });
        return seats;
    }

    @Override
    public boolean hasSoldSeat(int pid, int seatNum) {
        return jdbcTemplate.queryForObject(HAS_SOLD_SEAT, new Object[]{pid, seatNum}, int.class) == 1;
    }
}
