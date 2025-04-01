import com.entreprise.employee.Employee;
import com.entreprise.employee.EmployeeService;
import com.entreprise.exception.InvalidEmployeeException;
import com.entreprise.util.EmployeeDataLoader;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        Scanner scanner = new Scanner(System.in);
        try {
            List<Employee> employees = EmployeeDataLoader.loadEmployees("employees.csv");
            for (Employee employee : employees) {
                employeeService.addEmployee(employee);
            }
        } catch (IOException | InvalidEmployeeException e) {
            System.out.println("Erreur lors du chargement des données : " + e.getMessage());
        }

        while (true) {
            System.out.println("Menu :");
            System.out.println("1. Ajouter un employé");
            System.out.println("2. Afficher tous les employés");
            System.out.println("3. Filtrer par département");
            System.out.println("4. Trier par salaire décroissant");
            System.out.println("5. Calculer le total des salaires");
            System.out.println("6. Afficher l'employé le mieux payé");
            System.out.println("7. Quitter");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Ajout d'un employé
                    System.out.println("Entrez les informations de l'employé (id, nom, département, salaire) :");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    String department = scanner.nextLine();
                    double salary = scanner.nextDouble();
                    try {
                        employeeService.addEmployee(new Employee(id, name, department, salary));
                    } catch (InvalidEmployeeException e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }
                    break;
                case 2:
                    // Afficher tous les employés
                    employeeService.displayEmployees();
                    break;
                case 3:
                    // Filtrer par département
                    System.out.println("Entrez le département : ");
                    String dept = scanner.nextLine();
                    employeeService.filterByDepartment(dept);
                    break;
                case 4:
                    // Trier par salaire décroissant
                    employeeService.sortBySalary();
                    break;
                case 5:
                    // Calculer le total des salaires
                    employeeService.totalSalary();
                    break;
                case 6:
                    // Afficher l'employé le mieux payé
                    employeeService.highestPaidEmployee();
                    break;
                case 7:
                    System.out.println("Au revoir!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
}
