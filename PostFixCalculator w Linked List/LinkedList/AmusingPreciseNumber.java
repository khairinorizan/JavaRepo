package Assignment2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 
 * @author Muhammad Khairi Norizan
 *
 */
public class AmusingPreciseNumber {
	private AmusingLinkedList<Integer> leftVal;
	private AmusingLinkedList<Integer> rightVal;
	private int sign;
	
	/**
	 * Create an AmusingPreciseNumber from an integer
	 * 
	 * @param numb
	 */
	public AmusingPreciseNumber(int numb) {
		this(Integer.toString(numb));
	}
	
	/**
	 * Create an AmusingPreciseNumber from a String. The formatting of the string is some number
	 * of digits with an optional decimal point. 
	 * 
	 * The constructor is required to throw a runtime exception if the string does not have a valid
	 * syntax
	 * 
	 * @param numb
	 */
	public AmusingPreciseNumber(String numb) {
		leftVal = new AmusingLinkedList<Integer>();
		rightVal = new AmusingLinkedList<Integer>();
		sign = 1;
		
		String decimal = ".";
		int decimalCount = 0;

		// TODO Auto-generated catch block
		if(numb == "") {
			throw new RuntimeException();
			
		}else if(numb.length() != 1 && (numb.charAt(0) == '+' || numb.charAt(0) == '-' || numb.charAt(0) == '.')) {
				
			for(int i=1; i < numb.length(); i++) {
				 if(!Character.isDigit(numb.charAt(i))) {
					if(numb.charAt(i) == '.') {
						if(i == numb.length()-1) {
							throw new RuntimeException();
						}
							
						decimalCount++;
							
						if(decimalCount > 1) {
							throw new RuntimeException();
						}
					}else {
						throw new RuntimeException();
					}
						
				 }else {
					continue;
				 }
			}
					
		}else {
				
			for(int i=0; i < numb.length(); i++) {
					
				if(numb.charAt(i) == '.') {
					decimalCount++;
						
					if(decimalCount > 1) {
						throw new RuntimeException();
					}
				}else if(!Character.isDigit(numb.charAt(i))) {
					throw new RuntimeException();
				}
			}

		}
		
		if(numb.indexOf(decimal) == -1) { //no decimal place. rightVal is null
			
			int i = 0;
			int j = 1;
			
			if(numb.charAt(0) == '-') {
				i = 1;
				j = 2;
				sign = -1;
				leftVal.add(sign);
			}else if(numb.charAt(0) == '+') {
				i = 1;
				j = 2;
				leftVal.add(sign);
			}else{
				
				leftVal.add(sign);
			}
			
			while(i < numb.length()) {
				
				if(numb.length() == 1 || (numb.length() == 2 && numb.charAt(0) == '-')) {
					leftVal.add(Character.getNumericValue(numb.charAt(i)));
					
					i++;
					continue;
					
				}else if(numb.charAt(i) == '0' && numb.charAt(j) == '0') {
					if(i >= j) {
						leftVal.add(Character.getNumericValue(numb.charAt(i)));
						
						i++;
						
						continue;
					}
					i++;
					j++;
					continue;
				}else if(i+1 == j && (numb.charAt(i) == '0' && numb.charAt(j) != '0')) { //001
					i++;
					continue;
				}
				
				
				leftVal.add(Character.getNumericValue(numb.charAt(i)));
				
				i++;
			}
			
		}else { //there's a decimal place. rightVal not null
			String lValue = numb.substring(0, numb.indexOf(decimal));
			String rValue = numb.substring(numb.indexOf(decimal)+1, numb.length());
			
			int i = 0;
			int j = 1;
			
			if(lValue.equals("")) {
				leftVal.add(sign);
				
			}else if(lValue.charAt(0) == '-') {
				i = 1;
				j = 2;
				sign = -1;
				leftVal.add(sign);

			}else if(numb.charAt(0) == '+') {
				i = 1;
				j = 2;
				leftVal.add(sign);
			}else {
				leftVal.add(sign);				
			}
			
			while(i < lValue.length()) {
				if(lValue.length() == 1 || (lValue.length() == 2 && lValue.charAt(0) == '-')) {
					leftVal.add(Character.getNumericValue(lValue.charAt(i)));
					
					i++;
					continue;
					
				}else if(i < lValue.length() && j > lValue.length()-1) { //-00.0000059
					leftVal.add(Character.getNumericValue(lValue.charAt(i)));
					
					i++;
					continue;
					
					
				}else if(lValue.charAt(i) == '0' && lValue.charAt(j) == '0') {
					if(i >= j) {
						leftVal.add(Character.getNumericValue(numb.charAt(i)));
						
						i++;
						
						continue;
					}
					i++;
					j++;
					continue;
				}else if(i+1 == j && (lValue.charAt(i) == '0' && lValue.charAt(j) != '0')) {
					i++;
					continue;
				}
				
				
				leftVal.add(Character.getNumericValue(lValue.charAt(i)));
				
				i++;
			}
			
			for(int x = rValue.length()-1; x >=0; x--) {
				if(rValue.charAt(x) == '0') {
					rValue = rValue.substring(0, x);
				}else {
					break;
				}
				
			}
			
			i = 0;
			
			while(i < rValue.length()) {
				rightVal.add(Character.getNumericValue(rValue.charAt(i)));
				i++;
			}
		}
		
		
	
	}
	
