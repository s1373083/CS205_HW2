package edu.monmouth.hw3;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import edu.monmouth.library.Book;
import edu.monmouth.library.BookExceptions;
import edu.monmouth.library.Journal;
import edu.monmouth.library.JournalException;
import edu.monmouth.library.LibraryItem;
import edu.monmouth.library.StatusType;

public class HW3 {
	
	public static void main(String[] args) {
	    if (!initialization(args)) {
	        return;
	    }

	    List<String> stringList = new ArrayList<String>();
	    List<LibraryItem> libraryItemList = new ArrayList<LibraryItem>();

	    readStringFile(args[LibraryItemConstants.STRING_FILE_INDEX], stringList);
	    readLibraryFile(args[LibraryItemConstants.LIBRARY_FILE_INDEX], libraryItemList);

	    System.out.println("STRING LIST TESTS");
	    testStringList(stringList);
	    System.out.println();
	    System.out.println("LIBRARY ITEM LIST TESTS");
	    testLibraryItemList(libraryItemList);
	}
	
	public static boolean initialization(String[] args) {
	    if (args == null || args.length != LibraryItemConstants.REQUIRED_ARGUMENTS) {
	        System.err.println("Error: Must recieve three arguments");
	        return false;
	    }

	    try {
	        PrintStream output = new PrintStream(args[LibraryItemConstants.OUTPUT_FILE_INDEX]);
	        System.setOut(output);
	        System.setErr(output);
	        return true;
	    } catch (FileNotFoundException e) {
	        System.err.println("Error: Could not open output file.");
	        return false;
	    }
	}
	   
	public static void readStringFile(String fileName, List<String> stringList) {
	        Scanner input = null;

	        try {
	            input = new Scanner(new File(fileName));

	            while (input.hasNextLine()) {
	                String line = input.nextLine();
	                stringList.add(line);
	            }
	       
	        } catch (FileNotFoundException e) {
	            System.err.println("Error: Could not open string file: " + fileName);
	        } finally {
	            if (input != null) {
	                input.close();
	            }
	        }
	    }
	       
	public static void readLibraryFile(String fileName, List<LibraryItem> libraryItemList) {
	            Scanner input = null;

	            try {
	                input = new Scanner(new File(fileName));

	                while (input.hasNextLine()) {
	                    String line = input.nextLine();

	                    try {
	                        LibraryItem item = buildLibraryItemFromLine(line);
	                        libraryItemList.add(item);
	                    } catch (Exception e) {
	                        System.err.println("Invalid library item line: " + line);
	                        System.err.println("Reason: " + e.getMessage());
	                    }
	                }
	           
	            } catch (FileNotFoundException e) {
	                System.err.println("Error: could not open library file: " + fileName);
	           
	            } finally {
	                if (input != null) {
	                    input.close();
	                }
	            }
	}
	            public static LibraryItem buildLibraryItemFromLine(String line) throws Exception {
	                if (line == null || line.trim().length() == 0) {
	                    throw new IllegalArgumentException("Empty line");
	                }

	                String[] parts = line.split(LibraryItemConstants.COMMA);

	                if (parts.length != LibraryItemConstants.REQUIRED_LIBRARY_FIELDS) {
	                    throw new IllegalArgumentException("Wrong number of fields");
	                }

	                String type = parts[LibraryItemConstants.TYPE_INDEX].trim();
	                String title = parts[LibraryItemConstants.TITLE_INDEX].trim();
	                String person = parts[LibraryItemConstants.PERSON_INDEX].trim();
	                int number = Integer.parseInt(parts[LibraryItemConstants.NUMBER_INDEX].trim());
	                StatusType status = StatusType.valueOf(parts[LibraryItemConstants.STATUS_INDEX].trim());

	                if (type.equalsIgnoreCase("b")) {
	                    return new Book(title, person, number, status);
	                } else if (type.equalsIgnoreCase("j")) {
	                    return new Journal(title, person, number, status);
	                } else {
	                    throw new IllegalArgumentException("Unknown type: " + type);
	                }
	            }
	    
	            public static void testStringList(List<String> stringList) {
	                System.out.println("isEmpty(): " + stringList.isEmpty());
	                System.out.println("size(): " + stringList.size());

	                System.out.println();
	                System.out.println("iterator():");
	                Iterator<String> iterator = stringList.iterator();
	                while (iterator.hasNext()) {
	                    System.out.println(iterator.next());
	                }

	                System.out.println();
	                System.out.println("listIterator() forward:");
	                ListIterator<String> forward = stringList.listIterator();
	                while (forward.hasNext()) {
	                    System.out.println(forward.next());
	                }

	                System.out.println();
	                System.out.println("listIterator() reverse:");
	                ListIterator<String> reverse = stringList.listIterator(stringList.size());
	                while (reverse.hasPrevious()) {
	                    System.out.println(reverse.previous());
	                }

	                System.out.println();
	                System.out.println("add():");
	                stringList.add("Added String");
	                printStringList(stringList);

	                System.out.println();
	                System.out.println("remove(index):");
	                if (!stringList.isEmpty()) {
	                    String removed = stringList.remove(0);
	                    System.out.println("Removed: " + removed);
	                } else {
	                    System.out.println("String list is empty.");
	                }
	                printStringList(stringList);

	                System.out.println();
	                System.out.println("size() after add/remove: " + stringList.size());
	            }
	            
