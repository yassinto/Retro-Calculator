package domain;

// Interface to create calculation method
public interface Calculation {
	
	// Method to calculate; accepts two double's and and int that indicates the operator used; return ans as double
	public default double calculate(double num1, double num2, int operator) {
		double ans = 0; // ans default at 0
		switch(operator) { // switch based on operator int
		case -1: // Negates
			ans = -(num1);
			break;
		case 0: // Equals
			ans = num1;
			break;
		case 1: // Addition
			ans = num1 + num2;
			break;
		case 2: // Subtraction
			ans = num1 - num2;
			break;
		case 3: // Multiplication
			ans = num1 * num2;
			break;
		case 4: // Division
			ans = num1 / num2;
			break;
		case 5: // Square root of
			ans = Math.sqrt(num1);
			break;
		case 6: // To the power of 2
			ans = num1 * num1;
			break;
		}
		return ans;		
	}

}
