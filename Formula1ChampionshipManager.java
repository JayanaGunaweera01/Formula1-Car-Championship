import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;
import java.util.stream.Collectors;


class Formula1ChampionshipManager implements ChampionshipManager {
    // Max number of drivers that can belong to Formula 1 championship
    private final int CHAMPIONSHIP_CAPACITY = 20;
    // Create empty list of driver
    List<Formula1Driver> driversList = new ArrayList<>(CHAMPIONSHIP_CAPACITY);
    // Max number of races that can be played in one season in Formula 1 championship
    private final int RACES_CAPACITY = 38;
    // Create empty list of races
    List<Race> racesList = new ArrayList<>(RACES_CAPACITY);
    private int driverCount;


    // Implementation of method required by Formula 1 Championship Manager interface
    public int getNoOfDrivers() {
        return driversList.size();
    }


    // Menu methods
    String menuList() {
        System.out.println(
                "\nTo continue, choose option from the menu:\n"
                        + "---------------------------------------\n"
                        + "C:\t Create a new driver and add to the championship\n"
                        + "R:\t Remove a driver and his team from championship\n"
                        + "Z:\t Change the driver for an existing constructor team\n"
                        + "D:\t Display the various statistics for a selected existing driver.\n"
                        + "T:\t Display the Formula 1 Driver Table\n"
                        + "M:\t Add a race completed with its date and the positions that all the drivers achieved.\n"
                        + "S:\t Save data in to file\n"
                        + "G:\t Launch Graphical User Interface(GUI)\n"
                        + "Q:\t Quit program\n");
        Scanner sc = new Scanner(System.in);
        String menuChoice = sc.nextLine().toLowerCase();
        return menuChoice;
    }

   void createDriver() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Type name of the driver: ");
        String name = sc.nextLine();
        // Validate input
        while (nameValidationFailed(name)) {
            name = sc.nextLine();
        }
        System.out.print("Type driver's location: ");
        String location = sc.nextLine();
        // Validate second input
        while (nameValidationFailed(location)) {
            location = sc.nextLine();
        }
        System.out.print("Type driver's team: ");
        String team = sc.nextLine();
        // Validate third input


        while (nameValidationFailed(team)) {
            team = sc.nextLine();

        }

// Add new instance of a Formula1Driver to the driver list

