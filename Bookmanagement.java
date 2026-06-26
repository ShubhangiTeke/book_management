package projects;
import java.util.*;

class Date {
    private int day;
    private int month;
    private int year;

    public Date() {}

    public void accept(Scanner sc) {

        while (true) {
            try {
                System.out.print("Enter day: ");
                day = sc.nextInt();
                if (day >= 1 && day <= 31) break;
                System.out.println("Invalid day");
            } catch (InputMismatchException e) {
                System.out.println("Day must be number");
                sc.next();
            }
        }

        while (true) {
            try {
                System.out.print("Enter month: ");
                month = sc.nextInt();
                if (month >= 1 && month <= 12) break;
                System.out.println("Invalid month");
            } catch (InputMismatchException e) {
                System.out.println("Month must be number");
                sc.next();
            }
        }

        while (true) {
            try {
                System.out.print("Enter year: ");
                year = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Year must be number");
                sc.next();
            }
        }
    }

    public int getYear() {
        return year;
    }

    public void display() {
        System.out.printf("%02d-%02d-%04d\n", day, month, year);
    }
}

class Book {
    private int bookId;
    private String title;
    private String author;
    private Date publishDate = new Date();

    public int getBookId() {
        return bookId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void accept(Scanner sc, ArrayList<Book> list) {

        // Unique ID check
        while (true) {
            try {
                System.out.print("Enter book id: ");
                bookId = sc.nextInt();

                boolean exists = false;
                for (Book b : list) {
                    if (b.getBookId() == bookId) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) break;

                System.out.println("Book ID already exists!");
            } catch (InputMismatchException e) {
                System.out.println("Book ID must be number");
                sc.next();
            }
        }

        sc.nextLine(); // clear buffer

        System.out.print("Enter title: ");
        title = sc.nextLine();

        System.out.print("Enter author: ");
        author = sc.nextLine();

        publishDate.accept(sc);
    }

    public void display() {
        System.out.println("\n---------------------");
        System.out.println("Book ID   : " + bookId);
        System.out.println("Title     : " + title);
        System.out.println("Author    : " + author);
        System.out.print("Date      : ");
        publishDate.display();
    }
}

public class Bookmanagement{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        int ch;

        do {
            System.out.println("\n====== BOOK MENU ======");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book by ID");
            System.out.println("3. Search Book by Year");
            System.out.println("4. Display All Books");
            System.out.println("5. Delete Book by ID");
            System.out.println("6. Exit");

            while (true) {
                try {
                    System.out.print("Enter choice: ");
                    ch = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Enter valid number!");
                    sc.next();
                }
            }

            switch (ch) {

                case 1:
                    Book b = new Book();
                    b.accept(sc, books);
                    books.add(b);
                    System.out.println("Book added!");
                    break;

                case 2:
                    System.out.print("Enter book id: ");
                    int id = sc.nextInt();

                    boolean found = false;

                    for (Book bk : books) {
                        if (bk.getBookId() == id) {
                            bk.display();
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Book not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();

                    boolean foundYear = false;

                    for (Book bk : books) {
                        if (bk.getPublishDate().getYear() == year) {
                            bk.display();
                            foundYear = true;
                        }
                    }

                    if (!foundYear) {
                        System.out.println("No books found!");
                    }
                    break;

                case 4:
                    if (books.isEmpty()) {
                        System.out.println("No books available!");
                    } else {
                        for (Book bk : books) {
                            bk.display();
                        }
                    }
                    break;

                case 5:
                    System.out.print("Enter book id to delete: ");
                    int delId = sc.nextInt();

                    boolean removed = false;

                    Iterator<Book> itr = books.iterator();
                    while (itr.hasNext()) {
                        Book bk = itr.next();
                        if (bk.getBookId() == delId) {
                            itr.remove();
                            removed = true;
                            break;
                        }
                    }

                    if (removed) {
                        System.out.println("Book deleted successfully!");
                    } else {
                        System.out.println("Book not found!");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (ch != 6);

        sc.close();
    }
}