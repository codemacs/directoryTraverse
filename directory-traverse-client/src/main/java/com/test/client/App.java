package com.test.client;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		processRequest();
	}

	private static void processRequest() {
		String choice = "Y";

		while ("Y".equalsIgnoreCase(choice)) {

			Scanner scanner = new Scanner(System.in);

			System.out.println("Please enter service host address:");
			String hostAdress = scanner.nextLine();

			System.out.println("Please enter service host port:");
			String hostPort = scanner.nextLine();

			System.out.println("Please enter service directory path to get details:");
			String filePath = scanner.nextLine();

			RequestProcessor requestProcessor = new RequestProcessor();
			requestProcessor.processPosRequest(hostAdress, hostPort, filePath);

			System.out.println("Do you want to continue Y/N ? Please enter:");
			choice = scanner.nextLine();
		}

		System.out.println("Termination the program.......");
	}
}
