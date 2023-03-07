package app.controller;


import app.Dao.WeatherRepository;
import app.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import app.services.WeatherService;

import java.util.List;

@RestController
public class Api {

    @Autowired
    WeatherService ws;

    @Autowired
    private WeatherRepository weatherRepository;

    @GetMapping("api/a/{Weather}")
    public void getItem(@PathVariable String weather) {

    }

    @GetMapping("api/all")
    public List<Weather> getAll() {
        Weather wea = new Weather();
        System.out.println(ws);

        wea.setName("testest");
        ws.addWeatherToDatabase(wea);

        return ws.getAll();
    }



}