	            public static void testLibraryItemList(List<LibraryItem> libraryItemList) {
	                System.out.println("isEmpty(): " + libraryItemList.isEmpty());
	                System.out.println("size(): " + libraryItemList.size());

	                System.out.println();
	                System.out.println("iterator():");
	                Iterator<LibraryItem> iterator = libraryItemList.iterator();
	                while (iterator.hasNext()) {
	                    System.out.println(iterator.next());
	                }

	                System.out.println();
	                System.out.println("listIterator() forward:");
	                ListIterator<LibraryItem> forward = libraryItemList.listIterator();
	                while (forward.hasNext()) {
	                    System.out.println(forward.next());
	                }

	                System.out.println();
	                System.out.println("listIterator() reverse:");
	                ListIterator<LibraryItem> reverse = libraryItemList.listIterator(libraryItemList.size());
	                while (reverse.hasPrevious()) {
	                    System.out.println(reverse.previous());
	                }

	                System.out.println();
	                System.out.println("add():");
	                try {
	                    Book addedBook = new Book("Added Book", "Added Author", 100, StatusType.ONSHELF);
	                    libraryItemList.add(addedBook);
	                    System.out.println("Added: " + addedBook);
	                } catch (BookExceptions e) {
	                    System.err.println("Error adding Book: " + e.getMessage());
	                }
	                printLibraryItemList(libraryItemList);

	                System.out.println();
	                System.out.println("remove(index):");
	                if (!libraryItemList.isEmpty()) {
	                    LibraryItem removed = libraryItemList.remove(0);
	                    System.out.println("Removed: " + removed);
	                } else {
	                    System.out.println("Library item list is empty.");
	                }
	                printLibraryItemList(libraryItemList);

	                System.out.println();
	                System.out.println("contains() with Book:");
	                testContainsBook(libraryItemList);

	                System.out.println();
	                System.out.println("remove(object) with Journal:");
	                testRemoveJournal(libraryItemList);

	                System.out.println();
	                System.out.println("size() after add/remove: " + libraryItemList.size());
	            }
	          
	            public static void testContainsBook(List<LibraryItem> libraryItemList) {
	                try {
	                    Book knownBook = new Book("Added Book", "Added Author", 999, StatusType.BORROWED);
	                    Book unknownBook = new Book("Not In List", "Nobody", 200, StatusType.ONSHELF);

	                    System.out.println("Contains known Book? " + libraryItemList.contains(knownBook));
	                    System.out.println("Contains unknown Book? " + libraryItemList.contains(unknownBook));
	                } catch (BookExceptions e) {
	                    System.err.println("Book contains test failed: " + e.getMessage());
	                }
	            }
	         
	            public static void testRemoveJournal(List<LibraryItem> libraryItemList) {
	                try {
	                    Journal knownJournal = new Journal("Added Journal", "Added Editor", 7, StatusType.ONSHELF);
	                    Journal unknownJournal = new Journal("Missing Journal", "Missing Editor", 99, StatusType.ONSHELF);

	                    libraryItemList.add(knownJournal);
	                    System.out.println("Added Journal for remove(object) test:");
	                    printLibraryItemList(libraryItemList);

	                    boolean removedKnown = libraryItemList.remove(
	                            new Journal("Added Journal", "Added Editor", 7, StatusType.BORROWED));
	                    System.out.println("remove(known Journal): " + removedKnown);
	                    printLibraryItemList(libraryItemList);

	                    boolean removedUnknown = libraryItemList.remove(unknownJournal);
	                    System.out.println("remove(unknown Journal): " + removedUnknown);
	                    printLibraryItemList(libraryItemList);

	                } catch (JournalException e) {
	                    System.err.println("Journal remove test failed: " + e.getMessage());
	                }
	            }
	            
	           public static void printStringList(List<String> stringList) {
	                for (int i = 0; i < stringList.size(); i++) {
	                    System.out.println("Index " + i + ": " + stringList.get(i));
	                }
	            }
	          
	           public static void printLibraryItemList(List<LibraryItem> libraryItemList) {
	        	    for (int i = 0; i < libraryItemList.size(); i++) {
	        	        System.out.println("Index " + i + ": " + libraryItemList.get(i));
	        	    }
	        	}
	}

