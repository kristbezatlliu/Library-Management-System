/*Build a class called Search which is used to search on the ArrayLists of a Library object.
● This class may not have any variables and the methods should be static.*/
import java.util.ArrayList;

public class Search {
	/*Build authorName(name) method to search through the ArrayList with the name of the author. If
	it finds the name, it should return the index of the object in the ArrayList. On the contrary it
	returns -1 and prints on the screen: “Author was not found”.*/

	public static int authorName(String name, Library libraryClass) {
		ArrayList<Author> authors = libraryClass.getAuthors();
		int authorIndex = -1;
		
		for(int i=0; i<authors.size(); i++) {
			if(authors.get(i).getName().equalsIgnoreCase(name)) {
				authorIndex = i;
			}
		}
		
		if (authorIndex == -1) {
			System.out.println("Author not found");
		}
		
		return authorIndex;
	}
	
	/*Same thing happens with the method memberName(name) which searches on the ArrayList of
	members.*/
	public static int memberName(String name, Library libraryClass) {
		ArrayList<Member> members = libraryClass.getMembers();
		int memberIndex = -1;
		
		for(int i=0; i<members.size(); i++) {
			if(members.get(i).getName().equalsIgnoreCase(name)) {
				memberIndex = i;
			}
		}
		
		if (memberIndex == -1) {
			System.out.println("Member not found");
		}
		
		return memberIndex;
	}
	
	/*Build the same to search for books in the bookName(name).*/
	public static int bookName(String name, Library libraryClass) {
		ArrayList<Book> books = libraryClass.getBooks();
		int bookIndex = -1;
		
		for(int i=0; i<books.size(); i++) {
			if(books.get(i).getBookTitle().equalsIgnoreCase(name)) {
				bookIndex = i;
			}
		}
		
		if (bookIndex == -1) {
			System.out.println("Book not found");
		}
		
		return bookIndex;
	}
}
