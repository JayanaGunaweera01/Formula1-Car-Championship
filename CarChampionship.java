public class CarChampionship {

    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to the Formula 1 Championship Simulator!\n");
        // Create Formula 1 instance
        Formula1ChampionshipManager carChampionship = new Formula1ChampionshipManager() {
            @Override
            public int getNoOfDrivers() {
                return driversList.size();
            }
        };
        // Load data from file if exists
        carChampionship.loadDataFromFile();
        // Current no of drivers in the league
        System.out.println("########  The season 2021-22 is ongoing!  ########");
        System.out.println("There currently are " + carChampionship.getNoOfDrivers() + " drivers in the Formula 1 Championship.");

        // Loading main menu:
        String menuChoice = carChampionship.menuList();
        while (!menuChoice.equals("q")) {
            switch (menuChoice) {
                case "c":
                    System.out.println("\nCreate new driver:\n---------------------");
                    carChampionship.createDriver();
                    menuChoice = carChampionship.menuList();
                    break;
                case "r":
                    System.out.println("\nRemove driver from Formula 1 Championship:\n---------------------");
                    carChampionship.removeDriver();
                    menuChoice = carChampionship.menuList();
                    break;
                case "z":
                    System.out.println("\nChange the driver for an existing constructor team:\n---------------------");
                    carChampionship.changeTeam();
                    menuChoice = carChampionship.menuList(); //theres an error to solve in this method.Commented the method
                    break;
                case "d":
                    System.out.println("\nDisplay details & stats of a driver:\n---------------------");
                    carChampionship.displayDriverStats();
                    menuChoice = carChampionship.menuList();
                    break;
                case "t":
                    System.out.println("\nDisplay Formula 1 Championship table:\n--------------------------------------------------");
                    carChampionship.displayTable();
                    menuChoice = carChampionship.menuList();
                    break;
                case "m":
                    System.out.println("\nAdd a played race:\n---------------------");
                    carChampionship.addRace();
                    menuChoice =carChampionship.menuList();
                    break;
                case "s":
                    System.out.println("\nSave data to the file:\n---------------------");
                    carChampionship.saveToFile();
                    menuChoice = carChampionship.menuList();
                    break;
                case "g":
                    System.out.println("\nLaunch Graphical User Interface:\n---------------------");
                    carChampionship.startGui();
                    menuChoice = carChampionship.menuList();
                    break;
                default:
                    System.out.println("\nInvalid input!");
                    carChampionship.menuList();
            }
        }
        // Closing the program:
        System.out.println("\nYour session has ended. Goodbye!");
    }
}