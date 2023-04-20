import java.util.List;

public interface EmployeeDAO {
    void adEmployee();

    Employee getEmployee(int id);

    List<Employee> getAllEmployees();

    void changeEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
