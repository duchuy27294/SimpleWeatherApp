package com.huypham.model;

import java.util.Collections;
import java.util.List;
// import java.util.concurrent.SubmissionPublisher;
// import java.util.concurrent.Flow.Subscriber;

public class Location {
    private String name;
    private Integer cod;
    private Integer id;
    private String base;
    private Coordinate coord;
    private List<Weather> weather;
    // private SubmissionPublisher<Weather> weatherPublisher;
    private Main main;
    // private SubmissionPublisher<Main> mainPublisher;
    private Sys sys;


    public Location(String name, Integer cod, Integer id, String station, Coordinate coord, List<Weather> weather, Main main, Sys sys) {
        this.name = name;
        this.cod = cod;
        this.id = id;
        this.base = station;
        this.coord = coord;
        this.weather = weather;
        this.main = main;
        this.sys = sys;
        // this.weatherPublisher = new SubmissionPublisher<Weather>();
        // this.mainPublisher = new SubmissionPublisher<Main>();
    }

    // public void addWeatherSubscription(Subscriber<Weather> subscriber){
    //     weatherPublisher.subscribe(subscriber);
    // }

    // public void addMainSubscription(Subscriber<Main> subscriber){
    //     mainPublisher.subscribe(subscriber);
    // }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return this.cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBase() {
        return this.base;
    }

    public void setBase(String station) {
        this.base = station;
    }

    public Coordinate getCoord() {
        return this.coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return Collections.unmodifiableList(this.weather);
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
        // this.weatherPublisher.submit(this.weather.get(0));
    }


    public Main getMain() {
        return this.main;
    }

    public void setMain(Main main) {
        this.main = main;
        // this.mainPublisher.submit(this.main);
    }


    public Sys getSys() {
        return this.sys;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", cod='" + getCod() + "'" +
            ", id='" + getId() + "'" +
            ", station='" + getBase() + "'" +
            ", coord='" + getCoord().toString() + "'" +
            ", weather=" + getWeather().get(0).toString() + "'" +
            "}";
    }
    
}
