package Assignment3;

import java.util.Scanner;

import Assignment2.AmusingPreciseNumber;

/**
 * 
 * @author Muhammad Khairi Norizan
 *
 * @param <E>
 */
public class Calculator<E> extends Deque228<E>{
	
	/**
	 * A helper method to check if the string passed is numeric
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	} 
	
	public static void main(String[] args) {
		
		Scanner exprsSc = null;
		String postFixExpression = "";
		Deque228<AmusingPreciseNumber> operandStack = new Deque228<AmusingPreciseNumber>();
						
		System.out.println("Evaluation of Postfix Expression");
		System.out.println();
		System.out.println("Enter the postfix expression");
		
		exprsSc = new Scanner(System.in);
		postFixExpression = exprsSc.nextLine();
		
		Scanner input = new Scanner(postFixExpression);
		
		while(input.hasNext()) {
			String curr = input.next();
			
			if(isNumeric(curr) == false && !curr.equals("neg") && !curr.equals("+") && !curr.equals("-") && !curr.equals("abs")) {
				throw new RuntimeException();
				
			}else if(curr.equals("abs")) {
					AmusingPreciseNumber num = operandStack.pop();
					
					operandStack.add(AmusingPreciseNumber.abs(num));
					
			}else if(curr.equals("neg")) {
					AmusingPreciseNumber num = operandStack.pop();
					
					operandStack.add(AmusingPreciseNumber.negate(num));
					
			}else if(curr.equals("+")) {
					
					AmusingPreciseNumber num2 = operandStack.pollLast();
					AmusingPreciseNumber num1 = operandStack.pollLast();
					
					operandStack.add(AmusingPreciseNumber.add(num1, num2));
					
			}else if(curr.equals("-")){ //minus sign
					AmusingPreciseNumber num2 = operandStack.pollLast();
					AmusingPreciseNumber num1 = operandStack.pollLast();
					
					operandStack.add(AmusingPreciseNumber.subtract(num1, num2));
					
					
			}else {
					AmusingPreciseNumber operandToStack = new AmusingPreciseNumber(curr);
					
					operandStack.add(operandToStack);
					
			}
		}
		
		if(operandStack.size() > 1) {
			System.out.println("Too many operands");
		}else {
			AmusingPreciseNumber retVal = operandStack.pop();
			System.out.println("The result is:");
			System.out.println(retVal.toString());
		}
		

		
	}
	
}
