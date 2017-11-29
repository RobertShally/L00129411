package ie.lyit.serialize;




import ie.lyit.hotel.Customer; 
import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class CustomerSerializer implements Serializable, DAO{

	//declare an arraylist of customer objects
	private ArrayList<Customer> customers;
	
	//Sets the name of outputed file to customers.ser 
	final String FILENAME = "customers.ser";
	
	//Create instance of singleton design pattern
	private static CustomerSerializer singletonInstance;
		
	//Default Constructer
	private CustomerSerializer() {
		
		//Creates the customer arraylist
		customers = new ArrayList<Customer>();
		
	}
	
	public static CustomerSerializer getSingletonInstance() {
		
		if (singletonInstance == null) 
		{
			singletonInstance = new CustomerSerializer();
			System.out.println("Single Object Created: " + singletonInstance);
		}
		return singletonInstance;
	}
	
	
	
	// view method, used to display the chosen customer from list (null if not found)
	public Customer view() {
		Scanner keyboard = new Scanner(System.in);
		
		// Read in the customer number chosen by the user
		System.out.print("ENTER CUSTOMER NUMBER : ");
		int cusToView=keyboard.nextInt();
		
		// for loop to loop through customer objects
		for (Customer tmpCus:customers) {
			
			// if number entered is equal to a customer number
			if(tmpCus.getNumber() == cusToView) {
				// display the customer
				System.out.println(tmpCus);
				return tmpCus;
			}
		}
		// if no customer number matches that number the return null
	    return null;		
	}
	
	// delete method, used to delete customer from list
	public void delete() {
		
		//this method calls the view method to find the customer
		Customer tmpCus = view();
		// if the number entered does match a customer number
		if(tmpCus != null)
			// remove that customer
			customers.remove(tmpCus);
	}
	
	// add method for adding a customer to the custome arraylist
	public void add() {
		
		//Create a Customer object
		Customer customer = new Customer();
		//Read in details
		customer.read();
		// This next line then adds it to the customers arraylist
		customers.add(customer);
	}
	
	// list method, used for viewing all customers in the arraylist
	public void list() {
		
		//for loop to loop through customer objects
		for(Customer tmpCus:customers)
		//prints contents of customers
			System.out.print(tmpCus);
	}
	
	// edit method, used to edit the selected customer from the list of customers
	public void edit() {
		
		//this method also calls the view method to find the customer for editing
		Customer tmpCus = view();
		// if the number entered does match a customer number
		if(tmpCus != null) {
			// get its index in the list
			int index=customers.indexOf(tmpCus);
			// and read in a new customer
			tmpCus.read();
			// resets the customer object in list
			customers.set(index, tmpCus);
		}
	}
	
	
	// This method will serialize the customers ArrayList when called, 
	// i.e. it will write it to a file called customers.ser
	// wrapped in try/catches to ensure program still runs even if there is an error
	public void writeRecordsToFile(){
		ObjectOutputStream os=null;
		try {
			// Serialize the ArrayList...
			FileOutputStream fileStream = new FileOutputStream(FILENAME);
			
			os = new ObjectOutputStream(fileStream);
				
			os.writeObject(customers);
			System.out.println("Done writing to file . . .");
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot create file to store customers.");
		}
		catch(IOException ioE){
			System.out.println(ioE.getMessage());
		}
		// finally to ensure code executes closing statement
		finally {
			try {
				
				os.close();
			}
			catch(IOException ioE){
				System.out.println(ioE.getMessage());
			}
		}
	}

	// This method will deserialize the customers ArrayList when called, 
	// i.e. it will restore the ArrayList from the file customers.ser
	public void readRecordsFromFile(){
		ObjectInputStream is=null;
		
		try {
			// Deserialize the ArrayList...
			FileInputStream fileStream = new FileInputStream(FILENAME);
		
			is = new ObjectInputStream(fileStream);
				
			customers = (ArrayList<Customer>)is.readObject();
			
			System.out.println("Done reading from file . . .");

		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot create file to store Customer details.");
		}
		catch(IOException ioE){
			System.out.println(ioE.getMessage());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		// finally to ensure code executes closing statement
		finally {
			try {
				is.close();
			}
			catch(IOException ioE){
				System.out.println(ioE.getMessage());
			}
		}
	}
}