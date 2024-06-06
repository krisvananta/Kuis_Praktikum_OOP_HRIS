public class Developer extends Employee{
    public Developer(String name, String birthDate, String department) {
        super(name, birthDate, department);
        this.position = "Developer";
        this.salary = 10000000;
    }

    @Override
    public int getSalary() {
        return salary;
    }
}