	/**
	 * The same as the string constructor except the input comes from arbitrary Reader. This
	 * means that there is no bound on the number of digits for this constructor. The format
	 * is similar to the String constructor except that whitespace character is treated as a
	 * termination of the input and no further data is read from the stream once this whitespace
	 * character is read. Leading whitespace characters are ignored.
	 * 
	 * @param r
	 */
	public AmusingPreciseNumber(Reader r) {
		
		this(reader(r));
		
	}
	
	/**
	 * A simple copy constructor. Required a deep copy.
	 * 
	 * @param numb
	 */
	public AmusingPreciseNumber(AmusingPreciseNumber numb) {
		leftVal = new AmusingLinkedList<Integer>();
		rightVal = new AmusingLinkedList<Integer>();
		
		leftVal.addAll(numb.leftVal);
		
		if(numb.rightVal.size() != 0) {
			rightVal.addAll(numb.rightVal);
		}
		
	}
	
	/*
	 * Helper method to read from a Reader type that the parameter passed and convert them
	 * to string 
	 */
	private static String reader(Reader r) {
		StringBuilder str = new StringBuilder();
		
		try {
			int data = r.read();
			
			while(str.length() == 0 && data == 32) {
				data = r.read();
			}
			
			while(data != -1 && data != 32) { //32 is SPACE character 
				str.append((char)data);
				
				data = r.read();
			}
			
			r.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str.toString();
	}
	
	private static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	} 
	
	/**
	 * Add numb to this AmusingPreciseNumber
	 * 
	 * @param numb
	 */
	public void add(AmusingPreciseNumber numb) {
		AmusingPreciseNumber retVal = add(this, numb);
		
		leftVal.clear();
		
		leftVal.addAll(retVal.leftVal);
		
		if(retVal.rightVal.size() != 0) {
			if(rightVal.size() != 0) {
				rightVal.clear();
				rightVal.addAll(retVal.rightVal);	
			}else {
				rightVal.addAll(retVal.rightVal);
			}
		}
		
		
	}
	
	/**
	 * Subtract numb from this AmusingPreciseNumber
	 * 
	 * @param numb
	 */
	public void subtract(AmusingPreciseNumber numb) {
		
		AmusingPreciseNumber retVal = subtract(this, numb);
		
		leftVal.clear();
		
		leftVal.addAll(retVal.leftVal);
		
		if(retVal.rightVal.size() != 0) {
			if(rightVal.size() != 0) {
				rightVal.clear();
				rightVal.addAll(retVal.rightVal);	
			}else {
				rightVal.addAll(retVal.rightVal);
			}
		}
	}
	
	/**
	 * Negate this AmusingPreciseNumber
	 */
	public void negate() {
		String value = this.toString();
		
		if(value.equals("0")) {
			abs();
		}else {
			if(leftVal.get(0) == -1) {
				abs();
			}else {
				leftVal.set(0, -1);
			}
		}
		
		
	}
	
