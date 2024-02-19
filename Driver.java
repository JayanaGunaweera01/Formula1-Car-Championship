public abstract class Driver {
    private String name, location, team,position;

    Driver(String name, String location, String team) {
        this.name = name;
        this.location = location;
        this.team = team;
        this.position=position;

     }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getLocation() {
        return location;
    }

    void setLocation(String location) {
        this.location = location;
    }

    String getTeam() {
        return team;
    }

    void setTeam(String team) {
        this.team = team;
    }
    String getPosition() {
        return position;
    }

    void setPosition(String position) {
        this.position = position;
    }


}