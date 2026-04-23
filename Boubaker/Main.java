import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<SmartFeedStock> feedStocks = new ArrayList<>();
        ArrayList<Operation> operations = new ArrayList<>();

        while (isRunning) {
            System.out.println("\n==================================================");
            System.out.println("                FARM MANAGEMENT SYSTEM");
            System.out.println("==================================================");
            System.out.println("1.Employees Manegment");
            System.out.println("2.Animals Manegment");
            System.out.println("3.Feed Manegment");
            System.out.println("4.Buy/Sell Operation Manegment");
            System.out.println("5.Exit");
            int choice = selectChoice(5, input);
            switch (choice) {
                case 1 -> { // Employee Manegment
                    employeeMenu: while (true) {
                        System.out.println("1.Add new Employee");
                        System.out.println("2.view employees records");
                        System.out.println("3.Delete employee");
                        System.out.println("4.Search for Employee");
                        System.out.println("5.exit");
                        choice = selectChoice(5, input);

                        switch (choice) {
                            case 1 -> {
                                addEmployee(employees, input);
                            }
                            case 2 -> {
                                ShowEmployees(employees);
                            }
                            case 3 -> {
                                System.out.print("Enter employee Id : ");
                                int id = input.nextInt();
                                removeEmployee(employees, id);
                            }
                            case 4 -> {
                                System.out.print("Enter Id of employee : ");
                                int id = input.nextInt();
                                int index = findEmployeeIndex(employees, id);
                                if (index == -1) {
                                    System.out.println("\ncan't find employee!\n");
                                    break;
                                }
                                Employee emp = employees.get(index);
                                System.out.println("1.Show deitals");
                                System.out.println("2.Assign task");
                                System.out.println("3.Promote");
                                System.out.println("4.Years of service");
                                System.out.println("5.exit");
                                choice = selectChoice(5, input);
                                switch (choice) {
                                    case 1 -> {
                                        System.out.println(emp);
                                    }
                                    case 2 -> {
                                        emp.assignTask();
                                    }
                                    case 3 -> {
                                        System.out.print("Enter new role : ");
                                        input.nextLine(); // تنظيف buffer
                                        String newRole = input.nextLine();
                                        System.out.print("Enter increase Percent : ");
                                        double increasePercent = input.nextDouble();
                                        emp.promote(newRole, increasePercent);
                                    }
                                    case 4 -> {
                                        System.out.println(emp.getYearsOfService());
                                    }
                                    case 5 -> {
                                    }

                                }

                            }
                            case 5 -> {
                                break employeeMenu;
                            }
                        }

                    }
                }
                case 2 -> {
                } // Animal manegment
                case 3 -> {
                } // FeedStock manegemnt

                case 4 -> { // buy/sell manegement
                    operationMenu: while (true) {
                        System.out.println("1.Sell");
                        System.out.println("2.Buy");
                        System.out.println("3.exit");
                        choice = selectChoice(3, input);
                        switch (choice) {
                            case 1 -> {
                                System.out.println("\nAviarbles Animals : ");
                                displayAnimals(animals);
                                System.out.println("Enter id : ");
                                int id = input.nextInt();
                                int index = findAnimalIndex(animals, id);
                                if (index == -1) {
                                    System.out.println("\ncan't fina this animal!\n");
                                    break;
                                }
                                Animal animal = animals.get(index);
                                Operation operation = new Operation();
                                operation.sell(animal);
                                operation.showSell();
                                operations.add(operation);
                                animals.remove(index);
                            }
                            case 2 -> {
                                System.out.println("1.Animal");
                                System.out.println("2.Feed");
                                choice = selectChoice(2, input);
                                switch (choice) {
                                    case 1 -> {
                                        Operation operation = new Operation();
                                        System.out.println("Enter type : ");
                                        System.out.println("1. COW");
                                        System.out.println("2. SHEEP");
                                        System.out.println("3. GOAT");

                                        choice = selectChoice(3, input);

                                        AnimalType type = switch (choice) {
                                            case 1 -> AnimalType.COW;
                                            case 2 -> AnimalType.SHEEP;
                                            case 3 -> AnimalType.GOAT;
                                            default -> null;
                                        };
                                        System.out.print("Enter weight : ");
                                        double weight = input.nextDouble();
                                        System.out.print("Enter age : ");
                                        int age = input.nextInt();

                                        Animal animal = operation.buyAnimal(type, weight, age);
                                        operation.showAnimalPurchase();
                                        animals.add(animal);
                                        operations.add(operation);
                                    }
                                    case 2 -> {
                                        System.out.print("Enter feed type: ");
                                        System.out.println("Enter type : ");
                                        System.out.println("1. BARLEY");
                                        System.out.println("2. CORN");
                                        System.out.println("3. GRASS");

                                        choice = selectChoice(3, input);

                                        FeedType type = switch (choice) {
                                            case 1 -> FeedType.BARLEY;
                                            case 2 -> FeedType.CORN;
                                            case 3 -> FeedType.GRASS;
                                            default -> null;
                                        };

                                        System.out.print("Enter quantity (kg): ");
                                        double quantity = input.nextDouble();

                                        System.out.print("Enter minimum threshold: ");
                                        double minThreshold = input.nextDouble();

                                        System.out.print("Enter price per kg: ");
                                        double price = input.nextDouble();

                                        System.out.print("Enter quality (0-100): ");
                                        int quality = input.nextInt();

                                        System.out.print("Enter expiry year: ");
                                        int year = input.nextInt();

                                        System.out.print("Enter expiry month: ");
                                        int month = input.nextInt();

                                        System.out.print("Enter expiry day: ");
                                        int day = input.nextInt();
                                        input.nextLine(); // تنظيف buffer

                                        System.out.print("Enter storage location: ");
                                        String location = input.nextLine();
                                        Operation operation = new Operation();
                                        SmartFeedStock feed = operation.buyFeed(type, quantity, minThreshold, price,
                                                quality, LocalDate.of(year, month, day), location);
                                        operation.showFeedPurchase();
                                        feedStocks.add(feed);
                                        operations.add(operation);
                                    }                                    
                                    case 3 -> { break operationMenu; }

                                }
                            }
                            case 3 -> {
                                displayOperations(operations);
                            }
                            case 4 -> {
                                break operationMenu;
                            }
                        }
                    }
                }
                case 5 -> {
                    isRunning = false;
                } // exit

            }

        }

        input.close();

    }

    static int selectChoice(int numChoices, Scanner input) {
        System.out.print("Enter your choice : ");
        int choice;
        do {
            choice = input.nextInt();
            if (choice <= 0 || choice > numChoices)
                System.out.print("Invalid choice! enter again : ");
        } while (choice <= 0 || choice > numChoices);
        return choice;
    }

    // String name, String role, String phone, double salary
    static void addEmployee(ArrayList<Employee> List, Scanner input) {
        System.out.print("name : ");
        input.nextLine();
        String name = input.nextLine();
        // input.nextLine();
        System.out.print("role : ");
        // input.nextLine();
        String role = input.nextLine();
        // input.nextLine();
        System.out.print("phone : ");
        // input.nextLine();
        String phone = input.nextLine();
        System.out.print("salary : ");
        double salary = input.nextDouble();

        List.add(new Employee(name, role, phone, salary));

    }

    static void removeEmployee(ArrayList<Employee> employees, int id) {
        boolean removed = employees.removeIf(emp -> emp.getId() == id);
        if (removed)
            System.out.println("Employee[id : " + id + "] has been removed\n");
        else
            System.out.println("!can't find employee[id : " + id + "]\n");
    }

    static void ShowEmployees(ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(employee);

        }
    }

    static int findEmployeeIndex(ArrayList<Employee> employees, int id) {

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                return i; // رجعنا index
            }
        }

        return -1; // غير موجود
    }

    static void displayAnimals(ArrayList<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    static int findAnimalIndex(ArrayList<Animal> animals, int id) {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getId() == id) {
                return i; // رجعنا index
            }
        }

        return -1; // غير موجود
    }

    static void displayOperations(ArrayList<Operation> operations) {
        for (Operation operation : operations) {
                System.out.println(operation.showAnimalPurchase());
                System.out.println(operation.showFeedPurchase());
                System.out.println(operation.showSell());
        }
    }

}
