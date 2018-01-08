package mygui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ItemListener;
import java.util.concurrent.TimeUnit;

//Cormac Buckley 15534413

public class Slideshow extends JFrame {

	private static final Dimension WindowSize = new Dimension(800,600);
	private JComboBox imagesComboBox;
	private JLabel label;
	private JPanel buttonPanel;
	private JButton buttons[];
	private String names[] = {"Animals", "Flowers", "Food"};
	private int inc = 0, reset = 0; //inc determines the increment to select the correct image range
	//reset determines whether the slide loop needs to reset to corectly display the selected image set 
	
	private Icon icons[] = {new ImageIcon("dog1.jpg"),new ImageIcon("dog2.jpg"),new ImageIcon("dog3.jpg"), new ImageIcon("flower1.jpg"),new ImageIcon("flower2.jpg"), new ImageIcon("flower3.jpg"),new ImageIcon("food1.jpeg"),new ImageIcon("food2.jpg"),new ImageIcon("food3.jpg")};
	
	public Slideshow() throws InterruptedException {
		super("Slideshow");
		
		
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		setBounds(x, y, WindowSize.width, WindowSize.height);
		setVisible(true);
		
		BorderLayout layout = new BorderLayout(5,5);
		Container container = getContentPane();
		container.setLayout(layout);
		
		buttons = new JButton[3];
		
		 JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JButton animal = new JButton( "Animal Background Color");
		JButton flower = new JButton( "Flower Background Color");
		JButton food = new JButton( "Food Background Color");
		
		ButtonHandler handler = new ButtonHandler();
		animal.addActionListener(handler);
		flower.addActionListener(handler);
		food.addActionListener(handler);
		
		
			 buttonPanel.add(animal);
			 buttonPanel.add(flower);
			 buttonPanel.add(food);
		
		container.add( buttonPanel, BorderLayout.SOUTH );
		 
		
		JPanel ComboBox = new JPanel(new FlowLayout(FlowLayout.CENTER));

		imagesComboBox = new JComboBox(names);
		imagesComboBox.setMaximumRowCount(3);
		
		imagesComboBox.addItemListener(	new ItemListener() 
		{
		 public void itemStateChanged( ItemEvent event )
		 {
		 if ( event.getStateChange() == ItemEvent.SELECTED )
			 if(imagesComboBox.getSelectedIndex() == 0) {
		 label.setIcon(icons[imagesComboBox.getSelectedIndex()] );
		 inc = 0;
		 reset = 1;
			 }
		 if (imagesComboBox.getSelectedIndex() == 1) {
			 inc = 2;
			 label.setIcon(icons[3] );
			 reset = 1;
		}
		 else  if (imagesComboBox.getSelectedIndex() == 2){
			 inc = 4;
			 label.setIcon(icons[6] );
			 reset = 1;
		}
		 }
		 }
			 );
		
		ComboBox.add(imagesComboBox);
		
		container.add( ComboBox, BorderLayout.NORTH);		
		
		 // set up JLabel to display ImageIcons
		while(true) {
			reset = 0;
			int i = imagesComboBox.getSelectedIndex() + inc;
			for(i = imagesComboBox.getSelectedIndex() + inc; i < imagesComboBox.getSelectedIndex() + inc + 3; i++) {
		 label = new JLabel( icons[ i ] );
		 container.add( label );
		
		 setVisible( true );
		 
		 TimeUnit.SECONDS.sleep(1);
		 container.remove(label);
		 
		 if(reset == 1){
		 i = imagesComboBox.getSelectedIndex() + inc;
		 reset = 0;
		 }
			}
		}
		 }
		
		 // execute application
		 public static void main( String args[] ) throws InterruptedException
		 {
		 Slideshow application = new Slideshow();
		
		 application.setDefaultCloseOperation(
		 JFrame.EXIT_ON_CLOSE );
		 }
		 
		 private class ButtonHandler implements ActionListener {
			 
			  // handle button event
			  public void actionPerformed( ActionEvent event )
			  {
				  if(event.getActionCommand().equals("Animal Background Color")) {
					  getContentPane().setBackground(Color.DARK_GRAY);
					
				  }
				  else if(event.getActionCommand().equals("Flower Background Color")) {
					  getContentPane().setBackground(Color.PINK);
				  }
					 else if(event.getActionCommand().equals("Food Background Color")) {
						  getContentPane().setBackground(Color.ORANGE);
						  
					  }
			  
			  }
			 
			  }
	
		}
