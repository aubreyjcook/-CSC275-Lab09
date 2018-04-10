import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lab9Driver {
	Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		new Lab9Driver();
	}
	
	public Lab9Driver() throws FileNotFoundException {
		ArrayList<Student> personalInfo = new ArrayList<>();
		java.io.File dataFile = new java.io.File("data.txt");
		if(dataFile.exists()) {loadData(dataFile, personalInfo);};
		
		System.out.println("Use the menu below to manage personal info held in the database.");
		System.out.println("");
		
		boolean choiceSelected = false;
		
			do {
				try {
					// Give the user a list of their options
					System.out.println("1: Add a contact to the list.");
					System.out.println("2: Remove a contact from the list.");
					System.out.println("3: Display entries in the list.");
					System.out.println("0: Exit the program.");

					// Get the user input
					int userChoice = input.nextInt();
					input.nextLine();
					
					switch (userChoice) {
						
						case 1:
							addStudent(personalInfo);
							storeData(dataFile, personalInfo);
							break;
						case 2:
							removeStudent(personalInfo);
							storeData(dataFile, personalInfo);
							break;
						case 3:
							displayStudents(personalInfo);
							break;
						case 0:
							System.out.println("Exiting...");
							System.exit(0);
						default:
							System.out.println("Invalid value. Choose a number 0-3 only.");
							break;
				}
			} catch (InputMismatchException ex) {
					System.out.println("Bad input.");
					input.nextLine();
				}
			} while (!choiceSelected);
		}
	
	private void addStudent(ArrayList<Student> personalInfo) {
		Student tempStudent = new Student();
		 
		System.out.println("Enter the student's first name");
		String userInput = input.nextLine();
		tempStudent.setFirstName(userInput);
		System.out.println("Enter the student's last name");
		userInput = input.nextLine();
		tempStudent.setLastName(userInput);
		System.out.println("Enter the student's major");
		userInput = input.nextLine();
		tempStudent.setMajor(userInput);
		System.out.println("Enter the student's GPA");
		userInput = input.nextLine();
		tempStudent.setGPA(userInput);
		System.out.println("Enter the student's UIN");
		userInput = input.nextLine();
		tempStudent.setUIN(userInput);
		System.out.println("Enter the student's netID");
		userInput = input.nextLine();
		tempStudent.setNetID(userInput);
		System.out.println("Enter the student's age");
		userInput = input.nextLine();
		tempStudent.setAge(userInput);
		System.out.println("Enter the student's gender");
		userInput = input.nextLine();
		tempStudent.setGender(userInput);
		
		personalInfo.add(tempStudent);
		System.out.println("Student added successfully");
		return;
	}

	private void removeStudent(ArrayList<Student> personalInfo) {
		if(personalInfo.size() == 0) {
			System.out.println("The database has no students to remove!");
			return;
		} else {
			System.out.println("Enter the UIN of the student to be removed.");
			String userInput = input.nextLine();
			
			for(int i = 0; i < personalInfo.size(); i++) {
				if(personalInfo.get(i).UIN.equals(userInput)) {
					personalInfo.remove(i);
					
					System.out.println("Student removed.");
					break;
				} else if (i == personalInfo.size() - 1) {
					System.out.println("That student is not in the database.");
				}
			}
			return;		
		}
	}

	private void displayStudents(ArrayList<Student> personalInfo) {
		System.out.println("--------------------------------");
		for(int i = 0; i < personalInfo.size(); i++) {
			System.out.println(personalInfo.get(i).UIN);
			System.out.println(personalInfo.get(i).firstName);
			System.out.println(personalInfo.get(i).lastName);
			System.out.println(personalInfo.get(i).major);
			System.out.println(personalInfo.get(i).GPA);
			System.out.println(personalInfo.get(i).netID);
			System.out.println(personalInfo.get(i).age);
			System.out.println(personalInfo.get(i).gender);
			System.out.println("--------------------------------");
		}
	}
	
	public static void storeData(File dataFile, ArrayList<Student> personalInfo) {
		if(personalInfo.size() <= 0) {
			return;
		}
		else {
				eraseData(dataFile);
				try(
					java.io.PrintWriter output = new java.io.PrintWriter(dataFile);
				) {
					for(int i = 0; i < personalInfo.size(); i++) {
						output.println(personalInfo.get(i).UIN); 
						output.println(personalInfo.get(i).firstName);
						output.println(personalInfo.get(i).lastName); 
						output.println(personalInfo.get(i).major);    
						output.println(personalInfo.get(i).GPA);           
						output.println(personalInfo.get(i).netID);    
						output.println(personalInfo.get(i).age);      
						output.println(personalInfo.get(i).gender);   
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			System.out.println("Data Stored.");
			return;
		}
	}
	
	public static void loadData(File dataFile, ArrayList<Student> personalInfo) throws FileNotFoundException {
		Student tempStudent = new Student();
		
		if(dataFile.exists()) {
			Scanner input = new Scanner(dataFile);
			while(input.hasNext()) {
				tempStudent.setUIN(input.nextLine());
				tempStudent.setFirstName(input.nextLine());
				tempStudent.setLastName(input.nextLine());
				tempStudent.setMajor(input.nextLine());
				tempStudent.setGPA(input.nextLine());
				tempStudent.setNetID(input.nextLine());
				tempStudent.setAge(input.nextLine());
				tempStudent.setGender(input.nextLine());	
				
				personalInfo.add(tempStudent);
			}
			input.close();
		} else {
			System.out.println("No file to load.");
		}
	}
	
	public static void eraseData(File dataFile) {
		if(dataFile.delete())
        {return;}
        else
        {
            System.out.println("Failed to delete the file");
        }
	}
	
}
