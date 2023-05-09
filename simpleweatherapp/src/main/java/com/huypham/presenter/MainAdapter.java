package com.huypham.presenter;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

// import com.huypham.model.Location;
import com.huypham.model.Main;
import com.huypham.model.WeatherApp;
import com.huypham.view.WeatherGui;

public class MainAdapter implements Subscriber<Main> {

    private WeatherApp model;
    private WeatherGui view;
    private Subscription subscription;

    public MainAdapter(WeatherApp model, WeatherGui view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Main item) {
        double temp = item.getTemp();
        this.view.getLblTemp().setText(String.valueOf(temp) + "\u00B0" + "C");
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
    }

    @Override
    public void onComplete() {
    }
    
}
