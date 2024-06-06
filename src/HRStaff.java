public class HRStaff extends Employee{
    public HRStaff(String name, String birthDate, String department) {
        super(name, birthDate, department);
        this.position = "HR Staff";
        this.salary = 5000000;
    }

    @Override
    public int getSalary() {
        return salary;
    }
}
