package todo.email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


class DailyCompletedTasks{
	
	String completedTitle;
	Object completedTask;
	Object completedDate;
	
	
   DailyCompletedTasks(String completedTitle,Object completedTask,Object completedDate){
		this.completedTitle = completedTitle;
		this.completedTask = completedTask;
		this.completedDate = completedDate;
		
	}
   
public String toString() {
		return  completedTask+" "+" completed-at: "+completedDate+" |";
	}
	
}


class DailyTasks {

	private String taskNumber;
	private String taskTitle;
	private String dailyTasks;
	Object createdDate;
	
	DailyTasks(String taskNumber,String taskTitle, String dailyTasks,Object createdDate) {
		this.taskNumber = taskNumber;
		this.taskTitle = taskTitle; 
		this.dailyTasks = dailyTasks;
		this.createdDate = createdDate;
	}

	public String toString() {   
		return "Task-"+taskNumber+" "+taskTitle + " : " + dailyTasks+" | created-at: "+createdDate;
	}
	
	
}


interface TaskTypes {

	public void menu();

	public void addTask() throws NotANumberException ;

	public void listTask();

	public void updateTask();

	public void markTask();

	public void deleteTask();
                                                      
}

class NotANumberException extends Exception{
	
}


class AllTasks implements TaskTypes {

	Scanner scanner = new Scanner(System.in);
	
	LinkedHashMap<String,DailyTasks> map = new LinkedHashMap<String,DailyTasks>();

	LinkedHashMap<String,DailyCompletedTasks> map2 = new LinkedHashMap<String,DailyCompletedTasks>();
	
	String taskNumber;
	String dailyTask;
	String taskTitle;
	
    boolean keyFound = true;
	
	
	Date currentDate;
    SimpleDateFormat currentFormat;
    
    Date completedDate; 
	SimpleDateFormat date;

	public void menu() {
		System.out.println("----------------------");
		System.out.println("TODO APP");
		System.out.println("----------------------");
		System.out.println("1. Add Task");
		System.out.println("2. List Task");
		System.out.println("3. Update Task");
		System.out.println("4. Mark as Completed");
		System.out.println("5. Delete Task");
		System.out.println("0. Exit Program");
		System.out.print("Select Option to  Proceed : ");

	}

	public void addTask()throws NotANumberException{
		
		boolean ExceptionCheck = false;
		
	    String regex = "[1-9]";
	  
	    currentDate = new Date();
	    currentFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
		System.out.println("-------------------");
		System.out.print("Enter Task Number : ");
		try {
		taskNumber = scanner.nextLine();
		if(!taskNumber.matches(regex)) {
			throw new NotANumberException();
		}
		}
		catch(NotANumberException exception) {
			ExceptionCheck = true;
	        exception.printStackTrace();
		}
		finally {
			System.out.println("-------------------------------");
			System.out.println("For Task Number Use Number Only");
			System.out.println("-------------------------------");
		}
		if(keyFound != map.containsKey(taskNumber)){
			if(ExceptionCheck == true) {              
				System.out.print("Enter Task Number : "); 
				taskNumber = scanner.nextLine();
			}
			if(keyFound != map.containsKey(taskNumber)) {
			System.out.print("Enter Task Title : ");
			taskTitle = scanner.nextLine();
			System.out.print("Enter Task : ");
			dailyTask = scanner.nextLine();
		
			map.put(taskNumber, new DailyTasks(taskNumber,taskTitle,dailyTask,currentFormat.format(currentDate)));
		
			System.out.println("--------------------");
			System.out.println("TASK ADDED");
			System.out.println("--------------------");
			}
			else{
				System.out.println("---------------------------");
				System.out.println("Task Number Already Exists!");
			}
		}
		else{
			System.out.println("---------------------------");
			System.out.println("Task Number Already Exists!");
		}
			}


	public void listTask() {
		System.out.println("--------------------");
		System.out.println("Current Tasks ");
		System.out.println("--------------------");
		for(Map.Entry<String,DailyTasks> show : map.entrySet()) {
			System.out.println(show.getValue());
		}
		System.out.println("--------------------");
		System.out.println("Completed Tasks");
		System.out.println("--------------------");
		
		
		for(Map.Entry<String, DailyCompletedTasks> showCompleted : map2.entrySet() ) {
			System.out.println(showCompleted.getKey()+" "+showCompleted.getValue());
		
		}
		System.out.println("--------------------");
	}

