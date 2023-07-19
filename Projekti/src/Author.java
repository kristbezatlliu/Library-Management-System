import java.util.ArrayList;
import java.io.Serializable;

public class Author extends Person implements Addable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ArrayList<Book> booklist;
	
	//Build one constructor which needs all the variables of the Person class and initializes the ArrayList<> of Books. 
	Author(String name, int day, int month, int year){
		super(name, day, month, year);
		booklist = new ArrayList<Book>();
	}
	
	public ArrayList<Book> getBooks(){
		return booklist;
	}
	
	// Implement the addBook method which adds a book to the ArrayList. 
	public void addBook(Book b, ArrayList<Book> bList) {
		booklist.add(b);
	}
	
	//Create a method which creates a StringBuilder of all the books in the ArrayList and returns the StringBuilder as a String. 
	public String getAllBooks() {
		StringBuilder builder = new StringBuilder();
		
		if(booklist.size() == 0) {
			builder.append("Author " + super.getName() + " has no books!");
			return builder.toString();
		}
		
		for(int i=0; i<booklist.size(); i++) {
			builder.append(booklist.get(i).getBookTitle() + ", ");
		}
		
		return builder.toString();
	}
	
	//Create a method bookSuggestion, which prints on the screen randomly one book from the ArrayList of books. 
	public void bookSuggestion() {
		int randombook = (int) (Math.random() * booklist.size());
 		System.out.println("Suggested book: " + booklist.get(randombook).getBookTitle() + " written by: " + booklist.get(randombook).getBookAuthor().getName());
	}
	
	//Create a method getBookIndex which returns the index of a Book. 
	public int getBookIndex(String bookName) {
		for(int i=0; i<booklist.size(); i++) {
			if (bookName.equalsIgnoreCase(booklist.get(i).getBookTitle())) {
				return i;
			}
		}
		System.out.println("Book not found!");
		return -1;
	}
	
	//Create a method replaceBook which replaces a book with a new one in the list with a given index. 
	public void replaceBook(int index, Book newBook) {
		try {
			booklist.set(index, newBook);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error replacing book: Invalid index");
		}
	}
	
	//Create a toString function which returns name, birthday and age. 
	public String toString() {
		return "Author: " + super.getName() + ", Age: " + super.getAge() + ", Birthday: " + super.getDay() + "." + super.getMonth() + "." + super.getYear() + "\n";
	}
}
