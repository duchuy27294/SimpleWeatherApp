package com.huypham.model;

public class Coordinate {
    private double lat;
    private double lon;
    
    public Coordinate(){
        lat = 0.0;
        lon = 0.0;
    }

    public Coordinate(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat(){
        return this.lat;
    }

    public double getLon(){
        return this.lon;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public void setLon(double lon){
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "{" +
            " lat='" + getLat() + "'" +
            ", lon='" + getLon() + "'" +
            "}";
    }

}
