package com.huypham;

import java.io.IOException;
import java.net.URISyntaxException;
import com.huypham.model.WeatherApp;
import com.huypham.presenter.Controller;
import com.huypham.presenter.LocationAdapter;
import com.huypham.presenter.MainAdapter;
import com.huypham.presenter.WeatherAdapter;
import com.huypham.view.WeatherGui;

/**
 * Hello world!
 *
 */
public class App 
{
    public App() throws URISyntaxException, IOException {
        WeatherApp model = new WeatherApp();
        WeatherGui view = new WeatherGui();
        Controller controller = new Controller(model,view);
        controller.registerEvents();
        LocationAdapter lAdapter = new LocationAdapter(model,view);
        model.addLocationSubscription(lAdapter);
        WeatherAdapter wAdapter = new WeatherAdapter(model, view);
        model.addWeatherSubscription(wAdapter);
        MainAdapter mAdapter = new MainAdapter(model, view);
        model.addMainSubscription(mAdapter);
        view.setVisible(true);
        
    }

    public static void main( String[] args ) throws IOException, URISyntaxException
    {
        new App();
    }
}
