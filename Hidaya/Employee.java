// Class Employee ( تمثيل كلاس الموظف )
// ======================================
import java.time.LocalDate;

public class Employee {
    // Private Attributs (خصائص خاصة - Encapsulation)
    private static int counter = 1; // Counter to generate ID automatically (عداد لإنشاء ID)
    private int id;
    private String name;
    private String role;
    private String phone;
    private double salary;
    private LocalDate hireDate;
    private int tasksCount;

    // ======================================
    //  Constructor (بناء الكائن)
    // ======================================
    public Employee(String name, String role, String phone, double salary) {
        this.id = counter++; // ID automatically (تلقائي)
        this.setName(name);
        this.setRole(role);
        this.setPhone(phone);
        setSalary(salary); // validation
        this.hireDate = LocalDate.now(); // تاريخ التوظيف
        this.tasksCount = 0;
    }

    // ======================================
    //  Getters (استرجاع القيم)
    // ======================================
    public int getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getPhone() { return phone; }
    public double getSalary() { return salary; }
    public LocalDate getHireDate() { return hireDate; }
    public int getTasksCount() { return tasksCount; }

    // ======================================
    // Setters (تعديل مع التحقق)
    // ======================================
    public void setName(String name) {
        if (name != null && !name.isEmpty())
            this.name = name;
    }
    public void setRole(String role) {
        if (role != null && !role.isEmpty())
            this.role = role;
    }
    public void setPhone(String phone) {
        if (phone != null && phone.matches("\\d{8,}")) // au moins 8 chiffres
            this.phone = phone;
        else
            System.out.println("❌ Invalid phone number / رقم غير صالح");
    }

    public void setSalary(double salary) {
        if (salary >= 0)
            this.salary = salary;
        else
            System.out.println("❌ Invalid salary / راتب غير صالح");
    }
    // ======================================
    //  Usefull Methodes (دوال مفيدة)
    // ======================================

    //  Add task (إضافة مهمة)
    public void assignTask() {
        tasksCount++;
    }
    //  Calculate annual salary (حساب الراتب السنوي)
    public double calculateAnnualSalary() {
        return salary * 12;
    }

    //  Calculate bonus (حساب مكافأة حسب الأداء)
    public double calculateBonus() {
        if (tasksCount >= 20) return salary * 0.20;
        if (tasksCount >= 10) return salary * 0.10;
        return salary * 0.05;
    }
    // Promotion (ترقية الموظف)
    public void promote(String newRole, double increasePercent) {
        setRole(newRole);
        salary += salary * (increasePercent / 100);
    }
    //  Seniority (مدة العمل)
    public int getYearsOfService() {
        return LocalDate.now().getYear() - hireDate.getYear();
    }
    // ======================================
    //  Display  (عرض المعلومات)
    // ======================================
    @Override
    public String toString() {
        return "ID: " + id +
               "\n Name: " + name +
               "\n Role: " + role +
               "\n Phone: " + phone +
               "\n Salary: " + salary +
               "\n Tasks: " + tasksCount +
               "\n Hire Date: " + hireDate;
    }
}
