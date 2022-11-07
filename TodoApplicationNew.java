package application.todoApp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


class Mail{
	
	 Object mail;
	
	Mail(Object mail){
		this.mail =  mail;
	}
	
	public String toString() {
		return ""+mail;
	}
}


class DailyCompletedTasks{
	
	int completedTitle;
	Object completedTask;
	Object completedDate;

	
	
   DailyCompletedTasks(int completedTitle,Object completedTask,Object completedDate){
		this.completedTitle = completedTitle;
		this.completedTask = completedTask;
		this.completedDate = completedDate;
		
	}
   
public String toString() {
		return  completedTask+" "+" completed-at: "+completedDate+" |";
	}
	
}


class DailyTasks {

	private int taskNumber;
	private String taskTitle;
	private String dailyTasks;
	Object createdDate;

	
	
	DailyTasks(int taskNumber,String taskTitle, String dailyTasks,Object createdDate) {
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

	public void addTask();

	public void listTask();

	public void updateTask();

	public void markTask();

	public void deleteTask();
	
	public void mail();

}


class AllTasks implements TaskTypes {

	Scanner scanner = new Scanner(System.in);
	Scanner scanner2 = new Scanner(System.in);
	
	LinkedHashMap<Integer,DailyTasks> map = new LinkedHashMap<Integer,DailyTasks>();

	LinkedHashMap<Integer,DailyCompletedTasks> map2 = new LinkedHashMap<Integer,DailyCompletedTasks>();
	
	LinkedHashMap<Integer,Mail> Mail = new LinkedHashMap<Integer,Mail>();

	
	int taskNumber;
	String dailyTask;
	String taskTitle;
	
	Date currentDate = new Date();
    SimpleDateFormat currentFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public void menu() {
		System.out.println("----------------------");
		System.out.println("TODO APP");
		System.out.println("----------------------");
		System.out.println("1. Add Task");
		System.out.println("2. List Task");
		System.out.println("3. Update Task");
		System.out.println("4. Mark as Completed");
		System.out.println("5. Delete Task");
		System.out.println("6. View Mail");
		System.out.println("0. Exit Program");
		System.out.print("Select Option to  Proceed : ");

	}

	public void addTask() {
	
	    
		System.out.println("-------------------");
		System.out.print("Enter Task Number : ");
		taskNumber = scanner.nextInt();
		System.out.print("Enter Task Title : ");
		taskTitle = scanner2.nextLine();
		System.out.print("Enter Task : ");
		dailyTask = scanner2.nextLine();
	//	list.add(new DailyTasks(taskNumber, taskTitle, dailyTask,currentFormat.format(currentDate)));
		
		map.put(taskNumber, new DailyTasks(taskNumber,taskTitle,dailyTask,currentFormat.format(currentDate)));
		
		
		System.out.println("--------------------");
		System.out.println("TASK ADDED");
		System.out.println("--------------------");

	}

	public void listTask() {
		System.out.println("--------------------");
		System.out.println("Current Tasks ");
		System.out.println("--------------------");
		for(Map.Entry<Integer,DailyTasks> show : map.entrySet()) {
			System.out.println(show.getValue());
		}
		System.out.println("--------------------");
		System.out.println("Completed Tasks");
		System.out.println("--------------------");
		
		
		for(Map.Entry<Integer, DailyCompletedTasks> showCompleted : map2.entrySet() ) {
			System.out.println(showCompleted.getKey()+" "+showCompleted.getValue());
		
		}
		System.out.println("--------------------");
	}

	public void updateTask() {
		System.out.print("Enter Task Number to Update : ");
		int taskNumber = scanner.nextInt();
		for(Map.Entry<Integer, DailyTasks> access  : map.entrySet()) {
			if(taskNumber == access.getKey()) {
				System.out.print("Enter Task Title : ");
				taskTitle = scanner2.nextLine();
				System.out.print("Enter New Task : ");
				dailyTask = scanner2.nextLine();
				map.put(taskNumber, new DailyTasks(taskNumber,taskTitle,dailyTask,currentFormat.format(currentDate)));
			}
		}
		for(Map.Entry<Integer,DailyTasks> access : map.entrySet()) {
		if(taskNumber != access.getKey()) {
			System.out.println("--------------------");
			System.out.println("Task Not Found");
			System.out.println("--------------------");
			if(taskNumber != access.getKey()) {
				break;
			}
		} else {
			System.out.println("--------------------");
			System.out.println("Task Updated");
			System.out.println("--------------------");
			if(taskNumber == access.getKey()) {
				break;
			}
		}
		}	
	
	}

	public void markTask() {
		
		Date completedDate = new Date();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		listTask();
		System.out.print("Enter Task Number To Mark As Completed: ");
		int taskNumber = scanner.nextInt();
		for(Map.Entry<Integer, DailyTasks> access : map.entrySet()) {
		
			if(taskNumber == access.getKey()) {	
			    int dailyTask = access.getKey();
				Object taskTitle = access.getValue();
				
				Object mail = access.getKey()+" "+access.getValue()+"  completed-at: "+date.format(completedDate)+" |";
				
				map2.put(taskNumber, new DailyCompletedTasks(dailyTask,taskTitle,date.format(completedDate)));
				
				Mail.put(taskNumber, new Mail(mail));
				
				System.out.println("----------------");
				System.out.println("Task Marked as Completed");
				System.out.println("----------------");
				}
			}
		      map.remove(taskNumber);
		}

	public void deleteTask() {
		System.out.print("Enter TaskNumber to Delete : ");
		int deleteTask = scanner.nextInt();
		for(Map.Entry<Integer,DailyTasks> show : map.entrySet()) {
			if(show.getKey() == deleteTask) {
				System.out.println("--------------------");
				System.out.println("Task Deleted");
				System.out.println("--------------------");
				break;
			}
			if(show.getKey() !=  deleteTask) {
				System.out.println("--------------------");
				System.out.println("Task Not Found");
				System.out.println("--------------------");
				break;
			}
		}
		map.remove(deleteTask);
	}
	
	public void mail() {
		System.out.println("----------------------");
		System.out.println("All Inboxes ");
		System.out.println();
	   for(Map.Entry<Integer, Mail> viewMail : Mail.entrySet()) {
		  System.out.println(viewMail.getValue());
	   }
		
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
				todo.addTask();
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
			case 6:
				todo.mail();
				break;
			default:
				System.out.println("---------------------");
				System.out.println("Enter valid choice! ");
			}

		} while (choice != 0);

	}

}

