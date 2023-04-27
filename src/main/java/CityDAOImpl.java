import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;


public class CityDAOImpl implements CityDAO {
    @Override
    public void adCity(City newCity) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(newCity);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public City getCity(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        City city = entityManager.find(City.class,id);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return city;
    }

    @Override
    public void changeCity(int id, String cityName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        City city = entityManager.find(City.class,id);
        city.setCityName(cityName);
        entityManager.merge(city);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void deleteCity(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        City city = entityManager.find(City.class,id);
        entityManager.remove(city);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void getAllCities() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        String jpqlQuery = "from City";
        TypedQuery<City> query = entityManager.createQuery(jpqlQuery, City.class);
        List<City> cities = query.getResultList();
        entityManager.getTransaction().commit();
        for (City city : cities) {
            System.out.println("Идентификатор: " + city.getCityId());
            System.out.println("Город: " + city.getCityName());
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
