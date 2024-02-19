
public class Formula1Driver extends Driver {
    int racesPlayed, firstPlaces, secondPlaces, thirdPlaces, points,draws;

    Formula1Driver(String name, String location, String team) {
        super(name, location, team);
    }

    int getfirstPlaces() {
        return firstPlaces;
    }

    void setfirstPlaces(int firstPlaces) {
        this.firstPlaces = firstPlaces;
    }

    int getsecondPlaces() {
        return secondPlaces;
    }

    void setsecondPlaces(int secondPlaces) {
        this.secondPlaces = secondPlaces;
    }

    int getthirdPlaces() {
        return thirdPlaces;
    }

    void setthirdPlaces(int thirdPlaces) {
        this.thirdPlaces = thirdPlaces;
    }

    int getDraws() {
        return draws;
    }

    void setDraws(int draws) {
        this.points = draws;
    }
    int getPoints() {
        return points;
    }

    void setPoints(int points) {
        this.points = points;
    }

    int getRacesPlayed() {
        return racesPlayed;
    }

    void setRacesPlayed(int racesPlayed) {
        this.racesPlayed = racesPlayed;
    }
}