	public void updateTask() {
		listTask();
		System.out.print("Enter Task Number to Update : ");
		String taskNumber = scanner.nextLine();
		if(keyFound == map.containsKey(taskNumber)) {
				System.out.print("Enter Task Title : ");
				taskTitle = scanner.nextLine();
				System.out.print("Enter New Task : ");
				dailyTask = scanner.nextLine();
				
				map.put(taskNumber, new DailyTasks(taskNumber,taskTitle,dailyTask,currentFormat.format(currentDate)));
				
				System.out.println("--------------------");
				System.out.println("Task Updated");
				System.out.println("--------------------");
		}else {
			System.out.println("--------------------");
			System.out.println("Task not found ");
		}
	
	}

	public void markTask() {
		
		completedDate = new Date();
	    date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		listTask();
		System.out.print("Enter Task Number To Mark As Completed: ");
	    taskNumber = scanner.nextLine();
		for(Map.Entry<String, DailyTasks> access : map.entrySet()) {
	
		if(keyFound == map.containsKey(taskNumber)) {
			    String  taskId = access.getKey();
				Object dailyTask = access.getValue();
			
				map2.put(taskNumber, new DailyCompletedTasks(taskId,dailyTask,date.format(completedDate)));
				
				System.out.println("----------------");
				System.out.println("Task Marked as Completed");
				System.out.println("----------------");
				
				System.out.println("==================================================================================================================");
				
		        String to = "arun35476@gmail.com";
		        
		        String from = "mathersm889@gmail.com";
		        
		        String host = "smtp.gmail.com";

	
		       Properties properties = new Properties();

		        properties.put("mail.smtp.host", host);
		        properties.put("mail.smtp.port", "465");
		        properties.put("mail.smtp.ssl.enable", "true");
		        properties.put("mail.smtp.auth", "true");
		        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); 
		        

		        Session session = Session.getInstance(properties, new Authenticator() {

		             public PasswordAuthentication getPasswordAuthentication() {

		                return new PasswordAuthentication("mathersm889@gmail.com", "password");

		            }

		        });

		    //    session.setDebug(true);

		        try {
		            MimeMessage message = new MimeMessage(session);

		            message.setFrom(new InternetAddress(from));

		            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		            message.setSubject("COMPLETED TASKS");
		            
		            message.setText(dailyTask+" completed-at: "+date.format(completedDate)+" |");

		            System.out.println("sending...");
		     
		            Transport.send(message);
		            System.out.println("Mail Sent Successfully....");
		        } catch (MessagingException exception) {
		            exception.printStackTrace();
		        }	
					
				System.out.println("==================================================================================================================");
				break;
				}
		else {
			System.out.println("--------------------");
			System.out.println("Task Not Found");
			break;
		}
			}
		      map.remove(taskNumber);
		}

	public void deleteTask() {
		listTask();
		System.out.print("Enter TaskNumber to Delete : ");
		String deleteTask = scanner.nextLine();
		if(keyFound == map.containsKey(deleteTask)) {
				System.out.println("--------------------");
				System.out.println("Task Deleted");
				System.out.println("--------------------");
		}
		else {
			System.out.println("----------------------");
			System.out.println("Task not found ");
		}
		map.remove(deleteTask);
	}

}

public class TodoApplication extends AllTasks {

	public static void main(String[] args) {

		TodoApplication todo = new TodoApplication();

		int choice;
		Scanner userInput = new Scanner(System.in);

		do {
			todo.menu();
			choice = userInput.nextInt();
			switch (choice) {
			case 1:
				try {
					todo.addTask();
				} catch (NotANumberException exception) {
					exception.printStackTrace();
				}
				break;
			case 2:
				todo.listTask();
				break;
			case 3:
				todo.updateTask();
				break;
			case 4:
				todo.markTask();
				break;
			case 5:
				todo.deleteTask();
				break;
			case 0:
				System.out.println("---------------------");
				System.out.println("End of program");
				System.out.println("---------------------");
				break;
			default:
				System.out.println("---------------------");
				System.out.println("Enter valid choice! ");
			}

		} while (choice != 0);

	}

}
