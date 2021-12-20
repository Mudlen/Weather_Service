package com.example.weather_service.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "weather_history", schema = "weather_service")
public class WeatherHistoryEntity {
    private Date weatherDate;
    private String weatherValue;

    @Id
    @Column(name = "weather_date", nullable = false)
    public Date getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(Date weatherDate) {
        this.weatherDate = weatherDate;
    }

    @Basic
    @Column(name = "weather_value", nullable = false, length = 10)
    public String getWeatherValue() {
        return weatherValue;
    }

    public void setWeatherValue(String weatherValue) {
        this.weatherValue = weatherValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherHistoryEntity that = (WeatherHistoryEntity) o;
        return Objects.equals(weatherDate, that.weatherDate) && Objects.equals(weatherValue, that.weatherValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weatherDate, weatherValue);
    }

    @Override
    public String toString() {
        return "WeatherHistoryEntity{" +
                "weatherDate=" + weatherDate +
                ", weatherValue='" + weatherValue + '\'' +
                '}';
    }
}
