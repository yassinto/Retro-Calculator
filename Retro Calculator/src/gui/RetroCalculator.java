package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import domain.Calculation;

public class RetroCalculator extends JFrame {
	// Main panel where all the other panels will in inserted in
	private JPanel retroPanel;
	// Components of the panel; each new line represents a new panel
	private JTextField display, miniDisplay;
	private JButton button7, button8, button9, buttonPlus, buttonMinus;
	private JButton button4, button5, button6, buttonMultiply, buttonDivide;
	private JButton button1, button2, button3, buttonSquare, buttonPower;
	private JButton buttonC, button0, buttonDecimal, buttonNegate, buttonEquals;
	// Variables needed to store data related to the calculations
	private double num1, num2, ans;
	private int operator;
	private boolean isAns, isDec;

	// Constructor
	public RetroCalculator() {
		// Initializing main panel and sub-panels
		retroPanel = new JPanel(new GridLayout(5, 1));
		JPanel displayPanel = getDisplayPanel();
		JPanel buttonPanel1 = getButtonPanel1();
		JPanel buttonPanel2 = getButtonPanel2();
		JPanel buttonPanel3 = getButtonPanel3();
		JPanel buttonPanel4 = getButtonPanel4();

		// Adding sub-panels into main panel from top to bottom
		retroPanel.add(displayPanel);
		retroPanel.add(buttonPanel1);
		retroPanel.add(buttonPanel2);
		retroPanel.add(buttonPanel3);
		retroPanel.add(buttonPanel4);

		setContentPane(retroPanel);
	}

	// Method that creates the display panel
	public JPanel getDisplayPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		display = new JTextField("", 20);
		miniDisplay = new JTextField("", 20);
		setDisplay(display, false);
		setDisplay(miniDisplay, true);

		panel.add(display, BorderLayout.CENTER);
		panel.add(miniDisplay, BorderLayout.SOUTH);

