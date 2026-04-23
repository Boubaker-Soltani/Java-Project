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
                    employeeMenu:
                    while(true){
                        System.out.println("1.Add new Employee");
                        System.out.println("2.view employees records");
                        System.out.println("3.Delete employee");
                        System.out.println("4.exit");
                        choice = selectChoice(4, input);
                        
                        switch (choice) {
                            case 1 -> { addEmployee(employees, input); }
                            case 2 -> { ShowEmployees(employees); }
                            case 3 -> { System.out.print("Enter employee Id : ");
                                        int id = input.nextInt();
                                        removeEmployee(employees, id);
                                    }
                            case 4 -> { break employeeMenu; }
                        }
                            
                            
                    }
                }
                case 2 -> {} // Animal manegment
                case 3 -> {} // FeedStock manegemnt
                case 4 -> {} // buy/sell manegement
                case 5 -> { isRunning = false; } // exit
                
            }
        
        
        
        
        
        
        }

        input.close();

    }

    static int selectChoice(int numChoices, Scanner input){
        System.out.print("Enter your choice : ");
        int choice;
        do{
            choice = input.nextInt();
            if(choice <= 0 ||choice > numChoices) System.out.print("Invalid choice! enter again : ");
        }while(choice <= 0 ||choice > numChoices);
            return choice;
    }
    // String name, String role, String phone, double salary
    static void addEmployee(ArrayList<Employee> List ,Scanner input){
        System.out.print("name : ");
        input.nextLine();
        String name = input.nextLine();
        //input.nextLine();
        System.out.print("role : ");
        //input.nextLine();
        String role = input.nextLine();
        //input.nextLine();
        System.out.print("phone : ");
        //input.nextLine();
        String phone = input.nextLine();
        System.out.print("salary : ");
        double salary = input.nextDouble();

        List.add(new Employee(name, role, phone, salary));
    
    }
    static void removeEmployee(ArrayList<Employee> employees, int id){
        boolean removed = employees.removeIf(emp -> emp.getId() == id);
        if(removed) System.out.println("Employee[id : " + id + "] has been removed\n" );
        else System.out.println("!can't find employee[id : " + id + "]\n");
    }

    static void ShowEmployees(ArrayList<Employee> employees){
            for(Employee employee : employees){
                System.out.println(employee);

            }
    }
}
