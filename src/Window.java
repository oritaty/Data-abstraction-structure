/**
 * Final project for Data abstraction/structure class.
 * Implementing a video shop simulator by using Array/LinkedList.
 * @author yusuke
 * class CSE274_C
 * date May 8, 2016
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Window extends JFrame implements ActionListener {

	// Constants.
	public static final int HEIGHT = 650;
	public static final int WIDTH = 500;
	public static final int PHOTO_HEIGHT = 300;
	public static final Color DEFAULT_BACKGROUND_COLOR = new Color(15, 82, 188);
	public static final Color DEFAULT_FONT_COLOR = new Color(222, 176, 55);

	// Instance variables.
	private List<Movie> movies;
	private List<Customer> customers;
	private Customer customerLoggingIn;

	// Frames & Panels.
	private JPanel mainPanel;
	private final JPanel homePanel = new HomePanel(this);
	private final JPanel moviePanel = new MoviePanel(this);
	private final JPanel customerPanel = new CustomerPanel(this);
	private final JPanel rentPanel = new RentPanel(this);
	private final JPanel returnPanel = new ReturnPanel(this);
	private final JPanel loginPanel = new LoginPanel(this);
	private static final JLabel photoLabel = createPhotoLabel();
	private JLabel nameLabel = createNameLabel();
	private final JPanel[] panels = { homePanel, moviePanel, customerPanel,
			rentPanel, returnPanel, loginPanel };

	// Constructor.
	public Window() {

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(DEFAULT_BACKGROUND_COLOR);

		mainPanel.add(photoLabel);
		mainPanel.add(nameLabel);
		homePanel.setVisible(true);
		customerPanel.setVisible(false);
		moviePanel.setVisible(false);
		rentPanel.setVisible(false);
		returnPanel.setVisible(false);

		getContentPane().add(mainPanel, BorderLayout.CENTER);
		movies = new ArrayList<Movie>();
		customers = new LinkedList<Customer>();
		customerLoggingIn = null;
	}

	private JLabel createNameLabel() {

		String message = null;

		if (customerLoggingIn == null) {
			message = "You are not logged-in yet.";
		} else {
			message = "You are logging-in as "
					+ this.customerLoggingIn.getFullName();
		}

		JLabel label = new JLabel(message, SwingConstants.CENTER);
		label.setBounds(0, 600, WIDTH, 30);
		label.setForeground(DEFAULT_FONT_COLOR);
		label.setBorder(BorderFactory
				.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		return label;
	}

	private static JLabel createPhotoLabel() {

		JLabel label = null;

		try {
			BufferedImage image = ImageIO.read(new File("BlockbusterMod.jpg"));
			label = new JLabel(new ImageIcon(image));
			label.setBounds(0, 0, WIDTH, PHOTO_HEIGHT);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return label;
	}

	// May not need this.
	public void actionPerformed(ActionEvent e) {

	}

	private void replacePanel(JPanel panel) {

		for (int i = 0; i < panels.length; i++) {
			if (panels[i] == panel) {
				panels[i].setVisible(true);
			} else {
				panels[i].setVisible(false);
			}
		}
	}

	public static void main(String[] args) throws IOException {

		Window window = new Window();
		window.setTitle("My Video Shop");
		window.setBounds(0, 0, WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	private static class HomePanel extends JPanel implements ActionListener {

		private Window window;
		private JLabel label1, label2;
		private JComboBox<String> box;
		private JButton submit;
		public static final String[] OPTIONS = { "Add a new movie",
				"Register a new customer", "Rent a movie", "Return a movie",
				"Login to the system." };

		public HomePanel(Window win) {

			this.window = win;
			this.setBounds(0, window.PHOTO_HEIGHT, window.WIDTH, window.HEIGHT
					- window.PHOTO_HEIGHT - 50); // height of nameLabel.
			this.setBackground(DEFAULT_BACKGROUND_COLOR);
			this.setVisible(false);
			this.setLayout(null);

			label1 = new JLabel("Welcome to B***kbuster !!");
			label1.setBounds(50, 25, 400, 40);
			label1.setForeground(DEFAULT_FONT_COLOR);
			label1.setFont(new Font("Century", Font.ITALIC, 30));
			this.add(label1);

			label2 = new JLabel("What would you like to do today ?");
			label2.setForeground(DEFAULT_FONT_COLOR);
			label2.setBounds(140, 100, 300, 40);
			this.add(label2);

			box = new JComboBox<String>(OPTIONS);
			box.setBounds(150, 160, 200, 30);
			box.setSelectedIndex(0);
			box.addActionListener(this);
			this.add(box);

			submit = new JButton("Submit");
			submit.setBounds(200, 235, 100, 30);
			submit.addActionListener(this);
			this.add(submit);

			window.add(this);
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == submit) {
				switch (box.getSelectedIndex()) {
				case 0:
					window.replacePanel(window.moviePanel);
					break;
				case 1:
					window.replacePanel(window.customerPanel);
					break;
				case 2:
					window.replacePanel(window.rentPanel);
					break;
				case 3:
					window.replacePanel(window.returnPanel);
					break;
				case 4:
					window.replacePanel(window.loginPanel);
					break;
				}
			}
		}
	}

	private static class MoviePanel extends JPanel implements ActionListener {

		private Window window;
		private JLabel label1, label2, label3, label4, label5, label6;
		private JTextField entry1, entry2, entry3, entry4, entry5;
		private JButton submit, home;

		public MoviePanel(Window win) {

			this.window = win;
			this.setBackground(DEFAULT_BACKGROUND_COLOR);
			this.setBounds(0, window.PHOTO_HEIGHT, window.WIDTH, window.HEIGHT
					- window.PHOTO_HEIGHT - 50);
			this.setVisible(false);
			this.setLayout(null);

			label1 = new JLabel("Add a new movie to the list.");
			label1.setBounds(30, 15, 500, 40);
			label1.setForeground(DEFAULT_FONT_COLOR);
			label1.setFont(new Font("Century", Font.ITALIC, 30));
			this.add(label1);

			label2 = new JLabel("Title: ");
			label2.setBounds(30, 60, 300, 40);
			label2.setForeground(DEFAULT_FONT_COLOR);
			this.add(label2);

			entry1 = new JTextField("");
			entry1.setBounds(120, 70, 300, 20);
			entry1.addActionListener(this);
			this.add(entry1);

			label3 = new JLabel("Director: ");
			label3.setBounds(30, 100, 300, 40);
			label3.setForeground(DEFAULT_FONT_COLOR);
			this.add(label3);

			entry2 = new JTextField();
			entry2.setBounds(120, 110, 300, 20);
			entry2.addActionListener(this);
			this.add(entry2);

			label4 = new JLabel("Producer: ");
			label4.setBounds(30, 140, 300, 40);
			label4.setForeground(DEFAULT_FONT_COLOR);
			this.add(label4);

			entry3 = new JTextField("");
			entry3.setBounds(120, 150, 300, 20);
			entry3.addActionListener(this);
			this.add(entry3);

			label5 = new JLabel("<html><p>"
					+ "Actor(s): <br/>(Comma seperated)" + "</p></html>");
			label5.setBounds(30, 190, 300, 40);
			label5.setForeground(DEFAULT_FONT_COLOR);
			this.add(label5);

			entry4 = new JTextField("");
			entry4.setBounds(120, 190, 300, 20);
			entry4.addActionListener(this);
			this.add(entry4);

			label6 = new JLabel("Description: ");
			label6.setBounds(30, 220, 300, 40);
			label6.setForeground(DEFAULT_FONT_COLOR);
			this.add(label6);

			entry5 = new JTextField("");
			entry5.setBounds(120, 230, 300, 20);
			entry5.addActionListener(this);
			this.add(entry5);

			submit = new JButton("Submit");
			submit.setBounds(140, 260, 100, 30);
			submit.addActionListener(this);
			this.add(submit);

			home = new JButton("Home");
			home.setBounds(240, 260, 100, 30);
			home.addActionListener(this);
			this.add(home);

			window.add(this);
		}

		private void clear() {

			this.entry1.setText("");
			this.entry2.setText("");
			this.entry3.setText("");
			this.entry4.setText("");
			this.entry5.setText("");
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == submit) {

				String title = entry1.getText();
				String director = entry2.getText();
				String producer = entry3.getText();
				String[] actors = entry4.getText().split(", ");
				String description = entry5.getText();

				if (description == null) {
					description = "No review.";
				}

				if (title.isEmpty() || director.isEmpty() || producer.isEmpty()
						|| entry4.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Invalid Imput. "
							+ "You must fill everything other than the description form. "
							+ "Please ry again");
					clear();
				} else {
					Movie newMovie = new Movie(window.movies.size(), title,
							director, producer, actors, description);
					window.movies.add(newMovie);
					clear();
				}
			} else if (e.getSource() == home) {
				window.replacePanel(window.homePanel);
				clear();
			}
		}
	}

	private static class CustomerPanel extends JPanel implements ActionListener {

		private Window window;
		private JLabel label1, label2, label3, label4, label5, label6, label7;
		private JTextField entry1, entry2, entry3, entry4, entry5, entry6;
		private JButton submit, home;

		public CustomerPanel(Window win) {

			this.window = win;
			this.setBounds(0, window.PHOTO_HEIGHT, window.WIDTH, window.HEIGHT
					- window.PHOTO_HEIGHT - 50);
			this.setBackground(DEFAULT_BACKGROUND_COLOR);
			this.setVisible(false);
			this.setLayout(null);

			label1 = new JLabel("Add a new customer to the list.");
			label1.setBounds(20, 15, 500, 40);
			label1.setForeground(DEFAULT_FONT_COLOR);
			label1.setFont(new Font("Century", Font.ITALIC, 30));
			this.add(label1);

			label2 = new JLabel("First: ");
			label2.setBounds(130, 60, 300, 40);
			label2.setForeground(DEFAULT_FONT_COLOR);
			this.add(label2);

			entry1 = new JTextField();
			entry1.setBounds(180, 70, 150, 20);
			entry1.addActionListener(this);
			this.add(entry1);

			label3 = new JLabel("Middle: ");
			label3.setBounds(130, 110, 300, 40);
			label3.setForeground(DEFAULT_FONT_COLOR);
			this.add(label3);

			entry2 = new JTextField();
			entry2.setBounds(180, 120, 150, 20);
			entry2.addActionListener(this);
			this.add(entry2);

			label4 = new JLabel("Last: ");
			label4.setBounds(130, 160, 300, 40);
			label4.setForeground(DEFAULT_FONT_COLOR);
			this.add(label4);

			entry3 = new JTextField();
			entry3.setBounds(180, 170, 150, 20);
			entry3.addActionListener(this);
			this.add(entry3);

			label5 = new JLabel("Phone: ");
			label5.setBounds(130, 210, 300, 40);
			label5.setForeground(DEFAULT_FONT_COLOR);
			this.add(label5);

			entry4 = new JTextField();
			entry4.setBounds(180, 220, 40, 20);
			entry4.addActionListener(this);
			this.add(entry4);

			label6 = new JLabel(" - ");
			label6.setSize(30, 40);
			label6.setLocation(217, 210);
			label6.setForeground(DEFAULT_FONT_COLOR);
			this.add(label6);

			entry5 = new JTextField();
			entry5.setBounds(230, 220, 40, 20);
			entry5.addActionListener(this);
			this.add(entry5);

			label7 = new JLabel(" - ");
			label7.setSize(30, 40);
			label7.setLocation(267, 210);
			label7.setForeground(DEFAULT_FONT_COLOR);
			this.add(label7);

			entry6 = new JTextField();
			entry6.setBounds(280, 220, 40, 20);
			entry6.addActionListener(this);
			this.add(entry6);

			submit = new JButton("Submit");
			submit.setBounds(140, 260, 100, 30);
			submit.addActionListener(this);
			this.add(submit);

			home = new JButton("Home");
			home.setBounds(240, 260, 100, 30);
			home.addActionListener(this);
			this.add(home);

			window.add(this);
		}

		private void clear() {

			this.entry1.setText("");
			this.entry2.setText("");
			this.entry3.setText("");
			this.entry4.setText("");
			this.entry5.setText("");
			this.entry6.setText("");
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == submit) {
				
				char mInit;
				String first = entry1.getText();
				String last = entry3.getText();
				String fDigits = entry4.getText();
				String mDigits = entry5.getText();
				String lDigits = entry6.getText();

				if (entry2.getText().isEmpty()
						|| entry2.getText().equalsIgnoreCase("none")) {
					mInit = ' ';
				} else {
					mInit = entry2.getText().charAt(0);
				}
				
				String alert = "Invalid input on ";
				boolean isInvalidInput;
				if (first.isEmpty()) {
					alert += "your first name. Please try again.";
					isInvalidInput = true;
				} else if (last.isEmpty()) {
					alert += "your last name. Please try again.";
					isInvalidInput = true;
				} else if ((fDigits.isEmpty() || fDigits.length() != 3) 
						|| (mDigits.isEmpty() || mDigits.length() != 3)
						|| (lDigits.isEmpty() || lDigits.length() != 4)) {
					alert += "your phone number. Please follow 3-3-4 format.";
					isInvalidInput = true;
				} else {
					alert = null;
					isInvalidInput = false;
				}
					
				if (isInvalidInput) {		
					JOptionPane.showMessageDialog(this, alert);
					isInvalidInput = false;
					clear();
				} else {
					boolean isAlreadyAMember = false;
					String targetName, targetPhone;
					for (int i = 0; i < window.customers.size(); i++) {
						targetName = window.customers.get(i).getFullName();
						targetPhone = window.customers.get(i).getPhoneNumber();
						if ((targetName.equals(first + " " + mInit + ". " + last) || 
								targetName.equals(first + " " + last)) && 
								targetPhone.equals(fDigits + "-" + mDigits + "-" + lDigits)) {
							isAlreadyAMember = true;
							break;
						}
					}

					if (isAlreadyAMember) {
						JOptionPane.showMessageDialog(this, "" + first
								+ ", you are already our member.");
						clear();
					} else {
						Customer newCustomer;
						if (mInit == ' ' || entry2.getText().isEmpty()) {
							newCustomer = new Customer(first, last, (fDigits
									+ "-" + mDigits + "-" + lDigits),
									window.customers.size());

						} else {
							newCustomer = new Customer(first, mInit, last,
									(fDigits + "-" + mDigits + "-" + lDigits),
									window.customers.size());
						}
						window.customers.add(newCustomer);
						JOptionPane.showMessageDialog(
								this,
								"Your customer ID number is: "
										+ newCustomer.getCustomerID()
										+ ".\n Please keep remember that.");
						clear();
					}
				}
			} else if (e.getSource() == home) {
				window.replacePanel(window.homePanel);
				clear();
			}
		}
	}

	private static class RentPanel extends JPanel implements ActionListener {

		private Window window;
		private JLabel label1, label2;
		private JButton submit, home;
		private JComboBox<String> box;
		private JOptionPane result;
		private JScrollPane scrollablePane;
		private final String[] OPTIONS = { "Display all (default)",
				"Without duplicates", "By alphabetically (coming soon)" };

		public RentPanel(Window win) {

			this.window = win;
			this.setBounds(0, window.PHOTO_HEIGHT, window.WIDTH, window.HEIGHT
					- window.PHOTO_HEIGHT - 50);
			this.setBackground(DEFAULT_BACKGROUND_COLOR);
			this.setVisible(false);
			this.setLayout(null);

			label1 = new JLabel("Choose a movie to rent.");
			label1.setBounds(75, 15, 500, 40);
			label1.setForeground(DEFAULT_FONT_COLOR);
			label1.setFont(new Font("Century", Font.ITALIC, 30));
			this.add(label1);

			label2 = new JLabel("How do you like to search for movies ?");
			label2.setBounds(125, 100, 400, 40);
			label2.setForeground(DEFAULT_FONT_COLOR);
			this.add(label2);

			box = new JComboBox<String>(OPTIONS);
			box.setBounds(145, 160, 200, 40);
			box.addActionListener(this);
			box.setSelectedIndex(0);
			this.add(box);

			submit = new JButton("Submit");
			submit.setBounds(140, 260, 100, 30);
			submit.addActionListener(this);
			this.add(submit);

			home = new JButton("Home");
			home.setBounds(240, 260, 100, 30);
			home.addActionListener(this);
			this.add(home);

			window.add(this);
		}

		private List<Movie> availabilityList(List<Movie> list) {

			List<Movie> rtn = new LinkedList<Movie>();
			List<String> nameList = new LinkedList<String>();

			for (int i = 0; i < list.size(); i++) {
				if (!nameList.contains(list.get(i).getTitle())) {
					rtn.add(list.get(i));
					nameList.add(list.get(i).getTitle());
				}
			}
			return rtn;
		}

		// Sort movies alphabetically.
		private List<Movie> alphabeticalSort(List<Movie> list) {

			Collections.sort(list);

			// Because Collections.sort() in String is in reverse alphabetical
			// order...
			Collections.reverse(list);
			return list;
		}

		private void displayMovies(List<Movie> list) {

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(list.size(), 2));

			if (list.size() == 0) {
				JLabel label = new JLabel("No result.", SwingConstants.CENTER);
				panel.add(label);
			} else {
				for (int i = 0; i < list.size(); i++) {

					int index = i;
					String movieTitle = list.get(i).getTitle();
					JLabel label = new JLabel("<html><p>Title: ",
							SwingConstants.CENTER);
					JButton button = new JButton("<html><u>" + movieTitle
							+ "</u></html>");
					button.setBorderPainted(false);
					button.setForeground(Color.BLUE);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (e.getSource() == button) {
								String[] choices = { "Cancel",
										"Rent this movie" };
								JPanel panel2 = new JPanel();
								panel2.setLayout(new BorderLayout());
								JLabel label0 = new JLabel("<html><p><br/>"
										+ list.get(index).toString()
												.replaceAll("\n", "<br/>")
										+ "</p></html>");
								panel2.add(label0, BorderLayout.PAGE_START);
								int selected = JOptionPane.showOptionDialog(
										panel, panel2, "Confirmation",
										JOptionPane.CANCEL_OPTION,
										JOptionPane.INFORMATION_MESSAGE, null,
										choices, 1);
								switch (selected) {
								case 0:
									// Do nothing.
									break;
								case 1:
									window.customerLoggingIn
											.addNewMovie(window.movies
													.remove(index));
									break;
								}
							}
						}
					});
					panel.add(label);
					panel.add(button);
				}
			}
			JScrollPane scrollPane = new JScrollPane(panel);
			scrollPane.setPreferredSize(new Dimension(300, 300));
			JOptionPane.showMessageDialog(this, scrollPane, "Results",
					JOptionPane.DEFAULT_OPTION);
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == submit) {
				if (window.customerLoggingIn == null) {
					JOptionPane.showMessageDialog(this,
							"You are not logged in yet.");
				} else {
					switch (box.getSelectedIndex()) {
					case 0:
						displayMovies(window.movies);
						break;
					case 1:
						displayMovies(availabilityList(window.movies));
						break;
					case 2:
						displayMovies(alphabeticalSort(window.movies));
						break;
					}
				}
			} else if (e.getSource() == home) {
				window.replacePanel(window.homePanel);
			}
		}
	}

	private static class ReturnPanel extends JPanel implements ActionListener {

		private Window window;
		private JLabel label1, label2;
		private JButton submit, home, refresh;
		private JComboBox<String> box;
		private static boolean refreshed = false;

		public ReturnPanel(Window win) {

			this.window = win;
			this.setBounds(0, window.PHOTO_HEIGHT, window.WIDTH, window.HEIGHT
					- window.PHOTO_HEIGHT - 50);
			this.setBackground(DEFAULT_BACKGROUND_COLOR);
			this.setVisible(false);
			this.setLayout(null);

			label1 = new JLabel("Choose a movie to return.");
			label1.setBounds(50, 25, 500, 40);
			label1.setForeground(DEFAULT_FONT_COLOR);
			label1.setFont(new Font("Century", Font.ITALIC, 30));
			this.add(label1);

			label2 = new JLabel("Select from below: ");
			label2.setBounds(110, 120, 150, 30);
			label2.setForeground(DEFAULT_FONT_COLOR);
			this.add(label2);

			if (this.window.customerLoggingIn == null) {
				box = new JComboBox<String>();
				final String[] noEntry = { "Not available. Please login first." };
				box.setModel(new DefaultComboBoxModel<String>(noEntry));
			} else {
				box = updatedBox();
				refreshed = true;
			}
			box.setBounds(100, 150, 300, 30);
			this.add(box);

			refresh = new JButton("<html><u>&#9650;Refresh</u></html>");
			refresh.setBounds(310, 180, 100, 30);
			refresh.setBorderPainted(false);
			refresh.setForeground(Color.RED);
			refresh.addActionListener(this);
			this.add(refresh);

			submit = new JButton("Submit");
			submit.setBounds(140, 260, 100, 30);
			submit.addActionListener(this);
			this.add(submit);

			home = new JButton("Home");
			home.setBounds(240, 260, 100, 30);
			home.addActionListener(this);
			this.add(home);

			window.add(this);
		}

		private JComboBox<String> updatedBox() {

			if (this.window.customerLoggingIn == null
					|| window.customerLoggingIn.getMoviesRenting().size() == 0) {
				final String[] noEntry = { "Not available. Please login first." };
				box.setModel(new DefaultComboBoxModel<String>(noEntry));
			} else {
				String[] titles = new String[this.window.customerLoggingIn
						.getMoviesRenting().size()];

				for (int i = 0; i < this.window.customerLoggingIn
						.getMoviesRenting().size(); i++) {
					titles[i] = this.window.customerLoggingIn
							.getMoviesRenting().get(i).getTitle();
					box.setModel(new DefaultComboBoxModel<String>(titles));// box
				}
			}
			box.revalidate();
			box.repaint();
			return box;
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == submit) {
				if (window.customerLoggingIn == null) {
					JOptionPane.showMessageDialog(this,
							"You are not logged in yet.");
				} else {
					if (window.customerLoggingIn.getMoviesRenting().size() == 0) {
						refreshed = false;
					}
					if (refreshed) {
						int index;
						if (box.getSelectedIndex() < 0) {
							index = 0;
						} else {
							index = box.getSelectedIndex();
						}

						window.movies.add(window.customerLoggingIn
								.getMoviesRenting().get(index));
						window.customerLoggingIn
								.removeMovie(window.customerLoggingIn
										.getMoviesRenting().get(index));
						this.box = updatedBox();
						refreshed = false; //
					}

				}
			} else if (e.getSource() == refresh) {
				if (window.customerLoggingIn == null) {
					JOptionPane.showMessageDialog(this,
							"You are not logged in yet.");
				} else {
					this.box = updatedBox();
					refreshed = true;
				}
			} else if (e.getSource() == home) {
				window.replacePanel(window.homePanel);
			}
		}
	}

	private static class LoginPanel extends JPanel implements ActionListener {

		private Window window;
		private JLabel label1, label2, label3;
		private JTextField entry;
		private JButton submit, home, register;

		public LoginPanel(Window win) {

			this.window = win;
			this.setBounds(0, window.PHOTO_HEIGHT, window.WIDTH, window.HEIGHT
					- window.PHOTO_HEIGHT - 50);
			this.setBackground(DEFAULT_BACKGROUND_COLOR);
			this.setVisible(false);
			this.setLayout(null);

			label1 = new JLabel(
					"Login to the system with your customer ID number.");
			label1.setBounds(50, 25, 400, 40);
			label1.setForeground(DEFAULT_FONT_COLOR);
			label1.setFont(new Font("Century", Font.ITALIC, 15));
			this.add(label1);

			label2 = new JLabel("Your customer ID: ");
			label2.setBounds(150, 110, 400, 40);
			label2.setForeground(DEFAULT_FONT_COLOR);
			this.add(label2);

			entry = new JTextField();
			entry.setBounds(270, 115, 50, 30);
			this.add(entry);

			label3 = new JLabel("Not a member yet ? Register now !!");
			label3.setBounds(100, 150, 400, 40);
			label3.setForeground(DEFAULT_FONT_COLOR);
			this.add(label3);

			register = new JButton("<html><u>&#9650;Register</u></html>");
			register.setBounds(300, 155, 100, 30);
			register.setBorderPainted(false);
			register.setForeground(Color.RED);
			register.addActionListener(this);
			this.add(register);

			submit = new JButton("Submit");
			submit.setBounds(140, 260, 100, 30);
			submit.addActionListener(this);
			this.add(submit);

			home = new JButton("Home");
			home.setBounds(240, 260, 100, 30);
			home.addActionListener(this);
			this.add(home);

			window.add(this);
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == submit) {
				if (entry.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this,
							"Invalid input. Try again.");
				} else {
					try {
						boolean found = false;
						int inputCID = Integer.parseInt(entry.getText());

						for (int i = 0; i < window.customers.size(); i++) {
							if (window.customers.get(i).getCustomerID() == inputCID) {
								window.customerLoggingIn = window.customers
										.get(i);
								window.nameLabel.setVisible(false);
								window.mainPanel.add(window.createNameLabel());
								window.nameLabel.revalidate();
								window.nameLabel.repaint();

								JOptionPane
										.showMessageDialog(
												this,
												"Welcome back "
														+ window.customerLoggingIn
																.getFirstName()
														+ " !!");
								entry.setText("");
								found = true;
								break;
							}
						}

						//Not found then...
						if (!found) {
							JOptionPane.showMessageDialog(this,
									"You are not registered yet.");
							entry.setText("");
						}
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(this,
								"Invalid input. Try again.");
					}
				}
			} else if (e.getSource() == home) {
				window.replacePanel(window.homePanel);
			} else if (e.getSource() == register) {
				window.replacePanel(window.customerPanel);
			}
		}
	}
}


