package com.example.weather_service.Service;

import com.example.weather_service.DAO.WeatherHistoryDAO;
import com.example.weather_service.Entity.WeatherHistoryEntity;

import java.util.Date;

public class WeatherHistoryService {
    private final WeatherHistoryDAO weatherHistoryDAO = new WeatherHistoryDAO();

    public void save(WeatherHistoryEntity weatherHistoryEntity){
        weatherHistoryDAO.save(weatherHistoryEntity);
    }
    public void delete(WeatherHistoryEntity weatherHistoryEntity){
        weatherHistoryDAO.delete(weatherHistoryEntity);
    }
    public void update(WeatherHistoryEntity weatherHistoryEntity){
        weatherHistoryDAO.update(weatherHistoryEntity);
    }
    public WeatherHistoryEntity getWeatherHistoryEntityByCurrentDate(){
        Date date = new Date();
        long currentTime = date.getTime();
        return weatherHistoryDAO.getWeatherByDate(new java.sql.Date(currentTime));
    }
}
