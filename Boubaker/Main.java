import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// ======================================
// MAIN CLASS (الكلاس الرئيسي)
// ======================================
public class Main {

    public static void main(String[] args) {

        // Scanner for user input (أداة قراءة إدخال المستخدم)
        Scanner input = new Scanner(System.in);

        // System state (حالة تشغيل البرنامج)
        boolean isRunning = true;

        // Data storage (تخزين البيانات)
        ArrayList<Animal> animals = new ArrayList<>();     // قائمة الحيوانات
        ArrayList<Employee> employees = new ArrayList<>(); // قائمة الموظفين
        ArrayList<SmartFeedStock> feedStocks = new ArrayList<>(); // قائمة الأعلاف

        // Veterinarian instance (كائن الطبيب البيطري)
        Veterinarian vet = new Veterinarian("Ali", "0550000000", "General", 5);

        // ======================================
        // MAIN LOOP (الحلقة الرئيسية للنظام)
        // ======================================
        while (isRunning) {

            // Main menu UI (واجهة القائمة الرئيسية)
            System.out.println("\n==================================");
            System.out.println("        FARM MANAGEMENT SYSTEM");
            System.out.println("==================================");

            System.out.println("1. Employees Management");
            System.out.println("2. Animals Management");
            System.out.println("3. Feed Management");
            System.out.println("4. Buy/Sell Operations");
            System.out.println("5. Exit");

            // Get validated choice (اختيار مع تحقق)
            int choice = selectChoice(5, input);

            switch (choice) {

                // ======================================
                // EMPLOYEE MENU (إدارة الموظفين)
                // ======================================
                case 1 -> {
                    employeeMenu: // label للخروج من الحلقة
                    while (true) {

                        System.out.println("\n--- EMPLOYEE MENU ---");
                        System.out.println("1. Add Employee");
                        System.out.println("2. Show Employees");
                        System.out.println("3. Delete Employee");
                        System.out.println("4. Exit");

                        choice = selectChoice(4, input);

                        switch (choice) {

                            // إضافة موظف جديد
                            case 1 -> addEmployee(employees, input);

                            // عرض جميع الموظفين
                            case 2 -> showEmployees(employees);

                            // حذف موظف حسب ID
                            case 3 -> {
                                System.out.print("Enter ID: ");
                                int id = input.nextInt();
                                removeEmployee(employees, id);
                            }

                            // خروج من القائمة
                            case 4 -> { break employeeMenu; }
                        }
                    }
                }

                // ======================================
                // ANIMAL MENU (الحيوانات)
                // ======================================
                case 2 -> {
                    animalMenu:
                    while (true) {

                        System.out.println("\n--- ANIMAL & VETERINARY MENU ---");
                        System.out.println("1. Show Animals");
                        System.out.println("2. Diagnose");
                        System.out.println("3. Treat Animal");
                        System.out.println("4. Feed Animal");
                        System.out.println("5. Exit");

                        choice = selectChoice(5, input);

                        switch (choice) {

                            // عرض الحيوانات
                            case 1 -> displayAnimals(animals);

                            // تشخيص الحيوان
                            case 2 -> {
                                Animal a = findAnimal(animals, input);
                                if (a != null) {
                                    System.out.println("Diagnosis: " + vet.diagnose(a));
                                }
                            }

                            // علاج الحيوان
                            case 3 -> {
                                Animal a = findAnimal(animals, input);
                                if (a != null) {
                                    input.nextLine(); // تنظيف buffer
                                    System.out.print("Medicine: ");
                                    String med = input.nextLine();
                                    vet.treatAnimal(a, med);
                                }
                            }

                            // تغذية الحيوان
                            case 4 -> {
                                Animal a = findAnimal(animals, input);
                                if (a != null) {
                                    System.out.print("Food amount: ");
                                    double food = input.nextDouble();
                                    a.feed(food);
                                }
                            }

                            // خروج
                            case 5 -> { break animalMenu; }
                        }
                    }
                }

                // ======================================
                // FEED MENU (إدارة الأعلاف)
                // ======================================
                case 3 -> {
                    feedMenu:
                    while (true) {

                        System.out.println("\n--- FEED MENU ---");
                        System.out.println("1. Show Feed");
                        System.out.println("2. Consume Feed");
                        System.out.println("3. Exit");

                        choice = selectChoice(3, input);

                        switch (choice) {

                            // عرض كل الأعلاف
                            case 1 -> feedStocks.forEach(System.out::println);

                            // استهلاك علف
                            case 2 -> {
                                System.out.print("Index: ");
                                int i = input.nextInt();

                                // تحقق من صحة index
                                if (i >= 0 && i < feedStocks.size()) {
                                    System.out.print("Amount: ");
                                    double a = input.nextDouble();
                                    feedStocks.get(i).consumeFeed(a);
                                }
                            }

                            // خروج
                            case 3 -> { break feedMenu; }
                        }
                    }
                }

                // ======================================
                // OPERATIONS MENU (عمليات البيع والشراء)
                // ======================================
                case 4 -> {
                    operationMenu:
                    while (true) {

                        System.out.println("\n--- BUY / SELL MENU ---");
                        System.out.println("1. Sell Animal");
                        System.out.println("2. Buy Animal");
                        System.out.println("3. Buy Feed");
                        System.out.println("4. Exit");

                        choice = selectChoice(4, input);

                        switch (choice) {

                            // بيع حيوان
                            case 1 -> {
                                Animal a = findAnimal(animals, input);

                                if (a != null) {
                                    Operation op = new Operation();

                                    op.sell(a); // تنفيذ البيع

                                    animals.remove(a); // حذف من المزرعة

                                    System.out.println(op.showSell()); // طباعة الفاتورة
                                }
                            }

                            // شراء حيوان
                            case 2 -> {
                                System.out.println("1.COW 2.SHEEP 3.GOAT");

                                int c = selectChoice(3, input);

                                // تحويل الاختيار إلى enum
                                AnimalType type = switch (c) {
                                    case 1 -> AnimalType.COW;
                                    case 2 -> AnimalType.SHEEP;
                                    case 3 -> AnimalType.GOAT;
                                    default -> null;
                                };

                                System.out.print("Weight: ");
                                double w = input.nextDouble();

                                System.out.print("Age: ");
                                int age = input.nextInt();

                                Operation op = new Operation();

                                Animal animal = op.buyAnimal(type, w, age);

                                animals.add(animal); // إضافة للمزرعة

                                System.out.println(op.showAnimalPurchase());
                            }

                            // شراء علف
                            case 3 -> {
                                System.out.println("1.BARLEY 2.CORN 3.GRASS");

                                int c = selectChoice(3, input);

                                FeedType type = switch (c) {
                                    case 1 -> FeedType.BARLEY;
                                    case 2 -> FeedType.CORN;
                                    case 3 -> FeedType.GRASS;
                                    default -> null;
                                };

                                System.out.print("Quantity: ");
                                double q = input.nextDouble();

                                System.out.print("Min threshold: ");
                                double min = input.nextDouble();

                                System.out.print("Price/kg: ");
                                double price = input.nextDouble();

                                System.out.print("Quality: ");
                                int quality = input.nextInt();

                                SmartFeedStock feed = new Operation().buyFeed(
                                        type, q, min, price, quality,
                                        LocalDate.now().plusDays(30), "Storage A"
                                );

                                feedStocks.add(feed);

                                System.out.println("Feed purchased successfully");
                            }

                            case 4 -> { break operationMenu; }
                        }
                    }
                }

                // إنهاء البرنامج
                case 5 -> isRunning = false;
            }
        }

        input.close(); // غلق Scanner
    }

