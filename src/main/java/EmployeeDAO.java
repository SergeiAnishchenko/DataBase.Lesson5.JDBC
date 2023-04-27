import java.util.List;

public interface EmployeeDAO {

    void adEmployee(String newEmployee);

    Employee getEmployee(int id);

    void getAllEmployees();

    void changeEmployee(int id, String firstname, String lastName, String gender, int age, City city);

    void deleteEmployee(int id);
}