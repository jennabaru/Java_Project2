package project2;

public class Location{
    private String location;
    private String funFact;

    public Location(String location, String funFact) throws IllegalArgumentException{
        this.location=location;
        this.funFact=funFact;
        if(location == null || location.length()==0){
            throw new IllegalArgumentException("Location is expected");
        }
    }
}