import java.util.Arrays;

public class Movie implements Comparable<Movie>{

	private int mID;
	private String title;
	private String director;
	private String producer;
	private String[] actors;
	private String description;

	public Movie(int mID, String title, String director, String producer,
			String[] actors, String description) {

		this.title = title;
		this.director = director;
		this.producer = producer;
		this.actors = actors;
		this.description = description;
		this.mID = mID;
	}
	
	public int getMId() {
		return this.mID;
	}

	public String getTitle() {
		return this.title;
	}
	
	public String getDirector() {
		return this.director;
	}
	
	public String getProducer() {
		return this.producer;
	}
	
	public String getActors() {
		
		String rtn = "";
		
		for (int i = 0; i < this.actors.length; i++) {
			if (i == this.actors.length - 1) {
				rtn += this.actors[i];
			} else {
				rtn += this.actors[i] + ", ";
			}
		}
		return rtn;
	}
	
	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return "Title: " + getTitle() + "\n" +
				"Director: " + getDirector() + "\n" +
				"Producer: " + getProducer() + "\n" +
				"Actors: " + getActors() + "\n" +
				"Description: " + getDescription() + "\n\n";
	}

	@Override
	public int compareTo(Movie o) {
		
		String thisTitle = this.getTitle();
		String thatTitle = o.getTitle();
		
		if (thisTitle.length() > thatTitle.length()) {
			while (thisTitle.length() > thatTitle.length()) {
				thatTitle += "0";
			}
		} else {
			while (thisTitle.length() < thatTitle.length()) {
				thisTitle += "0";
			}
		}
		assert(thisTitle.length() == thatTitle.length());
		
		for (int i = 0; i < thisTitle.length(); i++) {
			if (thisTitle.charAt(i) > thatTitle.charAt(i)) {
				return -1;
			} else if (thisTitle.charAt(i) < thatTitle.charAt(i)) {
				return 1;
			}
		}
		return 0;
	}
	
	//Tester.
	private static void printAll(Movie[] movies) {
		
		for (Movie m : movies) {
			System.out.println(m.mID + " " + m.getTitle());
		}
	}
	
	public static void main(String[] args) {
		
		String[] actors = {"Tim R", "Morgan F"};
		Movie m1 = new Movie(0, "Shawshank Redemption", "Frank D", "Warner", actors, "Good one");
		Movie m2 = new Movie(1, "Shawsssssshank Redemption", "Frank D", "Warner", actors, "Good one");
		Movie m3 = new Movie(2, "Shawsshank Redemption", "Frank D", "Warner", actors, "Good one");
		Movie m4 = new Movie(3, "Shawssshank Redemption", "Frank D", "Warner", actors, "Good one");
		Movie m5 = new Movie(4, "Shawsshank Redemption", "Frank D", "Warner", actors, "Good one");
		
		Movie[] movies = {m1, m2, m3, m4, m5};
		printAll(movies);
		System.out.println("==========================");
		Arrays.sort(movies);
		printAll(movies);
	}
}

