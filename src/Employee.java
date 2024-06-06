import java.util.Random;

abstract class Employee {
    protected String id;
    protected String name;
    protected String birthDate;
    protected String department;
    protected String position;
    protected int salary;

    public Employee(String name, String birthDate, String department) {
        this.name = name;
        this.birthDate = birthDate;
        this.department = department;
        this.id = generateUniqueId(department, birthDate);
    }

    private String generateUniqueId(String department, String birthDate) {
        String deptCode = department.equals("Product") ? "PROD" : "HR";
        String uniqueNumber = String.format("%04d", new Random().nextInt(10000));
        return "000" + deptCode + birthDate.replace("-", "") + uniqueNumber;
    }

    public static String[] getDepartment() {
        return new String[]{"Product", "Human Resources"};
    }

    public abstract int getSalary();

    public String getDetails() {
        return String.format("ID: %s, Name: %s, BirthDate: %s, Department: %s, Position: %s, Salary: %d",
                id, name, birthDate, department, position, salary);
    }
}