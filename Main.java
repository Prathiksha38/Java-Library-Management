import java.util.*;
import java.io.*;

class Book {
    int bookId;
    String bookName;
    String author;
    String price;
    boolean available;

    Book(int bookId, String bookName, String author, String price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.available = true;
    }
}

class Student {
    int id;
    String name;
    int password;
    boolean borrowed;

    Student(int id, String name, int password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.borrowed = false;
    }
}

public class Main {

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static int bookId = 1000;
    static int studentId = 100;

    public static void main(String[] args) {

        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");

        while (true) {
            System.out.println("\n1. Admin Login");
            System.out.println("2. Student");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            if (choice == 1) {
                adminLogin();
            } 
            else if (choice == 2) {
                studentMenu();
            } 
            else {
                System.out.println("Thank you!");
                break;
            }
        }
    }

    static void adminLogin() {

        System.out.print("Admin ID: ");
        int id = sc.nextInt();

        System.out.print("Password: ");
        int pass = sc.nextInt();

        if (id == 123 && pass == 345) {

            while (true) {

                System.out.println("\n1. Add Books");
                System.out.println("2. View Books");
                System.out.println("3. View Issued Books");
                System.out.println("4. Exit");

                int choice = sc.nextInt();

                if (choice == 1) {
                    addBooks();
                }
                else if (choice == 2) {
                    viewBooks();
                }
                else if (choice == 3) {
                    viewIssuedBooks();
                }
                else {
                    break;
                }
            }
        }
        else {
            System.out.println("Invalid admin credentials");
        }
    }

    static void addBooks() {

        System.out.print("How many books: ");
        int count = sc.nextInt();

        for (int i = 0; i < count; i++) {

            bookId++;

            System.out.print("Book Name: ");
            String name = sc.next();

            System.out.print("Author: ");
            String author = sc.next();

            System.out.print("Price: ");
            String price = sc.next();

            Book book = new Book(bookId, name, author, price);
            books.add(book);

            System.out.println("Book added successfully!");
        }
    }

    static void viewBooks() {

        for (Book b : books) {

            System.out.println("\nBook ID: " + b.bookId);
            System.out.println("Book Name: " + b.bookName);
            System.out.println("Author: " + b.author);
            System.out.println("Price: " + b.price);

            if (b.available)
                System.out.println("Available");
            else
                System.out.println("Not Available");
        }
    }

    static void viewIssuedBooks() {

        for (Book b : books) {
            if (!b.available) {

                System.out.println("\nBook ID: " + b.bookId);
                System.out.println("Book Name: " + b.bookName);
                System.out.println("Author: " + b.author);
                System.out.println("Price: " + b.price);
            }
        }
    }

    static void studentMenu() {

        while (true) {

            System.out.println("\n1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            if (choice == 1) {
                signUp();
            }
            else if (choice == 2) {
                signIn();
            }
            else {
                break;
            }
        }
    }

    static void signUp() {

        studentId++;

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Password: ");
        int pass = sc.nextInt();

        Student student = new Student(studentId, name, pass);
        students.add(student);

        System.out.println("Registration successful!");
        System.out.println("Your ID: " + studentId);
    }

    static void signIn() {

        System.out.print("Student ID: ");
        int id = sc.nextInt();

        System.out.print("Password: ");
        int pass = sc.nextInt();

        for (Student s : students) {

            if (s.id == id && s.password == pass) {

                System.out.println("Login Successful");

                while (true) {

                    System.out.println("\n1. View Books");
                    System.out.println("2. Borrow Book");
                    System.out.println("3. Return Book");
                    System.out.println("4. Exit");

                    int choice = sc.nextInt();

                    if (choice == 1) {
                        viewBooks();
                    }
                    else if (choice == 2) {
                        borrowBook(s);
                    }
                    else if (choice == 3) {
                        returnBook(s);
                    }
                    else {
                        break;
                    }
                }
            }
        }
    }

    static void borrowBook(Student s) {

        if (s.borrowed) {
            System.out.println("You already borrowed a book.");
            return;
        }

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        for (Book b : books) {

            if (b.bookId == id && b.available) {

                b.available = false;
                s.borrowed = true;

                System.out.println("Book borrowed successfully!");
                return;
            }
        }

        System.out.println("Book not available");
    }

    static void returnBook(Student s) {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        for (Book b : books) {

            if (b.bookId == id && !b.available) {

                b.available = true;
                s.borrowed = false;

                System.out.println("Book returned successfully!");
                return;
            }
        }

        System.out.println("Invalid book ID");
    }
}