/** Driver program for CS4141 Programming Assignment
 * Author: Edward Lynch-Milner
 * Student ID: 18222021
 * Course: LM121 - Computing Technologies
 */
public class Driver {
    public static void main(String[] args) {
        //Creating a list of 32 for ease of testing
        System.out.println("Creating a new instance of the Spreadsheet class called namesList");
        Spreadsheet namesList = new Spreadsheet(32);
        System.out.println("\nnamesList before any names added:");
        namesList.Display();
        System.out.println("Adding 30 sheets to the list");
        for (int i = 0; i < 29; i++) {
            namesList.add();
        }
        //If namesList.add() returns true the first statement will be executed
        if (namesList.add()) {
            System.out.println("1 extra sheet was added successfully");
        } else {
            System.out.println("1 extra sheet was not added successfully as the max number of sheets is reached");
        }

        System.out.printf("The length of the list is %s and the last sheet in the list is %s\n",namesList.length(),namesList.sheetName(31));
        System.out.println("\nMoving Sheet1 to the end of namesList");
        namesList.moveToEnd("Sheet1");

        System.out.println("\nMoving Sheet20 to the end of namesList");
        namesList.moveToEnd("Sheet20");

        System.out.println("\nRemoving the last element from namesList");
        String returnedName = namesList.remove(31);
        System.out.printf("The removed sheet at index 31(the last index) was %s \n",returnedName);

        System.out.println("\nRemoving Sheet12 from namesList");
        int returnedIndex = namesList.remove("Sheet12");
        String name = "Sheet12";
        System.out.printf("The sheet %s was removed from index %d\n",name,returnedIndex);
        namesList.Display();
        
        System.out.println("\nCreating an instance of the Spreadsheet class called names");
        Spreadsheet names = new Spreadsheet();
        //Creating a list with 6 sheets
        for (int i = 0; i < 3; i++) {
            names.add();
        }
        System.out.println("\nnames after adding 3 sheets: ");
        names.Display();

        System.out.println("Renaming Sheet1 to Income");
        int renamed = names.rename("Sheet1", "Income");
        if (renamed != -1) {
            System.out.println("The sheet has been renamed to Income");
        } else {
            System.out.println("\nThe sheet has not been renamed");
        }

        System.out.println("Renaming Sheet2 to income");
        renamed = names.rename("Sheet2","income");
        if (renamed != -1) {
            System.out.println("\nThe sheet has been renamed to income");
        } else {
            System.out.println("\nThe sheet was not successfully renamed as income already exists");
        }
        names.Display();

        System.out.println("Moving Sheet3 to after Sheet5");
        names.move("Sheet3","Sheet5",false);
        names.Display();
        System.out.println("Moving Sheet5 to before Sheet2");
        names.move("Sheet5","Sheet2",true);
        names.Display();

        System.out.printf("The index of Sheet5 after moving is %d\n",names.index("Sheet5"));
        System.out.printf("The sheet name at index 4 is %s\n",names.sheetName(4));
        System.out.printf("The sheet name at index 0 is %s\n",names.sheetName(0));

        System.out.println("Removing the sheet at index 5");
        System.out.printf("The sheet removed from index 5 was %s\n",names.remove(5));
        names.Display();
        
        System.out.println("Adding a new sheet");
        names.add();
        names.Display();

        System.out.println("Moving sheet at index 0 to before the sheet at index 3");
        System.out.printf("The moved sheet is %s\n",names.move(0,3,true));
        names.Display();

        System.out.println("Moving sheet at index 0 to after the sheet that was at index 3");
        System.out.printf("The moved sheet is %s\n",names.move(0,3,false));
        names.Display();

        System.out.printf("The length of the names list is %d\n",names.length());

        //This is to create a list to test if the moveToEnd method works consistently
        System.out.println("\nCreating an instance of the Spreadsheet class called names6");
        Spreadsheet names6 = new Spreadsheet(6);
        for (int i = 0; i < 3; i++) {
            names6.add();
        }
        System.out.println("\nThe list before moving to end: ");
        names6.Display();

        for (int x = 0; x < names6.length() - 3; x++) {
            names6.moveToEnd(0);
        }
        System.out.println("\nThe list after moving 3 sheets to the end: ");
        names6.Display();
        
        System.out.println("\nCreating an instance of the Spreadsheet class called names4");
        //Creating a list to test move method to reverse a list
        System.out.println("\nI will now reverse the array to prove that the move method works consistenly");
        Spreadsheet names4 = new Spreadsheet(4);
        for (int i = 0; i < 1; i++) {
            names4.add();
        }
        System.out.println("\nBefore reversing: ");
        names4.Display();

        for (int i = 0; i < names4.length() - 1; i++) {
            names4.move(names4.length() - 1,i,true);
        }

        System.out.println("\nAfter reversing: ");
        names4.Display();

        System.out.println("\nReverting names back to original positions");
        for (int i = names4.length() -1 ; i >= 0; i--) {
            names4.move(0,i,false);
        }
        names4.Display();

        System.out.println("Attempting to rename a sheet name with symbols that are not allowed");
        System.out.println("Renaming Sheet1 to $Sheet_1");
        if (names4.rename("Sheet1","$Sheet_1") != -1) {
            System.out.println("The sheet was renamed successfully");
        } else {
            System.out.println("The sheet was not renamed as illegal characters were used");
        }  
        names4.Display();

        System.out.println("Moving Sheet2 to the end");
        int movedIndex = names4.moveToEnd("Sheet2");
        if (movedIndex != -1) {
            System.out.printf("Sheet2 was moved to the end to index %d \n",movedIndex);
        } else {
            System.out.println("Sheet2 was not moved to the end");
        }

        System.out.println("Attempting to move Sheet1 to Sheet1");
        if (names4.move("Sheet1", "Sheet1",true) == -1) {
            System.out.println("Sheet1 is already at the position");
        } else {
            System.out.println("Sheet1 was moved");
        }
        
        System.out.println("\nAttempting to find Sheet20");
        if (names4.index("Sheet20") != -1) {
            System.out.println("Sheet20 was found");
        } else {
            System.out.println("Sheet20 was not found");
        }
    }
}