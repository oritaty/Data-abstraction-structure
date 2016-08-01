import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {

	private int cID;
	private String fullName;
	private String phoneNumber;
	private List<Movie> moviesRenting;

	public Customer(String fName, char mInit, String lName, String phone, int cID) {

		super(fName, mInit, lName);
		this.cID = cID;
		this.fullName = getFullName();
		this.phoneNumber = phone;
		this.moviesRenting = new ArrayList<Movie>();
	}

	public Customer(String fName, String lName, String phone, int cID) {

		super(fName, lName);
		this.cID = cID;
		this.fullName = getFullName();
		this.phoneNumber = phone;
		this.moviesRenting = new ArrayList<Movie>();
	}
	
	public void addNewMovie(Movie m) {
		this.moviesRenting.add(m);
	}
	
	public void removeMovie(Movie m) {
		this.moviesRenting.remove(m);
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public int getCustomerID() {
		return this.cID;
	}
	
	public List<Movie> getMoviesRenting() {
		return this.moviesRenting;
	}
	
	public String printAllMovies() {
		
		String rtn = "";
		for (int i = 0; i < this.moviesRenting.size(); i++) {
			rtn += this.moviesRenting.get(i).getTitle() + "\n";
		}
		return rtn;
	}
}
