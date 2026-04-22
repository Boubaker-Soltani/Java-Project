
// Class Cow (تمثيل البقرة)
// ======================================

public class Cow extends Animal {
    private double milkPerDay; // لتر حليب يوميا
    // ======================================
    // Constructor (بناء كائن البقرة)
    // ======================================

    Cow(double weight, int age) {

        // Call parent constructor (استدعاء الكلاس الأب Animal)
        super (weight, age, "Barley, corn", "milk");
        this.milkPerDay = 10; // 10 لتر يوميا
    }

    // ======================================
    // Override Method
    // ======================================

    // Production الإنتاج
    @Override
    public double getProduction() {
        if (!this.isHealthy()) {
            System.out.println("⚠ Cow is not healthy. Treat before milking.");
            return 0;
        }
        return this.milkPerDay;
    }

    // Calculate Price (حساب سعر الحيوان)
    @Override
    public double getPrice(){
        return weight * 6 * (getHealthScore()  /100.0);
    }

    // صوت البقرة
    @Override
    public String makeSound() {
        if (this.isHealthy())
            return "🐄 Moo";
        else
            return " Silence";
    }
}
