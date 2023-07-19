//PIKA 5
import java.io.Serializable;

public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private Author author;
	private String title;
	private int pages;
	private String ISBN;
	
	//Build one constructor which needs all the variables as parameters to be initialized. The Book
	//should also be put to the ArrayList of the Author by using addBook method.
	Book(Author author, String title, int pages, String ISBN){
		this.author = author;
		this.title = title;
		this.pages = pages;
		this.ISBN = ISBN;
		author.addBook(this, null);
	}
	
	public Author getBookAuthor() {
		return author;
	}
	
	//Build a method to update the title of the book.
	public void updateBookTitle(String title) {
		this.title = title;
	}
	
	//Build another method to retrieve the title of the book.
	public String getBookTitle() {
		return title;
	}
	
	//Build one last method which is called booksLikeThis which gives you a random book of the same Author.
	public Book booksLikeThis() {
		int randomBookNumber = (int) (Math.random() * author.getBooks().size()); 
		return author.getBooks().get(randomBookNumber);
	}
	
	//Build a toString function which returns all the details of the book.
	public String toString() {
		return "Book: " + title + " written by: " + author.getName() + ", Pages: " + pages + ", ISBN: " + ISBN + "\n";
	}
}
