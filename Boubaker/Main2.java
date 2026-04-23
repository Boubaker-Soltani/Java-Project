import java.time.LocalDate;
import java.util.*;

// ======================================
// MAIN SYSTEM (النظام الرئيسي)
// ======================================
public class Main {

    static Scanner input = new Scanner(System.in);

    static ArrayList<Animal> animals = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<SmartFeedStock> feeds = new ArrayList<>();
    static ArrayList<Operation> operations = new ArrayList<>();

    public static void main(String[] args) {

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n========== FARM SYSTEM ==========");
            System.out.println("1. Employees");
            System.out.println("2. Animals + Veterinary");
            System.out.println("3. Feed Stock");
            System.out.println("4. Operations");
            System.out.println("5. Exit");

            int choice = selectChoice(5);

            switch (choice) {

                case 1 -> employeeMenu();
                case 2 -> animalMenu(); // 🔥 تم دمج البيطري هنا
                case 3 -> feedMenu();
                case 4 -> operationMenu();
                case 5 -> isRunning = false;
            }
        }
    }

    // ======================================
    // EMPLOYEE MENU
    // ======================================
    static void employeeMenu() {
        while (true) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1.Add");
            System.out.println("2.Show");
            System.out.println("3.Delete");
            System.out.println("4.Exit");

            int c = selectChoice(4);

            switch (c) {
                case 1 -> addEmployee();
                case 2 -> employees.forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    employees.removeIf(e -> e.getId() == id);
                }
                case 4 -> { return; }
            }
        }
    }

    static void addEmployee() {
        input.nextLine();
        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Role: ");
        String role = input.nextLine();

        System.out.print("Phone: ");
        String phone = input.nextLine();

        System.out.print("Salary: ");
        double salary = input.nextDouble();

        employees.add(new Employee(name, role, phone, salary));
    }

    // ======================================
    // ANIMAL + VETERINARY MENU
    // ======================================
    static void animalMenu() {

        Veterinarian vet = new Veterinarian("Ali", "0550000000", "General", 5);

        while (true) {
            System.out.println("\n--- Animal Menu ---");
            System.out.println("1.Add Animal");
            System.out.println("2.Show Animals");
            System.out.println("3.Health Check");
            System.out.println("4.Treat");
            System.out.println("5.Sell");
            System.out.println("6.Exit");

            int c = selectChoice(6);

            switch (c) {

                case 1 -> addAnimal();
                case 2 -> animals.forEach(System.out::println);

                case 3 -> {
                    Animal a = findAnimal();
                    if (a != null) {
                        System.out.println("Health: " + vet.diagnose(a));
                    }
                }

                case 4 -> {
                    Animal a = findAnimal();
                    if (a != null) {
                        input.nextLine();
                        System.out.print("Medicine: ");
                        String med = input.nextLine();
                        vet.treatAnimal(a, med);
                    }
                }

                case 5 -> {
                    Animal a = findAnimal();
                    if (a != null) {
                        System.out.println("Sold for: " + a.getPrice());
                        animals.remove(a);
                    }
                }

                case 6 -> { return; }
            }
        }
    }

    static void addAnimal() {
        System.out.println("1.COW 2.SHEEP 3.GOAT");
        int c = selectChoice(3);

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

        Animal a = new Operation().buyAnimal(type, w, age);
        animals.add(a);
    }

    static Animal findAnimal() {
        System.out.print("ID: ");
        int id = input.nextInt();

        for (Animal a : animals)
            if (a.getId() == id)
                return a;

        System.out.println("Not found!");
        return null;
    }

    // ======================================
    // FEED MENU
    // ======================================
    static void feedMenu() {

        while (true) {
            System.out.println("\n--- Feed Menu ---");
            System.out.println("1.Add Feed");
            System.out.println("2.Show");
            System.out.println("3.Consume");
            System.out.println("4.Exit");

            int c = selectChoice(4);

            switch (c) {
                case 1 -> addFeed();
                case 2 -> feeds.forEach(System.out::println);
                case 3 -> consumeFeed();
                case 4 -> { return; }
            }
        }
    }

    static void addFeed() {

        System.out.println("1.BARLEY 2.CORN 3.GRASS");
        int c = selectChoice(3);

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

        SmartFeedStock f = new SmartFeedStock(
                type.name(), q, min, price, quality,
                LocalDate.now().plusDays(30), "Main Storage"
        );

        feeds.add(f);
    }

    static void consumeFeed() {
        System.out.print("Feed index: ");
        int i = input.nextInt();

        if (i >= 0 && i < feeds.size()) {
            System.out.print("Amount: ");
            double a = input.nextDouble();
            feeds.get(i).consumeFeed(a);
        }
    }

    // ======================================
    // OPERATIONS MENU
    // ======================================
    static void operationMenu() {

        while (true) {
            System.out.println("\n--- Operations ---");
            System.out.println("1.Sell Animal");
            System.out.println("2.View History");
            System.out.println("3.Exit");

            int c = selectChoice(3);

            switch (c) {

                case 1 -> {
                    Animal a = findAnimal();
                    if (a != null) {
                        Operation op = new Operation();
                        op.sell(a);
                        operations.add(op);
                        animals.remove(a);
                        System.out.println(op.showSell());
                    }
                }

                case 2 -> operations.forEach(System.out::println);
                case 3 -> { return; }
            }
        }
    }

    // ======================================
    // UTIL
    // ======================================
    static int selectChoice(int max) {
        int c;
        do {
            System.out.print("Choice: ");
            c = input.nextInt();
        } while (c < 1 || c > max);
        return c;
    }
}
