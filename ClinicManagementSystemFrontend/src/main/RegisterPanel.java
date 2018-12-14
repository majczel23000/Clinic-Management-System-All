package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


import main.LoginPanel.Login;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class RegisterPanel extends JPanel {
	
	private JButton registerButton;						//przycisk "Zarejestruj"
	private JButton loginPanelButton;					//przycisk "Przejdz do logowania"
	private JPanel titlePanel;							// panel na tytu³
	private JPanel inputPanel;							// panel na pola
	private JPanel buttonPanel;							// panel na przyciski
	private JPanel parentPanel;							// panel na panele pól i przycisków
	private JTextField firstNameInput; 					//input na imie
	private JTextField lastNameInput; 					//input na nazwisko
	private JTextField emailInput; 						//input na maila
	private JPasswordField passwordInput; 				//input na has³o
	private JTextField peselInput; 						//input na pesel
	private JPanel registerPanel;
	// wyra¿enie regularne dla poprawnego emaila
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public RegisterPanel() {
		registerPanel = this;
		this.setLayout(new FlowLayout());
		createHeader();
		createButtons();
		createFields();
		createPanels();
	}
	
	// tworzy tytu³ strony 
	private void createHeader() {
		JLabel title = new JLabel("<html><div style='font-size: 30px; text-align: center;'>Clinic Management System</div>"
				+ "<div style='text-align: center; margin: 40px 0px 0px 0px; font-size: 16px'>Zarejestruj siê w naszym systemie:</div></html>");
		titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1,1));
		titlePanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
		titlePanel.setBackground(new Color(255, 228, 188));
		titlePanel.add(title);
	}
	
    // tworzy przyciski	
	private void createButtons() {
		registerButton = new Register();				// przycisk do rejestracji
		loginPanelButton = new LoginPanelButton();		// przycisk do przejœcia do logowania
	}
	
	// tworzy labelki wraz z inputami
	private void createFields() {
		JLabel firstName = new JLabel("Imiê: ");
		firstName.setFont(new Font("Arial", Font.PLAIN,20));
		JLabel lastName = new JLabel("Nazwisko: ");
		lastName.setFont(new Font("Arial", Font.PLAIN,20));
		JLabel email = new JLabel("E-mail: ");
		email.setFont(new Font("Arial", Font.PLAIN,20));
		JLabel password = new JLabel("Has³o: ");
		password.setFont(new Font("Arial", Font.PLAIN,20));
		JLabel pesel = new JLabel("PESEL: ");
		pesel.setFont(new Font("Arial", Font.PLAIN,20));
		
		emailInput = new JTextField();
		emailInput.setFont(new Font("Arial", Font.PLAIN,15));
		emailInput.setPreferredSize(new Dimension(200,40));
		emailInput.setMargin(new Insets(0, 10, 0, 10));
		
		passwordInput = new JPasswordField();
		passwordInput.setFont(new Font("Arial", Font.PLAIN,15));
		passwordInput.setMargin(new Insets(0, 10, 0, 10));
		
		firstNameInput = new JTextField();
		firstNameInput.setFont(new Font("Arial", Font.PLAIN,15));
		firstNameInput.setPreferredSize(new Dimension(200,20));
		firstNameInput.setMargin(new Insets(0, 10, 0, 10));
		
		lastNameInput = new JTextField();
		lastNameInput.setFont(new Font("Arial", Font.PLAIN,15));
		lastNameInput.setPreferredSize(new Dimension(200,20));
		lastNameInput.setMargin(new Insets(0, 10, 0, 10));
		
		peselInput = new JTextField();
		peselInput.setFont(new Font("Arial", Font.PLAIN,15));
		peselInput.setPreferredSize(new Dimension(200,20));
		peselInput.setMargin(new Insets(0, 10, 0, 10));
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5,2,10,10));
		inputPanel.setBackground(new Color(255, 228, 188));
		inputPanel.add(firstName);
		inputPanel.add(firstNameInput);
		inputPanel.add(lastName);
		inputPanel.add(lastNameInput);
		inputPanel.add(email);
		inputPanel.add(emailInput);
		inputPanel.add(password);
		inputPanel.add(passwordInput);
		inputPanel.add(pesel);
		inputPanel.add(peselInput);
	}
	
	// tworzy panel przycisków i parent panel
	private void createPanels() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,1,10,10));
		buttonPanel.add(registerButton);
		buttonPanel.add(loginPanelButton);
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setBackground(new Color(255, 228, 188));
		
		parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());
		parentPanel.setBackground(new Color(255, 228, 188));
		
		parentPanel.add(inputPanel, BorderLayout.CENTER);
		parentPanel.add(buttonPanel, BorderLayout.SOUTH);
		parentPanel.add(titlePanel, BorderLayout.NORTH);
		add(parentPanel);		
		setBackground(new Color(255, 228, 188));
	}
	
	// klasa dla przycisku 'Zarejestruj'
	class Register extends JButton implements ActionListener {

		Register() {
			super("Zarejestruj");
			addActionListener(this);
			this.setMargin(new Insets(10, 0, 10, 0));
			this.setBackground(new Color(198, 89, 0));
			this.setForeground(new Color(246, 246, 246));
			Border emptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
			this.setBorder(emptyBorder);
			// event po najechaniu i zjechaniu myszk¹ z przycisku
			this.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	registerButton.setBackground(new Color(255, 114, 0	));
			    	registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			    }
			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	registerButton.setBackground(new Color(198, 89, 0));
			    }
			});
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String allValidationErrors = "";
			// walidacja imienia
			String validationFirstNameResult = validateField(getFirstName(), "Imiê");
			if(!validationFirstNameResult.equals("")) {
				allValidationErrors += validationFirstNameResult + "\n";
			}
			// walidacja nazwiska
			String validationLastNameResult = validateField(getLastName(), "Nazwisko");
			if(!validationLastNameResult.equals("")) {
				allValidationErrors += validationLastNameResult  + "\n";
			}
			// walidacja peselu
			String validationPESELResult = validateField(getPesel(), "PESEL");
			if(!validationPESELResult.equals("")) {
				allValidationErrors += validationPESELResult  + "\n";
			} else if(!isNumeric(getPesel().getText())) {
				allValidationErrors += "Pesel musi zawieraæ same cyfry\n";
			} else if(getPesel().getText().length() != 11) {
				allValidationErrors += "Pesel musi zawieraæ 11 cyfr\n";
			}
			// walidacja has³a
			if(getPassword().length() < 5) {
				allValidationErrors += "Has³o musi zawieraæ conajmniej 5 znaków\n";
			}
			// walidacja email
			String validationEmailResult = validateField(getEmail(), "Email");
			if(!validationEmailResult.equals("")) {
				allValidationErrors += validationEmailResult  + "\n";
			} else if(!RegisterPanel.validate(getEmail().getText())) {
				allValidationErrors += "Proszê wpisaæ poprawny email\n";
			}
			if(!allValidationErrors.equals("")){
				JOptionPane.showMessageDialog(null, allValidationErrors);
			} else {
				try {
					registerUser(getFirstName().getText(), getLastName().getText(), getEmail().getText(), getPassword(), getPesel().getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	// sprawdza czy email ma poprawny format
	public static boolean validate(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
	        return matcher.find();
	}
	
	// sprawdza czy pesel jest liczb¹
	public static boolean isNumeric(String strNum) {
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}
	
	// walidacja imienia, nazwiska i peselu
	private String validateField(JTextField field, String name) {
		String error = "";
		String value = field.getText();
		if(value.equals("")) {
			error = "Proszê uzupe³niæ pole " + name;
		}
		return error;
	}
	
	// klasa dla przycisku 'Przejdz do logowania'
	class LoginPanelButton extends JButton implements ActionListener{
		LoginPanelButton(){
			super("PrzejdŸ do logowania");
			addActionListener(this);
			setBackground(new Color(188, 214, 255));
			Border emptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
			this.setBorder(emptyBorder);
			// event po najechaniu i zjechaniu myszk¹ z przycisku
			this.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	loginPanelButton.setBackground(new Color(211, 228, 255));
			    	loginPanelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			    }
			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	loginPanelButton.setBackground(new Color(188, 214, 255));
			    }
			});
		}
		
		public void actionPerformed(ActionEvent e) {
			changeViewToLogin();
		}
	}

	private void changeViewToLogin(){
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(registerPanel);
		JScrollPane scroll = new JScrollPane(new LoginPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		topFrame.getContentPane().removeAll();
		topFrame.getContentPane().add(scroll);
		topFrame.invalidate();
		topFrame.validate();
		topFrame.repaint();
		topFrame.setTitle("Logowanie");
		System.out.println("Funkcja przejœcia do okna logowania.");
	}
	
	// metoda wysy³a dane do serwera
	private void registerUser(String firstName, String lastName, String email, String password, String pesel) throws IOException {
		// zbieram danie do wys³ania
		JSONObject userData = new JSONObject();
		userData.put("username", firstName);
		userData.put("email", email);
		userData.put("password", password);
		userData.put("pesel", pesel);

		// route do rejestracji
		String registerUrl = "http://localhost:8081/register";
		try{
			URL url = new URL(registerUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// dane jakie wyœle, to json:
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			OutputStream os = conn.getOutputStream();
			os.write(userData.toString().getBytes("UTF-8"));
			os.close();
			// odczytuje odpowiedŸ od serwera
			InputStream in = new BufferedInputStream(conn.getInputStream());
			String result = IOUtils.toString(in, "UTF-8");
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(result);
			// pobieram id usera
			Object id = json.get("id");
			System.out.println("Zarejestrowan u¿ytkonika o ID: " + id.toString());
			in.close();
			conn.disconnect();
			activateUser(id.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// aktywacja u¿ytkownika po rejestracji
	private void activateUser(String userId){
		// route do aktywacji po id
		String activateUrl = "http://localhost:8081/register/" + userId + "/activate";
		try{
			URL url = new URL(activateUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			JOptionPane.showMessageDialog(null, "Pomyœlnie zarejestrowano. Mo¿na siê zalogowaæ.");
			changeViewToLogin();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	// gettery dla wszystkich inputów
	public JTextField getFirstName() {
		return firstNameInput;
	}
	public JTextField getLastName() {
		return lastNameInput;
	}
	public JTextField getEmail() {
		return emailInput;
	}
	public String getPassword() {
		String password = "";
		char[] pass = passwordInput.getPassword();
		for(int i = 0; i < pass.length; i++) {
			password += pass[i];
		}
		return password;
	}
	public JTextField getPesel() {
		return peselInput;
	}
	
}
