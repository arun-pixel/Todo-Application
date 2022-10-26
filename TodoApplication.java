package todo.application;

import java.util.*;

class DailyNotes{
	
	private int noteNumber;
	private String dailyNotes;
	
	DailyNotes(int noteNumber,String dailyNotes){
		this.noteNumber = noteNumber;
		this.dailyNotes = dailyNotes;
	}
	
	public int getNoteNumber() {
		return noteNumber;
	}
	
	public String getdailyNotes() {
		return dailyNotes;
	}
	
	public String toString() {
		return "Note"+noteNumber+": "+dailyNotes;
	}
}

public class TodoApplication {

	public static void main(String[] args) {
		
		List<DailyNotes> list = new ArrayList<DailyNotes>();
		
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		
		int choice;
		do {
			System.out.println("------------------------");
			System.out.println("TODO APP");
			System.out.println("------------------------");
			System.out.println("0. EXIT THE PROGRAM");
			System.out.println("1. ADD NOTES");
			System.out.println("2. UPDATE NOTES");
			System.out.println("3. LIST NOTES");
			System.out.println("4. DELETE NOTES");
			System.out.print("ENTER YOUR CHOICE : ");
			choice = scanner.nextInt();
			System.out.println("------------------------");
			
			switch(choice) {
			case 1:	
				System.out.print("Enter Note Number : ");
				int noteNumber = scanner.nextInt();
				
				System.out.print("Start Typing : ");
				String notes = scanner2.nextLine();
				
				list.add(new DailyNotes(noteNumber,notes));
				System.out.println("------------------------");
				System.out.println("Note Added");
				break;
			case 2:

				boolean found = false;
				System.out.print("Enter Note Number To Update : ");
				noteNumber = scanner.nextInt();
				ListIterator<DailyNotes> iterate = list.listIterator();
				while(iterate.hasNext()) {
					DailyNotes accessNote = iterate.next();
					if(accessNote.getNoteNumber() == noteNumber) {
						System.out.print("Enter new notes : ");
						notes = scanner2.nextLine();
						iterate.set(new DailyNotes(noteNumber,notes));
						found = true;
						System.out.println("------------------------");
					}
				}
				if(!found) {
					System.out.println("Note not found");
				}
				else {
					System.out.println("Note updated");
				}
				break;
			case 3:
				iterate = list.listIterator();
				while(iterate.hasNext()) {
					System.out.println(iterate.next());
				}
				break;
			case 4:
				found = false;
				System.out.print("Enter Note Number to Delete : ");
				noteNumber = scanner.nextInt();
			     iterate = list.listIterator();
			   while(iterate.hasNext()) {
				   DailyNotes accessNotes = iterate.next();
				   if(accessNotes.getNoteNumber() == noteNumber) {
					  iterate.remove();
					  found = true;
					  System.out.println("------------------------");
				   }
			   }
			   if(!found) {
				   System.out.println("Note not found");
			   }
			   else {
				   System.out.println("Note Deleted");
			   }
			   break;
			case 0:
				System.out.println("NOTE ENDED");
				System.out.println("------------------------");
				break;
				default:
					System.out.println("Enter valid choice! ");
			}
		
			
		}while(choice!=0);
		
		scanner.close();
		scanner2.close();

		
	}

}
