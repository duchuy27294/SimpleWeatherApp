package com.huypham.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.Flow.Subscriber;

import org.json.JSONObject;


import com.google.gson.Gson;

public class WeatherApp {
    private Location location;
    private static final String FIND_LOCATION_URL = "https://api.openweathermap.org/data/2.5/weather?";
    private final String FILENAME = "weather.json";
    
    private Integer zip;
    private SubmissionPublisher<Location> locationPublisher;
    private HttpClient client;
    private URI uri;
    private HttpRequest getRequest;
    private HttpResponse<String> getResponse;
    private Writer output;
    private final File file;
    private JSONObject jsonObject;
    private final Gson gson;
    private SubmissionPublisher<Weather> weatherPublisher;
    private SubmissionPublisher<Main> mainPublisher;

    public WeatherApp() throws URISyntaxException, IOException {
        this.location = null;
        this.locationPublisher = new SubmissionPublisher<>();
        this.weatherPublisher = new SubmissionPublisher<>();
        this.mainPublisher = new SubmissionPublisher<>();
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
        this.file = new File(FILENAME);
        if(file.isDirectory()){
            file.delete();
        }
        if(!file.exists()){
            file.createNewFile();
        }
    }

    public void addLocationSubscription(Subscriber<Location> subscriber){
        this.locationPublisher.subscribe(subscriber);
    }

    public void addWeatherSubscription(Subscriber<Weather> subscriber){
        this.weatherPublisher.subscribe(subscriber);
    }

    public void addMainSubscription(Subscriber<Main> subscriber){
        this.mainPublisher.subscribe(subscriber);
    }


    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        if (this.location == null){
            this.location = location;
            this.locationPublisher.submit(this.location);
        }
        else{
            if (!location.getName().equals(this.location.getName())){
                this.location = location;
                this.locationPublisher.submit(this.location);
                this.weatherPublisher.submit(this.location.getWeather().get(0));
                this.mainPublisher.submit(this.location.getMain());
            }
        }
    }

    public void findLocation(Integer zip) throws URISyntaxException, IOException, InterruptedException {
        this.uri = new URI(FIND_LOCATION_URL + "zip=" + String.valueOf(zip) + ",DE&"
                + "units=metric&"
                + "appid=" + CONSTANT.API_KEY);

        this.getRequest = HttpRequest.newBuilder()
            .uri(this.uri)
            .header("Content-Type", "application/json")
            .GET().build();

        this.getResponse = this.client.send(this.getRequest, BodyHandlers.ofString());

        this.jsonObject = new JSONObject(this.getResponse.body());
        this.writeJson();

        this.zip = zip;
        this.setLocation(this.gson.fromJson(this.getResponse.body(), Location.class));
    }

    public void update() throws URISyntaxException, IOException, InterruptedException {
        this.uri = new URI(FIND_LOCATION_URL + "zip=" + String.valueOf(this.zip) + ",DE&"
                + "units=metric&"
                + ",DE&appid=" + CONSTANT.API_KEY);
        
        this.getRequest = HttpRequest.newBuilder()
            .uri(this.uri)
            .header("Content-Type", "application/json")
            .GET().build();

        this.getResponse = this.client.send(this.getRequest, BodyHandlers.ofString());

        this.jsonObject = new JSONObject(this.getResponse.body());
        this.writeJson();

        Location newLocation = (Location)this.gson.fromJson(this.getResponse.body(), Location.class);
        this.location.setWeather(newLocation.getWeather());
        this.weatherPublisher.submit(this.location.getWeather().get(0));
        this.location.setMain(newLocation.getMain());
        this.mainPublisher.submit(this.location.getMain());
        
    }

    private void writeJson() throws IOException{
        this.output = new BufferedWriter(new FileWriter(this.file));
        this.output.write(this.jsonObject.toString(4));
        this.output.close();
    }

}
