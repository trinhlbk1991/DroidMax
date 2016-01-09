package com.icetea09.droidmax.model;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class WeatherForecast {

    public String cod;
    public double message;
    public int cnt;

    public City city;
    public java.util.List<List> list;

    public class City {
        public int id;
        public String name;
        public Coord coord;
        public String country;

        public class Coord {
            public double lon;
            public double lat;
        }
    }


    public class List {
        public long dt;
        public java.util.List<Weather> weather;

        public class Weather {
            public int id;
            public String main;
            public String description;
        }
    }

}
