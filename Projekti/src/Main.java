import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Library universityLibrary = new Library();
		int choice;
		Scanner stdin = new Scanner(System.in);
		
		try {
			do {
				System.out.println("\nMenu of functions");
				System.out.println("-------------------------");
				System.out.println("1. Add Author");
				System.out.println("2. Add Book");
				System.out.println("3. Add Member");
				System.out.println("4. List of Authors in Library");
				System.out.println("5. List of Books in Library");
				System.out.println("6. List of Members in Library");
				System.out.println("7. Get book Suggestion");
				System.out.println("8. Lend book to a member");
				System.out.println("9. Display all the books read by 1 member");
				System.out.println("10. Display all books from one author");
				System.out.println("11. Modify member details");
				System.out.println("12. Modify author details");
				System.out.println("13. Search books with a keyword (not done)");
				System.out.println("0. Exit");
				
				System.out.print("\nYour choice: ");
				choice = stdin.nextInt();
				
				switch(choice) {
					case 0: 
						break;
					case 1:
						//add author
						universityLibrary.addAuthor();
						break;
					case 2:
						//add book
						universityLibrary.addBook();
						break;
					case 3:
						//add member
						universityLibrary.addMember();
						break;
					case 4:
						//list authors
						universityLibrary.listOfAuthors();
						break;
					case 5:
						//list books
						universityLibrary.listOfBooks();
						break;
					case 6:
						//list members
						universityLibrary.listOfMembers();
						break;
					case 7:
						//book suggestion
						universityLibrary.getBookSuggestion();
						break;
					case 8:
						//lend book
						universityLibrary.lendBook();
						break;
					case 9:
						//display books read by member
						universityLibrary.displayBooksOfMember();
						break;
					case 10:
						//display all books from 1 author
						universityLibrary.displayBooksOfAuthor();
						break;
					case 11:
						//modify member details
						universityLibrary.modifyMember();
						break;
					case 12:
						//modify author
						universityLibrary.modifyAuthor();
						break;
					case 13:
						//search book with keyword
						System.out.println("not done yet");
						break;
					
					default:
						System.out.println("ERROR! Invalid choice");
						break; 
				}
			} while (choice != 0);
		} catch (Exception a) {
			a.printStackTrace();
		}
		
		//Mbyllim STDIN tek library.
		universityLibrary.close();
		stdin.close();
	}
}
