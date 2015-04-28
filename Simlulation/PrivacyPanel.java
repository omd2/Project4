import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PrivacyPanel extends JPanel {

	private OurController anApplet;
	
	private PrivacyPanel myPanel;
	
	private JLabel privacySetting;
	
	private JButton changeSetting;
	
	private String setting = "Public";
	
	private URL updateURL = OurController.class.getResource("UpdateButton.png");
	
	public PrivacyPanel(OurController thisApplet){
	
		this.anApplet = thisApplet;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(300, 45));
		setBackground(Color.WHITE);
		
		init();
		
		myPanel = this;
		
	}
	
	public void init(){
		
		privacySetting = new JLabel(setting);
		privacySetting.setAlignmentX(CENTER_ALIGNMENT);
		add(privacySetting);
		
		changeSetting = new JButton();
		changeSetting.setOpaque(false);
		changeSetting.setBorderPainted(false);
		changeSetting.setContentAreaFilled(false);
		ImageIcon post = new ImageIcon(updateURL);
	    changeSetting.setIcon(post);
		changeSetting.setAlignmentX(CENTER_ALIGNMENT);
		changeSetting.addActionListener(new ButtonListener());
		add(changeSetting);
		
	}
	
	public void display(){

		privacySetting.setText(setting);
		add(privacySetting);
		add(changeSetting);
		
	}
	
	class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){

			// http://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
        	// https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
			Object[] possibilities = {"Public", "Private", "Friends of Friends"};
			String newSetting = (String)JOptionPane.showInputDialog(
			                    anApplet,
			                    "Choose a privacy setting: ",
			                    "Customized Dialog",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    possibilities,
			                    "Public");
			
			
	    	setting = newSetting;

            	
            myPanel.display();
            myPanel.revalidate();
            myPanel.repaint();

			
		}
		
	}
	
}