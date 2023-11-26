package StudentGradeCalculator;

import java.util.Scanner;

public class GradeCalculator {

	public static void main(String[] args) {
		        Scanner scanner = new Scanner(System.in);

		        // Input: Taking marks obtained in each subject
		        System.out.print("Enter the number of subjects: ");
		        int numSubjects = scanner.nextInt();
		        int[] marksList = new int[numSubjects];

		        for (int i = 0; i < numSubjects; i++) {
		            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
		            marksList[i] = scanner.nextInt();
		        }

		        // Calculate total marks
		        int totalMarks = calculateTotalMarks(marksList);

		        // Calculate average percentage
		        double averagePercentage = calculateAveragePercentage(totalMarks, numSubjects);

		        // Calculate the grade
		        String grade = calculateGrade(averagePercentage);

		        // Display results
		        System.out.println("Total Marks: " + totalMarks);
		        System.out.println("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
		        System.out.println("Grade: " + grade);
		    }

		    // Function to calculate the total marks
		    public static int calculateTotalMarks(int[] marksList) {
		        int total = 0;
		        for (int marks : marksList) {
		            total += marks;
		        }
		        return total;
		    }

		    // Function to calculate the average percentage
		    public static double calculateAveragePercentage(int totalMarks, int numSubjects) {
		        return (double) totalMarks / (numSubjects * 100) * 100;
		    }

		    // Function to calculate the grade
		    public static String calculateGrade(double averagePercentage) {
		        if (averagePercentage >= 90) {
		            return "A+";
		        } else if (averagePercentage >= 80) {
		            return "A";
		        } else if (averagePercentage >= 70) {
		            return "B";
		        } else if (averagePercentage >= 60) {
		            return "C";
		        } else if (averagePercentage >= 50) {
		            return "D";
		        } else {
		            return "F";
		        }
		    }
}
