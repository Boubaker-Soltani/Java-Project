
// Class Goat (تمثيل الماعز)
// ======================================

public class Goat extends Animal {
    private double milkPerDay;

    // ======================================
    // Constructor (بناء كائن الماعز)
    // ======================================
    Goat(double weight, int age) {

        // Call parent constructor
        super(weight, age, "Grass", "milk, meat");
        this.milkPerDay = 5; // 5 لتر يوميا 
    }
    // ======================================
    // Override Method
    // ======================================

    // Production الإنتاج
    @Override
    public double getProduction() {
        if (!this.isHealthy()) {
            System.out.println("⚠ Goat is not healthy. Treat before milking.");
            return 0;
        }
        return this.milkPerDay;
    }

    // Calculate Price (حساب سعر الحيوان)
    @Override
    public double getPrice(){
        return weight * 5.5 * (getHealthScore() /100.0);
    }

    // صوت الماعز
    @Override
    public String makeSound() {
        if (this.isHealthy())
            return "🐐 Maa";
        else
            return " Silence";
    }
}
