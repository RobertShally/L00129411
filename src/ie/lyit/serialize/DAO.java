package ie.lyit.serialize;
import ie.lyit.hotel.Customer;

public interface DAO {

	public void readRecordsFromFile();
	public void writeRecordsToFile();
	public void add();
	public void delete();
	public void edit();
	public void list();
	public Customer view();
}
