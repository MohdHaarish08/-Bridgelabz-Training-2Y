public class SmartLibrarySystem {

    static class Book {
        int bookId;
        String title;
        String author;
        double price;

        Book(int bookId, String title, String author, double price) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.price = price;
        }

        public String toString() {
            return "[" + bookId + "] " + title + " - Rs. " + price;
        }
    }

    static int removeDuplicates(Book[] books, int n) {
        int uniqueIndex = 0;
        for (int i = 1; i < n; i++) {
            if (books[i].bookId != books[uniqueIndex].bookId) {
                uniqueIndex = uniqueIndex + 1;
                books[uniqueIndex] = books[i];
            }
        }
        return uniqueIndex + 1;
    }

    static void searchByTitle(Book[] books, int count, String query) {
        String q = query.toLowerCase();
        System.out.println("Search Results for '" + query + "':");
        for (int i = 0; i < count; i++) {
            String t = books[i].title.toLowerCase();
            if (t.contains(q)) {
                System.out.println("- Found: " + books[i]);
            }
        }
    }

    static void sortByPrice(Book[] books, int count) {
        int swaps = 0;
        for (int i = 0; i < count - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < count; j++) {
                if (books[j].price < books[minIndex].price) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Book temp = books[i];
                books[i] = books[minIndex];
                books[minIndex] = temp;
                swaps = swaps + 1;
            }
        }
        System.out.println("Books Sorted by Price:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + books[i]);
        }
        System.out.println("Total Swaps: " + swaps);
    }

    static int searchByPrice(Book[] books, int count, double target) {
        int low = 0;
        int high = count - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (books[mid].price == target) {
                return mid;
            } else if (books[mid].price < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    static int minBooksForTargetCost(Book[] books, int count, double targetCost) {
        int left = 0;
        double sum = 0;
        int minLength = 999999;
        for (int right = 0; right < count; right++) {
            sum = sum + books[right].price;
            while (sum >= targetCost) {
                int length = right - left + 1;
                if (length < minLength) {
                    minLength = length;
                }
                sum = sum - books[left].price;
                left = left + 1;
            }
        }
        if (minLength == 999999) {
            return 0;
        }
        return minLength;
    }

    public static void main(String[] args) {
        Book[] books = new Book[6];
        books[0] = new Book(101, "Data Structures", "Mark", 400.0);
        books[1] = new Book(101, "Data Structures", "Mark", 400.0);
        books[2] = new Book(102, "Java Basics", "James", 300.0);
        books[3] = new Book(103, "Python Guide", "Guido", 600.0);
        books[4] = new Book(104, "Database Systems", "Raghu", 500.0);
        books[5] = new Book(105, "Computer Networks", "Andrew", 700.0);

        int count = removeDuplicates(books, 6);
        System.out.println("Unique Books Count: " + count);
        for (int i = 0; i < count; i++) {
            System.out.println(books[i]);
        }
        System.out.println();

        Book[] shelfOrder = new Book[count];
        for (int i = 0; i < count; i++) {
            shelfOrder[i] = books[i];
        }

        searchByTitle(books, count, "data");
        System.out.println();

        sortByPrice(books, count);
        System.out.println();

        int index = searchByPrice(books, count, 500.0);
        System.out.println("Found at index: " + index);
        System.out.println();

        int minBooks = minBooksForTargetCost(shelfOrder, count, 1000.0);
        System.out.println("Minimum Consecutive Books Needed: " + minBooks);
    }
}