import java.sql.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    public static void main(String[] args) throws SQLException {

        try (final Connection connection =
                     ConnectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM employee")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int cityID = resultSet.getInt("city_id");

                System.out.println("Имя: " + firstName);
                System.out.println("Фамилия: " + lastName);
                System.out.println("Пол: " + gender);
                System.out.println("Возраст: " + age);
                System.out.println("ID города: " + cityID);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
        System.out.println();



        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        for
        (Employee employee : allEmployees) {
            System.out.println("Employee ID: " + employee.getId());
            System.out.println("name: " + employee.getFirstName());
            System.out.println("lastName: " + employee.getLastName());
            System.out.println("gender: " + employee.getGender());
            System.out.println("age: " + employee.getAge());
            System.out.println("city_id: " + employee.getCityID());
        }
        System.out.println();



        String newEmployee = "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES ('Федор', 'Пастухов', 'Мужчина', 27, 2)";
        employeeDAO.adEmployee(newEmployee);
        System.out.println();



        System.out.println(employeeDAO.getEmployee(3));
        System.out.println();



        employeeDAO.changeEmployee(3, "Сергей", "Прохоров", "Мужчина", 65, 3);
        System.out.println();



        employeeDAO.deleteEmployee(66);

    }
}
