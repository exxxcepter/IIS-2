import java.util.ArrayList;

public class Subject {
    String name;
    String task;
    String type;
    public ArrayList<String> goal;
    public ArrayList<String> confirmation;

    public Subject(String nm, String ts, String tp){
        name = nm;
        task = ts;
        type = tp;
        goal = new ArrayList<String>();
        confirmation = new ArrayList<String>();
    }
}
