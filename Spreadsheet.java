/** This is the class which will provide the methods for the CS4141 Spreadsheet
 * Assignment.
 * Author: Edward Lynch-Milner
 * Student ID: 18222021
 * Course: LM121 - Computing Technologies
 */
public class Spreadsheet {
    private int nextIndex = 3;
    private int N = 1;
    private int maxSize = 256;
    private String sheetName = "";
    private String[] spreadsheets;

    /** 
     * Creates the list spreadsheets with the default first 3 names, Sheet1 to Sheet3
     */
    public Spreadsheet() {
        spreadsheets = new String[maxSize];

        //Initialising the array with the default 3 sheets
        for (int i = 0; i < 3; i++) {
            spreadsheets[i] = "Sheet" + (N + i);
        }
        N = 4;
    }

    /**
     * An overloaded version of the constructor just for ease of testing
     * @param size the specified size for the list
     */
    public Spreadsheet(int size) {
        if (size >= 3 && size <= 256) {
            spreadsheets = new String[size];
            for (int i = 0; i < 3; i++) {
                spreadsheets[i] = "Sheet" + (N + i);
            }
            maxSize = size;
        } else {
            spreadsheets = new String[maxSize];
            for (int i = 0; i < 3; i++) {
                spreadsheets[i] = "Sheet" + (N + i);
            }
        }
        N = 4;
    }

    /**
     * Finds the index of the specified sheet name
     * @param sheetName the sheet of which its index is to be found
     * @return the index of the sheet found
     */
    public int index(String sheetName) {
        int i;
        for (i = 0; i < nextIndex && spreadsheets[i].compareToIgnoreCase(sheetName) != 0; i++);

        if (i < length()) {
            return i;
        } else {
            return -1;
        }	
    }

    /**
     * Checks if the list contains the specified sheet name
     * @param sheetName the name of the sheet to be checked
     * @return Returns if the list contains the sheet or not
     */
    private boolean contains(String sheetName) {

        return index(sheetName) != -1;
    }

    /**
     * Adds a new sheet to the list in the next available position
     * @return trueOrFalse Returns true if sheet is added successfully
     */
    public boolean add() {
        if (nextIndex < maxSize) {
            sheetName = "Sheet" + N;
            if (contains(sheetName)) {
                while (contains(sheetName)) {
                    N++;
                    sheetName = "Sheet" + N;
                }
            }
            spreadsheets[nextIndex] = "Sheet" + N;
            N++;
            nextIndex++;
            return true;
        } 
        return false;
    }

    /**
     * Removes the specified sheet
     * @param sheetName the sheet to be removed
     * @return the index of the sheet that was removed
     */
    public int remove(String sheetName) {
        int removedIndex = -1;
        if (contains(sheetName)) {
            if (length() > 1) {
                removedIndex = index(sheetName);
                for (int i = removedIndex; i < nextIndex - 1; i++) {
                    spreadsheets[i] = spreadsheets[i + 1];
                }
                nextIndex--;
			}
        }
        return removedIndex;
    }

    /**
     * Overloaded remove method to remove a sheet at a given index
     * @param index the index of the sheet to be removed
     * @return removedSheet the name of the sheet removed
     */
    public String remove(int index) {
        String removedSheet = "";
        if (length() > 1 && (index > -1 && index < length())) {
            removedSheet = spreadsheets[index];
            remove(removedSheet);
        }
        return removedSheet;
    }

    /**
     * Moves a sheet to another sheet, either before or after the sheet to
     * @param from the sheet to move from
     * @param to the sheet to move to
     * @param before moves the sheet before if true or after if false
     * @return the index of where the sheet was moved to
     */
    public int move (String from, String to, boolean before) {
        if (((contains(from) && contains(to))) && !(from.equals(to))) {
            int fromPosition = index(from);
            int toPosition = index(to);
            String temp = spreadsheets[fromPosition];
            int afterOrBefore;
            int toPosAdjust;
            if (fromPosition != maxSize - 1) {
                temp = spreadsheets[fromPosition+1];
            } 
            if (before == false){
                afterOrBefore = toPosition + 1;
                toPosAdjust = toPosition;
            } else {
                afterOrBefore = toPosition;
                toPosAdjust = toPosition - 1;
            }
            if (fromPosition > toPosition) {
                for (int i = fromPosition; i > toPosition; i--) {
                    spreadsheets[i] = spreadsheets[i - 1];
                }
                spreadsheets[afterOrBefore] = from;
                return index(from);
            } else { 
                for (int i = fromPosition; i < toPosition; i++) {
                    spreadsheets[i] = spreadsheets[i + 1];
                }
                spreadsheets[toPosAdjust] = from;
                if (fromPosition != toPosition - 1) {
                    spreadsheets[fromPosition] = temp;
                }
                return index(from);
            }
        }
        return -1;
    }
	
	/** Different move method
	public String move(int from, int to, boolean before) {
        /*
        String fromName = null;
        int i;
        // If the indexes are the same OR EITHER of them is not valid
        if(from == to || !isValidIndex(from) || !isValidIndex(to)) {
            // return null to indicate operation unsuccessful
            return null;
        }
        fromName = tabs[from]; // Make a note of the 'from' tab name
        if(from < to) {
            if(before) {
                shift('L',from+1,to-1);
                tabs[to-1] = fromName;
            } else {
                shift('L',from+1,to);
                tabs[to] = fromName;
            }
        } else {
            // from > to
            if(before) {
                shift('R',to,from-1);
                tabs[to] = fromName;
            } else {
                shift('R',to+1,from-1);
                tabs[to+1] = fromName;
            }
        }
        return fromName;
    }      **/

