public class Person {

	private String fName;
	private char mInit;
	private String lName;
	private String fullName;
	
	public Person(String fName, char mInit, String lName) {
		
		this.fName = fName;
		this.mInit = mInit;
		this.lName = lName;
		this.fullName = fName + " " + ("" + mInit).toUpperCase() + ". " + lName;
	}
	
	public Person(String fName, String lName) {
		
		this.fName = fName;
		this.mInit = ' ';
		this.lName = lName;
		this.fullName = fName + "" + mInit + lName;
	}
	
	public String getFirstName() {
		return this.fName;
	}
	
	public char getMInit() {
		return this.mInit;
	}
	
	public String getLastName() {
		return this.lName;
	}
	
	public String getFullName() {
		return this.fullName;
	}
}
