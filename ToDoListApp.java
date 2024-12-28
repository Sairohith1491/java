import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String title;
    private String dueDate;
    private boolean isCompleted;

    public Task(String title, String dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String toString() {
        return "Task: " + title + (dueDate.isEmpty() ? "" : ", Due: " + dueDate) + (isCompleted ? " [Completed]" : " [Pending]");
    }
}

public class ToDoListApp {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nTo-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. View Pending Tasks");
            System.out.println("4. View Completed Tasks");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    markTaskAsCompleted();
                    break;
                case 3:
                    viewTasks(false);
                    break;
                case 4:
                    viewTasks(true);
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter due date (optional, leave blank if none): ");
        String dueDate = scanner.nextLine();
        tasks.add(new Task(title, dueDate));
        System.out.println("Task added successfully.");
    }

    private static void markTaskAsCompleted() {
        System.out.println("\nPending Tasks:");
        int index = 1;
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                System.out.println(index + ". " + task);
                index++;
            }
        }

        if (index == 1) {
            System.out.println("No pending tasks.");
            return;
        }

        System.out.print("Enter the task number to mark as completed: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (taskNumber > 0 && taskNumber < index) {
            tasks.get(taskNumber - 1).markAsCompleted();
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void viewTasks(boolean completed) {
        System.out.println(completed ? "\nCompleted Tasks:" : "\nPending Tasks:");
        int index = 1;
        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                System.out.println(index + ". " + task);
                index++;
            }
        }

        if (index == 1) {
            System.out.println(completed ? "No completed tasks." : "No pending tasks.");
        }
    }
}
