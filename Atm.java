package exception.handling;

import java.util.InputMismatchException;
import java.util.Scanner;

class InsufficientMoneyException extends Exception {

	InsufficientMoneyException() {
	}

}

public class Atm {

	public static void main(String[] args) {

		int totalBalance = 2000;

		Scanner scanner = new Scanner(System.in);

		System.out.println("TotalBalance " + totalBalance);

		System.out.print("Enter amount to Withdraw : ");

		int withDraw = 0;
		try {

			withDraw = scanner.nextInt();
			if (withDraw > totalBalance)
				throw new InsufficientMoneyException();

		} catch (InsufficientMoneyException exception) {
			System.out.println("Insufficient Balance");
		} catch (InputMismatchException exception) {
			System.out.println("Enter Number Only ");
		} finally {
			if (withDraw < totalBalance && withDraw > 0) {
				System.out.println("You Withdrawed " + withDraw + " Rupees");
			} else {
				System.out.println("Available Amount " + totalBalance);
			}
		}

	}

}
