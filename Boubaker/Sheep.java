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

    // ======================================
    // Override Method (صوت الخروف)
    // ======================================
    @Override
    public void makeSound() {
        if (this.isHealthy())
            System.out.println("🐑 Baa");
        else
            System.out.println(" Silence");
    }
}
