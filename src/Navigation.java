import Main.Game_obj;

import java.util.ArrayList;

public class Navigation {
    Navigation(ArrayList<Game_obj.GamePerson> sinking_list, ArrayList<Game_obj.GamePerson> thirst_list, boolean has_seagull) {
        this.sinking_list = sinking_list;
        this.thirst_list = thirst_list;
        this.has_seagull = has_seagull;
    }

    private boolean has_seagull; // 4 полученная за игру чайка означает конец партии

    private ArrayList<Game_obj.GamePerson> sinking_list; // Люди за бортом
    private ArrayList<Game_obj.GamePerson> thirst_list; // Люди с жаждой

    public ArrayList<Game_obj.GamePerson> getSinkingList() {
        return sinking_list;
    }

    public ArrayList<Game_obj.GamePerson> getThirstList() {
        return thirst_list;
    }

    public boolean checkSinkingList(Game_obj.GamePerson person) {
        return sinking_list.contains(person);
    }

    public boolean checkThirstList(Game_obj.GamePerson person) {
        return thirst_list.contains(person);
    }

}
