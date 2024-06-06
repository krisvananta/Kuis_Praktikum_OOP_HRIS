public class Manager extends Employee{
    public Manager(String name, String birthDate, String department) {
        super(name, birthDate, department);
        this.position = "Manager";
        this.salary = 20000000;
    }

    @Override
    public int getSalary() {
        return salary;
    }
}
