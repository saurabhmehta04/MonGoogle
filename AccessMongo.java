import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.regex.Pattern;

import java.util.Arrays;
import java.util.Set;


public class AccessMongo extends JFrame {

	JTextField input;
	JTextArea output;
	
	Mongo client = null;
	DBCollection collection = null;
	BasicDBObject selection = null;
	BasicDBObject projection = null;
	DBCursor cursor = null;
	
	public AccessMongo() {
		setSize(600, 200);
		setLocation(400, 500);
		setTitle("Access MongoDB");
		
		Container cont = getContentPane();
		cont.setLayout(new BorderLayout() );
		
		JButton search = new JButton("Search");
		JButton connect = new JButton("Connect");
		JButton clear = new JButton("Clear");
		
		input = new JTextField(20);
		
		output = new JTextArea(10, 30);
		JScrollPane spOutput = new JScrollPane(output);
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		northPanel.add(connect);
		northPanel.add(input);
		northPanel.add(search);
		northPanel.add(clear);
		
		cont.add(northPanel, BorderLayout.NORTH);
				
		cont.add(spOutput, BorderLayout.CENTER);
		
		connect.addActionListener(new ConnectMongo());
		search.addActionListener(new GetMongo());
		clear.addActionListener(new ClearMongo());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	
	} //AccessMongo
	
	public static void main (String [] args) {
		
		AccessMongo runIt = new AccessMongo();
	
	}//main
	
	class ConnectMongo implements ActionListener {
		public void actionPerformed (ActionEvent event) {
		//in this section open the connection to MongoDB. 
		//You should enter the code to connect to the database here
		//Remember to connect to MongoDB, connect to the database and connect to the 
		//    desired collection
		
		try {
			//connect to the MongoDB server
//YOU SHOULD CONNECT TO THE SERVER HERE
client = new Mongo("localhost", 27017);
			output.append("Connection to server completed\n");
		
		}	
		catch (Exception e) {
			System.out.println("Error on connection");
			System.out.println(e.getMessage());
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		//access the database
//YOU SHOULD CONNECT TO THE DATABASE HERE
DB sampleDB = client.getDB("SampleSocial");
		output.append("Connection to database completed\n");
				
		//get the collection
		try {
//YOU SHOULD CONNECT TO THE COLLECTION HERE
collection = sampleDB.getCollection("Blog");
			output.append("Collection obtained\n");
			}
		catch (MongoException e){
			System.out.println("Error on collection");
			System.out.println(e.getMessage());
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		}//actionPerformed
	
	
	}//class ConnectMongo
	
	class GetMongo implements ActionListener {
		public void actionPerformed (ActionEvent event) {
		// In this section you should retrieve the data from the collection
		// and use a cursor to list the data in the output JTextArea
			Pattern regex = null;
			String searchText = input.getText();
		regex = Pattern.compile("^(.*?(\\b"+input.getText()+")[^$]*)$", Pattern.CASE_INSENSITIVE);
			
			
//SET UP YOUR SELECTION HERE			
			selection = null;
			
//RUN YOUR QUERY AND FILL THE CURSOR HERE		
			

//CYCLE THROUGH THE CURSOR HERE	AND ADD THE DATA TO THE TEXT AREA
//NOTICE THAT DATA IS ADDED TO THE TEXT AREA USING output.append(your data here +"\n")	
// REMOVE MULTI ROW COMMENTS (2 lines)	

/*
  String [][] data=new String[rowCount][2];
Set <String> fieldNames= null;

int counterDoc=0;
*/
			try {
			/*
				while(cursor.hasNext()) {
            DBObject outObj= cursor.next();
            fieldNames = outObj.keySet();
            int counterField=0;
            for(String s : fieldNames){  
               Object obj = outObj.get(s); 
               String value = ""+obj;
               selection [counterDoc] [counterField] = value;
               counterField++;
            }
            counterDoc++;//while
			}	//try*/
			}
			catch (MongoException e){
				System.out.println("Error on next()");
				System.out.println(e.getMessage());
				System.out.println(e.toString());
				e.printStackTrace();
			} //catch
			finally {
				cursor.close();
			} //finally
			
		
			
			output.append("**** END OF OUTPUT ****\n");
		
		}//actionPerformed
	}//class GetMongo
	
	class ClearMongo implements ActionListener {
		public void actionPerformed (ActionEvent event) {
		//in this section open the connection. Should be able to see if it is not null
		// to see if ti is already open
			output.setText("");
		
		}//actionPerformed
	
	
	}//class ClearMongo


} //class