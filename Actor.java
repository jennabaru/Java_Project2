package project2;

public class Actor{
    private String actorName;
    public Actor(String actorName) throws IllegalArgumentException {
        this.actorName = actorName;

        if(actorName == null|| actorName.length()==0 ){
            throw new IllegalArgumentException("Actor name is expected");
        }
    }

    public String name(){
        return actorName;
    }
}    