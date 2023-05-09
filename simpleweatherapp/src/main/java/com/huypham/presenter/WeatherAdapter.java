package com.huypham.presenter;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

// import com.huypham.model.Location;
import com.huypham.model.Weather;
import com.huypham.model.WeatherApp;
import com.huypham.view.WeatherGui;

public class WeatherAdapter implements Subscriber<Weather>{
    private WeatherApp model;
    private WeatherGui view;
    private Subscription subscription;
    private final String ICONS_PATH = "simpleweatherapp" + File.separator + "src" + File.separator + "main" 
                                    + File.separator + "java" + File.separator + "com"
                                    + File.separator + "huypham" + File.separator + "img";
    private ImageIcon icon;

    public WeatherAdapter(WeatherApp model, WeatherGui view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Weather item) {
        String description = item.getDescription();
        this.view.getLblDescription().setText(description);
        this.icon = new ImageIcon(this.ICONS_PATH + File.separator + item.getIcon() + ".png");
        this.view.getLblIcon().setIcon(this.icon);
        this.view.getLblIcon().setText("");
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
    }

    @Override
    public void onComplete() {
    }
}
