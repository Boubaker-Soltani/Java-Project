// Class Animal (تمثيل كلاس الحيوان - Abstract Class)
// ======================================
import java.time.LocalDate;

abstract class Animal {

    // ======================================
    // Attributes (الخصائص)
    // ======================================
    protected static int counter = 1; // Counter for auto ID (عداد لإنشاء ID تلقائي)
    private int id;
    protected double weight;
    protected String diet;
    protected String purpose;
    protected boolean alive;
    protected LocalDate birthDate;

    // ======================================
    // Constructor (بناء الكائن)
    // ======================================
    Animal(LocalDate birthDate, double weight, String diet, String purpose) {
        this.id = counter++; // assign unique ID (تعيين رقم معرف تلقائي)
        this.birthDate = birthDate;

        this.setWeight(weight);   // validation inside setter
        this.setDiet(diet);
        this.setPurpose(purpose);

        this.alive = true; // default state (الحيوان حي افتراضياً)
    }

    // ======================================
    // Abstract Method (دالة مجردة - لازم تتطبق في الأبناء)
    // ======================================
    public abstract void makeSound();

    // ======================================
    // Business Logic Methods (دوال منطق العمل)
    // ======================================

    // Check health status (فحص صحة الحيوان)
    protected boolean isHealthy() {
        return alive && weight > 20 && getAge() < 10;
    }

    // Display health status (عرض الحالة الصحية)
    public void healthStatus() {
        if (this.isHealthy())
            System.out.println("The Animal is healthy / الحيوان سليم");
        else
            System.out.println("⚠️ The Animal requires medical check / الحيوان يحتاج فحصا بيطريا");
    }

    // Check if animal can be sold (هل يمكن بيع الحيوان)
    public void readyForSell() {
        if (this.isHealthy())
            System.out.println("✔️ Animal can be sold / يمكن بيع الحيوان");
        else
            System.out.println("❌ Animal can't be sold / لا يمكن بيع الحيوان");
    }

    // Feed animal (تغذية الحيوان)
    public void feed(double food) {
        this.weight += food * 0.5; // conversion food → weight gain
    }

    // ======================================
    // Setters (تعديل القيم مع التحقق)
    // ======================================
    public void setWeight(double weight) {
        if (weight > 0)
            this.weight = weight;
        else
            System.out.println("❌ Invalid weight / وزن غير صالح");
    }

    public void setPurpose(String purpose) {
        if (purpose != null && !purpose.isEmpty())
            this.purpose = purpose;
        else
            this.purpose = "Unknown";
    }

    public void setDiet(String diet) {
        if (diet != null && !diet.isEmpty())
            this.diet = diet;
        else
            this.diet = "Unknown";
    }

    // ======================================
    // Getters (استرجاع البيانات)
    // ======================================
    public static int getCounter() { return counter; }
    public String getDiet() { return diet; }
    public int getId() { return id; }
    public String getPurpose() { return purpose; }
    public double getWeight() { return weight; }

    // Calculate age (حساب العمر)
    public int getAge() {
        if (birthDate == null) return 0;
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    // ======================================
    // Display Method (عرض المعلومات)
    // ======================================
    @Override
    public String toString() {
        return "ID: " + id +
               " | Type: " + getClass().getSimpleName() +
               " | Age: " + getAge() +
               " | Weight: " + weight +
               " | Purpose: " + purpose;
    }
}
