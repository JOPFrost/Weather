package com.example.weather.model;

public class WeatherResponse {

    public static class WeatherData {
        private Location location;
        private CurrentWeather current;

        // Getters and setters

        public Location getLocation() {
            return location;
        }

        public CurrentWeather getCurrent() {
            return current;
        }

    }

    public static class Location {
        private String name;  // City name
        private String region; // Region name of the city
        private String country; // Country name of the city
        private double lat; // Latitude of the city
        private double lon; // Longitude of the city
        private String tz_id; // Timezone ID of the city
        private long localtime_epoch; // Local time Unix time
        private String localtime; // Local time in the city

        // Getters and setters

        public String getName() {
            return name;
        }

        public String getRegion() {
            return region;
        }

        public String getCountry() {
            return country;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }

        public String getTz_id() {
            return tz_id;
        }

        public long getLocaltime_epoch() {
            return localtime_epoch;
        }

        public String getLocaltime() {
            return localtime;
        }

    }

    public static class CurrentWeather {
        private long last_updated_epoch; // Unix time
        private String last_updated; // time when the real time data was updated.
        private double temp_c; // Temperature in celsius
        private int is_day; // 1 = Yes 0 = No
        private Condition condition; // Weather condition
        private double wind_kph; // Wind speed in kilometer per hour
        private String wind_dir; // Wind direction as 16 point compass. e.g.: NSW
        private double pressure_mb; // Pressure in millibars
        
        private double precip_mm; // Precipitation amount in millimeters 
        private int humidity; // Humidity as percentage
        private int cloud; // Cloud cover as percentage
        private double feelslike_c; // Feels like temperature in celsius
        private double vis_km; // Visibility in kilometers
        private double uv; // UV Index


        // Getters and setters

        public long getLast_updated_epoch() {
            return last_updated_epoch;
        }

        public String getLast_updated() {
            return last_updated;
        }

        public double getTemp_c() {
            return temp_c;
        }

        public int getIs_day() {
            return is_day;
        }

        public Condition getCondition() {
            return condition;
        }

        public double getWind_kph() {
            return wind_kph;
        }

        public String getWind_dir() {
            return wind_dir;
        }

        public double getPressure_mb() {
            return pressure_mb;
        }

        public double getPrecip_mm() {
            return precip_mm;
        }

        public int getHumidity() {
            return humidity;
        }

        public int getCloud() {
            return cloud;
        }

        public double getFeelslike_c() {
            return feelslike_c;
        }

        public double getVis_km() {
            return vis_km;
        }

        public double getUv() {
            return uv;
        }


    }

    public static class Condition {
        private String text;
        private String icon;
        private int code;

        // Getters and setters

        public String getText() {
            return text;
        }

        public String getIcon() {
            return icon;
        }

        public int getCode() {
            return code;
        }

    }

}
