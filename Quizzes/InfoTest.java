import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/BoxLayoutDemoProject/src/layout/BoxLayoutDemo.java

@SuppressWarnings("serial")
public class InfoTest extends JPanel implements MyQuestion {

	// Applet object
	private OurController aController;
	
	// Variable to hold screen number
	int number;
	
	// Get image URLs
	URL submitURL = OurController.class.getResource("Submit Button.png");
	URL backgroundURL = OurController.class.getResource("InfoQuiz-01.png");
	
	// Image for background
	private Image backgroundImage;
	
	// Button to submit
	private JButton submitButton;
	
	// Labels for user info
	private JLabel nameLabel;
	private JLabel birthdayLabel;
	private JLabel emailLabel;
	private JLabel addressLabel;
	private JLabel phoneLabel;
	private JLabel ssnLabel;
	private JLabel required;
	
	// Fields for user info
	private JTextField nameField;
	private JTextField birthdayField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField phoneField;
	private JTextField ssnField;
	
	// Constructor
	public InfoTest(OurController thisController, int aNumber){

		// Set the applet
		this.aController = thisController;
		
		// Set the screen number
		number = aNumber;
        
		// Set the layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Set background
 		try {
 			backgroundImage = ImageIO.read(backgroundURL);
 		} catch (IOException e1) {
 			e1.printStackTrace();
 		}
        
        // Set Questions
        setQuestion();
        setAnswer();
        
        // Set button
		submitButton = new JButton();
		submitButton.addActionListener(new TextListener());
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		
		// Set button icon
		submitButton.setOpaque(false);
		submitButton.setBorderPainted(false);
		submitButton.setContentAreaFilled(false);
		ImageIcon submit = new ImageIcon(submitURL);
        submitButton.setIcon((submit));	
		
		// Create fields for information
		add(Box.createRigidArea(new Dimension(10,250)));
        add(nameLabel);
        add(nameField);
        add(birthdayLabel);
        add(birthdayField);
        add(emailLabel);
        add(emailField);
        add(addressLabel);
        add(addressField);
        add(phoneLabel);
        add(phoneField);
        add(ssnLabel);
        add(ssnField);
        add(submitButton);
        add(required);	
	}
	
	public void getAnswer(){
		// this is done by the action listener
	}
	
	public Boolean isCorrect(){
		
		// get the fields
        String name = nameField.getText();
        String birthday = birthdayField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String ssn = ssnField.getText();
        
        
        // if the required info is entered
        if(!name.equals("") && !email.equals("")){
        	
        	return true;
        	
        }else{
        	// https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
        	JOptionPane.showMessageDialog(aController,
        		    "Please provide some information before proceeding.",
        		    "Error",
        		    JOptionPane.ERROR_MESSAGE);

    		return false;
        }
	}
	
	// Set Answer
	public void setAnswer(){
		
		// Set fields for user to enter info into
		nameField = new JTextField(20);
		birthdayField = new JTextField(20);
		emailField = new JTextField(20);
		addressField = new JTextField(20);
		phoneField = new JTextField(20);
		ssnField = new JTextField(20);
		
		// Set dimensions of fields
		nameField.setMaximumSize(new Dimension(300,30));
		birthdayField.setMaximumSize(new Dimension(300,30));
		emailField.setMaximumSize(new Dimension(300,30));
		addressField.setMaximumSize(new Dimension(300,30));
		phoneField.setMaximumSize(new Dimension(300,30));
		ssnField.setMaximumSize(new Dimension(300,30));	
	}
	
	// Set Question
	public void setQuestion(){
		
		// Set labels
        nameLabel = new JLabel("*Name: ");
		birthdayLabel = new JLabel("Birthday:");
		emailLabel = new JLabel("*Email: ");
		addressLabel = new JLabel("Address: ");
		phoneLabel = new JLabel("Phone number: ");
		ssnLabel = new JLabel("Social security number: ");
		required = new JLabel("* Required information");
		
		// Align elements
		nameLabel.setAlignmentX(CENTER_ALIGNMENT);
		birthdayLabel.setAlignmentX(CENTER_ALIGNMENT);
		emailLabel.setAlignmentX(CENTER_ALIGNMENT);
		addressLabel.setAlignmentX(CENTER_ALIGNMENT);
		phoneLabel.setAlignmentX(CENTER_ALIGNMENT);
		ssnLabel.setAlignmentX(CENTER_ALIGNMENT);
		required.setAlignmentX(CENTER_ALIGNMENT);	
	}
	
	// Listener takes you to the next panel
	class TextListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){

			boolean isInfoCorrect = isCorrect();
			
			if (isInfoCorrect){
								
				aController.showNextTest(number);				
			}
		}		
	}	
	
	// Paint the background
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}