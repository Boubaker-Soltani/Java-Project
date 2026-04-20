// Class Cow (تمثيل البقرة)
// ======================================
import java.time.LocalDate;

public class Cow extends Animal {

    // ======================================
    // Constructor (بناء كائن البقرة)
    // ======================================
    Cow(LocalDate birthDate, double weight) {

        // Call parent constructor (استدعاء الكلاس الأب Animal)
        super(birthDate, weight, "Barley, corn", "milk");
    }

    // ======================================
    // Override Method (إعادة تعريف الصوت)
    // ======================================
    @Override
    public void makeSound() {
        if (this.isHealthy())
            System.out.println("🐄 Moo");
        else
            System.out.println(" Silence");
    }
}
