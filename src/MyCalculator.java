import java.awt.*;
import java.awt.event.*; 
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import java.lang.Math;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BorderFactory;

public class MyCalculator extends JFrame implements ActionListener {
	
	private JPanel p0, p1, p2, p3;
	
	private JButton buttonNumbers[];
	private String[] keyPad = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "="};
	private JButton buttonBasicOperation[];
	private String[] basicOperation = {"+", "-", "*", "/","Sqrt", "%", "CLR", "DEL" };
	private JButton buttonScientificOperation[];
	private String[] scientificOperation = {"SIN", "COS", "TAN", "Pi", "log", "ln", "CLR", "DEL"};
	
	private JTextField display;
	private JLabel label;
	private JButton buttonScientificCal;
	private JButton buttonBasicCal;
	
	double result;
	double[] S = new double[2]; 
	int op = 0; 					// easy to choose operations
	int answerFlag = 0;
	
	//constructor created 
	public MyCalculator() {
		super("Calculator");
		setLayout(new BorderLayout(10,10));
		
		//Scientific Calculator Panel - Panel 0
		p0 = new JPanel();
		p0.setLayout(new GridLayout(4,2,10,10));
		buttonScientificOperation = new JButton[8];
		for(int i=0; i < buttonScientificOperation.length ; i++) {
			buttonScientificOperation[i] = new JButton(scientificOperation[i]);
			buttonScientificOperation[i].addActionListener(this);
			p0.add(buttonScientificOperation[i]);
		}
		
		p0.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//Display Result Panel - Panel 1
		p1 = new JPanel();
		p1.setLayout(new GridLayout(4,1,5,5)); //check once
		
		display = new JTextField();
		display.setBounds(5, 5, 150, 100);
        label = new JLabel();
        buttonBasicCal = new JButton("Basic Calculator");
        buttonBasicCal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent b) {
        		System.out.println("Basic Calculator selected.");
        		remove(p0);
        		add(p3);
        		display.setText("");
        		revalidate();
        		repaint();
        	}
        });
        buttonScientificCal = new JButton("Scientific Calculator");
        buttonScientificCal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent s) {
        		System.out.println("Scientific Calculator selected.");
        		remove(p3);
        		add(p0);
        		display.setText("");
        		revalidate();
        		repaint();
        	}
        });
        
        p1.add(buttonBasicCal); p1.add(buttonScientificCal);
        
        p1.add(label);
        p1.add(display);
        buttonBasicCal.setHorizontalAlignment(JButton.LEFT);
        buttonScientificCal.setHorizontalAlignment(JButton.RIGHT);
        
        label.setHorizontalAlignment(JLabel.RIGHT);
		display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
		//Number Button Panel - Panel 2
        p2 = new JPanel();
		p2.setLayout(new GridLayout(4,3,15,15));
        buttonNumbers = new JButton[12];
		for(int i=0; i<=11; i++) {
			buttonNumbers[i] = new JButton(keyPad[i]);	
			buttonNumbers[i].addActionListener(this);
			p2.add(buttonNumbers[i]);
		}
		
		p2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//Control/operation Panel - Panel 3
		p3 = new JPanel();
		p3.setLayout(new GridLayout(4,2,15,15));
		buttonBasicOperation = new JButton[8];
		for (int i=0; i < buttonBasicOperation.length; i++) {
			buttonBasicOperation[i] = new JButton(basicOperation[i]);
			buttonBasicOperation[i].addActionListener(this);
			p3.add(buttonBasicOperation[i]);
		}
		p3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//Add all the panels to the Frame
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.LINE_START);
		add(p3, BorderLayout.CENTER);
		//add(p0, BorderLayout.LINE_END);

		//frame.getContentPane().add(P, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		
		setVisible(true);
			

}
	//Action Performed Method
	public void actionPerformed(ActionEvent e) {
		String source = ((JButton) e.getSource()).getText();
		
		if(tryParseInt(source)) {
			
			if(answerFlag == 1) {
				answerFlag = 0;
				display.setText("");
			}
			display.setText(display.getText() + source);
			label.setText(label.getText() + source);
		}
		else if(source == ".") {
			label.setText(label.getText() + source);
			display.setText(display.getText() + source);
		}
		else if (source == "+" || source == "-" || source == "*" || source == "/" || source == "%") {
			try {
				if(op == 0) {
					S[0] = Double.parseDouble(display.getText()); 
				}
				else {
					S[1] = Double.parseDouble(display.getText());
				}
			}
			catch(NumberFormatException n) {
				}
		}
		
		// Addition Operation
		if(source == "+") {
			if(S[1] != 0.0) {
				doOperations();
			}
			else {
				if(S[0] == 0) {
					label.setText("0");
				}
				display.setText("");
			}
			addNumbers();
		}
		
		// Subtraction Operation 
		if(source == "-") {
			if(S[1] != 0.0) {
				doOperations();
			}
			else {
				if(S[0] == 0) {
					label.setText("0");
				}
				display.setText("");
			}
			subNumbers();
			
		}
		
		// Multiplication Operation
		if(source == "*") {
			if(S[1] != 0.0) {
				doOperations();
			}
			else {
				if(S[0] == 0) {
					label.setText("0");
				}
				display.setText("");
			}
			multiplyNumbers();
			
		}
		
		// Division Operation
		if(source == "/") {
			if(S[1] != 0.0) {
				doOperations();
			}
			else {
				if(S[0] == 0) {
					label.setText("0");
				}
				display.setText("");
			}
			divideNumbers();
		}
		
		// Modulus Operation
		if(source == "%") {
			if(S[1] != 0.0) {
				doOperations();
			}
			else {
				if(S[0] == 0) {
					label.setText("0");
				}
				display.setText("");
			}
			getModValue();
		}
		
		// Square-Root Operation
		if(source == "Sqrt") {
			getSqrtValue();	
		}
		
		// Clear the display
		if(source == "CLR") {
			cleardisplay();			
		}
		// Delete Operation
		if(source == "DEL") {  
			backSpace();
		}
		
		// Equal-to Operation
		if(source == "=") {
			S[1] = Double.parseDouble(display.getText());
			int index = label.getText().length() - 1;
			
			if(op == 1) {
				
				if(label.getText().charAt(index) == '+') {
					label.setText(label.getText().substring(0, index));
					display.setText(String.valueOf(S[0]));
				}
				else {
					result = S[0] + S[1];
					display.setText(String.valueOf(result));
				}
				
			}
			else if(op == 2) {
				
				if(label.getText().charAt(index) == '-') {
					label.setText(label.getText().substring(0, index));
					display.setText(String.valueOf(S[0]));
				}
				else {
					result = S[0] - S[1];
					display.setText(String.valueOf(result));
				}
			}
			else if(op == 3) {
				
				if(label.getText().charAt(index) == '*') {
					label.setText(label.getText().substring(0, index));
					display.setText(String.valueOf(S[0]));
				}
				else {
					result = S[0] * S[1];
					display.setText(String.valueOf(result));
				}
			}
			else if(op == 4) {
				
				if(label.getText().charAt(index) == '/') {
					label.setText(label.getText().substring(0, index));
					display.setText(String.valueOf(S[0]));
				}
				else {
					result = S[0] / S[1];
					display.setText(String.valueOf(result));
				}
			}
			else if(op == 5) {
				
				if(label.getText().charAt(index) == '%') {
					label.setText(label.getText().substring(0, index));
					display.setText(String.valueOf(S[0]));
				}
				else {
					result = S[0] % S[1];
					display.setText(String.valueOf(result));
				}
			}
			
			//label.setText("");
			
		}
		
		// Scientific Calculator Operations
		if(source == "SIN") {
			getSinValue();
		}
		
		if(source == "COS") {
			getCosValue();
		}
		
		if(source == "TAN") {
			getTanValue();
			
		}
		
		if(source == "Pi") {
			getPiValue();
		}
		
		if(source == "log") {
			getLogValue();
			
		}
		
		if(source == "ln") {
			getNaturalLogValue();
			
		}
	}
	
	public void doOperations() {
		
		try {
		 switch(op) {
			case 1: 
				S[0] = S[0] + S[1];
				display.setText(String.valueOf(S[0]));
				answerFlag = 1;
				break;
			
			case 2: 
				S[0] = S[0] - S[1];
				display.setText(String.valueOf(S[0]));
				answerFlag = 1;
				break;
				
			case 3: 
				S[0] = S[0] * S[1];
				display.setText(String.valueOf(S[0]));
				answerFlag = 1;
				break;
				
			case 4: 
				S[0] = S[0] / S[1];
				display.setText(String.valueOf(S[0]));
				answerFlag = 1;
				break;
				
			case 5: 
				S[0] = S[0] % S[1];
				display.setText(String.valueOf(S[0]));
				answerFlag = 1;
				break;
			default: 
				op = -1;
		 }
		 if(op == -1) {
			 display.setText("ERROR");
		 }
		 else
			 display.setText(String.valueOf(S[0]));
	 }
	 catch(NumberFormatException i) {
		 i.printStackTrace();
	 }
		
	}
	
	public boolean tryParseInt(String value) {
		
		try {
			Integer.parseInt(value);
			return true;
		}
		catch (NumberFormatException io) {
			return false;
		}
	}
	public void addNumbers() {
		String text = label.getText().concat("+");
		op = 1;
		label.setText(text);
	}
	public void subNumbers() {
		String text = label.getText().concat("-");
		op = 2;
		label.setText(text);
	}
	public void multiplyNumbers() {
		String text = label.getText().concat("*");
		op = 3;
		label.setText(text);
	}
	public void divideNumbers() {
		String text = label.getText().concat("/");
		op = 4;
		label.setText(text);
	}
	private void getModValue() {
		String text = label.getText().concat("%");
		op = 5;
		label.setText(text);
	}
	private void getSqrtValue() {
		S[0] = Double.parseDouble(display.getText());
		double sqrt = Math.sqrt(S[0]);
		display.setText(String.valueOf(sqrt));
	}
	public void cleardisplay() {
		display.setText("");
		label.setText("");
		S = new double[2];
		answerFlag = 0;
		op = 0;
	}
	public void backSpace() {
		int l ;
		String s = display.getText();
		l = s.length();
		if(l>=1) {
			s = s.substring(0, l-1);
			display.setText("" + s);
			label.setText("" + s);
		}
	}
	private void getSinValue() {
		S[0] = Double.parseDouble(display.getText());
		double sin = Math.sin(S[0]);
		display.setText(String.valueOf(sin));
	}
	private void getCosValue() {
		S[0] = Double.parseDouble(display.getText());
		double cos = Math.cos(S[0]);
		display.setText(String.valueOf(cos));
	}
	private void getTanValue() {
		S[0] = Double.parseDouble(display.getText());
		double tan = Math.tan(S[0]);
		display.setText(String.valueOf(tan));
	}
	private void getPiValue() {
		S[0] = Double.parseDouble(display.getText());
		S[0] = S[0] * Math.PI;
		display.setText(String.valueOf(S[0]));
	}
	private void getLogValue() {
		S[0] = Double.parseDouble(display.getText());
		double logValue = Math.log10(S[0]);
		display.setText(String.valueOf(logValue));
		
	}
	private void getNaturalLogValue() {
		S[0] = Double.parseDouble(display.getText());
		double lnValue = Math.log10(S[0]);
		display.setText(String.valueOf(lnValue));
	}
	//Main method
	public static void main(String[] arg) {
		
		new MyCalculator();
	}

}
