package ProgrammingPractice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class test {
	   public static void main(String[] args) throws IOException {
		      int ch;
		      System.out.print ("Enter some text: ");
		     // ch = System.in.read ();
		     // String cc = Character.toString((char)ch);
		      //char test = 'S';
		      StringBuilder str = new StringBuilder();
		      Scanner sc = new Scanner(System.in);
			  String scan = sc.nextLine();
		      
		      while(!scan.equals("Sam:")) {    	  
			      str.append(scan);
			      str.append("\n");
		    	  
		    	  sc = new Scanner(System.in);
		    	  scan = sc.nextLine();
		      }
		      
		      str.append(scan);
		      str.append("\n");
		      
		      sc = new Scanner(System.in);
	    	  scan = sc.nextLine();
	    	  str.append(scan);
	    	  System.out.println(str.toString());
	    	  
	    	  
	    	  while ((char)(ch = System.in.read ()) != -1) {
					//System.out.print ((char) ch);
					str.append((char)ch);
				}
		      
		      
//		      while ((char)(ch = System.in.read ()) != -1) {
//		    	  System.out.print (ch);
//					//System.out.print ((char) ch);
//		    	  str.append((char)ch);
//				}
		      
//		      while (!cc.equals("Sam:")) {
//		    	  System.out.print ((char)ch);
//			      ch = System.in.read ();  
//			      cc = Character.toString((char)ch);
//		      }
		       
			
		   
	        // create map to store
//	        Map<String, List<String>> map = new HashMap<String, List<String>>();
//	        // create list one and store values
//	        List<String> valSetOne = new ArrayList<String>();
//	        valSetOne.add("Apple");
//	        valSetOne.add("Aeroplane");
//	        // create list two and store values
//	        List<String> valSetTwo = new ArrayList<String>();
//	        valSetTwo.add("Bat");
//	        valSetTwo.add("Banana");
//	        // create list three and store values
//	        List<String> valSetThree = new ArrayList<String>();
//	        valSetThree.add("Cat");
//	        valSetThree.add("Car");
//	        // put values into map
//	        map.put("A", valSetOne);
//	        map.put("B", valSetTwo);
//	        map.put("C", valSetThree);
//	        // iterate and display values
//	        System.out.println("Fetching Keys and corresponding [Multiple] Values n");
//	        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//	            String key = entry.getKey();
//	            List<String> values = entry.getValue();
//	            System.out.println("Key = " + key);
//	            System.out.println("Values = " + values + "n");
//	        }
	    }
}
