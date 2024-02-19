import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {

    public GUI(List<Formula1Driver> driversList, List<Race> racesList, String title){
        super(title);

        // Layout Set Up
        JTable table = loadDriversTable(driversList);
        JScrollPane tableScroll = new JScrollPane(table);
        getContentPane().add(tableScroll);
        JPanel navBar = new NavBar();
        navBar.setBackground(Color.PINK);
        getContentPane().add(navBar, BorderLayout.SOUTH);

        // Navbar buttons
        JLabel championshipLabel = new JLabel("Display Championship table by:");
        JLabel raceLabel = new JLabel("");
        JButton btnPoints = new JButton("Total Points");
        btnPoints.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("action was fired");
                int columnIndex = 7;
                sortTable(columnIndex, driversList, table);
            }
        });


        JButton btnRandomRace = new JButton("RandomRace");
        btnPoints.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("action was fired");
                int columnIndex = 7;
                sortTable(columnIndex, driversList, table);
            }
        });

        JButton btnsearch = new JButton("Search");
        btnPoints.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("action was fired");
                int columnIndex = 8;
                sortTable(columnIndex, driversList, table);
            }
        });

        JButton btnWins = new JButton("Number of first positions");
        btnWins.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("action was fired");
                int columnIndex = 2;
                sortTable(columnIndex, driversList, table);
            }
        });
        JButton btnExit = new JButton("Exit Program");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JButton btnRaces = new JButton("Display all");
        btnRaces.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("action was fired");
                // Remove current and create new table
                getContentPane().remove(tableScroll);
                JTable table = loadRacesTable(racesList);
                JScrollPane tableScroll = new JScrollPane(table);
                getContentPane().add(tableScroll);
                // Refresh GUI
                getContentPane().repaint();
                getContentPane().revalidate();

            }
        });

        //Buttons layout
        GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(20,10,-5,10);
        gbc.weightx = 0.6;
        gbc.gridx = 0;
        gbc.gridy = 3;
        navBar.add(btnExit, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        navBar.add(championshipLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        navBar.add(btnPoints, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        navBar.add(btnWins, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        navBar.add(btnRandomRace, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        navBar.add(btnsearch, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        navBar.add(raceLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        navBar.add(btnRaces, gbc);
    }

    JTable loadDriversTable(List<Formula1Driver> driversList) {
        String[] driversListColumns = {
                "Driver Name",
                "Races played",
                "\uD83E\uDD47 First Places",
                "\uD83E\uDD48 Second Places",
                "\uD83E\uDD49 Third Places",
                "\uD83C\uDF1FTOTAL POINTS"
        };

        DefaultTableModel tableModel = new DefaultTableModel(driversListColumns, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Integer.class;
                return super.getColumnClass(columnIndex);

            }
        };

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.setGridColor(Color.blue);
        populateDriversList(driversList, tableModel);
        return table;
    }

    JTable loadRacesTable(List<Race> racesList) {
        String[] racesColumns = {"Driver 1 ","Driver1 Score ","Date \uD83D\uDCC5", "Driver2 Score (score of other drivers)", "Driver2 (Drivers who played against Driver1) "};
        DefaultTableModel tableModel = new DefaultTableModel(racesColumns, 0);
        JTable table = new JTable(tableModel);
        table.setGridColor(Color.GREEN);
        table.setFillsViewportHeight(true);
        for (int i=0;i< racesList.size();i++) {
            String date = racesList.get(i).getDate();
            String driver1Name = racesList.get(i).getDriver1Name();
            String driver2Name = racesList.get(i).getdriver2Name();
            int driver1Score = racesList.get(i).getDriver1Score();
            int driver2Score = racesList.get(i).getDriver2Score();
            Object[] data = {driver1Name, driver1Score, date, driver2Score, driver2Name};
            tableModel.addRow(data);
        }
        return table;
    }

    void populateDriversList(List<Formula1Driver> driversList, DefaultTableModel tableModel) {
        // Access drivers data and load to the table
        for (int i = 0; i < driversList.size(); i++) {
            String driverName = (driversList.get(i)).getName();
            int racesPlayed = (driversList.get(i)).getRacesPlayed();
            int firstPlaces = (driversList.get(i)).getfirstPlaces();
            int secondPlaces = (driversList.get(i)).getsecondPlaces();
            int thirdPlaces = (driversList.get(i)).getthirdPlaces();
            int points = (driversList.get(i)).getPoints();

            Object[] data = {driverName, racesPlayed, firstPlaces, secondPlaces, thirdPlaces,points};
            tableModel.addRow(data);
        }
    }

    void sortTable(int columnIndex, List<Formula1Driver> driversList, JTable table) {
        TableRowSorter<TableModel> tableSorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(tableSorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(driversList.size());
        sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.DESCENDING));
        tableSorter.setSortKeys(sortKeys);
    }
}

class NavBar extends JPanel {

    public NavBar() {
        Dimension size = getPreferredSize();
        size.height = 200;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Formula 1 Championship Details \uD83C\uDFCE️"));
        setLayout(new GridBagLayout());
    }
}