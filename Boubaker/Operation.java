import java.time.LocalDate;

// ======================================
// Operation Class (تمثيل عمليات البيع والشراء)
// ======================================
public class Operation {

    // ======================================
    // Private Attributes (خصائص خاصة)
    // ======================================
    private static int counter = 1; // Counter to generate unique ID (عداد لإنشاء معرف تلقائي)
    private int id;
    private LocalDate date; // Operation date (تاريخ العملية)
    private double price;   // Price of operation (السعر)
    private double quantity; // Quantity (الكمية)
    private Animal animal;  // Related animal (الحيوان المرتبط بالعملية)
    private SmartFeedStock feed;
    // ======================================
    // Constructor (بناء الكائن)
    // ======================================
    public Operation() {
        this.id = counter++; // Auto-generated ID (معرف تلقائي)
        this.price = 0;
        this.quantity = 0;
    }

    // ======================================
    // SELL Method (دالة بيع حيوان)
    // ======================================
    public void sell(Animal animal) {

        // Check if animal exists (التحقق من وجود الحيوان)
        if (!animal.alive) {
            System.out.println("Animal is not existent !");
        } 
        else {
            this.animal = animal;
            this.date = LocalDate.now(); // Set sale date (تحديد تاريخ البيع)

            // Prepare animal for selling (تجهيز الحيوان للبيع)
            animal.readyForSell();

            // Calculate price (حساب السعر)
            this.price = animal.getPrice();

            // After selling, animal is no longer available (بعد البيع يصبح غير متوفر)
            animal.alive = false;
        }
    }

    // ======================================
    // BUY Method (دالة شراء حيوان)
    // ======================================
    public Animal buyAnimal(AnimalType type, double weight, int age) {

        // Create animal based on type (إنشاء الحيوان حسب النوع)
        switch (type) {
            case COW -> animal = new Cow(weight, age);
            case SHEEP -> animal = new Sheep(weight, age);
            case GOAT -> animal = new Goat(weight, age);

            default -> {
                System.out.println("This animal is not existent!");
                animal = null;
            }
        }

        // Register purchase date (تسجيل تاريخ الشراء)
        if (animal != null) {
            this.date = LocalDate.now();
            this.price = animal.getPrice(); // Set price at purchase (تحديد السعر عند الشراء)
            System.out.println("Animal purchased successfully");
        }

        return animal;
    }

     // ======================================
    // BUY Method (دالة شراء علف)
    // ======================================
    public SmartFeedStock buyFeed(FeedType type, double quantity, double minThreshold,
                              double pricePerKg, int quality,
                              LocalDate expiryDate, String location) {

    // Create new feed stock (إنشاء مخزون جديد)
        this.feed = new SmartFeedStock( 
            type.name(), quantity, minThreshold,
            pricePerKg, quality,
            expiryDate, location
    );

    // Register operation (تسجيل العملية)
    this.date = LocalDate.now();
    this.quantity = quantity;
    this.price = feed.getPrice();

    System.out.println("Feed purchased successfully");

    return feed;
}




    // ======================================
    // Display SELL (عرض تفاصيل البيع)
    // ======================================
    public String showSell() {

        // Check if animal exists (التحقق)
        if (animal == null) {
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

    // ======================================
    // Display PURCHASE (عرض تفاصيل الشراء)
    // ======================================
    public String showAnimalPurchase() {

        // Check if animal exists (التحقق)
        if (animal == null)
            return "No animal available for this purchase!";
    // Animal PURCHASE
        return  "===== ANIMAL PURCHASE RECEIPT =====\n" +
                "Purchase ID  : " + this.id + "\n" +
                "Date         : " + this.date + "\n" +
                "Animal Type  : " + animal.getClass().getSimpleName() + "\n" +
                "Animal ID    : " + animal.getId() + "\n" +
                "Weight (kg)  : " + animal.getWeight() + "\n" +
                "Age          : " + animal.getAge() + "\n" +
                "Price        : " + this.price + " DA\n" +
                "Status       : PURCHASED\n" +
                "============================";
    }
    // Feed PURCHASE
    public String showFeedPurchase(){

    // Check (التحقق)
    if(feed == null){
        return "No feed purchase available!";
    }

    return  "===== FEED PURCHASE RECEIPT =====\n" +
            "Purchase ID  : " + this.id + "\n" +
            "Date         : " + this.date + "\n" +
            "Feed Type    : " + feed.gettype() + "\n" +
            "Quantity (kg): " + this.quantity + "\n" +
            "Quality      : " + feed.getQuality() + "\n" +
            "Price / kg   : " + feed.getPricePerKg() + " DA\n" +
            "Total Price  : " + this.price + " DA\n" +
            "Location     : " + feed.getStorageLocation() + "\n" +
            "Expiry Date  : " + feed.getExpiryDate() + "\n" +
            "Status       : PURCHASED\n" +
            "===============================";
}
}
