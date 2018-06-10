package com.dadagum.service.impl;

import com.dadagum.bean.Film;
import com.dadagum.bean.FilmPeriod;
import com.dadagum.dao.FilmInfoDao;
import com.dadagum.exception.FilmNotFoundException;
import com.dadagum.service.FilmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmInfoServiceImpl implements FilmInfoService {

    @Autowired
    private FilmInfoDao filmInfoDao;

    @Override
    public List<Film> getFilmList() {
        return filmInfoDao.getFilmList();
    }

    @Override
    public List<FilmPeriod> getFilmPeriod(int fid) {
        return filmInfoDao.getFilmPeriod(fid);
    }


    @Override
    public Film getSingleFilm(int fid) {
        // check if film exists
        if (!isFilmExist(fid)) throw new FilmNotFoundException("传来无效的电影id");
        return filmInfoDao.getSingleFilm(fid);
    }

    @Override
    public boolean isFilmExist(int fid) {
        return filmInfoDao.getFilmCnt(fid) == 1;
    }
}
