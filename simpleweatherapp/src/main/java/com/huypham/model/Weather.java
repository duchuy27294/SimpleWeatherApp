package com.huypham.model;

public class Weather {
    private Long id;
    private String main;
    private String description;
    private String icon;


    public Weather(Long id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMain() {
        return this.main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", main='" + getMain() + "'" +
            ", description='" + getDescription() + "'" +
            ", icon='" + getIcon() + "'" +
            "}";
    }

}
