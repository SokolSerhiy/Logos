package ua;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import ua.controller.ApartmentController;
import ua.controller.AreaController;
import ua.controller.RentTypeController;
import ua.controller.StreetController;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		Scanner sc = context.getBean(Scanner.class);
		AreaController areaController = context.getBean(AreaController.class);
		RentTypeController rentTypeController = context.getBean(RentTypeController.class);
		StreetController streetController = context.getBean(StreetController.class);
		ApartmentController apartmentController = context.getBean(ApartmentController.class);
		boolean isRun = true;
		try {
			while (isRun) {
				System.out.println("Enter 0 to exit");
				System.out.println("Enter 1 to choose Area menu");
				System.out.println("Enter 2 to choose Rent Type menu");
				System.out.println("Enter 3 to choose Street menu");
				System.out.println("Enter 4 to choose Apartment menu");
				switch (sc.next()) {
				case "1":
					areaMenu(areaController, sc);
					break;
				case "2":
					rentTypeMenu(rentTypeController, sc);
					break;
				case "3":
					streetMenu(streetController, sc);
					break;
				case "4":
					apartmentMenu(apartmentController, sc);
					break;

				default:
					isRun = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.close();
	}

	@Bean(destroyMethod = "close")
	public Scanner getScanner() {
		return new Scanner(System.in);
	}

	private static void areaMenu(AreaController areaController, Scanner sc) {
		boolean isRun = true;
		while (isRun) {
			System.out.println("Enter 1 to add Area");
			System.out.println("Enter 2 to update Area");
			System.out.println("Enter 3 to delete Area");
			System.out.println("Enter 4 to show Area");
			System.out.println("Enter 5 to show all Area");
			switch (sc.next()) {
			case "1":
				areaController.save();
				break;
			case "2":
				areaController.update();
				break;
			case "3":
				areaController.delete();
				break;
			case "4":
				areaController.findOne();
				break;
			case "5":
				areaController.findAll();
				break;
			default:
				isRun = false;
			}
		}
	}
	
	private static void rentTypeMenu(RentTypeController rentTypeController, Scanner sc) {
		boolean isRun = true;
		while (isRun) {
			System.out.println("Enter 1 to add RentType");
			System.out.println("Enter 2 to update RentType");
			System.out.println("Enter 3 to delete RentType");
			System.out.println("Enter 4 to show RentType");
			System.out.println("Enter 5 to show all RentType");
			switch (sc.next()) {
			case "1":
				rentTypeController.save();
				break;
			case "2":
				rentTypeController.update();
				break;
			case "3":
				rentTypeController.delete();
				break;
			case "4":
				rentTypeController.findOne();
				break;
			case "5":
				rentTypeController.findAll();
				break;
			default:
				isRun = false;
			}
		}
	}
	
	private static void streetMenu(StreetController streetController, Scanner sc) {
		boolean isRun = true;
		while (isRun) {
			System.out.println("Enter 1 to add Street");
			System.out.println("Enter 2 to update Street");
			System.out.println("Enter 3 to delete Street");
			System.out.println("Enter 4 to show Street");
			System.out.println("Enter 5 to show all Street");
			switch (sc.next()) {
			case "1":
				streetController.save();
				break;
			case "2":
				streetController.update();
				break;
			case "3":
				streetController.delete();
				break;
			case "4":
				streetController.findOne();
				break;
			case "5":
				streetController.findAll();
				break;
			default:
				isRun = false;
			}
		}
	}
	
	private static void apartmentMenu(ApartmentController apartmentController, Scanner sc) {
		boolean isRun = true;
		while (isRun) {
			System.out.println("Enter 1 to add Apartment");
			System.out.println("Enter 2 to update Apartment");
			System.out.println("Enter 3 to delete Apartment");
			System.out.println("Enter 4 to show Apartment");
			System.out.println("Enter 5 to show all Apartment");
			switch (sc.next()) {
			case "1":
				apartmentController.save();
				break;
			case "2":
				apartmentController.update();
				break;
			case "3":
				apartmentController.delete();
				break;
			case "4":
				apartmentController.findOne();
				break;
			case "5":
				apartmentController.findAll();
				break;
			default:
				isRun = false;
			}
		}
	}
}
