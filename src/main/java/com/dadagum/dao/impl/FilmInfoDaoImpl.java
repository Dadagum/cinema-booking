package com.dadagum.dao.impl;

import com.dadagum.bean.Film;
import com.dadagum.bean.FilmPeriod;
import com.dadagum.dao.BookingOrderDao;
import com.dadagum.dao.FilmInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmInfoDaoImpl implements FilmInfoDao{

    static final String FILM_TABLE = "film";

    static final String FILM_PERIOD_TABLE = "film_period";

    private static final String GET_FILM_LIST = "SELECT * FROM " + FILM_TABLE;

    private static final String GET_SINGEL_FIML = "SELECT * FROM " + FILM_TABLE + " WHERE fid = ?";

    private static final String GET_FILM_PERIOD = "SELECT * FROM " + FILM_PERIOD_TABLE + " WHERE fid = ?";

    private static final String GET_FILM_CNT = "SELECT count(*) FROM " + FILM_TABLE + " WHERE fid = ?";

    private static final String HAS_FILM_PERIOD = "SELECT count(*) FROM " + FILM_PERIOD_TABLE + " WHERE pid = ?";

    private static final String GET_CHOSEN_PERIOD = "SELECT start_time, video_hall FROM " + FILM_PERIOD_TABLE + " WHERE pid = ?";

    private static final String GET_FILM_BY_PID = "SELECT * FROM " + FILM_TABLE + " NATURAL JOIN " + FILM_PERIOD_TABLE + " WHERE pid = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Film> getFilmList() {
        List<Film> result = new ArrayList<>();
        jdbcTemplate.query(GET_FILM_LIST, resultSet -> {
            Film film = new Film();
            film.setFid(resultSet.getInt("fid"));
            film.setName(resultSet.getString("name"));
            film.setDirector(resultSet.getString("director"));
            film.setActors(resultSet.getString("actors"));
            film.setPrice(resultSet.getDouble("price"));
            film.setLast_len(resultSet.getDouble("last_len"));
            film.setType(resultSet.getString("type"));
            film.setIntroduction(resultSet.getString("introduction"));
            result.add(film);
        });
        return result;
    }

    @Override
    public List<FilmPeriod> getFilmPeriod(int fid) {
        List<FilmPeriod> result = new ArrayList<>();
        jdbcTemplate.query(GET_FILM_PERIOD, new Object[]{fid}, resultSet -> {
            FilmPeriod filmPeriod = new FilmPeriod();
            filmPeriod.setPid(resultSet.getInt("pid"));
            filmPeriod.setStart_time(resultSet.getString("start_time"));
            filmPeriod.setVideo_hall(resultSet.getInt("video_hall"));
            result.add(filmPeriod);
        });
        return result;
    }

    @Override
    public Film getSingleFilm(int fid) {
        Film film = new Film();
        jdbcTemplate.query(GET_SINGEL_FIML, new Object[]{fid}, resultSet -> {
            film.setFid(resultSet.getInt("fid"));
            film.setName(resultSet.getString("name"));
            film.setDirector(resultSet.getString("director"));
            film.setActors(resultSet.getString("actors"));
            film.setPrice(resultSet.getDouble("price"));
            film.setLast_len(resultSet.getDouble("last_len"));
            film.setType(resultSet.getString("type"));
            film.setIntroduction(resultSet.getString("introduction"));
        });
        return film;
    }

    public int getFilmCnt(int fid){
        return jdbcTemplate.queryForObject(GET_FILM_CNT, new Object[]{fid}, int.class);
    }

    @Override
    public boolean hasFilmPeriod(int pid) {
        return jdbcTemplate.queryForObject(HAS_FILM_PERIOD, new Object[]{pid}, int.class) > 0;
    }

    @Override
    public FilmPeriod getChosenPeriod(int pid) {
        FilmPeriod period = new FilmPeriod();
        jdbcTemplate.query(GET_CHOSEN_PERIOD, new Object[]{pid}, resultSet -> {
            period.setStart_time(resultSet.getString("start_time"));
            period.setVideo_hall(resultSet.getInt("video_hall"));
        });
        return period;
    }

    @Override
    public Film getSingleFilmByPid(int pid) {
        Film film = new Film();
        jdbcTemplate.query(GET_FILM_BY_PID, new Object[]{pid}, resultSet -> {
            film.setFid(resultSet.getInt("fid"));
            film.setName(resultSet.getString("name"));
            film.setDirector(resultSet.getString("director"));
            film.setActors(resultSet.getString("actors"));
            film.setPrice(resultSet.getDouble("price"));
            film.setLast_len(resultSet.getDouble("last_len"));
            film.setType(resultSet.getString("type"));
            film.setIntroduction(resultSet.getString("introduction"));
        });
        return film;
    }
}