		return panel;
	}

	// The following four methods create panels for the button; each panel
	// represents one row of buttons
	public JPanel getButtonPanel1() {
		JPanel panel = new JPanel(new GridLayout(1, 5));
		button7 = new JButton("7");
		button8 = new JButton("8");
		button9 = new JButton("9");
		buttonPlus = new JButton("+");
		buttonMinus = new JButton("-");

		setButton(button7, 1);
		setButton(button8, 2);
		setButton(button9, 3);
		setButton(buttonPlus, 4);
		setButton(buttonMinus, 1);

		panel.add(button7);
		panel.add(button8);
		panel.add(button9);
		panel.add(buttonPlus);
		panel.add(buttonMinus);

		return panel;
	}

	public JPanel getButtonPanel2() {
		JPanel panel = new JPanel(new GridLayout(1, 5));
		button4 = new JButton("4");
		button5 = new JButton("5");
		button6 = new JButton("6");
		buttonMultiply = new JButton("×");
		buttonDivide = new JButton("÷");

		setButton(button4, 2);
		setButton(button5, 3);
		setButton(button6, 4);
		setButton(buttonMultiply, 1);
		setButton(buttonDivide, 2);

		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		panel.add(buttonMultiply);
		panel.add(buttonDivide);

		return panel;
	}

	public JPanel getButtonPanel3() {
		JPanel panel = new JPanel(new GridLayout(1, 5));
		button1 = new JButton("1");
		button2 = new JButton("2");
		button3 = new JButton("3");
		buttonSquare = new JButton("√x");
		buttonPower = new JButton("x\u00b2");

		setButton(button1, 3);
		setButton(button2, 4);
		setButton(button3, 1);
		setButton(buttonSquare, 2);
		setButton(buttonPower, 3);

		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(buttonSquare);
		panel.add(buttonPower);

		return panel;
	}

	public JPanel getButtonPanel4() {
		JPanel panel = new JPanel(new GridLayout(1, 5));
		buttonC = new JButton("C");
		button0 = new JButton("0");
		buttonDecimal = new JButton(".");
		buttonNegate = new JButton("+/-");
		buttonEquals = new JButton("=");

		setButton(buttonC, 4);
		setButton(button0, 1);
		setButton(buttonDecimal, 2);
		setButton(buttonNegate, 3);
		setButton(buttonEquals, 4);

		panel.add(buttonC);
		panel.add(button0);
		panel.add(buttonDecimal);
		panel.add(buttonNegate);
		panel.add(buttonEquals);

		return panel;
	}

	// Method to include all common design elements of the two displays; boolean
	// mini differentiates the font size between the main and mini-display
	public void setDisplay(JTextField display, boolean mini) {
		display.setEditable(false);
		display.setBackground(Color.decode("#b3e7b4"));

		if (mini) {
			display.setFont(new Font("Candara", Font.PLAIN, 15));
		} else {
			display.setFont(new Font("Candara", Font.PLAIN, 40));
		}
	}

	// Methods to include all common design elements for all the buttons; int
	// colorTheme differentiates color choice for each button
	public void setButton(JButton button, int colorTheme) {
		button.setFont(new Font("Candara", Font.PLAIN, 40));
		button.setForeground(Color.decode("#b3e7b4"));
		button.addActionListener(new NumberHandler()); // Adds one common
														// ActionListener for
														// all buttons

		switch (colorTheme) {
		case 1:
			button.setBackground(Color.decode("#fb6500"));
			break;
		case 2:
			button.setBackground(Color.decode("#001c58"));
			break;
		case 3:
			button.setBackground(Color.decode("#ffbb39"));
			break;
		case 4:
			button.setBackground(Color.decode("#4fb4b1"));
			break;
		}
	}

	// Inner class: one common ActionListener of all buttons
	class NumberHandler implements ActionListener, Calculation { // Implements a
																	// method to
																	// make
																	// calculations
																	// from
																	// interface
																	// Calculation

		public void actionPerformed(ActionEvent e) {
			String name = ((JButton) e.getSource()).getText(); // Takes label of
																// button to as
																// switch case
																// below
			switch (name) { // Starting cases below add numbers behind eachother in display
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
				if (isAns) { // Makes sure no number is added behind an already-displayed ans
					display.setText("");
					miniDisplay.setText("");
					isAns = false;
				}
				miniDisplay.setText(miniDisplay.getText() + name);
				display.setText(display.getText() + name);
				ans = 0.0; // New number so ans needs to be reset
				break;
			case "C": // Clears the displays and the variables
				display.setText("");
				miniDisplay.setText("");
				num1 = 0.0;
				num2 = 0.0;
				ans = 0.0;
				operator = 0;
				isAns = false;
				break;
			case "+": // First number assigned display number, display is cleared, and changes operator to the addition-variable 
				num1 = Double.parseDouble(display.getText());
				display.setText("");
				miniDisplay.setText(miniDisplay.getText() + name);
				operator = 1;
				break;
			case "-": // First number assigned display number, display is cleared, and changes operator to the subtraction-variable 
				if(display.getText().equals("")) { // If display is empty, add minus to negate number
					display.setText(name);
					miniDisplay.setText(name);
				}
				else {
				num1 = Double.parseDouble(display.getText());
				display.setText("");
				miniDisplay.setText(miniDisplay.getText() + name);
				operator = 2;
				}
				break;
			case "×": // First number assigned display number, display is cleared, and changes operator to the multiplication-variable 
				num1 = Double.parseDouble(display.getText());
				display.setText("");
				miniDisplay.setText(miniDisplay.getText() + name);
				operator = 3;
				break;
			case "÷": // First number assigned display number, display is cleared, and changes operator to the division-variable 
				num1 = Double.parseDouble(display.getText());
				display.setText("");
				miniDisplay.setText(miniDisplay.getText() + name);
				operator = 4;
				break;
			case "√x": // First number assigned display number, display is cleared, uses calculate() method to get square root and displays ans 
				num1 = Double.parseDouble(display.getText());
				display.setText("");
				miniDisplay.setText(name.substring(0, 1) + (("" + num1).indexOf(".") < 0 ? ("" + num1) 
						: ("" + num1).replaceAll("0*$", "").replaceAll("\\.$", ""))); // Removes trailing zeros behind decimal
				operator = 5;
				ans = calculate(num1, num2, operator);
				display.setText(("" + ans).indexOf(".") < 0 ? ("" + ans)
						: ("" + ans).replaceAll("0*$", "").replaceAll("\\.$", "")); // Removes trailing zeros behind decimal
				operator = 0;
				isAns = true; // There is an ans on display
				break;
			case "x\u00b2": // First number assigned display number, display is cleared, uses calculate() method to get power of 2 and displays ans 
				num1 = Double.parseDouble(display.getText());
				display.setText("");
				miniDisplay.setText((("" + num1).indexOf(".") < 0 ? ("" + num1)
						: ("" + num1).replaceAll("0*$", "").replaceAll("\\.$", "")) + name.substring(1)); // Removes trailing zeros behind decimal
				operator = 6;
				ans = calculate(num1, num2, operator);
				display.setText(("" + ans).indexOf(".") < 0 ? ("" + ans)
						: ("" + ans).replaceAll("0*$", "").replaceAll("\\.$", "")); // Removes trailing zeros behind decimal
				operator = 0;
				isAns = true; // There is an ans on display
				break;
			case ".": //Adds decimal point to display
				if (isAns) { // Makes sure no number is added behind an already-displayed ans
					display.setText("");
					miniDisplay.setText("");
					isAns = false;
				}
				if (display.getText() == "") { // Makes sure if display is empty, display is decimal number
					display.setText(0 + name);
					miniDisplay.setText(0 + name);
				} else if (display.getText().contains(".") == false) { // Makes sure there isn't already a decimal point present in display
					display.setText(display.getText() + name);
					miniDisplay.setText(miniDisplay.getText() + name);
				}
				break;
			case "+/-": // First number assigned display number, display is cleared, uses calculate() method to negate number and displays ans 
				num1 = Double.parseDouble(display.getText());
				operator = -1;
				ans = calculate(num1, num2, operator);
				display.setText(("" + ans).indexOf(".") < 0 ? ("" + ans)
						: ("" + ans).replaceAll("0*$", "").replaceAll("\\.$", "")); // Removes trailing zeros behind decimal
				miniDisplay.setText(display.getText());
				operator = 0; // Resets operator back to standard
				break;
			case "=": // Uses calculate() method with current data from variables and displays ans 
				if (operator == 0) { // If operator is standard, just displays same number as ans
					num1 = Double.parseDouble(display.getText());
				}
				num2 = Double.parseDouble(display.getText());
				ans = calculate(num1, num2, operator);
				display.setText(("" + ans).indexOf(".") < 0 ? ("" + ans)
						: ("" + ans).replaceAll("0*$", "").replaceAll("\\.$", ""));
				miniDisplay.setText("");
				// Resets all variables
				num1 = 0.0;
				num2 = 0.0;
				operator = 0;
				isAns = true; // There is an ans on display
				break;
			}
		}
	}

}
