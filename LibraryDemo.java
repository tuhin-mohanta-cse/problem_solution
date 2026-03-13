import java.util.*;

class Book {
    int id;
    String name;
    String author;
    boolean issued;

    Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.issued = false;
    }
}

public class LibraryDemo {

    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Logout");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    return;
            }
        }
    }

    static void userMenu() {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Logout");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    issueBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    return;
            }
        }
    }

    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, name, author));
        System.out.println("Book added successfully!");
    }

    static void viewBooks() {
        System.out.println("\n--- Book List ---");

        for (Book b : books) {
            System.out.println("ID: " + b.id +
                    " | Name: " + b.name +
                    " | Author: " + b.author +
                    " | Issued: " + b.issued);
        }
    }

    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id && !b.issued) {
                b.issued = true;
                System.out.println("Book issued successfully!");
                return;
            }
        }

        System.out.println("Book not available.");
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id && b.issued) {
                b.issued = false;
                System.out.println("Book returned successfully!");
                return;
            }
        }

        System.out.println("Invalid book ID.");
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== Library System ===");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin Password: ");
                    String pass = sc.next();

                    if (pass.equals("admin123")) {
                        adminMenu();
                    } else {
                        System.out.println("Wrong password");
                    }
                    break;

                case 2:
                    userMenu();
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}