import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Library{
	//Arsyeja qe e deklaroj ketu eshte sepse nese e bej close sa her qe mbaron kodi ne nje metode, kjo do te nxjeri error
	//Ne stackOverflow pash qe eshte mire ta mbyllesh skannerin kur te mbyllet projekti.
	Scanner stdin = new Scanner(System.in);
	
	//This class should have three ArrayLists. One for authors, one for books and one for members.
	//You should also include private variables name, day, month and year.
	ArrayList<Author> authors;
	ArrayList<Book> books;
	ArrayList<Member> members;
	
	/*You should create three text files: authors.txt, members.txt and books.txt, so you can check
		anytime what authors, books or members you have in your library simply by opening the files on
		your PC. The files should look like the following screenshots:*/
	File authorsFile = new File("C:\\Users\\krist\\eclipse-workspace\\Projekti\\authors.txt");
	File booksFile = new File("C:\\Users\\krist\\eclipse-workspace\\Projekti\\books.txt");	
	File membersFile = new File("C:\\Users\\krist\\eclipse-workspace\\Projekti\\members.txt");
	
	//The constructor only initializes these three ArrayLists.
	Library() throws ClassNotFoundException, IOException{
		Path path = Paths.get("C:\\Users\\krist\\eclipse-workspace\\Projekti\\database.dat");
   
		authors = new ArrayList<Author>();
		books = new ArrayList<Book>();
		members = new ArrayList<Member>(); 
		
		if (Files.exists(path)) {
			restore();
		}
	}
	
	/* kjo esht per te afishuar Press enter to continue */
	
	public static void enterToContinue(){
        System.out.println("Press \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/*Create a save() method that saves all three ArrayLists on a binary file called database.dat. Do not
	forget about the specifics of Serialization. You have to think carefully how this save method is
	going to work, because someone might shut down the program accidentally (or not) and the
	data that is not saved will be lost. You have to prevent that.
	
	*/
	
	public void save() throws IOException {
		//Perdorim bufferedOutputStream per me shume eficence.

		FileOutputStream fstream = new FileOutputStream("C:\\Users\\krist\\eclipse-workspace\\Projekti\\database.dat", false);
		BufferedOutputStream bstream = new BufferedOutputStream(fstream);
		ObjectOutputStream ostream = new ObjectOutputStream(bstream);

		//Serializimi behet me writeObject
		
		ArrayList<ArrayList> savingList = new ArrayList<ArrayList>();
		
		ArrayList<Author> authorsClone = (ArrayList<Author>) authors.clone();
		ArrayList<Author> membersClone = (ArrayList<Author>) members.clone();
		ArrayList<Author> booksClone = (ArrayList<Author>) books.clone();
		
		savingList.add(authorsClone);
		savingList.add(membersClone);
		savingList.add(booksClone);
		
		ostream.writeObject(savingList);

		ostream.close();
		bstream.close();
		fstream.close();
	}
	
	/*Create a restore() method that is called when an object of the Library Class is initialized. This
	method simply reads the content of the database.dat and puts them to the correct ArrayList.*/
	public void restore() throws IOException, ClassNotFoundException{
		FileInputStream fileinputstream = new FileInputStream("C:\\Users\\krist\\eclipse-workspace\\Projekti\\database.dat");
		BufferedInputStream bstream = new BufferedInputStream(fileinputstream);
		ObjectInputStream ostream = new ObjectInputStream(bstream);

		ArrayList<ArrayList> savingList = (ArrayList<ArrayList>) ostream.readObject();
		
		authors = savingList.get(0);
		members = savingList.get(1);
		books = savingList.get(2);
		
		bstream.close();
		ostream.close();
		fileinputstream.close();
	}
	
	
	/*Keto funksine bejne update files, kte e shkruajta jasht kerkeses. 
	Therritet gjithmon sa here qe duhen bere update files.
	  
	Esht pak e gjat, po nuk kisha si ta beja ndryshe*/
	
	public void updateAuthorsFile() throws FileNotFoundException {
		FileOutputStream fstream = new FileOutputStream(authorsFile, false); //kjo false mduket e ben qe te fshihet para se te hapet, sjam i sigurt
		PrintWriter pw = new PrintWriter(fstream);
		
		pw.println("This is the author text file.\n"
				+ "Here you will find all the authors you have in your library.\n");
		for(int i=0; i<authors.size(); i++) {
			pw.println(authors.get(i));
		}
		
		pw.flush();
		pw.close();
	}
	

	public void updateBooksFile() throws FileNotFoundException {
		FileOutputStream fstream = new FileOutputStream(booksFile, false); //kjo false mduket e ben qe te fshihet para se te hapet, sjam i sigurt
		PrintWriter pw = new PrintWriter(fstream);
		
		pw.println("This is the books text file.\n"
				+ "Here you will find all the books you have in your library.\n");
		for(int i=0; i<books.size(); i++) {
			pw.println(books.get(i));
		}
		pw.flush();
		pw.close();
	}
	
	public void updateMembersFile() throws FileNotFoundException {
		FileOutputStream fstream = new FileOutputStream(membersFile, false); //kjo false mduket e ben qe te fshihet para se te hapet, sjam i sigurt
		PrintWriter pw = new PrintWriter(fstream);
		
		pw.println("This is the members text file.\n"
				+ "Here you will find all the members you have in your library.\n");
		for(int i=0; i<members.size(); i++) {
			pw.println(members.get(i));
		}
		pw.flush();
		pw.close();
	}
	
	//Create addAuthor method which initializes an Author object and adds it to its ArrayList.
	public void addAuthor() {
		System.out.print("Enter Author Name: ");
		
		try {
			String authorName = stdin.next();
			
			System.out.println("Enter Author Brithday (Day): ");
			int day = stdin.nextInt();
			
			System.out.println("Enter Author Brithday (Month): ");
			int month = stdin.nextInt();
			
			System.out.println("Enter Author Brithday (Year): ");
			int year = stdin.nextInt();

			authors.add(new Author(authorName, day, month, year));
			save();
			updateAuthorsFile();
		} catch (Exception a) {
			System.out.println("Error! You must enter numerical values for day, month & year!");
			stdin.nextLine();
		}

		enterToContinue();
	}
	
	/*Create addBook method which initializes a Book object and adds it to its ArrayList. Before the
	book is created, it asks you for the name of the Author. If the Author is not found, then a new
	Author should be created.*/
	public void addBook() {
		System.out.println("What is the name of the Author?");
		
		try {
			String authorName = stdin.next();
			int authorIndex = Search.authorName(authorName, this);
			
			Author a = null;
			if(authorIndex == -1) { //nqs nuk e gjen autorin, ateher...
				//creating new author
				int authorDay;
				int authorMonth;
				int authorYear;
				
				System.out.println(authorName + " is not registered.");
				System.out.println("Enter " + authorName + "'s birthday (day)");
				authorDay = stdin.nextInt();
				System.out.println("Enter " + authorName + "'s birthday (month)");
				authorMonth = stdin.nextInt();
				System.out.println("Enter " + authorName + "'s birthday (year)");
				authorYear = stdin.nextInt();
				
				a = new Author(authorName, authorDay, authorMonth, authorYear);
				authors.add(a);
			} 
			
			System.out.println("Enter the book title: ");
			String title = stdin.next();
			
			System.out.println("Enter the book pages: ");
			int pages = stdin.nextInt();
			
			System.out.println("Enter the book ISBN: ");
			String ISBN = stdin.next();
			
			if(authorIndex != -1) { //nqs ekziston autori, merre autorin nga lista autoreve
				Book b = new Book(authors.get(authorIndex), title, pages, ISBN);
				books.add(b);
			} else { //perndryshe, shto autorin qe krijuam
				Book b = new Book(a, title, pages, ISBN);
				books.add(b);
			}
			save();
			updateAuthorsFile();
			updateBooksFile();

		} catch (Exception a) {
			System.out.println("Error: Invalid value entered, book not created.");
			stdin.nextLine();
		}
		
		enterToContinue();
	}
	
	//Create addMember method which initializes a Member object and adds it to its ArrayList.
	//Member(String name, int day, int month, int year){
	public void addMember() {
		String name;
		int day;
		int month;
		int year;
		
		try {
			System.out.println("Enter the new member's name: ");
			name = stdin.next();
			
			System.out.println("Enter the new member's birthday (day, month, year): ");
			day = stdin.nextInt();
			month = stdin.nextInt();
			year = stdin.nextInt();
			
			Member m = new Member(name, day, month, year);
			members.add(m);
			save();
			updateMembersFile();
		} catch (Exception a) {
			System.out.println("Error: Invalid values entered.");
			stdin.nextLine();
		}
		
		enterToContinue();
	}
	
	/*Create a listOfAuthors method which prints all Authors in the ArrayList of the Library class. Do
	the same with listOfBooks() and listOfMembers() methods.*/
	public void listOfAuthors() {
		System.out.println("List of all authors and their info: ");
		for(int i=0; i<authors.size(); i++) {
			System.out.print(authors.get(i));
		}
		System.out.println("");
		enterToContinue();
	}
	
	public void listOfMembers() {
		System.out.println("List of all members and their info: ");
		for(int i=0; i<members.size(); i++) {
			System.out.print(members.get(i));
		}
		System.out.println("");
		enterToContinue();
	}
	
	public void listOfBooks() {
		System.out.println("List of all members and their info: ");
		for(int i=0; i<books.size(); i++) {
			System.out.print(books.get(i));
		}
		System.out.println("");
		enterToContinue();
	}
	
	/*Create displayBooksOfAuthor to print only the books from one Author. When you call this
	function, it asks you for the name of the Author. You have to give the name in order to show the
	books.*/
	public void displayBooksOfAuthor() {
		if(authors.size() != 0) {
			try {
				System.out.println("Choose an author: ");
				for(int i=0; i<authors.size(); i++) {
					System.out.println(i + ". " + authors.get(i).getName());
				}
				
				System.out.print("Your choice: ");
				int choice = stdin.nextInt();
				
				if (choice >= 0 && choice < authors.size()) {
					System.out.println("Author " + authors.get(choice).getName() + "'s books: " + authors.get(choice).getAllBooks());
				}
			} catch(Exception a) {
				System.out.println("Error, invalid value!");
				stdin.nextLine();
			}
		} else {
			System.out.println("There are no authors to choose from!");
		}
		enterToContinue();
	}
	
	//Do the same way with displayBooksOfMember() to show the books a member has read.
	public void displayBooksOfMember() {
		if(members.size() != 0) {
			try {
				System.out.println("Choose a member: ");
				for(int i=0; i<members.size(); i++) {
					System.out.println(i + ". " + members.get(i).getName());
				}
				
				System.out.print("Your choice: ");
				int choice = stdin.nextInt();
				
				if (choice >= 0 && choice < members.size()) {
					System.out.println("Member " + members.get(choice).getName() + "'s books read: " + members.get(choice).getBooksRead());
				} else {
					System.out.println("Invalid choice!");
				}
			} catch(Exception a) {
				System.out.println("Error, invalid value!");
				stdin.nextLine();
			}
		} else {
			System.out.println("There are no members to choose from!");
		}
		enterToContinue();
	}
	
	/*Create modifyAuthor() method which asks the user which Author he wants to modify and then
	what he wants to modify, the name, the day, month or year of his birthday.*/
	public void modifyAuthor() {
		if(authors.size() != 0) {
			try {
				System.out.println("Which author do you want to modify?");
				
				for(int i=0; i<authors.size(); i++) {
					System.out.println(i + ". " + authors.get(i).getName());
				}
				
				System.out.print("Your choice: ");
				int authorIndex = stdin.nextInt();
				
				if(authorIndex >= 0 && authorIndex < authors.size()) {
					System.out.println("What do you want to modify?\n"
							+ "1. Name\n"
							+ "2. Day\n"
							+ "3. Month\n"
							+ "4. Year\n");
					int choice = stdin.nextInt();
					
					switch(choice) {
						case 1:
							System.out.println("Enter new name.");
							String oldName = authors.get(authorIndex).getName();
							String newName = stdin.next();
							authors.get(authorIndex).setName(newName);
							System.out.println(oldName + "'s name successfully changed to " + newName);
							save();
							break;
						case 2:
							System.out.println("Enter new day.");
							int newDay = stdin.nextInt();
							authors.get(authorIndex).setDay(newDay);
							save();
							System.out.println("Day successfully changed.");
							break;
						case 3:
							System.out.println("Enter new month.");
							int newMonth = stdin.nextInt();
							authors.get(authorIndex).setDay(newMonth);
							save();
							System.out.println("Month successfully changed.");
							break;
						case 4:
							System.out.println("Enter new year.");
							int newYear = stdin.nextInt();
							authors.get(authorIndex).setDay(newYear);
							save();
							System.out.println("Year successfully changed.");
							break;
						
						default:
							System.out.println("Inavlid choice.");
							break;
					}
				}
			} catch (Exception a) {
				System.out.println("Error, Invalid value type.");
				stdin.nextLine();
			}
		
		} else {
			System.out.println("There are no authors to modify!");
		}
		enterToContinue();
	}
	
	//The same should be done with modifyMember() method.
	public void modifyMember() {
		try {
			if(members.size() != 0) {
				System.out.println("Which member do you want to modify?");
				
				for(int i=0; i<members.size(); i++) {
					System.out.println(i + ". " + members.get(i).getName());
				}
				
				System.out.print("Your choice: ");
				int memberIndex = stdin.nextInt();
				
				if(memberIndex >= 0 && memberIndex < members.size()) {
					System.out.println("What do you want to modify?\n"
							+ "1. Name\n"
							+ "2. Day\n"
							+ "3. Month\n"
							+ "4. Year\n");
					int choice = stdin.nextInt();
					
					switch(choice) {
						case 1:
							System.out.println("Enter new name.");
							String oldName = members.get(memberIndex).getName();
							String newName = stdin.next();
							members.get(memberIndex).setName(newName);
							System.out.println(oldName + "'s name successfully changed to " + newName);
							save();
							break;
						case 2:
							System.out.println("Enter new day.");
							int newDay = stdin.nextInt();
							members.get(memberIndex).setDay(newDay);
							save();
							System.out.println("Day successfully changed.");
							break;
						case 3:
							System.out.println("Enter new month.");
							int newMonth = stdin.nextInt();
							members.get(memberIndex).setDay(newMonth);
							save();
							System.out.println("Month successfully changed.");
							break;
						case 4:
							System.out.println("Enter new year.");
							int newYear = stdin.nextInt();
							members.get(memberIndex).setDay(newYear);
							save();
							System.out.println("Year successfully changed.");
							break;
						
						default:
							System.out.println("Inavlid choice.");
							break;
					}
				} else {
					System.out.println("Invalid choice");
				}
			} else {
				System.out.println("There are no members to modify!");
			}
		} catch (Exception a) {
			System.out.println("Error, Invalid value type.");
			stdin.nextLine();
		}
		enterToContinue();
	}
	
	/*Create a getBookSuggestion() method which gives you a random book from an Author you
	choose while the book is on their booklist.*/
	public void getBookSuggestion() {
		//Listing authors to choose
		if(authors.size() > 0) {
			System.out.println("Choose an author:");
			for(int i=0; i<authors.size(); i++) {
				System.out.println(i + ". " + authors.get(i).getName());
			}
			
			System.out.print("Your choice: ");
			try {
				int choice = stdin.nextInt();
				
				if(choice >= 0 && choice < authors.size()) {
					authors.get(choice).bookSuggestion();
				} else {
					System.out.println("Invalid choice!");
				}
				
			} catch (Exception a) {
				System.out.println("Error, Invalid value type.");
				stdin.nextLine();
			}
		} else {
			System.out.println("You have no authors created");
		}
		enterToContinue();
	}
	
	/*Create a lendBook() method which means that the Library lends a book to its member and this
	book is added to the members book list. When you call the method, it asks you which book you
	want.*/
	public void lendBook() {
		
		if(members.size() > 0) {		
			System.out.println("Choose a book: ");
			for(int i=0; i<books.size(); i++) {
				System.out.println(i + ". " + books.get(i).getBookTitle());
			}
			
			System.out.print("Your choice: ");
			try {
				int choice = stdin.nextInt();
				
				if (choice >= 0 && choice < books.size()) {
					System.out.println("\nWho is lending this book?");
					for(int i=0; i<members.size(); i++) {
						System.out.println(i + ". " + members.get(i).getName());
					}
					System.out.print("\nYour choice: ");
					
					int secondchoice = stdin.nextInt();

					if(secondchoice >= 0 && secondchoice < members.size()) {
						members.get(secondchoice).addBook(books.get(choice), null);
						System.out.println("Member " + members.get(secondchoice).getName() + " has successfully lended the book!");
					} else {
						System.out.println("Invalid choice");
					}
				} else {
					System.out.println("Invalid choice!");
				}
			} catch (Exception a) {
				System.out.println("Error, Invalid value type.");
				stdin.nextLine();
			}
		} else {
			System.out.println("No members found, cannot lend a book to anyone!");
		}
		enterToContinue();
	}
	
	/*Getters*/
	public ArrayList<Author> getAuthors() {
		return authors;
	}
	
	public ArrayList<Member> getMembers() {
		return members;
	}
	
	public ArrayList<Book> getBooks() {
		return books;
	}
	
	public void close() {
		stdin.close();
	}
}	
