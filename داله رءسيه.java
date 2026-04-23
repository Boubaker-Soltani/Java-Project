import java.time.LocalDate;
import java.util.*;

public class Main {

    static Scanner input = new Scanner(System.in);

    static ArrayList<Animal> animals = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<SmartFeedStock> feedStocks = new ArrayList<>();
    static ArrayList<Operation> operations = new ArrayList<>();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            System.out.println("\n======================================");
            System.out.println("        FARM MANAGEMENT SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Employees Management");
            System.out.println("2. Animals Management");
            System.out.println("3. Feed Management");
            System.out.println("4. Buy / Sell Operations");
            System.out.println("5. Exit");
            System.out.println("======================================");

            int choice = selectChoice(5);

            switch (choice) {

                // ================= EMPLOYEES =================
                case 1 -> employeeMenu();

                // ================= ANIMALS =================
                case 2 -> animalMenu();

                // ================= FEED =================
                case 3 -> feedMenu();

                // ================= OPERATIONS =================
                case 4 -> operationMenu();

                case 5 -> {
                    running = false;
                    System.out.println("👋 Goodbye!");
                }
            }
        }

        input.close();
    }

    // =====================================================
    // EMPLOYEE MENU
    // =====================================================
    static void employeeMenu() {

        while (true) {
            System.out.println("\n===== EMPLOYEE MENU =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Show Employees");
            System.out.println("3. Delete Employee");
            System.out.println("4. Manage Employee");
            System.out.println("5. Back");

            int choice = selectChoice(5);

            switch (choice) {

                case 1 -> addEmployee();

                case 2 -> employees.forEach(System.out::println);

                case 3 -> {
                    System.out.print("Enter ID: ");
                    int id = input.nextInt();
                    employees.removeIf(e -> e.getId() == id);
                }

                case 4 -> manageEmployee();

                case 5 -> { return; }
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
        System.out.println("✔ Employee added");
    }

    static void manageEmployee() {

        System.out.print("Enter Employee ID: ");
        int id = input.nextInt();

        Employee emp = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (emp == null) {
            System.out.println("❌ Not found");
            return;
        }

        System.out.println("1. Assign Task");
        System.out.println("2. Promote");
        System.out.println("3. Show Info");

        int c = selectChoice(3);

        switch (c) {

            case 1 -> emp.assignTask();

            case 2 -> {
                input.nextLine();
                System.out.print("New Role: ");
                String role = input.nextLine();

                System.out.print("Increase %: ");
                double inc = input.nextDouble();

                emp.promote(role, inc);
            }

            case 3 -> System.out.println(emp);
        }
    }

    // =====================================================
    // ANIMAL MENU
    // =====================================================
    static void animalMenu() {

        while (true) {

            System.out.println("\n===== ANIMAL MENU =====");
            System.out.println("1. Add Animal");
            System.out.println("2. Show Animals");
            System.out.println("3. Feed Animal");
            System.out.println("4. Health Check");
            System.out.println("5. Back");

            int choice = selectChoice(5);

            switch (choice) {

                case 1 -> addAnimal();

                case 2 -> animals.forEach(System.out::println);

                case 3 -> {
                    Animal a = findAnimal();
                    if (a != null) {
                        System.out.print("Food: ");
                        a.feed(input.nextDouble());
                    }
                }

                case 4 -> {
                    Animal a = findAnimal();
                    if (a != null)
                        a.healthStatus();
                }

                case 5 -> { return; }
            }
        }
    }

    static void addAnimal() {

        System.out.println("1. Cow");
        System.out.println("2. Goat");
        System.out.println("3. Sheep");

        int type = selectChoice(3);

        System.out.print("Weight: ");
        double w = input.nextDouble();

        System.out.print("Age: ");
        int age = input.nextInt();

        Animal a = switch (type) {
            case 1 -> new Cow(w, age);
            case 2 -> new Goat(w, age);
            case 3 -> new Sheep(w, age);
            default -> null;
        };

        if (a != null) {
            animals.add(a);
            System.out.println("✔ Animal added ID: " + a.getId());
        }
    }

    static Animal findAnimal() {

        System.out.print("Enter ID: ");
        int id = input.nextInt();

        return animals.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // =====================================================
    // FEED MENU
    // (تبسيط فقط - يمكنك توسعته لاحقاً)
    // =====================================================
    static void feedMenu() {

        System.out.println("\n===== FEED STOCK =====");

        for (SmartFeedStock s : feedStocks) {
            System.out.println(s);
        }
    }

    // =====================================================
    // OPERATIONS MENU
    // =====================================================
    static void operationMenu() {

        System.out.println("\n===== OPERATIONS =====");
        System.out.println("1. Sell Animal");
        System.out.println("2. Buy Animal");

        int c = selectChoice(2);

        Operation op = new Operation();

        switch (c) {

            case 1 -> {
                Animal a = findAnimal();
                if (a != null) {
                    op.sell(a);
                    operations.add(op);
                    animals.remove(a);
                    System.out.println(op.showSell());
                }
            }

            case 2 -> {
                System.out.println("Buying animal...");
                System.out.println("Use animal menu instead (structured system)");
            }
        }
    }

    // =====================================================
    // UTILITY
    // =====================================================
    static int selectChoice(int max) {

        int c;

        do {
            System.out.print("Choice: ");
            c = input.nextInt();

            if (c < 1 || c > max)
                System.out.println("❌ Invalid");

        } while (c < 1 || c > max);

        return c;
    }
}
