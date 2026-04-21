
// Class Sheep (تمثيل الخروف)
// ======================================
import java.time.LocalDate;

public class Sheep extends Animal {

    // ======================================
    // Constructor (بناء كائن الخروف)
    // ======================================
    Sheep(LocalDate birthDate, double weight) {

        // Call parent constructor
        super(birthDate, weight, "Barley", "meat, wool");
    }

    private double calculateMeat() {
        return this.weight * 0.40 * (getHealthScore() / 100.0); // Meat = %40 of weight
    }

    private double calculateWool() {
        return this.weight * 0.05 * (getHealthScore() / 100.0); // %5 of weight
    }

    // ======================================
    // Override Method
    // ======================================

    // Production الانتاج
    @Override
    public double getProduction() {
        // التحقق ان الحيوان حي
        if (!alive) {
            System.out.println("Sheep already dead.");
            return 0;
        }
        if (getHealthScore() >= 50) {
            this.alive = false;
            return calculateMeat() + calculateWool();
        }
        // الحيوان في حالة حرجة لا يمكن التضحية به
        System.out.println("⚠ Sheep in critical condition. Cannot sacrifice.");
        return 0;
    }

    // صوت الخروف
    @Override
    public String makeSound() {
        if (this.isHealthy())
            return "🐑 Baa";
        else
            return " weak sound";
    }
}
