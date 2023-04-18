import java.sql.*;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        final String user = "postgres";
        final String password = "Zvbkkzvbkk2727!";
        final String url = "jdbc:postgresql://localhost:5432/skypro5";
        try (final Connection connection =
                     DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM employee")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                String cityName = resultSet.getString("city_name");

                System.out.println("Имя: " + firstName);
                System.out.println("Фамилия: " + lastName);
                System.out.println("Пол: " + gender);
                System.out.println("Город: " + cityName);

            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        for
        (Employee employee : allEmployees) {
            System.out.println("Employee ID: " + employee.getId());
            System.out.println("name: " + employee.getFirstName());
            System.out.println("lastName: " + employee.getLastName());
            System.out.println("gender: " + employee.getGender());
            System.out.println("age: " + employee.getAge());

        }
        employeeDAO.adEmployee();
        employeeDAO.getEmployee(3);
        employeeDAO.changeEmployee(4);
        employeeDAO.deleteEmployee(2);
    }
}
