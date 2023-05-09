package com.huypham.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import com.huypham.model.WeatherApp;
import com.huypham.view.WeatherGui;

public class Controller {
    private WeatherApp model;
    private WeatherGui view;

    public Controller(WeatherApp model, WeatherGui view){
        this.model = model;
        this.view = view;
    }

    public void registerEvents() {
        this.view.getBtnSetLocation().addActionListener(e -> {
            try {
                setLocation(e);
            } catch (URISyntaxException | IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        this.view.getBtnRefresh().addActionListener(e -> {
            try {
                refresh(e);
            } catch (URISyntaxException | IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        this.view.getTfZip().addActionListener(e -> {
            try{
                setLocation(e);
            }
            catch (URISyntaxException | IOException | InterruptedException ex){
                ex.printStackTrace();
            }
        });
    }

    private void setLocation(ActionEvent e) throws URISyntaxException, IOException, InterruptedException{
        try{
            Integer zipCode = Integer.parseInt(this.view.getTfZip().getText());
            this.model.findLocation(zipCode);
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private void refresh(ActionEvent e) throws URISyntaxException, IOException, InterruptedException{
        try{
            this.model.update();
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}
