package com.entreprise.employee;

import com.entreprise.exception.InvalidEmployeeException;

import java.util.*;

public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) throws InvalidEmployeeException {
        if (employee.getName().isEmpty()) {
            throw new InvalidEmployeeException("Le nom de l'employé ne peut pas être vide.");
        }
        if (employee.getSalary() < 0) {
            throw new InvalidEmployeeException("Le salaire ne peut pas être négatif.");
        }
        employees.add(employee);
    }

    public void displayEmployees() {
        employees.forEach(System.out::println);
    }

    public void filterByDepartment(String department) {
        List<Employee> filtered = employees.stream()
                .filter(e -> department.equals(e.getDepartment()))
                .toList();
        filtered.forEach(System.out::println);
    }

    public void sortBySalary() {
        employees.stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .forEach(System.out::println);
    }

    public void totalSalary() {
        double total = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("Total des salaires : " + total);
    }

    public void highestPaidEmployee() {
        Employee highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
        if (highestPaid != null) {
            System.out.println("L'employé le mieux payé : " + highestPaid);
        } else {
            System.out.println("Aucun employé trouvé.");
        }
    }
}
