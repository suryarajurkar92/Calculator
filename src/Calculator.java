
import java.util.Scanner;

public class Calculator {
	int num1;
	int num2;
	int answer;

//constructor invoked
	public Calculator(int firstNum, int secondNum) 
	{	
		num1 = firstNum;
		num2 = secondNum;
	}
	
	//public String toString()
	//{
		//return "Welcome to my Calculator ! ";
	//}
	
	public int operations() 
	{
		System.out.println("Select your operation: \n1 for Addition \n2 for Subtraction \n3 for Multiplication \n4 for division \n");
		Scanner inp = new Scanner(System.in);
		int mathOperation = inp.nextInt();
		switch(mathOperation) {
		case 1:
			answer = num1 + num2;

		break;
		case 2:
			answer = num1 - num2;
		break;	
		case 3:
			answer = num1 * num2;
		break;	
		case 4:
			answer = num1 / num2;
		break;
		default:
			System.out.println("Illigal Operation.");
		}
		return answer;
	
	}
	//Main Method
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter first number");
		int firstNum = in.nextInt();
		System.out.println("Enter second number");
		int secondNum = in.nextInt();
		
		Calculator cal = new Calculator(firstNum, secondNum);
		//System.out.println(cal.toString());
	
		int result =  cal.operations();
		System.out.println("The result of the operation is: " +result);
		
	}

}