	/**
	 * Compute and store the absolute value of this AmusingPreciseNumber
	 */
	public void abs() {
		leftVal.set(0, 1);
	}
	
	/**
	 * Return an AmusingPreciseNumber that is the sum of numb1 and numb2. Numb1 and numb2 are
	 * unchanged 
	 * 
	 * @param numb1
	 * @param numb2
	 * @return
	 */
	public static AmusingPreciseNumber add(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2) {
		

		String fullNumb1 = numb1.toString();
		String fullNumb2 = numb2.toString();
		
		if(numb1.rightVal.size() == 0 && numb2.rightVal.size() == 0) {
			
			int sum = Integer.parseInt(fullNumb1) + Integer.parseInt(fullNumb2);
			String sumInString = String.valueOf(sum);
			
			AmusingPreciseNumber retVal = new AmusingPreciseNumber(sumInString);
			
			return retVal;
			
		}else {
			
			double sum = Double.parseDouble(fullNumb1) + Double.parseDouble(fullNumb2);
			String sumInString = String.valueOf(sum);
			
			AmusingPreciseNumber retVal = new AmusingPreciseNumber(sumInString);
			 
			return retVal;
		}

	}
	
	/**
	 * Return an AmusingPreciseNumber that is the difference of numb1 and numb2 (numb1 minus numb2)
	 * 
	 * @param numb1
	 * @param numb2
	 * @return
	 */
	public static AmusingPreciseNumber subtract(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2) {

		String fullNumb1 = numb1.toString();
		String fullNumb2 = numb2.toString();
		
		if(numb1.rightVal.size() == 0 && numb2.rightVal.size() == 0) {
			int difference = Integer.parseInt(fullNumb1) - Integer.parseInt(fullNumb2);
			String differenceInString = String.valueOf(difference);
			
			AmusingPreciseNumber retVal = new AmusingPreciseNumber(differenceInString);
			 
			return retVal;
		}else {
			double difference = Double.parseDouble(fullNumb1) - Double.parseDouble(fullNumb2);
			String differenceInString = String.valueOf(difference);
			
			AmusingPreciseNumber retVal = new AmusingPreciseNumber(differenceInString);
			 
			return retVal;
		}
		
		
	}
	
	/**
	 * Return an AmusingPreciseNumber that is the negative of numb and leave numb unchanged
	 * 
	 * @param numb
	 * @return
	 */
	public static AmusingPreciseNumber negate(AmusingPreciseNumber numb) {
		String numbToString = numb.toString();
		
		AmusingPreciseNumber tempNumb = new AmusingPreciseNumber(numbToString);
		
		tempNumb.negate();
		
		return tempNumb;
	}
	
	/**
	 * Return an AmusingPreciseNumber that is the negative of numb and leave numb unchanged
	 * 
	 * @param numb
	 * @return
	 */
	public static AmusingPreciseNumber abs(AmusingPreciseNumber numb) {
		String numbToString = numb.toString();
		
		AmusingPreciseNumber tempNumb = new AmusingPreciseNumber(numbToString);
		
		tempNumb.abs();
		
		return tempNumb;
	}
	
	/**
	 * Return a string of the AmusingPreciseNumber
	 * 
	 * @param numb
	 * @return
	 */
	public String toString() {
		
		String retVal = "";
		int i = 0;
		
		if(this.leftVal.get(0) == -1) {
			retVal += "-";
			i = 1;
		}else {
			i = 1;
		}
		
		if(this.leftVal.size() == 1) {
			retVal += "0";
		}else {
			while(i < this.leftVal.size()) {
				String val = Integer.toString(this.leftVal.get(i));
				retVal += val;
				i++;
			}
		}
		
		i = 0;
		
		if(this.rightVal.size() == 0) {
			return retVal;
		}else {
			retVal += ".";
			
			while(i < this.rightVal.size()) {
				String val = Integer.toString(this.rightVal.get(i));
				retVal += val;
				i++;
			}
		}
		
		
		
		return retVal;
	}
}
