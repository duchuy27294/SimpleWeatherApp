package com.huypham.presenter;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import com.huypham.model.Location;
import com.huypham.model.WeatherApp;
import com.huypham.view.WeatherGui;

public class LocationAdapter implements Subscriber<Location> {

    private WeatherApp model;
    private WeatherGui view;
    private Subscription subscription;

    public LocationAdapter(WeatherApp model, WeatherGui view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Location item) {
        this.view.getLblCity().setText(item.getName() + ", " + item.getSys().country());
        try {
            this.model.update();
            this.subscription.request(1);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable throwable) {
    }

    @Override
    public void onComplete() {
    }
    
}
