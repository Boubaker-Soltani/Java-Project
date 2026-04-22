// Class Animal (تمثيل كلاس الحيوان - Abstract Class)
abstract class Animal {

    // ======================================
    // Attributes (الخصائص)
    // ======================================
    protected static int counter = 1; // Counter for auto ID (عداد لإنشاء ID تلقائي)
    protected int id;
    protected double weight;
    protected String diet;
    protected String purpose;
    protected boolean alive;
    protected int age;
    protected int healthScore;

    // ======================================
    // Constructor (بناء الكائن)
    // ======================================
    Animal(double weight, int age, String diet, String purpose) {
        this.id = counter++; // assign unique ID (تعيين رقم معرف تلقائي)
        this.setAge(age);

        this.setWeight(weight);   // validation inside setter
        this.setDiet(diet);
        this.setPurpose(purpose);

        this.alive = true; // default state (الحيوان حي افتراضياً)
        this.healthScore = 100; // default state (الحيوان سليم مبدئيا)
    }

    // ======================================
    // Abstract Method (دالة مجردة - لازم تتطبق في الأبناء)
    // ======================================

    public abstract String makeSound();
    public abstract double getProduction();
    public abstract double getPrice();
    
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
// Improve Animal health (تحسين صحة الحيوان)
    public void improveHealth(int value){
        this.healthScore += value;
    }

    // Check if animal can be sold (هل يمكن بيع الحيوان)
    public String readyForSell() {
        if (this.isHealthy())
           return " ✔️ Animal can be sold / يمكن بيع الحيوان";
        else
            return " ❌ Animal must be sold immediately // يجب بيع الحيوان فورا";
    }

    // Feed animal (تغذية الحيوان)
    public void feed(double food) {
        this.weight += food * 0.5; // conversion food → weight gain
    }

    // ======================================
    // Setters (تعديل القيم مع التحقق)
    // ======================================
    protected void setWeight(double weight) {
        if (weight > 0)
            this.weight = weight;
        else
            System.out.println("❌ Invalid weight / وزن غير صالح");
    }

    protected void setPurpose(String purpose) {
        if (purpose != null && !purpose.isEmpty())
            this.purpose = purpose;
        else
            this.purpose = "Unknown";
    }

    protected void setDiet(String diet) {
        if (diet != null && !diet.isEmpty())
            this.diet = diet;
        else
            this.diet = "Unknown";
    }

    protected void setAge(int age){
        if(age < 0 || age > 100){
            System.out.println("❌ Invalid Age // عمر غير صالح");
            this.age = 0;
        }
        this.age = age;
    }


    // ======================================
    // Getters (استرجاع البيانات)
    // ======================================
    public static int getCounter() { return counter; }
    public String getDiet() { return diet; }
    public int getId() { return id; }
    public String getPurpose() { return purpose; }
    public double getWeight() { return weight; }
    // Calculate health score (حساب مستوى الصحة)
    public int getHealthScore(){
        if(!alive) this.healthScore = 0; //Animal is dead (الحيوان ميت)
        if(weight < 100) this.healthScore -= 20;
        if(getAge() > 10) this.healthScore -= 10;
        return this.healthScore;
    }
    public int getAge() {
        return this.age;
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
