import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Panel extends JPanel {

	// initialize various graphical components
	public static JLabel playerNameGraphic;
	public static JLabel timerGraphic;
	public static JLabel roundNumberGraphic;
	public static JLabel firstCharGraphic;
	public static JLabel secondCharGraphic;
	public static JLabel thirdCharGraphic;
	//
	public static JLabel blankMessage;
	public static JLabel blankMessage2;
	//
	public static JTextField textField;
	public static JTextArea acceptedListGraphic;
	public static JTextArea rejectedListGraphic;
	public static JLabel pointGraphic;

	// create the panel to be used in the frame
	public Panel() {
		super.setPreferredSize(new Dimension(900, 600));

		// add the player's name, timer, and round number; set various text properties
		playerNameGraphic = new JLabel("");
		playerNameGraphic.setFont (playerNameGraphic.getFont ().deriveFont (20.0f));;
		playerNameGraphic.setHorizontalAlignment(JLabel.CENTER);
		timerGraphic = new JLabel ("GET READY!");
		timerGraphic.setFont (timerGraphic.getFont ().deriveFont (24.0f));;
		timerGraphic.setHorizontalAlignment(JLabel.CENTER);
		roundNumberGraphic = new JLabel ("");
		roundNumberGraphic.setFont (roundNumberGraphic.getFont ().deriveFont (20.0f));;
		roundNumberGraphic.setHorizontalAlignment(JLabel.CENTER);
		// add the characters
		firstCharGraphic = new JLabel ("");
		firstCharGraphic.setHorizontalAlignment(JLabel.CENTER);
		firstCharGraphic.setFont (firstCharGraphic.getFont ().deriveFont (40.0f));;
		secondCharGraphic = new JLabel ("");
		secondCharGraphic.setHorizontalAlignment(JLabel.CENTER);
		secondCharGraphic.setFont (firstCharGraphic.getFont ().deriveFont (40.0f));;
		thirdCharGraphic = new JLabel ("");
		thirdCharGraphic.setFont (firstCharGraphic.getFont ().deriveFont (40.0f));;
		thirdCharGraphic.setHorizontalAlignment(JLabel.CENTER);

		// add two blank messages to fill the grid space around the text field
		blankMessage = new JLabel ("");
		blankMessage2 = new JLabel ("");
		
		// set a font for the JTextField and JTextAreas
		Font myFont = new Font("SansSerif", Font.BOLD, 14);		
		
		// add the text field, set its text properties
		textField = new JTextField("");
		textField.setFont(myFont);
		textField.setHorizontalAlignment(JTextField.CENTER);
	    textField.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	        	
	    		// check if the word entered was '`', if so, print the viable dictionary
	    		if (textField.getText().equals("`")) {
	    			System.out.println(Logic.myViableDictionary);
	    		}
	    		else {
	    			
	    			// don't submit text if the temporary stopwatch is running or just enter was pressed
	    			if (!TemporaryStopwatch.timerIsTemporary && !textField.getText().equals("")) {
	    				
	    	    		// check if the word entered was '/end', if so, end the current player's turn
	    	    		if (textField.getText().equals("/end")) {
	    	    			Stopwatch.timer.cancel();
	    	    			// call the logic function to change the player number and round #
	    	    			try {
								if (!Logic.updateNumberAndRound()) {
									// call the temporary stopwatch
									TemporaryStopwatch.setMyStopwatch();
								}
							} catch (IOException e) {
								System.out.println("NO DICTIONARY COULD BE FOUND");
								System.exit(0);
								e.printStackTrace();
							}
	    	    		}
	    	    		else {
	    	    			// check if the submitted word is viable, then allot points
	    	    			ViabilityChecker.checkMyWord(textField.getText());
	    	    		}
		    		}
	    		}
	    		// reset the text field for future submissions
	    		textField.setText("");
	        }
	      });

		//add the accepted/rejected lists and points
		acceptedListGraphic = new JTextArea ("Accepted words:");
		acceptedListGraphic.setFont(myFont);
		acceptedListGraphic.setEditable(false);
		JScrollPane acceptedScrollPane = new JScrollPane(acceptedListGraphic);
		//acceptedListGraphic.setAlignment(JTextArea.ALIGN_CENTER);
		rejectedListGraphic = new JTextArea ("Rejected words:");
		rejectedListGraphic.setFont(myFont);
		rejectedListGraphic.setEditable(false);
		JScrollPane rejectedScrollPane = new JScrollPane(rejectedListGraphic);
		//rejectedListGraphic.setHorizontalAlignment(JLabel.CENTER);
		
		// add the point graphic and set its text properties
		pointGraphic = new JLabel (Logic.getMyPlayerName() + "'s Points: ");
		pointGraphic.setFont (pointGraphic.getFont ().deriveFont (20.0f));;
		pointGraphic.setHorizontalAlignment(JLabel.CENTER);

		// set the grid layout for the panel; this will look like a 3X4 grid
		GridLayout myPanel = new GridLayout(0,3);
		super.setLayout(myPanel);

		// add the top row
		super.add(playerNameGraphic);
		super.add(timerGraphic);
		super.add(roundNumberGraphic);
		// add the char row
		super.add(firstCharGraphic);
		super.add(secondCharGraphic);
		super.add(thirdCharGraphic);
		// add the text field
		super.add(blankMessage);
		super.add(textField);
		super.add(blankMessage2);
		// add the accepted/rejected lists and the points for the player
		super.add(acceptedScrollPane);
		super.add(pointGraphic);
		super.add(rejectedScrollPane);
	}

	// updates the timer graphic, depending on whether or not the timer is temporary
	public static void updateTimerGraphic() {

		if (TemporaryStopwatch.timerIsTemporary) {
			timerGraphic.setText("Round starting in: " + String.valueOf(TemporaryStopwatch.getTime()));
		}
		else {
			timerGraphic.setText("Time left: " + String.valueOf(Stopwatch.getTime()));
		}
	}
}
