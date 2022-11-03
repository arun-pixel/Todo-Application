
package application.todo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

class DailyTasks {

	private int taskNumber;
	private String taskTitle;
	private String dailyTasks;
	Object createdDate;
	Object completedDate;
	

	DailyTasks(int taskNumber, String taskTitle, String dailyTasks,Object createdDate) {
		this.taskTitle = taskTitle;
		this.taskNumber = taskNumber;
		this.dailyTasks = dailyTasks;
		this.createdDate = createdDate;
	}

	DailyTasks(String taskTitle, String dailyTasks,Object createdDate,Object completedDate) {
		this.taskTitle = taskTitle;
		this.dailyTasks = dailyTasks;
		this.createdDate = createdDate;
		this.completedDate = completedDate;
	}

	public int getTaskNumber() {
		return taskNumber;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public String getDailyTasks() {
		return dailyTasks;
	}

	public String toString() {   
		return taskTitle + " : " + dailyTasks+" | created-at: "+createdDate;
	}
	
	public String getCompletedDate() {  
		return taskTitle + " : " + dailyTasks+" | created-at "+createdDate+" | completed-at "+completedDate;
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

	ListIterator<DailyTasks> iterate;

	LinkedList<DailyTasks> list = new LinkedList<DailyTasks>();

	LinkedList<DailyTasks> list2 = new LinkedList<DailyTasks>();

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
		list.add(new DailyTasks(taskNumber, taskTitle, dailyTask,currentFormat.format(currentDate)));
		System.out.println("--------------------");
		System.out.println("TASK ADDED");
		System.out.println("--------------------");

	}

	public void listTask() {
		int showTaskNumber = 0;
		int showTaskNumber2 = 0;
		System.out.println("--------------------");
		System.out.println("Current Tasks ");
		System.out.println("--------------------");
		for (DailyTasks showTask : list) {
			System.out.println("Task-" + (++showTaskNumber) + " " + showTask);
		}
		System.out.println("--------------------");
		System.out.println("Completed Tasks");
		System.out.println("--------------------");
		for (DailyTasks showCompletedTask : list2) {
			System.out.println("Completed Task-" + (++showTaskNumber2) + " " + showCompletedTask.getCompletedDate());
		}
		System.out.println("--------------------");
	}

	public void updateTask() {
		iterate = list.listIterator();
		System.out.print("Enter Task Number to Update : ");
		taskNumber = scanner.nextInt();
		while (iterate.hasNext()) {
			DailyTasks access = iterate.next();
			if (taskNumber == access.getTaskNumber()) {
				System.out.print("Enter Task Title : ");
				taskTitle = scanner2.nextLine();
				System.out.print("Enter New Task : ");
				dailyTask = scanner2.nextLine();
				// list.set((taskNumber-1), new DailyTasks(taskNumber,taskTitle,dailyTask));
				iterate.set(new DailyTasks(taskNumber, taskTitle, dailyTask,currentFormat.format(currentDate)));
			}
		}
		if (taskNumber <= 0 || taskNumber > list.size()) {
			System.out.println("--------------------");
			System.out.println("Task Not Found");
			System.out.println("--------------------");
		} else {
			System.out.println("--------------------");
			System.out.println("Task Updated");
			System.out.println("--------------------");
		}
	}

	public void markTask() {
		Date completedDate = new Date();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		listTask();
		iterate = list.listIterator();
		System.out.print("Enter Task Number : ");
		int taskNumber = scanner.nextInt();
		while (iterate.hasNext()) {
			DailyTasks access = iterate.next();
			if (taskNumber == access.getTaskNumber()) {
				String taskTitle = access.getTaskTitle();
				String dailyTask = access.getDailyTasks();
				list2.add(new DailyTasks(taskTitle, dailyTask,currentFormat.format(currentDate),date.format(completedDate)));
				System.out.println("----------------");
				System.out.println("Task Marked as Completed");
				System.out.println("----------------");
				list.remove(taskNumber - 1);
			}
		}

	}

	public void deleteTask() {
		System.out.print("Enter TaskNumber to Delete : ");
		int deleteTask = scanner.nextInt();
		if (deleteTask - 1 < 0 || deleteTask > list.size()) {
			System.out.println("--------------------");
			System.out.println("Wrong Index ! Please Enter Task Number | 1 to " + list.size()+" |");
		} else {
			list.remove(deleteTask - 1);
			System.out.println("--------------------");
			System.out.println("Task Deleted");
			System.out.println("--------------------");
		}
	}
	
	public void mail() {
		System.out.println("----------------------");
		System.out.println("All Inboxes ");
		System.out.println();
		for(DailyTasks showMail : list2) {
			System.out.println(showMail.getCompletedDate());
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
			default:
				System.out.println("---------------------");
				System.out.println("Enter valid choice! ");
			}

		} while (choice != 0);

	}

}