    // ======================================
    // UTIL METHODS (دوال مساعدة)
    // ======================================

    // اختيار مع تحقق (validated input)
    static int selectChoice(int max, Scanner input) {
        int c;
        do {
            System.out.print("Enter choice: ");
            c = input.nextInt();
        } while (c < 1 || c > max);
        return c;
    }

    // إضافة موظف
    static void addEmployee(ArrayList<Employee> list, Scanner input) {
        input.nextLine();

        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Role: ");
        String role = input.nextLine();

        System.out.print("Phone: ");
        String phone = input.nextLine();

        System.out.print("Salary: ");
        double salary = input.nextDouble();

        list.add(new Employee(name, role, phone, salary));
    }

    // حذف موظف حسب ID
    static void removeEmployee(ArrayList<Employee> list, int id) {
        list.removeIf(e -> e.getId() == id);
    }

    // عرض الموظفين
    static void showEmployees(ArrayList<Employee> list) {
        list.forEach(System.out::println);
    }

    // عرض الحيوانات
    static void displayAnimals(ArrayList<Animal> list) {
        list.forEach(System.out::println);
    }

    // البحث عن حيوان حسب ID
    static Animal findAnimal(ArrayList<Animal> list, Scanner input) {
        System.out.print("Enter ID: ");
        int id = input.nextInt();

        for (Animal a : list)
            if (a.getId() == id)
                return a;

        System.out.println("Not found!");
        return null;
    }
}
