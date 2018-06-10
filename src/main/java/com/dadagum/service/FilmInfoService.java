package com.dadagum.service;

import com.dadagum.bean.Film;
import com.dadagum.bean.FilmPeriod;

import java.util.List;

public interface FilmInfoService {

    /**
     * 电影列表
     * @return 所有电影的信息
     */
    public List<Film> getFilmList();

    /**
     * @param fid 电影id
     * @return 此电影的场次信息
     */
    public List<FilmPeriod> getFilmPeriod(int fid);

    /**
     * 得到某一个电影的详细信息
     * @param fid 电影id
     * @return 某一个电影的详细信息
     */
    public Film getSingleFilm(int fid);

    /**
     * 判断某一个电影是否真实存在
     * @param fid 电影id
     * @return
     */
    public boolean isFilmExist(int fid);
}
