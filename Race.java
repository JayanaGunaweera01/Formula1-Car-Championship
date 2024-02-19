
public class Race {
    String date, driver1Name, driver2Name;
    int driver1Score, driver2Score;

    public Race(String date, String driver1Name, String driver2Name, int driver1Score, int driver2Score) {
        this.date = date;
        this.driver1Name = driver1Name;
        this.driver2Name = driver2Name;
        this.driver1Score = driver1Score;
        this.driver2Score = driver2Score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDriver1Name() {
        return driver1Name;
    }

    public void setdriver1Name(String driver1Name) {
        this.driver1Name = driver1Name;
    }

    public String getdriver2Name() {
        return driver2Name;
    }

    public void setDriver2Name(String driver2Name) {
        this.driver2Name = driver2Name;
    }

    public int getDriver1Score() {
        return driver1Score;
    }

    public void setDriver1Score(int driver1Score) {
        this.driver1Score = driver1Score;
    }

    public int getDriver2Score() {
        return driver2Score;
    }

    public void setDriver2Score(int driver2Score) {
        this.driver2Score = driver2Score;
    }
}