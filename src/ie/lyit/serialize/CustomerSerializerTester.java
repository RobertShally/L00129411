package ie.lyit.serialize;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.Serializable;
import ie.lyit.serialize.CustomerSerializer;
import ie.lyit.hotel.Customer;
import ie.lyit.hotel.Name;

public class CustomerSerializerTester {
		
	public static void main(String[] args) {
		
		CustomerSerializer customerSerializer = CustomerSerializer.getSingletonInstance();
		Scanner keyboardIn = new Scanner(System.in);
		
		Customer cObj = new Customer("Mr","Joe","Doe","12 Hi Rd,Letterkenny",
				                             "0871234567","joe@hotmail.com");
		//customerSerializer.add();
		//customerSerializer.view();
		//customerSerializer.list();


		// Read the ArrayList from the File
		customerSerializer.readRecordsFromFile();
		
		// List all the books in the ArrayList
		//customerSerializer.list();
		
		
		//Switch style menu . . . 
		//integer option to save menu choices
		int option = 0;
				
				do {
					
					// Section wrapped in try-catch to handle the event if/when the user
					// enters an invalid customer number in the list. 
					try {
							System.out.println("\t1. Add");
							System.out.println("\t2. List");
							System.out.println("\t3. View");
							System.out.println("\t4. Edit");
							System.out.println("\t5. Delete");
							System.out.println("\t6. Quit");
							System.out.println("Enter Option: ");
							option=keyboardIn.nextInt();
							
							switch(option){
							case 1: customerSerializer.add();break;
							case 2:	customerSerializer.list();break;
							case 3:	customerSerializer.view();break;
							case 4:	customerSerializer.edit();break;
							case 5:	customerSerializer.delete();break;
							case 6:	break;
								default:System.out.println("INVALID OPTION SELECTED!!!");
							}
				    }catch(InputMismatchException e) {
				    	System.out.println("Option entered is incorrenct.");
				    	break;
				    }
				catch(NoSuchElementException e) {
					System.out.println("That customer is not on this list.");
					break;
				}
	
				}while(option != 6);
					
	
				
				// Write the Arraylist to file
				customerSerializer.writeRecordsToFile();
				
				
	}
	}


