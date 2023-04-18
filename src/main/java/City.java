public class City {

    private String cityName;
    private int cityId;

    public City(String cityName, int cityId) {
        this.cityName = cityName;
        this.cityId = cityId;
    }

    public City() {
    };

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


}
