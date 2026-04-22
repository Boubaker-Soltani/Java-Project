import java.time.LocalDate;

// ======================================
// Classe Operation (إدارة عمليات البيع والشراء)
// ======================================
public class Operation {

    // ======================================
    // Attributs (خصائص)
    // ======================================
    private static int counter = 1; // عداد لإنشاء ID تلقائي
    private int id;
    private LocalDate date;
    private double price;
    private double quantity;
    private Animal animal;

    // ======================================
    // Constructor (بناء الكائن)
    // ======================================
    public Operation(){
        this.id = counter++;
        this.price = 0;
        this.quantity = 0;
    }

    // ======================================
    // Méthode SELL (بيع حيوان)
    // ======================================
    public void sell(Animal animal){

        // التحقق إن كان الحيوان موجود
        if(!animal.alive){
            System.out.println("Animal is not existent !");
        } 
        else {
            this.animal = animal;
            this.date = LocalDate.now();

            // تجهيز الحيوان للبيع
            animal.readyForSell();

            // حساب السعر حسب صحة الحيوان
            this.price = animal.getPrice();

            // بعد البيع يصبح غير حي
            animal.alive = false;
        }
    }

    // ======================================
    // Méthode BUY (شراء حيوان)
    // ======================================
    public Animal buyAnimal(AnimalType type, double weight, int age){

        switch (type) {
            case COW -> animal = new Cow(weight, age);
            case SHEEP -> animal = new Sheep(weight, age);
            case GOAT -> animal = new Goat(weight, age);

            default -> {
                System.out.println("This animal is not existent!");
                animal = null;
            }
        }

        // تسجيل تاريخ العملية
        if(animal != null){
            this.date = LocalDate.now();
            System.out.println("Animal purchased successfully");
        }

        return animal;
    }

    // ======================================
    // Affichage (طباعة معلومات البيع)
    // ======================================
    public String showSell(){

        if(animal == null){
            return "No animal available for this operation!";
        }

        return  "===== SALE RECEIPT =====\n" +
                "Sale ID      : " + this.id + "\n" +
                "Date         : " + this.date + "\n" +
                "Animal Type  : " + animal.getClass().getSimpleName() + "\n" +
                "Animal ID    : " + animal.getId() + "\n" +
                "Weight (kg)  : " + animal.getWeight() + "\n" +
                "Price        : " + this.price + " DA\n" +
                "Status       : SOLD\n" +
                "========================";
    }
}
