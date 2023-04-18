import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    final String user = "postgres";
    final String password = "Zvbkkzvbkk2727!";
    final String url = "jdbc:postgresql://localhost:5432/skypro5";

    @Override
    public void adEmployee() {
        try (final Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement =
                    connection.createStatement();
            int adCount = statement.executeUpdate("INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES ('Илья', 'Иванов', 'Мужчина', 27, 2)");

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void getEmployee(int id) {
        String sql = "SELECT * FROM employee WHERE id = " + id;
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

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
                System.out.println("Id города: " + cityID);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (final Connection connection = DriverManager.getConnection(url, user, password);
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
    public void changeEmployee(int id) {
        String sql = "UPDATE employee SET first_name = 'Ирина', last_name = 'Самсонова', gender = 'Женщина', age = 23 WHERE id = " + id;
        try (final Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement =
                    connection.createStatement();
            int changeCount = statement.executeUpdate(sql);
            System.out.println("Количество измененных сотрудников: " + changeCount);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = " + id;

        try (final Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement =
                    connection.createStatement();
            int deleteCount = statement.executeUpdate(sql);
            System.out.println("Количество удаленных сотрудников: " + deleteCount);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
