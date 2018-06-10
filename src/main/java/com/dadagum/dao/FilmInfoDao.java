package com.dadagum.dao;

import com.dadagum.bean.Film;
import com.dadagum.bean.FilmPeriod;

import java.util.List;

public interface FilmInfoDao {

    public List<Film> getFilmList();

    public List<FilmPeriod> getFilmPeriod(int fid);

    public Film getSingleFilm(int fid);

    public int getFilmCnt(int fid);

    public boolean hasFilmPeriod(int pid);

    public FilmPeriod getChosenPeriod(int pid);

    public Film getSingleFilmByPid(int pid);

}
