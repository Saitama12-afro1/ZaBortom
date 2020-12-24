package Main;

import Main.Game_obj;

import java.util.ArrayList;
import java.util.Collections;

public class Navigation {
    Navigation(ArrayList<Game_obj.GamePerson> sinking_list, ArrayList<Game_obj.GamePerson> thirst_list,
               boolean has_seagull, boolean thirst_for_fighting, boolean thirst_for_swimming, boolean has_death_seagull)
    {
        this.sinking_list = sinking_list;
        this.thirst_list = thirst_list;
        this.has_seagull = has_seagull;
        this.thirst_for_fighting = thirst_for_fighting;
        this.thirst_for_swimming = thirst_for_swimming;
        this.has_death_seagull = has_death_seagull;
    }

    Navigation(Game_obj.GamePerson sinking_person, ArrayList<Game_obj.GamePerson> thirst_list,
               boolean has_seagull, boolean thirst_for_fighting, boolean thirst_for_swimming, boolean has_death_seagull)
    {
        this.sinking_list = new ArrayList<>(Collections.singletonList(sinking_person));
        this.thirst_list = thirst_list;
        this.has_seagull = has_seagull;
        this.thirst_for_fighting = thirst_for_fighting;
        this.thirst_for_swimming = thirst_for_swimming;
        this.has_death_seagull = has_death_seagull;
    }

    Navigation(ArrayList<Game_obj.GamePerson> sinking_list, Game_obj.GamePerson thirsty_person,
               boolean has_seagull, boolean thirst_for_fighting, boolean thirst_for_swimming, boolean has_death_seagull)
    {
        this.sinking_list = sinking_list;
        this.thirst_list = new ArrayList<>(Collections.singletonList(thirsty_person));
        this.has_seagull = has_seagull;
        this.thirst_for_fighting = thirst_for_fighting;
        this.thirst_for_swimming = thirst_for_swimming;
        this.has_death_seagull = has_death_seagull;
    }

    Navigation(Game_obj.GamePerson sinking_person, Game_obj.GamePerson thirsty_person,
               boolean has_seagull, boolean thirst_for_fighting, boolean thirst_for_swimming, boolean has_death_seagull)
    {
        this.sinking_list = new ArrayList<>(Collections.singletonList(sinking_person));
        this.thirst_list = new ArrayList<>(Collections.singletonList(thirsty_person));
        this.has_seagull = has_seagull;
        this.thirst_for_fighting = thirst_for_fighting;
        this.thirst_for_swimming = thirst_for_swimming;
        this.has_death_seagull = has_death_seagull;
    }

    // флаги (Полагаю эти флаги будут читаться классом игры для наложения эффектов на игроков и изменения хода игры)
    public  boolean has_seagull; // 4 полученная за игру чайка означает конец партии
    public boolean has_death_seagull; // отменяет одну чайку
    public boolean thirst_for_fighting; // Накладывается ли жажда для дравшихся
    public boolean thirst_for_swimming; // Накладывается ли жажда для грёбших

    public ArrayList<Game_obj.GamePerson> sinking_list; // Люди за бортом
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
