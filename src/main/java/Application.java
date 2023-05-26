import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Application {
    public static void main(String[] args) throws SQLException {

//        try (final Connection connection =
//                     ConnectionManager.getConnection();
//             PreparedStatement statement =
//                     connection.prepareStatement("SELECT * FROM employee")) {
//
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//
//                String firstName = resultSet.getString("first_name");
//                String lastName = resultSet.getString("last_name");
//                String gender = resultSet.getString("gender");
//                int age = resultSet.getInt("age");
//                int cityID = resultSet.getInt("city_id");
//
//                System.out.println("Имя: " + firstName);
//                System.out.println("Фамилия: " + lastName);
//                System.out.println("Пол: " + gender);
//                System.out.println("Возраст: " + age);
//                System.out.println("ID города: " + cityID);
//            }
//        } catch (SQLException e) {
//            System.out.println("Ошибка при подключении к БД!");
//            e.printStackTrace();
//        }
//        System.out.println();
//
//
//        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
//        List<Employee> allEmployees = employeeDAO.getAllEmployees();
//        for
//        (Employee employee : allEmployees) {
//            System.out.println("Employee ID: " + employee.getId());
//            System.out.println("name: " + employee.getFirstName());
//            System.out.println("lastName: " + employee.getLastName());
//            System.out.println("gender: " + employee.getGender());
//            System.out.println("age: " + employee.getAge());
//            System.out.println("city_id: " + employee.getCityID());
//        }
//        System.out.println();
//
//
//        String newEmployee = "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES ('Леопольд', 'Котов', 'Мужчина', 5, 3)";
//        employeeDAO.adEmployee(newEmployee);
//        System.out.println();
//
//
//        System.out.println(employeeDAO.getEmployee(3));
//        System.out.println();
//
//
//        employeeDAO.changeEmployee(3, "Артемий", "Болдышев", "Мужчина", 100, 2);
//        System.out.println();
//
//
//        employeeDAO.deleteEmployee(87);

        CityDAO cityDAO = new CityDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        City newCity = new City("Владивосток");

        Employee employee4 = new Employee("Василий", "Леонов", "Мужчина", 34, newCity);
        Employee employee5 = new Employee("Владимир", "Федотов", "Мужчина", 24, newCity);
        Employee employee6 = new Employee("Ольга", "Новикова", "Женщина", 25, newCity);
        List<Employee> newEmployees = new ArrayList<>();
        newEmployees.add(employee4);
        newEmployees.add(employee5);
        newEmployees.add(employee6);

        newCity.setEmployees(newEmployees);

        cityDAO.adCity(newCity);

        cityDAO.getAllCities();
        employeeDAO.getAllEmployees();

        employeeDAO.changeEmployee(89, "Олег", "Нестеров", "Мужчина", 42, newCity);
        System.out.println(employeeDAO.getEmployee(89));

        cityDAO.deleteCity(15);
        cityDAO.getAllCities();
        employeeDAO.getAllEmployees();
    }
}