        driversList.add(new Formula1Driver(name, location, team));
        // Confirm that added successfully
        System.out.println("...\n" + name + " driver has been added!");
        displayDriversNoInfo();


    }

    void removeDriver() {
        Scanner sc = new Scanner(System.in);
        // Display current drivers
        displayDriversNames(driversList);
        System.out.print("Which driver to REMOVE: ");
        String name = sc.nextLine();
        // Validate input
        while (driverNotInChampionship(name)) {
            System.out.print("Wrong driver name!\nTry again: ");
            name = sc.nextLine();
        }
        // Remove driver
        Iterator<Formula1Driver> itr = driversList.iterator();
        while (itr.hasNext()) {
            Formula1Driver driver = itr.next();
            if (driver.getName().equals(name)) {
                itr.remove();
                System.out.println("...\n" + name + " removed!");
            }
        }
        displayDriversNoInfo();
    }

    public void changeTeam() {
        String name, location, team;
        System.out.println("\n Driver changing... \n");
        Scanner sc = new Scanner(System.in);
        // Display current drivers
        displayDriversNames(driversList);
        System.out.print("Enter team");
        team=sc.nextLine();
        if (!driverNotInChampionship(team)) {

            for (Formula1Driver d : driversList) {
                if (d.getTeam().equals(team)) {
                    System.out.print("Enter new driver name ");
                    name = sc.nextLine();
                    System.out.print("Enter new location name");
                    location = sc.nextLine();
                    d.setName(name);
                    d.setLocation(location);
                    System.out.print("Driver  "+name+"changed to  team  "+team+"and location to  "+location);
                    break;
                }
            }


        }


    }


    void displayDriverStats() {
        Scanner sc = new Scanner(System.in);
        // Display current drivers
        displayDriversNames(driversList);
        System.out.print("Name of the driver: ");
        String name =  sc.nextLine();
        // Validate input
        while (driverNotInChampionship(name)) {
            System.out.print("Wrong driver name!\nTry again: ");
            name = sc.nextLine
        ();
        }
        // Display driver info
        Iterator<Formula1Driver> itr = driversList.iterator();
        while (itr.hasNext()) {
            Formula1Driver driver = itr.next();
            if (driver.getName().equals(name)) {
                System.out.println(
                        "...\n" + name + " Driver from " + driver.getLocation() +"  from team  "+ "\n"
                                + "---------------------------\n"
                                + "Races played:\t " + driver.getRacesPlayed() + "\n"
                                + "First Places:\t\t\t " + driver.getfirstPlaces() + "\n"
                                + "Second Places:\t\t\t " + driver.getsecondPlaces() + "\n"
                                + "Third Places:\t\t " + driver.getthirdPlaces() + "\n"
                                + "TOTAL POINTS:\t " + driver.getPoints() + "\n"
                );
            }
        }
    }

    void displayTable() {
        System.out.format("                DRIVER |  RP   1st   2nd   3rd   Pts\n");
        List<Formula1Driver> driversCopy = driversList.stream().collect(Collectors.toList());
        // Selection Sort to display drivers row in the right order
        for (int i = 0; i < driversCopy.size() - 1; i++) {
            Formula1Driver firstUnsorted = driversCopy.get(i);
            Formula1Driver bestDriver = firstUnsorted;
            int index = i;

            for (int j = i+1; j < driversCopy.size(); j++) {
                Formula1Driver next = driversCopy.get(j);

                if (bestDriver.getPoints() == next.getPoints()) {
                    // Resolve same points
                    if (bestDriver.getPoints()  < next.getPoints()) {
                        bestDriver = next;
                        index = j;
                    }
                }
                if (bestDriver.getPoints() < next.getPoints()) {
                    bestDriver = next;
                    index = j;
                }
            }
            Formula1Driver sorted = bestDriver;
            driversCopy.set(index, firstUnsorted);
            driversCopy.set(i,sorted);
            // Print out driver with highest points directly
            displayRow(sorted);
            // After the last iteration, display the last element
            if (i == driversCopy.size() - 2) {
                displayRow(driversCopy.get(i + 1));
            }
        }

    }

           /* System.out.print("Type driver's position: ");
        String position;
        position = sc.nextLine();
        int points;

        for (Formula1Driver d : driversList) {

            if (d.getPosition().equals("1")) {
                points=25;
                break;
            } else if (d.getPosition().equals("2")) {
                points = 18;
                break;
            } else if (d.getPosition().equals("3")) {
                points = 15;
                break;

            } else if (d.getPosition().equals("4")) {
                points = 12;
                break;

            } else if (d.getPosition().equals("5")) {
                points = 10;
                break;

            } else if (d.getPosition().equals("6")) {
                points = 8;
                break;

            } else if (d.getPosition().equals("7")) {
                points = 6;
                break;
            } else if (d.getPosition().equals("8")) {
                points = 4;
                break;
            } else if (d.getPosition().equals("9")) {
                points = 2;
                break;
            } else if (d.getPosition().equals("10")) {
                points = 1;
                break;
            } else {
                points = 0;
                break;


            }

        }
*/

    void addRace() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Type date of the race (dd-mm): ");
        String date = sc.nextLine();
        while (wrongDateFormat(date)) {
            System.out.print("Wrong date format!\nTry again: ");
            date = sc.nextLine();
        }
        // Display current drivers
        displayDriversNames(driversList);
        // Take and validate input for drivers' names
        System.out.print("Type name of the driver: ");
        String driver1Name = sc.nextLine();
        while (driverNotInChampionship(driver1Name)) {
            System.out.print("Wrong driver name!\nTry again: ");
            driver1Name = sc.nextLine();
        }
        System.out.println("What is the position " + driver1Name + " achieved?");
        String driver1Position = sc.nextLine();

        int driver1firstPlaces = 0;
        int driver1secondPlaces = 0;
        int driver1thirdPlaces = 0;
        int driver1fourthPlaces = 0;
        int driver1fifthPlaces = 0;
        int driver1sixthPlaces = 0;
        int driver1seventhPlaces = 0;
        int driver1eighthPlaces = 0;
        int driver1ninethPlaces = 0;
        int driver1tenthPlaces = 0;
        if (driver1Position.equals("1")) {
            String driver1firstPlacesAsString = sc.nextLine();
            while (isNotNumberInRange(driver1firstPlacesAsString)) {
                System.out.print("Please type a number\nTry again: ");
                driver1firstPlacesAsString = sc.nextLine();
                driver1firstPlaces = Integer.parseInt(driver1firstPlacesAsString);

            }
            if (driver1Position.equals("2")) {
                String driver1secondPlacesAsString = sc.nextLine();
                while (isNotNumberInRange(driver1secondPlacesAsString)) {
                    System.out.print("Please type a number\nTry again: ");
                    driver1secondPlacesAsString = sc.nextLine();
                    driver1secondPlaces = Integer.parseInt(driver1secondPlacesAsString);
                }
                if (driver1Position.equals("3")) {
                    String driver1thirdPlacesAsString = sc.nextLine();
                    while (isNotNumberInRange(driver1thirdPlacesAsString)) {
                        System.out.print("Please type a number\nTry again: ");
                        driver1thirdPlacesAsString = sc.nextLine();
                        driver1thirdPlaces = Integer.parseInt(driver1thirdPlacesAsString);
                    }
                    if (driver1Position.equals("4")) {
                        String driver1fourthPlacesAsString = sc.nextLine();
                        while (isNotNumberInRange(driver1fourthPlacesAsString)) {
                            System.out.print("Please type a number\nTry again: ");
                            driver1fourthPlacesAsString = sc.nextLine();
                            driver1fourthPlaces = Integer.parseInt(driver1fourthPlacesAsString);
                        }
                        if (driver1Position.equals("5")) {
                            String driver1fifthPlacesAsString = sc.nextLine();
                            while (isNotNumberInRange(driver1fifthPlacesAsString)) {
                                System.out.print("Please type a number\nTry again: ");
                                driver1fifthPlacesAsString = sc.nextLine();
                                driver1fifthPlaces = Integer.parseInt(driver1fifthPlacesAsString);
                            }
                            if (driver1Position.equals("6")) {
                                String driver1sixthPlacesAsString = sc.nextLine();
                                while (isNotNumberInRange(driver1sixthPlacesAsString)) {
                                    System.out.print("Please type a number\nTry again: ");
                                    driver1sixthPlacesAsString = sc.nextLine();
                                    driver1sixthPlaces = Integer.parseInt(driver1sixthPlacesAsString);
                                }
                                if (driver1Position.equals("7")) {
                                    String driver1seventhPlacesAsString = sc.nextLine();
                                    while (isNotNumberInRange(driver1seventhPlacesAsString)) {
                                        System.out.print("Please type a number\nTry again: ");
                                        driver1seventhPlacesAsString = sc.nextLine();
                                        driver1seventhPlaces = Integer.parseInt(driver1seventhPlacesAsString);
                                    }
                                    if (driver1Position.equals("8")) {
                                        String driver1eighthPlacesAsString = sc.nextLine();
                                        while (isNotNumberInRange(driver1eighthPlacesAsString)) {
                                            System.out.print("Please type a number\nTry again: ");
                                            driver1eighthPlacesAsString = sc.nextLine();
                                            driver1eighthPlaces = Integer.parseInt(driver1eighthPlacesAsString);
                                        }
                                        if (driver1Position.equals("9")) {
                                            String driver1ninethPlacesAsString = sc.nextLine();
                                            while (isNotNumberInRange(driver1ninethPlacesAsString)) {
                                                System.out.print("Please type a number\nTry again: ");
                                                driver1ninethPlacesAsString = sc.nextLine();
                                                driver1ninethPlaces = Integer.parseInt(driver1ninethPlacesAsString);
                                            }
                                            if (driver1Position.equals("10")) {
                                                String driver1tenthPlacesAsString = sc.nextLine();
                                                while (isNotNumberInRange(driver1tenthPlacesAsString)) {
                                                    System.out.print("Please type a number\nTry again: ");
                                                    driver1tenthPlacesAsString = sc.nextLine();
                                                    driver1tenthPlaces = Integer.parseInt(driver1tenthPlacesAsString);
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        int driver1Score = (driver1firstPlaces * 25 + driver1secondPlaces * 18 + driver1thirdPlaces * 15 + driver1fourthPlaces * 12 + driver1fifthPlaces * 10 + driver1sixthPlaces * 8 + driver1seventhPlaces * 6 + driver1eighthPlaces * 4 + driver1ninethPlaces * 2 + driver1tenthPlaces * 2);

        //ArrayList<String> driver2Name = new ArrayList<String>();
        String driver2Name = sc.nextLine();
        System.out.println("Players who played against " + driver1Name + " are" + driversList);

        int driver2firstPlaces = 0;
        int driver2secondPlaces = 0;
        int driver2thirdPlaces = 0;
        int driver2fourthPlaces = 0;
        int driver2fifthPlaces = 0;
        int driver2sixthPlaces = 0;
        int driver2seventhPlaces = 0;
        int driver2eighthPlaces = 0;
        int driver2ninethPlaces = 0;
        int driver2tenthPlaces = 0;

        for (Formula1Driver d : driversList) {
            System.out.println("What is the position" + driversList + "got in the race?");
            String driver2Position = sc.nextLine();

            if (driver2Position.equals("1")) {
                String driver2firstPlacesAsString = sc.nextLine();
                while (isNotNumberInRange(driver2firstPlacesAsString)) {
                    System.out.print("Please type a number\nTry again: ");
                    driver2firstPlacesAsString = sc.nextLine();
                    driver2firstPlaces = Integer.parseInt(driver2firstPlacesAsString);

                }
                if (driver2Position.equals("2")) {
                    String driver2secondPlacesAsString = sc.nextLine();
                    while (isNotNumberInRange(driver2secondPlacesAsString)) {
                        System.out.print("Please type a number\nTry again: ");
                        driver2secondPlacesAsString = sc.nextLine();
                        driver2secondPlaces = Integer.parseInt(driver2secondPlacesAsString);
                    }
                    if (driver2Position.equals("3")) {
                        String driver2thirdPlacesAsString = sc.nextLine();
                        while (isNotNumberInRange(driver2thirdPlacesAsString)) {
                            System.out.print("Please type a number\nTry again: ");
                            driver2thirdPlacesAsString = sc.nextLine();
                            driver2thirdPlaces = Integer.parseInt(driver2thirdPlacesAsString);
                        }
                        if (driver2Position.equals("4")) {
                            String driver2fourthPlacesAsString = sc.nextLine();
                            while (isNotNumberInRange(driver2fourthPlacesAsString)) {
                                System.out.print("Please type a number\nTry again: ");
                                driver2fourthPlacesAsString = sc.nextLine();
                                driver2fourthPlaces = Integer.parseInt(driver2fourthPlacesAsString);
                            }
                            if (driver1Position.equals("5")) {
                                String driver2fifthPlacesAsString = sc.nextLine();
                                while (isNotNumberInRange(driver2fifthPlacesAsString)) {
                                    System.out.print("Please type a number\nTry again: ");
                                    driver2fifthPlacesAsString = sc.nextLine();
                                    driver2fifthPlaces = Integer.parseInt(driver2fifthPlacesAsString);
                                }
                                if (driver1Position.equals("6")) {
                                    String driver2sixthPlacesAsString = sc.nextLine();
                                    while (isNotNumberInRange(driver2sixthPlacesAsString)) {
                                        System.out.print("Please type a number\nTry again: ");
                                        driver2sixthPlacesAsString = sc.nextLine();
                                        driver2sixthPlaces = Integer.parseInt(driver2sixthPlacesAsString);
                                    }
                                    if (driver2Position.equals("7")) {
                                        String driver2seventhPlacesAsString = sc.nextLine();
                                        while (isNotNumberInRange(driver2seventhPlacesAsString)) {
                                            System.out.print("Please type a number\nTry again: ");
                                            driver2seventhPlacesAsString = sc.nextLine();
                                            driver2seventhPlaces = Integer.parseInt(driver2seventhPlacesAsString);
                                        }
                                        if (driver2Position.equals("8")) {
                                            String driver2eighthPlacesAsString = sc.nextLine();
                                            while (isNotNumberInRange(driver2eighthPlacesAsString)) {
                                                System.out.print("Please type a number\nTry again: ");
                                                driver2eighthPlacesAsString = sc.nextLine();
                                                driver2eighthPlaces = Integer.parseInt(driver2eighthPlacesAsString);
                                            }
                                            if (driver2Position.equals("9")) {
                                                String driver2ninethPlacesAsString = sc.nextLine();
                                                while (isNotNumberInRange(driver2ninethPlacesAsString)) {
                                                    System.out.print("Please type a number\nTry again: ");
                                                    driver2ninethPlacesAsString = sc.nextLine();
                                                    driver2ninethPlaces = Integer.parseInt(driver2ninethPlacesAsString);
                                                }
                                                if (driver1Position.equals("10")) {
                                                    String driver2tenthPlacesAsString = sc.nextLine();
                                                    while (isNotNumberInRange(driver2tenthPlacesAsString)) {
                                                        System.out.print("Please type a number\nTry again: ");
                                                        driver2tenthPlacesAsString = sc.nextLine();
                                                        driver2tenthPlaces = Integer.parseInt(driver2tenthPlacesAsString);
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


        }


        int driver2Score = (driver2firstPlaces * 25 + driver2secondPlaces * 18 + driver2thirdPlaces * 15 + driver2fourthPlaces * 12 + driver2fifthPlaces * 10 + driver2sixthPlaces * 8 + driver2seventhPlaces * 6 + driver2eighthPlaces * 4 + driver2ninethPlaces * 2 + driver2tenthPlaces * 2);

        // Process input to update drivers' statistics
        if (driver1Score == driver2Score) {

            updateDriversDraw(driver1Name, driver2Name, driver1Score);

        } else if (driver1Score > driver2Score) {
            updateDriversFirstSecondThird(driver1Name, driver2Name, driver1Score, driver2Score);
        } else {
            updateDriversFirstSecondThird(driver2Name, driver1Name, driver2Score, driver1Score);
        }
        // Add race to the race list
        racesList.add(new Race(date, driver1Name, driver2Name, driver1Score, driver2Score));
        System.out.println("... Adding\n" + driver1Name + "\t" + driver1Score + "\t" + "[" + date + "]" + "\t" + driver2Score + "\t" + driver2Name + "\nThe race has been added!");
    }

    void saveToFile() throws Exception {
        // Saving list of drivers
        try {
            File file = new File("." + File.separator + "drivers_list.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            Iterator<Formula1Driver> itr = driversList.iterator();
            while (itr.hasNext()) {
                Formula1Driver driver = itr.next();
                writer.write(

                        driver.getName() + "\n"
                                + driver.getLocation() +"  - is the driver's location"+ "\n"
                                + driver.getRacesPlayed() +"  - is the number of races played by the driver "+ "\n"
                                + driver.getfirstPlaces() + " - is the number of fist places achieved"+"\n"
                                + driver.getsecondPlaces() + "- is the number of second places achieved"+ "\n"
                                + driver.getthirdPlaces() + " - is the number of third places achieved"+"\n"
                                + driver.getPoints() + " - is the number of points got achieved"+"\n"
                );
            }
            writer.close();
            System.out.println("...\nDrivers list has been saved!");
        }
        catch (Exception error) {
            System.out.println("Exception error:\n" + error);
        }
        // Saving list of played races
        try {
            File file = new File("." + File.separator + "played_races.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            Iterator<Race> itr = racesList.iterator();
            while (itr.hasNext()) {
                Race race = itr.next();
                writer.write(
                        race.getDate() + "\n"
                                + race.getDriver1Name() + "\n"
                                + race.getdriver2Name() + "\n"
                                + race.getDriver1Score() + "\n"
                                + race.getDriver2Score() + "\n"
                );
            }
            writer.close();
            System.out.println("...\nRaces data has been saved!");
        }
        catch (Exception error) {
            System.out.println("Exception error:\n" + error);
        }
    }

    void startGui() {
        System.out.println("...\nProgram starting in a new window...");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
               try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                   ex.printStackTrace();
                }
                JFrame gui = new GUI(driversList, racesList, "Formula 1 Championship Simulator");
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setSize(900,500);
                gui.setVisible(true);
            }
        });
    }


    // Additional methods
    void loadDataFromFile() throws Exception {
        try {
            // Load drivers list
            String path = System.getProperty("user.dir");
            Scanner readFile = new Scanner(new BufferedReader(new FileReader(path + File.separator + "drivers_list.txt")));
            while (readFile.hasNext()) {
                String name = readFile.nextLine();
                String location = readFile.nextLine();
                String team = readFile.nextLine();
                Formula1Driver driver = new Formula1Driver(name, name, location);
                driver.setRacesPlayed(Integer.parseInt(readFile.nextLine()));
                driver.setfirstPlaces(Integer.parseInt(readFile.nextLine()));
                driver.setsecondPlaces(Integer.parseInt(readFile.nextLine()));
                driver.setthirdPlaces(Integer.parseInt(readFile.nextLine()));
                driver.setPoints(Integer.parseInt(readFile.nextLine()));
                driversList.add(driver);
            }
            readFile.close();
            System.out.println("... Drivers have been loaded!");
        }
        catch (FileNotFoundException error) {
            System.out.println("Exception error:\nNo data to load!\nAdd and save data first, then reopen the simulator.\n");
        }
        // Load races list
        try {
            String path = System.getProperty("user.dir");
            Scanner readFile = new Scanner(new BufferedReader(new FileReader(path + File.separator + "played_races.txt")));
            while (readFile.hasNext()) {
                String date = readFile.nextLine();
                String driver1Name = readFile.nextLine();
                String driver2Name = readFile.nextLine();
                int driverScore = Integer.parseInt(readFile.nextLine());
                int driver2Score = Integer.parseInt(readFile.nextLine());
                Race race = new Race(date, driver1Name, driver2Name, driverScore, driver2Score);
               race.setDate(date);
                race.setdriver1Name(driver1Name);
                race.setDriver2Name(driver2Name);
                race.setDriver1Score(driverScore);
                race.setDriver2Score(driver2Score);
                racesList.add(race);
            }
            readFile.close();
            System.out.println("... Races history has been loaded!\n");
        }
        catch (FileNotFoundException error) {
            System.out.println("Exception error:\nNo data to load!\nAdd and save data first, then reopen the simulator.\n");
        }
    }

    private void displayDriversNoInfo() {
        System.out.println("There are now " + getNoOfDrivers() + " drivers in the Premier League.");
    }

    private void updateDriversDraw(String driver1, String driver2, int firstPlaces) {
        Iterator<Formula1Driver> itr = driversList.iterator();
        while (itr.hasNext()) {
            Formula1Driver driver = itr.next();
            if (driver.getName().equals(driver1)) {
                driver.setRacesPlayed(driver.getRacesPlayed() + 1);
                driver.setDraws(driver.getDraws() +1);
                driver.setPoints(driver.getDraws()*25);

            }
        }
        Iterator<Formula1Driver> itr2 = driversList.iterator();
        while (itr2.hasNext()) {
            Formula1Driver driver = itr2.next();
            if (driver.getName().equals(driver2)) {
                driver.setRacesPlayed(driver.getRacesPlayed() + 1);
                driver.setDraws(driver.getDraws()+1 );
                driver.setPoints(driver.getPoints());

            }
        }

    }

    private void updateDriversFirstSecondThird(String winner, String loser, int winnerScore, int loserScore) {
        Iterator<Formula1Driver> itr = driversList.iterator();
        while (itr.hasNext()) {
            Formula1Driver driver = itr.next();
            if (driver.getName().equals(winner)) {
                driver.setRacesPlayed(driver.getRacesPlayed() + 1);
                driver.setfirstPlaces(driver.getfirstPlaces() + 1);
                driver.setPoints(driver.getfirstPlaces()*25);
            }
        }
        Iterator<Formula1Driver> itr2 = driversList.iterator();
        while (itr2.hasNext()) {
            Formula1Driver driver = itr2.next();
            if (driver.getName().equals(loser)) {
                driver.setRacesPlayed(driver.getRacesPlayed() + 1);
                driver.setsecondPlaces(driver.getsecondPlaces()+1);
                driver.setPoints(driver.getsecondPlaces()*18);


            }
        }
        Iterator<Formula1Driver> itr3 = driversList.iterator();
        while (itr3.hasNext()) {
            Formula1Driver driver = itr3.next();
            if (driver.getName().equals(loser)) {
                driver.setRacesPlayed(driver.getRacesPlayed() + 1);
                driver.setthirdPlaces(driver.getthirdPlaces()+1);
                driver.setPoints(driver.getthirdPlaces()*15);


            }
        }
    }

    private void displayRow(Formula1Driver driver) {
        System.out.format("%20s" +   "%7d" +   "%4d" +   "%4d" +  "%4d\n",
                driver.getName(),
                driver.getRacesPlayed(),
                driver.getfirstPlaces(),
                driver.getsecondPlaces(),
                driver.getthirdPlaces(),
                driver.getPoints()
        );
    }

    private void displayDriversNames(List<Formula1Driver> driversList) {
        System.out.println("Current driver list:");
        driversList.forEach(driver -> {
            if (driversList.indexOf(driver) < driversList.size()-1) {
                System.out.print(driver.getName() + ", ");
            } else {
                System.out.println(driver.getName());
            }
        });
    }


    // Validation methods
    private boolean nameValidationFailed(String name) {
        if (name.equals("")) {
            System.out.print("Name cannot be empty!\nTry again: ");
            return true;
        }
        if (isInteger(name)) {
            System.out.print("Name cannot be a number!\nTry again: ");
            return true;
        }
        return false;
    }
    private boolean locationValidationFailed(String location) {
        if (location.equals("")) {
            System.out.print("Loacation cannot be empty!\nTry again: ");
            return true;
        }
        if (isInteger(location)) {
            System.out.print("Location cannot be a number!\nTry again: ");
            return true;
        }
        return false;
    }

    private boolean teamValidationFailed(String team) {
        if (team.equals("")) {
            System.out.print("Team name cannot be empty!\nTry again: ");
            return true;
        }
        if (isInteger(team)) {
            System.out.print("Team name cannot be a number!\nTry again: ");
            return true;
        }
        return false;
    }


    private boolean driverNotInChampionship(String name) {
        Iterator<Formula1Driver> itr = driversList.iterator();
        while (itr.hasNext()) {
            Formula1Driver driver = itr.next();
            if (driver.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotNumberInRange(String numberAsString) {
        try {
            int number = Integer.parseInt(numberAsString);
            if (number >= 0 && number < 100) {
                return false;
            }
            return true;
        } catch (NumberFormatException error) {
            return true;
        }
    }

    private boolean isInteger(String name) {
        try {
            int integer = Integer.parseInt(name);
            return true;
        }
        catch(NumberFormatException error) {
            return false;
        }
    }

    private boolean wrongDateFormat(String date) {
        if (Pattern.matches("[0-3][0-9]-[0-1][0-9]", date)) {
            return false;
        }
        return true;
    }
} // End of main method