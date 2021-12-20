package com.example.weather_service.SpringControllers;

import com.example.weather_service.Entity.WeatherHistoryEntity;
import com.example.weather_service.Service.WeatherHistoryService;
import com.example.weather_service.Util.LoadWebSite;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Parser;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("weather")
public class WeatherHistoryController {

    private final WeatherHistoryService weatherHistoryService = new WeatherHistoryService();

    @GetMapping()
    public WeatherHistoryEntity get(){
        WeatherHistoryEntity current = weatherHistoryService.getWeatherHistoryEntityByCurrentDate();
        if(current == null){
            LoadWebSite site = new LoadWebSite("https://yandex.ru/");
            String htmlCode = site.getHtmlCode();
            String regex = "<div class='weather__temp'>(.+?)</div>";
            Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(htmlCode);
            WeatherHistoryEntity newRecord = new WeatherHistoryEntity();
            newRecord.setWeatherDate(new Date(new java.util.Date().getTime()));
            if(matcher.find()) {
                newRecord.setWeatherValue(matcher.group(1));
            }else {
                newRecord.setWeatherValue("");
            }
            weatherHistoryService.save(newRecord);
            return weatherHistoryService.getWeatherHistoryEntityByCurrentDate();
        }
        return weatherHistoryService.getWeatherHistoryEntityByCurrentDate();
    }




}
