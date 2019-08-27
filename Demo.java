//all things needed for this program
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class Demo extends JPanel {
	//all the buttons,textfields, labels used for the first window, and the strings used for the inputs
    private final JButton jcomp1;
    private JButton Button2;
    private JTextField R2;
    private JTextField R1;
    private JTextField R3;
    private JTextField R4;
    private JTextField R5;
    private JTextField R6;
    private JTextField R7;
    private JLabel L1;
    private JLabel L2;
    private JLabel L3;
    private JLabel L4;
    private JLabel L5;
    private JLabel L6;
    private JLabel L7;
    private String T1;
    private String T2;
    private String T3;
    private String T4;
    private String T5;
    private String T6;
    private String T7;
    private static JFrame self;
    
    public Demo() {
    	
        //here all the components of the window are being set up in their proper places
        jcomp1 = new JButton ("OK");
        jcomp1.setMnemonic('O');
        //where button gets what happens when its clicked
        jcomp1.addActionListener(new okbuttonlistener());
        Button2 = new JButton ("Cancel");
        Button2.setMnemonic('C');
        Button2.addActionListener(new button2listener());
        R2 = new JTextField (15);
        R1 = new JTextField (15);
        R3 = new JTextField (15);
        R4 = new JTextField (15);
        R5 = new JTextField (15);
        R6 = new JTextField (15);
        R7 = new JTextField (15);
        L1 = new JLabel ("Database URL");
        L2 = new JLabel ("Host Name");
        L3 = new JLabel ("Port");
        L4 = new JLabel ("Database");
        L5 = new JLabel ("User Name");
        L6 = new JLabel ("Password");
        L7 = new JLabel ("Driver");

        //adjust size and set layout
        setPreferredSize (new Dimension (291, 226));
        setLayout (null);

        //add components to frame
        add (jcomp1);
        add (Button2);
        add (R2);
        add (R1);
        add (R3);
        add (R4);
        add (R5);
        add (R6);
        add (R7);
        add (L1);
        add (L2);
        add (L3);
        add (L4);
        add (L5);
        add (L6);
        add (L7);

        //location of the components on the frame
        jcomp1.setBounds (65, 170, 75, 30);
        Button2.setBounds (145, 170, 75, 30);
        R2.setBounds (190, 20, 100, 18);
        R1.setBounds (190, 0, 100, 18);
        R3.setBounds (190, 40, 100, 18);
        R4.setBounds (190, 60, 100, 18);
        R5.setBounds (190, 80, 100, 18);
        R6.setBounds (190, 100, 100, 18);
        R7.setBounds (190, 120, 100, 18);
        L1.setBounds (0, 0, 100, 18);
        L2.setBounds (0, 20, 100, 18);
        L3.setBounds (0, 40, 100, 18);
        L4.setBounds (0, 60, 100, 18);
        L5.setBounds (0, 80, 100, 18);
        L6.setBounds (0, 100, 100, 18);
        L7.setBounds (0, 120, 100, 18);
        
    }
    //what happens when ok button is clicked
    private class okbuttonlistener implements ActionListener
    {
    	
            @Override
    	public void actionPerformed(ActionEvent e)
    	{
    		//all text is stored in the strings
    		T1 = R1.getText().toString();
    		T2 =R2.getText().toString();
    		T3 =R3.getText().toString();
    		T4 = R4.getText().toString();
    		T5 = R5.getText().toString();
    		T6 = R6.getText().toString();
    		T7 = R7.getText().toString();
    		String T8 = T1 + T2 + ":" + T3 + "/" + T4;
    		try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();//T7).newInstance();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
                
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection( T8, T5, T6 );
                } catch (SQLException ex) {
                    Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(0);
                }
    		JFrame anotherframe = new My_window(connection);
    		setVisible(false);
    		anotherframe.setVisible(true);
    		self.dispose();
    		
    		
    	}
    	
    }
    private class button2listener implements ActionListener
    {
            @Override
    	public void actionPerformed(ActionEvent e)
    	{
    		//closes window
    		System.exit(0);
    	}
    }
    public static void main (String[] args) throws InterruptedException{
    	//here frame is made
        JFrame frame = new JFrame ("Database Connection");
        self = frame;
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Demo());//adds everything made in Demo() to frame
        frame.pack();
        frame.setVisible (true);
        
        
    }
}
