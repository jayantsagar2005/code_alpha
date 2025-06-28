package task1_student_grade_tracker;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("===== Student Grade Tracker =====");

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter name of student " + i + ": ");
            String name = scanner.nextLine();

            System.out.print("Enter grade for " + name + ": ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            students.add(new Student(name, grade));
        }

        // Calculations
        double total = 0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;
        String topStudent = "", lowStudent = "";

        for (Student s : students) {
            total += s.grade;

            if (s.grade > highest) {
                highest = s.grade;
                topStudent = s.name;
            }else if(s.grade == highest){
                topStudent = topStudent + ", " + s.name;
            }

            if (s.grade < lowest) {
                lowest = s.grade;
                lowStudent = s.name;
            }else if(s.grade == lowest){
                topStudent = topStudent + ", " + s.name;
            }

        }

        double average = total / students.size();

        // Display summary
        System.out.println("\n    ===== Summary Report =====");
        System.out.printf("Average Grade: %.2f\n", average);
        System.out.printf("Highest Grade: %.2f (%s)\n", highest, topStudent);
        System.out.printf("Lowest Grade: %.2f (%s)\n", lowest, lowStudent);

        System.out.println("\n===== All Students =====");
        for (Student s : students) {
            System.out.printf("Name: %-15s Grade: %.2f\n", s.name, s.grade);
        }

        scanner.close();
    }
}
