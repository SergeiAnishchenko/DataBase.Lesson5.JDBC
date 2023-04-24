import java.util.List;

public interface EmployeeDAO {

    void adEmployee(String newEmployee);

    Employee getEmployee(int id);

    List<Employee> getAllEmployees();

    void changeEmployee(int id, String firstname, String lastName, String gender, int age, int cityID);


    void deleteEmployee(int id);
}
