
// Class Goat (تمثيل الماعز)
// ======================================
import java.time.LocalDate;

public class Goat extends Animal {
    private double milkPerDay;

    // ======================================
    // Constructor (بناء كائن الماعز)
    // ======================================
    Goat(LocalDate birthDate, double weight, double milkPerDay) {

        // Call parent constructor
        super(birthDate, weight, "Grass", "milk, meat");
        this.milkPerDay = milkPerDay;
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

    // صوت الماعز
    @Override
    public String makeSound() {
        if (this.isHealthy())
            return "🐐 Maa";
        else
            return " Silence";
    }
}
