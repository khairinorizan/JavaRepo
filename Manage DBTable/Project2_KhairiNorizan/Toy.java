package ProjectToy;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Toy {
	/* Inner class AttributesTypes */
	static class AttributesTypes {
		String atrrName;
		int type;
		String value;
		
		public AttributesTypes(String atrrName, int type) {
			this.atrrName = atrrName;
			this.type = type;
		}
		
		public String getAtrrName() {			
			return atrrName;
		}
		
		public void setAtrrName(String atrrName) {
			this.atrrName = atrrName;
		}
		
		public int getType() {
			return type;
		}
		
		public void setType(int type) {
			this.type = type;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
		
	}
	/* End of Inner class AttributesTypes */
	
	/* Global Variables */
	private static boolean createTable = false;
	private static int recordCount = 0;
	private static StringBuilder headerFile;
	private static String attrStrings;
	private static ArrayList<AttributesTypes> attrList = new ArrayList<AttributesTypes>();
	/* End of Global Variables */
	
	/* Helper Method */
	
	/* Read all content of file and update necessary global variables */
	private static void readAllAttributes(File inputFile) {
		try {
			Scanner readFile = new Scanner(inputFile);
			attrStrings = "";
			
			/* Skips first line */
			readFile.nextLine();
			/* End Skips first line */
			
			/* Reads all attributes */
			while(readFile.hasNextLine()) {
				String currLine = readFile.nextLine();
				attrStrings += currLine + "\n";
			}
			
		} catch (FileNotFoundException e) {
			/* Successful termination */
			System.exit(0);
		}
	}
	/* End of read all content of file and update necessary global variables */
	
	/* End of Helper Method */
	
	/* Create Table */
	public static void createXYZ() throws IOException {
		File xyz = new File("xyz.tb");
		Scanner inputScan;
		
		if(xyz.createNewFile()) {
			boolean moreAttr = true;
			String attrString = "";
			String attrCountString = "";
			int attrCount = 0;
			
			while(moreAttr == true) {
				/* Read Attribute Name */
				System.out.println("Attribute name:");
				inputScan = new Scanner(System.in);	
				String attrName = inputScan.nextLine();
				
				/* Read Attribute Type */
				System.out.println("Select a number from the types:");
				System.out.println("1. Integer");
				System.out.println("2. Double");
				System.out.println("3. Boolean");
				System.out.println("4. String");
				inputScan = new Scanner(System.in);
				int types = inputScan.nextInt();
				
		
				attrCount++;
				while(true) {
					System.out.println("More attribute? (y/n):");
					inputScan = new Scanner(System.in);
					String yesOrNo = inputScan.nextLine();	
					
					if(yesOrNo.toLowerCase().compareTo("y") == 0) {
						AttributesTypes newAttrbt = new AttributesTypes(attrName,types);
						attrList.add(newAttrbt);						
						
						attrString += "[" + attrName + ":" + Integer.toString(types) + "]";
						moreAttr = true;
						break;
					}else if(yesOrNo.toLowerCase().compareTo("n") == 0) {
						AttributesTypes newAttrbt = new AttributesTypes(attrName,types);
						attrList.add(newAttrbt);
						
						attrCountString = "[" + Integer.toString(attrCount) + "]";
						attrString += "[" + attrName + ":" + Integer.toString(types) + "][0]";
						String headerString = attrCountString + attrString;
						
						/* Write to file */
						FileWriter writeToFile = new FileWriter("xyz.tb");
						writeToFile.write(headerString);
						writeToFile.close();
						System.out.println("Successfully add to xyz.tb!");
						
						moreAttr = false;
						break;
					}else {
						System.out.println("Wrong character input!");
					}
				}

			}		
			
			
		}else {
			System.out.println("Table has already been created!");
			/* Successful termination */
			System.exit(0);
		}
	}
	
	public static ArrayList<String> recordSplitByWhiteSpace(String[] wSpaces){
		ArrayList<String> retValue = new ArrayList<String>();
		
		for(int i = 0; i < wSpaces.length; i++) {
			if(wSpaces[i].length() == 0) {
				continue;
			}else {
				retValue.add(wSpaces[i].trim());				
			}
			
		}
		
		return retValue;
	}
	
	public static ArrayList<ArrayList<AttributesTypes>> getAllRecords() {
		File xyz = new File("xyz.tb");
		
		Scanner readFile;
		ArrayList<ArrayList<AttributesTypes>> allRecords = new ArrayList<ArrayList<AttributesTypes>>(); 
		
		try {
			readFile = new Scanner(xyz);
			
			
			String currLine = readFile.nextLine();
			/* Read all of the records in the file */
			while(readFile.hasNextLine()) {
				currLine = readFile.nextLine();
				
				String[] arrOfLine = currLine.split("\\{|\\}|\\|");
				
				ArrayList<String> arrOfRecords = recordSplitByWhiteSpace(arrOfLine);
				ArrayList<AttributesTypes> allAtrr = getAllAtrr();
				ArrayList<AttributesTypes> newRecord = new ArrayList<AttributesTypes>();
				
				/* Retrieve all records */
				int j = 0;
				for(int i = 0; i < allAtrr.size(); i++) {
					AttributesTypes currAttr = allAtrr.get(i);
					
					currAttr.setValue(arrOfRecords.get(j));
					j++;
					
					newRecord.add(currAttr);
				}
				/* End Retrieving all records */
				
				allRecords.add(newRecord);
				
			}
			/* End of read all of the records in the file */
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return allRecords;
	}
	
	public static ArrayList<AttributesTypes> getAllAtrr() {
		File xyz = new File("xyz.tb");
		
		Scanner readFile;
		ArrayList<AttributesTypes> allAttributes = new ArrayList<AttributesTypes>();
		try {
			readFile = new Scanner(xyz);
			
			/* Read only the first line of the file */
			String line = readFile.nextLine();
			String[] arrOfLine = line.split("\\:|[\\[\\]]");
					
			ArrayList<String> newArrofLine = splitWhiteSpace(arrOfLine);
					
			/* Create AttributesTypes and store into an ArrayList<AttributesTypes> */
			for(int i=1; i < newArrofLine.size()-1; i=i+2) {
				try {
					int atrrType = Integer.parseInt(newArrofLine.get(i+1));
					AttributesTypes newAttrr = new AttributesTypes(newArrofLine.get(i), atrrType);
							
					allAttributes.add(newAttrr);
							
				}catch(NumberFormatException e) {
					System.err.println("Wrong Format!");
					System.exit(1);
				}
						
			}
			/* End of create AttributesTypes and store into an ArrayList<AttributesTypes> */
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return allAttributes;
	}
	
	/* Display Header */
	public static void displayHeader() {
		File xyz = new File("xyz.tb");
		
		Scanner readFile;
		
		try {
			readFile = new Scanner(xyz);
			
			ArrayList<AttributesTypes> allAttributes = new ArrayList<AttributesTypes>();
			
			/* Read only the first line of the file */
			String line = readFile.nextLine();
			String[] arrOfLine = line.split("\\:|[\\[\\]]");
					
			ArrayList<String> newArrofLine = splitWhiteSpace(arrOfLine);
					
			/* Create AttributesTypes and store into an ArrayList<AttributesTypes> */
			for(int i=1; i < newArrofLine.size()-1; i=i+2) {
				try {
					int atrrType = Integer.parseInt(newArrofLine.get(i+1));
					AttributesTypes newAttrr = new AttributesTypes(newArrofLine.get(i), atrrType);
							
					allAttributes.add(newAttrr);
							
				}catch(NumberFormatException e) {
					System.err.println("Wrong Format!");
					System.exit(1);
				}
						
			}
			/* End of create AttributesTypes and store into an ArrayList<AttributesTypes> */
			
			/* Get total number of records */
			try {
				recordCount = Integer.valueOf(newArrofLine.get(newArrofLine.size() - 1));
			}catch(Exception e) {
				System.err.println("Invalid Format of Total Records! Program terminates!");
				System.exit(1);
			}
			/* End of get total number of records */
			
			for(int i=0; i < allAttributes.size(); i++) {
				if(allAttributes.get(i).getType() == 1) {
					System.out.println("Attribute" + (i+1) + ":" + allAttributes.get(i).getAtrrName() + "," + "Integer");
				}else if(allAttributes.get(i).getType() == 2) {
					System.out.println("Attribute" + (i+1) + ":" + allAttributes.get(i).getAtrrName() + "," + "Double");
				}else if(allAttributes.get(i).getType() == 3) {
					System.out.println("Attribute" + (i+1) + ":" + allAttributes.get(i).getAtrrName() + "," + "Boolean");
				}else if(allAttributes.get(i).getType() == 4) {
					System.out.println("Attribute" + (i+1) + ":" + allAttributes.get(i).getAtrrName() + "," + "String");
				}else {
					
				}
				
			}
			
			System.out.println(recordCount + " records");
			
				
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static ArrayList<String> splitWhiteSpace(String[] arrOfLine) {
		ArrayList<String> newArrayList = new ArrayList<String>();
		
		for(int i = 0; i < arrOfLine.length; i++) {
			if(arrOfLine[i].length() == 0) {
				continue;
			}else {
				newArrayList.add(arrOfLine[i]);				
			}
		}
		
		return newArrayList;
	}
	
	/* Insert Record */
	public static void insertRecord() {
		File xyz = new File("xyz.tb");
		
		if(xyz.exists()) {
			try {
				headerFile = new StringBuilder();
				Scanner readFile = new Scanner(xyz);
				ArrayList<AttributesTypes> allAttributes = new ArrayList<AttributesTypes>();
				
				/* Read only the first line of the file */
				String line = readFile.nextLine();
				headerFile.append(line);
				String[] arrOfLine = line.split("\\:|[\\[\\]]");
					
				ArrayList<String> newArrofLine = splitWhiteSpace(arrOfLine);
					
				/* Create AttributesTypes and store into an ArrayList<AttributesTypes> */
				for(int i=1; i < newArrofLine.size()-1; i=i+2) {
					try {
						int atrrType = Integer.parseInt(newArrofLine.get(i+1));
						AttributesTypes newAttrr = new AttributesTypes(newArrofLine.get(i), atrrType);
							
						allAttributes.add(newAttrr);
							
					}catch(NumberFormatException e) {
						System.err.println("Wrong Format!");
						System.exit(1);
					}
						
				}
				/* End of create AttributesTypes and store into an ArrayList<AttributesTypes> */
				
				/* Get total number of records */
				try {
					recordCount = Integer.valueOf(newArrofLine.get(newArrofLine.size() - 1));
				}catch(Exception e) {
					System.err.println("Invalid Format of Total Records! Program terminates!");
					System.exit(1);
				}
				/* End of get total number of records */
				
				/* End of reading the first line of the file */
				
				readAllAttributes(xyz);
				
				/* Read user input for all attributes */
				String record = "{";
				for(int i=0; i < allAttributes.size(); i++) {
					AttributesTypes currAttrr = allAttributes.get(i);
					
					/* Check for integer */
					if(currAttrr.getType() == 1) {
						System.out.println(currAttrr.getAtrrName() + ":");
						Scanner readAttributes = new Scanner(System.in);
						
						while(true) {
							try {
								int getAttr = readAttributes.nextInt();
								
								record += Integer.toString(getAttr);
								
								break;
								
							}catch(Exception e) {
								System.err.println("Not a valid integer! Enter an integer value");
							}
						}
						
					/* Check for double */
					}else if(currAttrr.getType() == 2) {
						System.out.println(currAttrr.getAtrrName() + ":");
						Scanner readAttributes = new Scanner(System.in);
						
						while(true) {
							try {
								double getAttr = readAttributes.nextDouble();
								
								record += Double.toString(getAttr);
								
								break;
								
							}catch(Exception e) {
								System.err.println("Not a valid double/integer! Enter a double/integer value");
							}
						}
						
					/* Check for boolean. Only accept T or F */
					}else if(currAttrr.getType() == 3) {
						while(true) {
							System.out.println(currAttrr.getAtrrName() + ":");
							Scanner readAttributes = new Scanner(System.in);
							String getAttr = readAttributes.next();
							
							if(getAttr.compareTo("F") == 0) {
								record += getAttr;
								break;
							}else if(getAttr.compareTo("T") == 0) {
								record += getAttr;
								break;
							}else {
								System.err.println("Invalid input! Please enter 'T' or 'F' only");
							}
							
						}
						
					/* Check for string */
					}else if(currAttrr.getType() == 4) {
						System.out.println(currAttrr.getAtrrName() + ":");
						Scanner readAttributes = new Scanner(System.in);
						String getAttr = readAttributes.nextLine();
						
						record += getAttr;
						
					}else {
						System.err.println("Invalid type!");
						System.exit(1);
					}
					
					if(i == (allAttributes.size() - 1)) {
						record += "}";
					}else {
						record += "|";
					}
				}
				
				/* Create new record to xyz.tb */
				if(recordCount == 0) {
					recordCount++;
					
					for(int i = headerFile.length()-1; i >= 0; i--) {
						if(headerFile.charAt(i) != '[') {
							headerFile.deleteCharAt(i);
						}else {
							
							headerFile.append(Integer.toString(recordCount));
							headerFile.append("]");
							break;
						}
					}
					FileWriter writeToFile = new FileWriter("xyz.tb");
					writeToFile.write(headerFile.toString() + "\n" + record);
					writeToFile.flush();
					writeToFile.close();
					
				/* Append new record to attrStrings  */
				}else {
					attrStrings += record;
					
					recordCount++;
					
					for(int i = headerFile.length()-1; i >= 0; i--) {
						if(headerFile.charAt(i) != '[') {
							headerFile.deleteCharAt(i);
						}else {
							
							headerFile.append(Integer.toString(recordCount));
							headerFile.append("]");
							break;
						}
					}
					
					FileWriter writeToFile = new FileWriter("xyz.tb");
					writeToFile.write(headerFile.toString() + "\n" + attrStrings);		
					writeToFile.flush();
					writeToFile.close();				
					
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Failed to read file!");
				e.printStackTrace();
			}
			
		}else {
			System.out.print("File does not exist!");
		}
		
	}
	
	/* Print the defined search condition */
	public static void printSearchCondition(String attrName, String val) {
		ArrayList<ArrayList<AttributesTypes>> allRecords = getAllRecords();
		
		for(int i = 0; i < allRecords.size(); i++) {
			ArrayList<AttributesTypes> currRecord = allRecords.get(i);
			
			int j = 0;
			while(j < currRecord.size()) {
				AttributesTypes currAttr = currRecord.get(j);
				if(currAttr.getAtrrName().compareTo(attrName) == 0) {
					if(currAttr.getValue().contains(val)) {
						
						System.out.println("Record " + i);
						
						for(int k=0; k < currRecord.size(); k++) {
							AttributesTypes thisAtrr = currRecord.get(k);
							System.out.println(thisAtrr.getAtrrName() + ": " + thisAtrr.getValue());
						}
						System.out.println();
						break;
					}
				}
				j++;
			}
		}
	}
	
	/* Print Record at 'rid' */
	public static void printRecordRid(int rid) {
		ArrayList<ArrayList<AttributesTypes>> allRecords = getAllRecords();
		
		if(rid > allRecords.size() - 1) {
			System.err.println("Index is out of bound");
			System.exit(1);
		}
		
		ArrayList<AttributesTypes> attributesTypesAtIndex = allRecords.get(rid);
		
		for(int i=0; i < attributesTypesAtIndex.size(); i++) {
			AttributesTypes thisAtrr = attributesTypesAtIndex.get(i);
			System.out.println(thisAtrr.getAtrrName() + ": " + thisAtrr.getValue());
		}
		
	}
	
	/* Delete Record at 'rid' */
	public static void deleteRecordRid(int rid) throws IOException {
		ArrayList<ArrayList<AttributesTypes>> allRecords = getAllRecords();
		
		if(rid > allRecords.size() - 1) {
			System.err.println("Index is out of bound");
		}else {
			File xyz = new File("xyz.tb");
			headerFile = new StringBuilder();
			
			Scanner readFile = new Scanner(xyz);
			
			/* Read only the first line of the file */
			String line = readFile.nextLine();
			headerFile.append(line);
			
			String[] arrOfLine = line.split("\\:|[\\[\\]]");
			ArrayList<String> newArrofLine = splitWhiteSpace(arrOfLine);
			
			/* Get total number of records */
			try {
				recordCount = Integer.valueOf(newArrofLine.get(newArrofLine.size() - 1));
			}catch(Exception e) {
				System.err.println("Invalid Format of Total Records! Program terminates!");
				System.exit(1);
			}
			/* End of get total number of records */
			
			for(int i = headerFile.length()-1; i >= 0; i--) {
				if(headerFile.charAt(i) != '[') {
					headerFile.deleteCharAt(i);
				}else {
					recordCount--;
					headerFile.append(Integer.toString(recordCount));
					headerFile.append("]");
					break;
				}
			}
			
			FileWriter writeToFile = new FileWriter("xyz.tb");
			writeToFile.write(headerFile.toString());
			writeToFile.flush();
			
			for(int i=0; i < allRecords.size()-1; i++) {
				String recordToWrite = "{";
				if(i == rid) {
					continue;
				}else {
					writeToFile.write("\n");
					ArrayList<AttributesTypes> curRecord = allRecords.get(i);
					
					for(int j=0; j < curRecord.size()-1; j++) {
						recordToWrite += curRecord.get(j).getValue() + "|";
					}
					recordToWrite += curRecord.get(curRecord.size()-1).getValue() + "}";
				}
				writeToFile.write(recordToWrite);
				writeToFile.flush();
			}
			
			if(allRecords.size()-1 != rid) {
				writeToFile.write("\n");
				ArrayList<AttributesTypes> curRecord = allRecords.get(allRecords.size()-1);
				String recordToWrite = "{";
				
				for(int j=0; j < curRecord.size()-1; j++) {
					recordToWrite += curRecord.get(j).getValue() + "|";
				}
				recordToWrite += curRecord.get(curRecord.size()-1).getValue() + "}";
				writeToFile.write(recordToWrite);
				writeToFile.flush();
				writeToFile.close();
			}
		}
		
	}
	
	public static void main(String[] args) {
		int userCommand = 0;
		
		do {
			/* Display menu */
			System.out.println("Project Toy");
			System.out.println("Command List:");
			System.out.println("1. Toy create xyz.tb (Create Table)");
			System.out.println("2. Toy header xyz.tb (Display Header Information)");
			System.out.println("3. Toy insert xyz.tb (Insert Record Into Table)");
			System.out.println("4. Toy display rid xyz.tb (Display record equals 'rid')");
			System.out.println("5. Toy delete rid xyz.tb (Delete record equals 'rid')");
			System.out.println("6. Toy Search Condition");
			System.out.println("0. Exit program");
			/* End Display menu */
			
			/* Get User Command */
			System.out.println();
			System.out.println("Enter command number: ");
			Scanner inputScanner = new Scanner(System.in);
			
			userCommand = inputScanner.nextInt();
			
			/* Perform operation based on command */
			if(userCommand == 1) {
				try {
					createXYZ();
				} catch (IOException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(userCommand == 2) {
				displayHeader();			
				
			}else if(userCommand == 3) {
				insertRecord();
				
			}else if(userCommand == 4) {
				int index = 0;
				
				while (true) {
					System.out.println("Enter record index:");
					inputScanner = new Scanner(System.in);
					index = inputScanner.nextInt();
					
					if(index < 0) {
						System.err.println("Invalid record id!");
					}else {
						printRecordRid(index);
						break;
					}
				}
				
				
			}else if(userCommand == 5) {
				try {
					int index = 0;
					System.out.println("Enter record index to be deleted:");
					inputScanner = new Scanner(System.in);
					index = inputScanner.nextInt();
						
					deleteRecordRid(index);
						
				} catch (IOException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}					
				
			}else if(userCommand == 6){
				String searchCondition;
				do {
					System.out.println("Enter the search condition");
					inputScanner = new Scanner(System.in);
					searchCondition = inputScanner.nextLine();
				}while(!searchCondition.contains("="));
				
				Scanner scanSearch = new Scanner(searchCondition);
				
				String attr = "";
				String value = "";
				boolean readVal = false;
				int equalCount = 0;
				
				while(scanSearch.hasNext()) {
					String curWord = scanSearch.next();
					
					if(curWord.compareTo("=") == 0) {
						readVal = true;
						equalCount++;
					}else if(readVal == true && equalCount == 1) {
						value += curWord;
					}else if(readVal == false && equalCount == 0){
						attr = curWord;
					}else {
						System.err.println("Invalid search format");
					}
				}
				
				boolean isDouble = true;
				/* Check if value is double */
				try {
					Double doubleVal = Double.parseDouble(value);
				}catch(NumberFormatException e) {
					isDouble = false;
				}
				
				if(isDouble) {
					int indexOfDecimal = value.indexOf(".");
					
					if(indexOfDecimal >= 0) {
						value = value.substring(0, indexOfDecimal);
					}
				}
				
				printSearchCondition(attr, value);
				
			}else if(userCommand == 0){
				System.out.println("Goodbye!");
				/* Successful termination */
				System.exit(0);
				
			}else {
				/* Prints error message and exit program */
				System.err.println("Invalid command! ");
			}
		}while(userCommand != 0);
		
	}
}
