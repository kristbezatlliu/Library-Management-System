import java.util.ArrayList;
import java.io.Serializable;

public class Member extends Person implements Addable, Comparable<Member>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ArrayList<Book> booksTaken;
	
	Member(String name, int day, int month, int year){
		super(name, day, month, year);
		booksTaken = new ArrayList<Book>();
	}
	
	@Override
	public void addBook(Book b, ArrayList<Book> bList) {
		booksTaken.add(b);
	}
	
	public String getBooksRead() {
		StringBuilder builder = new StringBuilder();
		
		if(booksTaken.size() == 0) {
			builder.append("Member " + super.getName() + " has read 0 books!");
			return builder.toString();
		}
		
		for(int i=0; i<booksTaken.size(); i++) {
			builder.append(booksTaken.get(i).getBookTitle() + ", ");
		}
		
		return builder.toString();
	}
	
	public int getBooksReadAmount() {
		return booksTaken.size();
	}
	
	public String getAllBooks() {
		StringBuilder builder = new StringBuilder();
		
		if(booksTaken.size() == 0) {
			builder.append("Member" + super.getName() + "has no books!");
			return builder.toString();
		}
		
		for(int i=0; i<booksTaken.size(); i++) {
			builder.append(booksTaken.get(i).getBookTitle() + ", ");
		}
		
		return builder.toString();
	}
	
	//Create a toString function which returns name, birthday, age and all the books read
	public String toString() {
		return "Author Name: " + super.getName() + ", Age: " + super.getAge() + ", Birthday: " + super.getDay() + "." + super.getMonth() + "." + super.getYear() + "\nBooks read: " + getAllBooks() + "\n";
	}
	
	@Override
	public int compareTo(Member m) {
		if (this.getBooksReadAmount() > m.getBooksReadAmount()) {
			System.out.println(this.getName() + " has more books read than " + m.getName());
			return 1;
		} else if (this.getBooksReadAmount() < m.getBooksReadAmount()) {
			System.out.println(m.getName() + " has read the same amount of books as " + this.getName());
			return 0;
		} else {
			System.out.println(m.getName() + " has more books read than " + this.getName());
			return -1;
		}
	}

}
