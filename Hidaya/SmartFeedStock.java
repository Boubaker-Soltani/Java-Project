import java.time.LocalDate;
import java.util.Random;

// Classe SmartFeedStock (مخزون الأعلاف الذكي)
// ======================================
public class SmartFeedStock {
    // 🔒 ID automatique
    private static int counter = 1;
    private int id;
    private String type; // نوع العلف
    private double quantity; // الكمية (kg)
    private double minThreshold; // الحد الأدنى
    private double pricePerKg; // السعر
    private int quality; // جودة العلف (0 - 100)
    private LocalDate expiryDate; // تاريخ الصلاحية
    private String storageLocation; // مكان التخزين
    // 📊 إحصائيات
    private double totalConsumed; // إجمالي الإستهلاك
    private int daysTracked;

    // ======================================
    // Constructor
    // ======================================
    public SmartFeedStock(String type, double quantity, double minThreshold,
            double pricePerKg, int quality,
            LocalDate expiryDate, String location) {

        this.id = counter++;
        this.type = type;
        this.quantity = quantity;
        this.minThreshold = minThreshold;
        this.pricePerKg = pricePerKg;
        this.quality = quality;
        this.expiryDate = expiryDate;
        this.storageLocation = location;

        this.totalConsumed = 0;
        this.daysTracked = 0;
    }

    // ======================================
    // ➕ إضافة علف (شراء)
    // ======================================
    public void addFeed(double amount) {
        quantity += amount;
        System.out.println("✅ Added " + amount + " kg of " + type);
    }

    // ======================================
    // ➖ استهلاك العلف
    // ======================================
    public void consumeFeed(double amount) {
        if (amount > quantity) {
            System.out.println("❌ Not enough feed!");
            return;
        }

        quantity -= amount;
        totalConsumed += amount;

        System.out.println("🐄 Consumed " + amount + " kg of " + type);
    }

    // ======================================
    // ⚠️ تنبيهات ذكية
    // ======================================
    public void checkAlerts() {

        if (quantity <= minThreshold) {
            System.out.println("⚠ LOW STOCK: " + type);
        }

        if (expiryDate.isBefore(LocalDate.now())) {
            System.out.println("❌ EXPIRED: " + type);
        }

        if (quality < 40) {
            System.out.println("🚨 LOW QUALITY feed!");
        }
    }

    // ======================================
    // 💡 اقتراح شراء
    // ======================================
    public void suggestRestock() {
        if (quantity < minThreshold) {
            double needed = (minThreshold * 2) - quantity;
            System.out.println("💡 Suggest buying " + needed + " kg of " + type);
        }
    }

    // ======================================
    // 📊 حساب التكلفة
    // ======================================
    public double calculateStockValue() {
        return quantity * pricePerKg;
    }

    // ======================================
    // 📈 تحليل الاستهلاك
    // ======================================
    public double getAverageConsumption() {
        if (daysTracked == 0)
            return 0;
        return totalConsumed / daysTracked;
    }

    // ======================================
    // 🎲 محاكاة يوم (ذكي جداً)
    // ======================================
    public void simulateDay() {
        Random r = new Random();

        // استهلاك عشوائي
        double consumed = 5 + r.nextInt(10);
        consumeFeed(consumed);

        // تدهور الجودة مع الوقت
        quality -= r.nextInt(3);
        if (quality < 0)
            quality = 0;

        daysTracked++;

        checkAlerts();
    }

    // ======================================
    // 🧠 تأثير العلف على الحيوان
    // ======================================
    public int getHealthImpact() {
        if (quality > 80)
            return +10;
        if (quality > 50)
            return +5;
        if (quality > 30)
            return 0;
        return -10;
    }

    // ======================================
    // 🔍 Getters
    // ======================================
    public int getId() { return id;}
    public String gettype() { return type; }
    public double getQuantity() { return quantity; }
    public int getQuality() { return quality; }
    public double getPrice(){ return quantity * pricePerKg; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getStorageLocation() { return storageLocation; }
    public double getPricePerKg() { return pricePerKg; }

    // ======================================
    // عرض
    // ======================================
    @Override
    public String toString() {
        return "Feed: " + type +
                " | Qty: " + quantity + "kg" +
                " | Quality: " + quality +
                " | Price: " + pricePerKg +
                " | Location: " + storageLocation +
                " | Expiry: " + expiryDate;
    }
}