    /**
     * An overloaded version of move
     * @param from the index of the sheet to be moved
     * @param to the index of where the sheet is to be moved
     * @param before moves the sheet before or after if true or false
     * @return returns the name of the sheet moved
     */
    public String move(int from, int to, boolean before) {
        if ((from > -1 && to > -1) && (from != to && (from < length() && to < length()))) {
            String fromName = spreadsheets[from];
            String toName = spreadsheets[to];
            move(fromName, toName, before);
            return fromName;
        }
        return "";
    }

    /**
     * Moves the sheet at the specified index to the end of the list 
     * after the last active sheet name
     * @param from the index of the sheet to be moved
     * @return movedSheetName the name of the sheet moved
     */
    public String moveToEnd(int from) {
        String movedSheetName = "";
        if (from > -1 && from < length()) {
            movedSheetName = spreadsheets[from];
            move(from, nextIndex - 1, false);
        }
        return movedSheetName;
    }
	
	/**Alternative to above 
	public int moveToEnd(String from) {
        int fromIndex = index(from);
        if(fromIndex != -1) {
            move(fromIndex,nextAvailablePosition - 1 /*end of list/ ,false /*after/);
        }
        return fromIndex;
    } **/

    /**
     * An overloaded version of moveToEnd with the name of the sheet specified
     * @param from the name of the sheet to be moved
     * @return movedIndex the index of the sheet moved
     */
    public int moveToEnd(String from) {
        int movedIndex = 0;
        if (contains(from)) {
            movedIndex = index(from);
            moveToEnd(movedIndex);
            return index(from);
        }

        return -1;
    }

    /**
     * Renames a sheet name to the desired sheet name
     * @param currentName the name of the sheet to be changed
     * @param newName the new name for the sheet
     * @return renamedIndex the index of the sheet that was renamed
     */
    public int rename(String currentName, String newName) {
        if (contains(currentName) && (!(contains(newName)) && validName(newName))) {
            spreadsheets[index(currentName)] = newName;
            return index(newName);
        } else if (currentName.compareToIgnoreCase(newName) == 0) { 
            //If the newName is the same as the currentName is is allowed to be changed(i.e. if cases want to be changed(Excel allows this))
            spreadsheets[index(currentName)] = newName;
            return index(newName);
        }
        return -1;
    }

    /**
     * Returns the sheet name that is at the specified index
     * @param index the index of the sheet to be returned
     * @return the name of the sheet at the specified index
     */
    public String sheetName(int index) {
        if (index < length() && index > -1) { //If index is length than length(), the value at that index mustn't be null
            return spreadsheets[index];
        }
        return "";
    }

    /**
     * Displays the array in a formatted version
     */
    public void Display() {
        System.out.println("------------------");
        System.out.println("     COLUMN 1");
        System.out.println("------------------");
        System.out.println("INDEX\tSHEET NAME\n");
        int column = 1;
        int num = 16;
        for (int i = 0; i < nextIndex; i++) {
            //If i % num == 16 a new column will be printed as the names are presented in columns of 16
            if (i % num == 0 && i > 0) {
                column++;
                System.out.println("------------------");
                System.out.println("     COLUMN" + " " + column);
                System.out.println("------------------");
                System.out.println("INDEX\tSHEET NAME\n");
                System.out.printf("%d\t%s\n",i,spreadsheets[i]);
            } else {
                System.out.printf("%d\t%s\n",i,spreadsheets[i]);
            }
        }
    }

    /**
     * Finds the length of the array
     * @return The length of the array excluding null elements
     */
    public int length() {
        //The variable nextIndex is the same as length so will just return the maxIndex as length. It is accurate as when a sheet is removed, nextIndex is decremented
        return nextIndex;
    }

    /**
     * Checks if a name is a valid name as per the specifications for the 
     * rename method, i.e. if it only has digits 0 to 9 and no symbols
     * @param name the Name to be verified
     * @return valid returns true if the name is valid
     */
    private boolean validName(String name) {
        int i;
        int nameLength = name.length();
        char ch = '\0';
        int countValid = 0;
        for(i = 0; i < nameLength; i++) {
            ch = name.charAt(i);

            if (ch == ' ' || (Character.isLetter(ch) || Character.isDigit(ch))) {
                countValid++;
            } else {
                countValid--;
            }
        }

        return countValid == nameLength;  //If the countValid is same as length of the name, all the characters must be valid and thus the name is valid
    }
}

 /**private void shift(char direction, int first, int last) {
        long start, finish;
        if(direction == 'L' || direction == 'l') {
            for(int i = first; i <= last; i++) {
                tabs[i-1] = tabs[i]; // Note i-1 to move down/left
            }
            // Alternatively
            //System.arraycopy(tabs,first,tabs,first-1,last-first+1);
        } else {
            for(int i = last; i >= first; i--) {
                tabs[i+1] = tabs[i];// Note i+1 to move up/right
            }
            // Alternatively
            //System.arraycopy(tabs,first,tabs,first+1,last-first+1);
        }
    }**/