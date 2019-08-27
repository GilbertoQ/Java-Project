import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class My_window extends JFrame 
{
	private String Input; 
        // string of whats inputted in second window
	private JPanel contentPane;
    private Connection connection1;
    private JTable table;
    private JScrollPane scroll;

	//here components for frame is created
   
	public My_window(Connection connection2)
	{
                connection1=connection2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Database");
		menuBar.add(mnNewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 11, 874, 121);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SQL Statement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel);
		
		JTextArea textArea = new JTextArea();
		scroll = new JScrollPane(textArea);
		textArea.setBounds(10, 21, 839, 89);
		panel.add(textArea);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		scroll = new JScrollPane();
		
		
		scroll.setBounds(30, 215, 824, 314);
		contentPane.add(scroll);
		
	
		JButton btnNewButton = new JButton("Execute");
		//here text inputted is stored into string input
		btnNewButton.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) 
			{
                                ResultSet rs;
				Input = textArea.getText().toString();  
                            try {
                                Statement statement=connection1.createStatement();
                                if(Input.charAt(0)=='U'||Input.charAt(0)=='u'||Input.charAt(0)=='D'||Input.charAt(0)=='d'||Input.charAt(0)=='I'||Input.charAt(0)=='i'||Input.charAt(0)=='c'||Input.charAt(0)=='C'){
                                    int a=statement.executeUpdate(Input);
                                }
                                
                                else {
                                	String[] column = null;
                                	Object [][]data=null;
                                        rs=statement.executeQuery(Input);
                                         ResultSetMetaData rsmd = rs.getMetaData();
                                         int columnsNumber = rsmd.getColumnCount();
                                         column=new String[columnsNumber];
                                         for(int k=0;k <= columnsNumber-1;k++){

                                        	column[k]=rsmd.getColumnLabel(k+1);
                                         }

                                         int p=0;
                                         data=new Object[100][columnsNumber];
                                        while(rs.next())
                                        {
                                            for(int i=0;i <= columnsNumber - 1;i++){
                                                 //put the data in string 2d array
                                                     data[p][i] = rs.getObject(i+1);
                                            }
                                         
                                            p++;
                                        }
                                        contentPane.remove(scroll);
                                    
                                        table = new JTable(data,column);
                                     
                                        scroll = new JScrollPane(table);
                                		table.setFillsViewportHeight(true);
                                		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                		scroll.setBounds(30, 215, 874, 314);
                                		contentPane.add(scroll);
                                		table.setEnabled(false);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(My_window.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
			}
		});

		btnNewButton.setBounds(375, 140, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setMnemonic('R');
		// resets both fields
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});

		btnNewButton_1.setBounds(425, 173, 89, 23);
		contentPane.add(btnNewButton_1);
		contentPane.setVisible(true);
		
		JButton btnNewButton_2 = new JButton("Union");
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("select * from Items where category = 'Books' And Books_Genre = 'Action'"
						+ " Union "
						+ "select * from Items where category = 'Books' and books_Genre = 'Adventure';");

				 btnNewButton.doClick();
			}
		});

		btnNewButton_2.setBounds(525, 173, 89, 23);
		contentPane.add(btnNewButton_2);
		contentPane.setVisible(true);
		
		JButton btnNewButton_3 = new JButton("Intersection");
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("select * from account a where length(password) < 14 AND EXISTS "
						+ "(select * from account b where NOT( b.password rlike '[0-9]' AND b.password rlike '[a-z]') AND a.email = b.email);");
				 btnNewButton.doClick();
			}
		});

		btnNewButton_3.setBounds(625, 173, 89, 23);
		contentPane.add(btnNewButton_3);
		contentPane.setVisible(true);
		
		JButton btnNewButton_4 = new JButton("Difference");
	
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("select * from account a1, (select * from account a2 where a2.email NOT like '%@gmail.com') as s1 where a1.email = s1.email;");
				 btnNewButton.doClick();
			}
		});

		btnNewButton_4.setBounds(725, 173, 89, 23);
		contentPane.add(btnNewButton_4);
		contentPane.setVisible(true);
		
		JButton btnNewButton_5 = new JButton("Division");
	
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("SELECT  a1.email "
						+ " FROM account as a1 "
						+ " WHERE (select count(coun1.category) from (Select s1.category, s2.email from (SELECT Distinct i3.category FROM Items as i3) as s1, "
						+ " (SELECT Distinct i2.category, b2.email "
						+ " FROM  belongsTo as b2, Items as i2 "
						+ " WHERE  b2.ID = i2.ID) as s2 where s1.category = s2.category) as coun1 where a1.email = coun1.email) = 8;");
				 btnNewButton.doClick();
			}
		});

		btnNewButton_5.setBounds(825, 173, 89, 23);
		contentPane.add(btnNewButton_5);
		contentPane.setVisible(true);
		
		
		JButton btnNewButton_6 = new JButton("Aggregation");
	
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("select count(*) from account;");
				 btnNewButton.doClick();
			}
		});

		btnNewButton_6.setBounds(25, 173, 89, 23);
		contentPane.add(btnNewButton_6);
		contentPane.setVisible(true);
		
		JButton btnNewButton_7 = new JButton("Join");
	
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("select * from belongsTo as b inner join account as a inner join items as i where b.email = a.email AND i.ID = b.ID;");
				 btnNewButton.doClick();
			}
		});

		btnNewButton_7.setBounds(125, 173, 89, 23);
		contentPane.add(btnNewButton_7);
		contentPane.setVisible(true);
		
		
		JButton btnNewButton_8 = new JButton("Create");
	
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Statement statement = null;
				try {
					statement = connection1.createStatement();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String input="CREATE TABLE account "
						+ "(First_Name CHAR(25) NOT NULL, "
						+ " Last_Name CHAR(25) NOT NULL, "
						+ " EMAIL CHAR(40), "
						+ " Address CHAR(40), "
						+ " Credit_Card_Number CHAR(40), "
						+ " Password CHAR(30) NOT NULL, "
						+ " Primary KEY(EMAIL) ); ";
						try {
							int a=statement.executeUpdate(input);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						input ="Create table belongsTo(ID INT, email CHAR(40),primary key(ID), foreign key(email) references account(email) "
						+ " ); ";
						try {
							int a=statement.executeUpdate(input);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						input="CREATE TABLE items("
						+ "ID INT,"
						+ "name CHAR(30),"
						+ "description TEXT(360),"
						+ "time_left DATETIME,"
						+ "item_condition CHAR(30),"
						+ "previous_bid DOUBLE,"
						+ "current_bid DOUBLE,"
						+ "starting_bid DOUBLE,"
						+ "selling_price DOUBLE,"
						+ "category CHAR(20),"
						+ "Sporting_Goods_Brand CHAR(30),"
						+ "Sporting_Goods_Weight DOUBLE,"
						+ "Sporting_Goods_Dimension_LxWxH DOUBLE,"
						+ "NBA BOOLEAN,"
						+ "NHL boolean,"
						+ "NFL boolean,"
						+ "MLB boolean,"
						+ "NCAA boolean,"
						+ "Exercise boolean,"
						+ "Studio char(30),"
						+ "Movie_Length TIME,"
						+ "Movie_Release_Date DATE,"
						+ "Director CHAR(30),"
						+ "Movie_Genre char(20),"
						+ "Movie_Rating char(5),"
						+ "Games_Release_Date DATE,"
						+ "Games_publisher char(50),"
						+ "platform char(20),"
						+ "Games_Genre char(20),"
						+ "Games_Rating char(8),"
						+ "Clothing_Weight DOUBLE,"
						+ "Size CHAR(10),"
						+ "Women boolean,"
						+ "Men boolean,"
						+ "Children boolean,"
						+ "Occasion char(20),"
						+ "Unisex boolean,"
						+ "Clothing_Brand char(30),"
						+ "Material char(30),"
						+ "Electronics_Brand char(50),"
						+ "Electronics_Model char(30),"
						+ "Processor char(30),"
						+ "RAM INT,"
						+ "hard_drive char(30),"
						+ "GPU char(30),"
						+ "Model_Number char(30),"
						+ "Operating_System char(30),"
						+ "Electronics_Weight DOUBLE,"
						+ "Electronics_item_dimension_LxWxH DOUBLE,"
						+ "USB_ports INT,"
						+ "Music_Genre char(20),Music_Release_Date DATE,"
						+ "Music_Length Double,"
						+ "Label char(30),"
						+ "Vehicle_Model char(30),"
						+ "Make char(30),"
						+ "mileage INT,"
						+ "Vehicle_Year DATE,"
						+ "Exterior_Color char(15),"
						+ "Number_Of_Cylinders INT,"
						+ "VIN INT unique,"
						+ "Vcondition char(50),"
						+ "Vehicle_title boolean,"
						+ "Drive_Type char(20),"
						+ "VEngine char(20),"
						+ "Transmission char(15),"
						+ "Books_Genre char(15),"
						+ "Books_Publisher char(30),"
						+ "hardcover Boolean,"
						+ "Page_Number INT,"
						+ "ISBN char(17),"
						+ "author char(50),"
						+ "primary key(ID),"
						+ "foreign key(ID) references belongsTo(ID) );";
						try {
							int a=statement.executeUpdate(input);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						input =  " create trigger item_check "
								+ " before insert on items "
								+ " for each row "
								+ " begin "
								+ " if NOT ( (new.category = 'Books' "
								+ " AND new.Books_Genre IS NOT NULL " 
								+ " AND new.Books_Publisher IS NOT NULL "
								+ " AND new.hardcover IS NOT NULL "
								+ " AND new.Page_Number > 0 "
								+ " AND new.ISBN IS NOT NULL "
								+ " AND new.author IS NOT NULL)"
								+ " OR "
								+ " ( new.category = 'Sporting Goods' "
								+ " AND new.Sporting_Goods_Brand IS NOT NULL "
								+ " AND new.Sporting_Goods_Weight > 0 "
								+ " AND new.Sporting_Goods_Dimension_LxWxH IS NOT NULL "
								+ " AND new.NBA IS NOT NULL "
								+ " AND new.NHL IS NOT NULL "
								+ " AND new.NFL IS NOT NULL "
								+ " AND new.MLB IS NOT NULL "
								+ " AND new.NCAA IS NOT NULL "
								+ " AND new.Exercise IS NOT NULL ) "
								+ " OR "
								+ " ( new.category = 'Movie' "
								+ " AND new.Studio IS NOT NULL "
								+ " AND new.Movie_Length IS NOT NULL "
								+ " AND new.Movie_Release_Date IS NOT NULL "
								+ " AND new.Director IS NOT NULL "
								+ " AND new.Movie_Genre IS NOT NULL "
								+ " AND new.Movie_Rating IS NOT NULL ) "
								+ " OR "
								+ " ( new.category = 'Games' "
								+ " AND new.Games_Release_Date IS NOT NULL "
								+ " AND new.Games_Publisher IS NOT NULL "
								+ " AND new.Platform IS NOT NULL "
								+ " AND new.Games_Genre IS NOT NULL "
								+ " AND new.Games_Rating IS NOT NULL ) "
								+ " OR "
								+ " ( new.category = 'Clothing' "
								+ " AND new.Clothing_Weight IS NOT NULL "
								+ " AND new.Size IS NOT NULL "
								+ " AND new.Women IS NOT NULL "
								+ " AND new.Men IS NOT NULL "
								+ " AND new.Children IS NOT NULL "
								+ " AND new.Occasion IS NOT NULL "
								+ " AND new.Unisex IS NOT NULL "
								+ " AND new.Clothing_Brand IS NOT NULL "
								+ " AND new.Material IS NOT NULL ) "
								+ " OR"
								+ " ( new.category = 'ELectronics' "
								+ " AND new.Electronics_Brand IS NOT NULL "
								+ " AND new.Processor IS NOT NULL "
								+ " AND new.Electronics_Model IS NOT NULL "
								+ " AND new.RAM IS NOT NULL "
								+ " AND new.hard_drive IS NOT NULL "
								+ " AND new.GPU IS NOT NULL "
								+ " AND new.Model_Number IS NOT NULL "
								+ " AND new.Operating_System IS NOT NULL "
								+ " AND new.Electronics_Weight IS NOT NULL "
								+ " AND new.Electronics_item_dimension_LxWxH IS NOT NULL "
								+ " AND new.USB_ports IS NOT NULL ) "
								+ " OR "
								+ " ( new.category = 'Misc' ) "
								+ " OR"
								+ " ( new.category = 'Music' "
								+ " AND new.Label IS NOT NULL "
								+ " AND new.Music_Genre IS NOT NULL "
								+ " AND new.Music_Release_Date IS NOT NULL "
								+ " AND new.Music_Length IS NOT NULL ) "
								+ " OR "
								+ " ( new.category = 'Vehicles' "
								+ " AND new.Vehicle_Model IS NOT NULL "
								+ " AND new.Make IS NOT NULL "
								+ " AND new.mileage IS NOT NULL "
								+ " AND new.Vehicle_Year IS NOT NULL "
								+ " AND new.Exterior_Color IS NOT NULL "
								+ " AND (new.Number_Of_Cylinders >-1 AND new.Number_Of_Cylinders < 13) "
								+ " AND new.VIN IS NOT NULL "
								+ " AND new.Vehicle_title IS NOT NULL "
								+ " AND new.Drive_Type IS NOT NULL "
								+ " AND new.VEngine IS NOT NULL "
								+ " AND new.Transmission IS NOT NULL "
								+ " AND new.VCondition IN ('used','new')) ) Then "
								+ " SIGNAL sqlstate '45000' "
								+ " SET message_text = 'Must fill all attributes for a category.'; "
								+ " elseif (new.current_bid <= new.previous_bid) then "
								+ " signal sqlstate '45000' "
								+ " SET message_text = 'No update with a bid lower than current bid.'; "
								+ " elseif (new.Vehicle_title = 'N') then "
								+ " signal sqlstate '45000' "
								+ " set message_text = 'Vehicle doesn\t have title'; "
								+ " end if; "
								+ " end; ";								
						try {
							int a=statement.executeUpdate(input);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						input = " create Trigger greater_bid "
								+ " before update on items "
								+ " for each row "
								+ " begin "
								+ " if (new.current_bid <= new.previous_bid) then "
								+ " signal sqlstate '45000' "
								+ " SET message_text = 'No update with a bid lower than current bid.'; "
								+ " end if; "
								+ " end; ";
						try {
							int a=statement.executeUpdate(input);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
			}
		});

		btnNewButton_8.setBounds(225, 173, 89, 23);
		contentPane.add(btnNewButton_8);
		contentPane.setVisible(true);
		
		
		JButton btnNewButton_9 = new JButton("Populate");
	
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Statement statement = null;
					try {
						statement = connection1.createStatement();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				String input="Insert into account(First_Name, Last_Name, EMAIL, Password) values ('Aaren', 'logie', 'alogie234@gmail.com', '71'); ";
						
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="Insert into account(First_Name, Last_Name, EMAIL, Password) values ('Aaren', 'logie', 'bytheby@gmail.com', '712A'); ";
				
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="Insert into account(First_Name, Last_Name, EMAIL, Password) values ('Aarika', 'cowin', 'acowin249@hotmail.com', '33'); ";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="insert into belongsto(ID,Email) values (16,'alogie234@gmail.com');";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="insert into belongsto(ID,Email) values (17,'alogie234@gmail.com');";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="insert into belongsto(ID,Email) values (18,'bytheby@gmail.com');";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="Insert into items(ID,name,description,time_left,item_condition,previous_bid,current_bid,starting_bid,selling_price,category,Books_Genre,Books_Publisher,hardcover,Page_Number,ISBN,author) values (16,'test item','this is only a test description',CURRENT_TIMESTAMP,'the item is a fake',0,1,100,9999,'Books','Action','Pinguin',1,117,'0135-895SAsdf','Me');";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				input="Insert into items(ID,name,description,time_left,item_condition,previous_bid,current_bid,starting_bid,selling_price,category,Books_Genre,Books_Publisher,hardcover,Page_Number,ISBN,author) values (17,'test item','this is only a test description',CURRENT_TIMESTAMP,'the item is a fake',0,1,100,9999,'Books','Adventure','Pinguin',0,117,'0135-895SAsdf','Me');";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="Insert into items(ID,name,description,time_left,item_condition,previous_bid,current_bid,starting_bid,selling_price,category,Books_Genre,Books_Publisher,hardcover,Page_Number,ISBN,author) values (18,'test item','this is only a test description',CURRENT_TIMESTAMP,'the item is a fake',0,1,100,9999,'Books','Scary','Pinguin',0,117,'0135-895SAsdf','Me');";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnNewButton_9.setBounds(325, 173, 89, 23);
		contentPane.add(btnNewButton_9);
		contentPane.setVisible(true);
		
	}
	
}
