// Class Goat (تمثيل الماعز)
// ======================================
import java.time.LocalDate;

public class Goat extends Animal {

    // ======================================
    // Constructor (بناء كائن الماعز)
    // ======================================
    Goat(LocalDate birthDate, double weight) {

        // Call parent constructor
        super(birthDate, weight, "Grass", "milk, meat");
    }

    // ======================================
    // Override Method (صوت الماعز)
    // ======================================
    @Override
    public void makeSound() {
        if (this.isHealthy())
            System.out.println("🐐 Maa");
        else
            System.out.println(" Silence");
    }
}
