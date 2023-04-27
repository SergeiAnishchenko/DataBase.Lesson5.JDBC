import java.util.List;

public interface CityDAO {
    void adCity(City newCity);

    City getCity(int id);

    void changeCity(int id, String cityName);

    void deleteCity(int id);

    void getAllCities();
}
