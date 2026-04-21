
// Class Cow (تمثيل البقرة)
// ======================================
import java.time.LocalDate;

public class Cow extends Animal {
    private double milkPerDay; // لتر حليب يوميا
    // ======================================
    // Constructor (بناء كائن البقرة)
    // ======================================

    Cow(LocalDate birthDate, double weight, double milkPerDay) {

        // Call parent constructor (استدعاء الكلاس الأب Animal)
        super(birthDate, weight, "Barley, corn", "milk");
        this.milkPerDay = milkPerDay;
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

    // صوت البقرة
    @Override
    public String makeSound() {
        if (this.isHealthy())
            return "🐄 Moo";
        else
            return " weak sound";
    }
}
