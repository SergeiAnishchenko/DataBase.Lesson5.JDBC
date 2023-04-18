import java.util.List;

public interface EmployeeDAO {
    void adEmployee();

    void getEmployee(int id);

    List<Employee> getAllEmployees();

    void changeEmployee(int id);

    void deleteEmployee(int id);
}
