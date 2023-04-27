import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {



    @Override
    public void adEmployee(String newEmployee) {
        try (final Connection connection = ConnectionManager.getConnection()) {
            Statement statement =
                    connection.createStatement();
            int adCount = statement.executeUpdate(newEmployee);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Employee getEmployee(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class,id);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return employee;
    }


    @Override
    public void getAllEmployees() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        String jpqlQuery = "from Employee";
        TypedQuery<Employee> query = entityManager.createQuery(jpqlQuery, Employee.class);
        List<Employee> employees = query.getResultList();
        entityManager.getTransaction().commit();
        for (Employee employee : employees) {
            System.out.println("Идентификатор: " + employee.getId());
            System.out.println("Имя: " + employee.getFirstName());
            System.out.println("Фамилия: " + employee.getLastName());
            System.out.println("Пол: " + employee.getGender());
            System.out.println("Возраст: " + employee.getAge());
            System.out.println("Город: " + employee.getCity());
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Override
    public void changeEmployee(int id,String firstname, String lastName,String gender,int age,City city) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        employee.setFirstName(firstname);
        employee.setLastName(lastName);
        employee.setGender(gender);
        employee.setAge(age);
        employee.setCity(city);
        entityManager.merge(employee);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }


    @Override
    public void deleteEmployee(int id) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class,id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}