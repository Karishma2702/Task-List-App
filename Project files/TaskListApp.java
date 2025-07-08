import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskListApp {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1 -> taskList.addTask(getTaskName(scanner));
                case 2 -> taskList.removeTaskPrompt(scanner);
                case 3 -> taskList.listTasks();
                case 4 -> {
                    System.out.print("Are you sure you want to quit? (y/n): ");
                    if (scanner.next().equalsIgnoreCase("y")) {
                        running = false;
                    }
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== Task List Menu ===");
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. List Tasks");
        System.out.println("4. Quit");
        System.out.print("Choose an option: ");
    }

    private static int getUserChoice(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String getTaskName(Scanner scanner) {
        System.out.print("Enter task name: ");
        return scanner.nextLine();
    }
}

class TaskList {
    private final ArrayList<String> tasks = new ArrayList<>();

    public void addTask(String name) {
        tasks.add(name);
        System.out.println("✅ Task added: " + name);
    }

    public void removeTaskPrompt(Scanner scanner) {
        if (isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }

        listTasks();
        System.out.print("Enter task number to remove: ");
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());
            if (isValidTaskNumber(taskNumber)) {
                String removed = tasks.remove(taskNumber - 1);
                System.out.println("🗑️ Removed: " + removed);
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    public void listTasks() {
        if (isEmpty()) {
            System.out.println("📭 Your task list is empty.");
        } else {
            System.out.println("\n📋 Your Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean isValidTaskNumber(int number) {
        return number >= 1 && number <= tasks.size();
    }
}
