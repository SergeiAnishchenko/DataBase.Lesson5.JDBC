import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        Employee employee = null;
        String sql = "SELECT * FROM employee WHERE id = " + id;
        try (final Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int cityID = resultSet.getInt("city_id");
                employee = new Employee(id, firstName, lastName, gender, age, cityID);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
        return employee;
    }


    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (final Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM employee")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int cityID = resultSet.getInt("city_id");

                employees.add(new Employee(id, firstName, lastName, gender, age, cityID));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
        return employees;
    }


    @Override
    public void changeEmployee(int id,String firstname, String lastName,String gender,int age,int cityID) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Employee employee = getEmployee(id);
        employee.setFirstName(firstname);
        employee.setLastName(lastName);
        employee.setGender(gender);
        employee.setAge(age);
        employee.setCityID(cityID);
        entityManager.merge(employee);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }


    @Override
    public void deleteEmployee(Employee employee) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.remove(employee);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
