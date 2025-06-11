public class Main {

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        // Array lists to store info
        ArrayList<String> serviceNames = new ArrayList<>();
        ArrayList<String> serviceCategories = new ArrayList<>();
        ArrayList<Double> serviceRates = new ArrayList<>();
        ArrayList<String> serviceSlots = new ArrayList<>();

        //Main Menu
        int choice = 0;
        while (choice != 4) {
            System.out.println("=== Local Service Marketplace ===");
            System.out.println("1. Add a Service");
            System.out.println("2. View Services by Category");
            System.out.println("3. Book a Service");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            sc.nextLine();

            //Add service code
            if (choice == 1) {
                String again = "yes";
                while (!again.equals("no")) {
                    System.out.print("Enter Service Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();
                    System.out.print("Enter Hourly Rate: $");
                    double rate = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Time Slots: ");
                    String slots = sc.nextLine();

                    serviceNames.add(name);
                    serviceCategories.add(category);
                    serviceRates.add(rate);
                    serviceSlots.add(slots);

                    System.out.println("Service added.");

                    System.out.println("Add another service?: ");
                    again = sc.nextLine();
                }
            }
    }
}