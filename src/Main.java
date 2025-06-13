//NAME: Nathan Pichach

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //max size for the arrays
        int max = 100;

        //Arrays
        String[] serviceNames = new String[max];
        String[] serviceCategories = new String[max];
        double[] serviceRates = new double[max];
        boolean[] serviceBooked = new boolean[max];
        int[] cartIndexes = new int[max];
        int cartCount = 0;
        int serviceCount = 0;


        //main menu
        boolean running = true;
        while (running) {
            System.out.println("\nMAIN MENU:");
            System.out.println("1. Add a Service");
            System.out.println("2. Book a Service");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1, 2, 3): ");
            String choice = sc.nextLine();

            //add a service
            if (choice.equals("1")) {
                boolean adding = true;
                while (adding && serviceCount < max) {
                    System.out.print("Enter service name: ");
                    serviceNames[serviceCount] = sc.nextLine();

                    System.out.print("Enter category: ");
                    serviceCategories[serviceCount] = sc.nextLine();

                    System.out.print("Enter hourly rate($): ");
                    String rates = sc.nextLine();
                    serviceRates[serviceCount] = Double.parseDouble(rates);

                    serviceBooked[serviceCount] = false;
                    serviceCount++;

                    System.out.println("Service added successfully!\n");
                    System.out.print("Do you want to add another service? (yes/no): ");
                    String addAnother = sc.nextLine();
                    if (addAnother.equals("no")) {
                        adding = false;
                    }
                }
            }
            //book a service
            else if (choice.equals("2")) {
                String[] categories = new String[max];
                int categoryCount = 0;

                //ensure the service doesn't already exist
                for (int i = 0; i < serviceCount; i++) {
                    boolean exists = false;
                    for (int j = 0; j < categoryCount; j++) {
                        if (serviceCategories[i].equals(categories[j])) {
                            exists = true;
                            j = categoryCount;
                        }
                    }
                    if (exists == false) {
                        categories[categoryCount] = serviceCategories[i];
                        categoryCount++;
                    }
                }

                if (categoryCount == 0) {
                    System.out.println("No services available to book.");
                }

                System.out.println("\nAvailable Categories:");
                for (int i = 0; i < categoryCount; i++) {
                    System.out.println((i + 1) + ". " + categories[i]);
                }

                System.out.print("Choose a category by number: ");
                String selection = sc.nextLine();
                int selected = Integer.parseInt(selection);
                if (selected < 1 || selected > categoryCount) {
                    System.out.println("Invalid category choice.");
                }

                //connect services based on category
                String chosenCategory = categories[selected - 1];
                int[] matches = new int[max];
                int matchCount = 0;

                for (int i = 0; i < serviceCount; i++) {
                    if (serviceCategories[i].equals(chosenCategory)) {
                        matches[matchCount] = i;
                        matchCount++;
                    }
                }

                if (matchCount == 0) {
                    System.out.println("No services found in this category.");
                }

                //display all services in a given category
                System.out.println("\nServices in " + chosenCategory + ":");
                for (int i = 0; i < matchCount; i++) {
                    int index = matches[i];
                    String status = serviceBooked[index] ? "(Fully Booked)" : "(Available)";
                    System.out.println((i + 1) + ". " + serviceNames[index] + " - $" + serviceRates[index] + " per hour " + status);
                }

                //find what service to add to cart
                System.out.print("Select a service to add to cart: ");
                String choose = sc.nextLine();
                int serviceChoice = Integer.parseInt(choose);
                if (serviceChoice < 1 || serviceChoice > matchCount) {
                    System.out.println("Invalid selection.");
                }

                //check if service is booked
                int selectedService = matches[serviceChoice - 1];
                if (serviceBooked[selectedService]) {
                    System.out.println("This service is fully booked.");
                }

                //add selected service to cart
                serviceBooked[selectedService] = true;
                cartIndexes[cartCount] = selectedService;
                cartCount++;
                System.out.println("Service added to cart.\n");

                //show cart
                System.out.println("\nCURRENT CART:");
                double total = 0;
                for (int i = 0; i < cartCount; i++) {
                    int index = cartIndexes[i];
                    System.out.println((i + 1) + ". " + serviceNames[index] + " - $" + serviceRates[index]);
                    total += serviceRates[index];
                }
                System.out.println("Total so far: $" + total);

                // manage the cart
                boolean managing = true;
                while (managing == true) {
                    System.out.println("\nCART MENU:");
                    System.out.println("1. Remove a Service");
                    System.out.println("2. Checkout");
                    System.out.println("3. Return to Main Menu");
                    System.out.print("Enter your choice: ");
                    String input = sc.nextLine();

                    //remove service from cart code
                    if (input.equals("1")) {
                        System.out.println("\nCURRENT CART:");
                        total = 0;
                        for (int i = 0; i < cartCount; i++) {
                            int index = cartIndexes[i];
                            System.out.println((i + 1) + ". " + serviceNames[index] + " - $" + serviceRates[index]);
                            total += serviceRates[index];
                        }
                        System.out.print("Enter the number of the service to remove: ");
                        String remove = sc.nextLine();
                        int removeIndex = Integer.parseInt(remove);
                        if (removeIndex < 1 || removeIndex > cartCount) {
                            System.out.println("Invalid index.");
                        }
                        else {
                            int removedServiceIndex = cartIndexes[removeIndex - 1];
                            serviceBooked[removedServiceIndex] = false;
                            for (int i = removeIndex - 1; i < cartCount - 1; i++) {
                                cartIndexes[i] = cartIndexes[i + 1];
                            }
                            cartCount--;
                            System.out.println("Service removed from cart.");
                        }
                    }
                    // invoice/pay for what's in cart
                    else if (input.equals("2")) {
                        System.out.println("\nINVOICE:");
                        total = 0;
                        for (int i = 0; i < cartCount; i++) {
                            int index = cartIndexes[i];
                            System.out.println(serviceNames[index] + " - $" + serviceRates[index]);
                            total += serviceRates[index];
                        }
                        System.out.println("Total: $" + total);
                        System.out.println("Thank you for your order!");

                        //resets the cart after purchase
                        cartCount = 0;
                        managing = false;
                    }

                    //back to main menu
                    else if (input.equals("3")) {
                        managing = false;
                    }
                    else {
                        System.out.println("Invalid input.");
                    }
                }
            }
            //exit the marketplace
            else if (choice.equals("3")) {
                System.out.println("Thank you for using the Marketplace. Goodbye!");
                running = false;
            }
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    
        sc.close();
    }
}