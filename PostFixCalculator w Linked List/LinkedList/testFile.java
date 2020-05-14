package Assignment2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

import Assignment2.AmusingLinkedList.Node;

public class testFile {
	public static <E> void main(String[] args) throws IOException {
		
	  
		
		AmusingLinkedList<Integer> test1 = new AmusingLinkedList<Integer>();
		AmusingLinkedList<Integer> test2 = new AmusingLinkedList<Integer>();
		
		
		ArrayList<Integer> testing = new ArrayList<Integer>();
		ArrayList<Integer> testing2 = new ArrayList<Integer>();
		Integer[] data = {0, 10, 20, 30, 40};
		testing2.add(1);
		testing2.add(2);
		testing2.add(3);
		testing2.add(4);
		
		test2.add(1);
		test2.add(2);
		test2.add(5);
		test2.add(4);
		
//		test1.add(1);
//		test1.add(2);
		
		test1.addAll(0, test2);
		System.out.println(test1.toString());
		
		//testing2.addAll(1,testing2);
		//testing.addAll(0, testing2);
		System.out.println(testing2.toString());
		
		
		
		String testNum = "0000";
		
		for(int i = testNum.length()-1; i >=0; i--) {
			if(testNum.charAt(i) == '0') {
				testNum = testNum.substring(0, i);
			}else {
				break;
			}
			
		}
		
		System.out.println(testNum);
		System.out.println(testNum.length());
		
		StringBuilder str = new StringBuilder();
		
		str.append('C');
		str.append('D');
		System.out.println(str.toString());
		
		//AmusingLinkedList<Integer> test1 = new AmusingLinkedList<Integer>();
		
		int test = 2;
		
//		test1.add(1);
//		test1.add(2);
//		test1.add(3);
//		test1.add(4);
//		//test1.remove(test);
//		System.out.println(test1.toString());
//		test1.clear();
//		System.out.println(test1.toString());
		
		
		
//		Reader reader = new FileReader("file.txt");
//		
//		AmusingPreciseNumber preciseTest = new AmusingPreciseNumber(reader);
//		System.out.println(AmusingPreciseNumber.toString(preciseTest.amusing));
		
//		test1.add(1);
//		test1.add(2);
//		test1.add(3);
//		test1.add(4);
		

		
		test1.addAll(test2);
		System.out.println(test1.toString());
		
		test1.contains(new Integer(2));
		
		ListIterator<Integer> iter = test1.listIterator();
		
		iter.add(1);
		iter.add(2);
		iter.add(3);
		iter.add(4);
		
		System.out.println(test1.toString());
		
		iter = test1.listIterator();
		
		iter.next();
		test = iter.next();
		iter.add(5);
		
		iter.remove();
		System.out.println(test1.toString());
		iter.toString();
		test = iter.next();
		test = iter.next();
		test = iter.previous();
		iter.remove();
		System.out.println(test1.toString());
		iter.set(3);
		System.out.println(test1.toString());
		
		
		//124
		
		
		//test1.containsAll(test2);
//		
//		iter.add(1);
//		iter.add(2);
//		iter.add(3);
//		
//		System.out.println(test1.toString());
		
		
		//iter.remove();
		
		
		
		
		
//		while(iter.hasNext() == true) {
//			test = iter.next();
//			//continue;
//		}
		
		
		
		
		
//
//		String integer = "-39090000.000";
//		String integer2 = "39090000.000";
//		String integer3 = ".950";
//		
//		AmusingPreciseNumber preciseTest = new AmusingPreciseNumber(integer3);
//		AmusingPreciseNumber preciseTest2 = new AmusingPreciseNumber(preciseTest);
//		
//		//AmusingPreciseNumber ret = AmusingPreciseNumber.add(preciseTest,preciseTest2);
//		
//		//AmusingPreciseNumber.toString();
//		//AmusingPreciseNumber.toString(preciseTest2);
//		
//		System.out.println(AmusingPreciseNumber.toString(preciseTest));
		
		
	}
}
