package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NavigationDeck  {
    public NavigationDeck(Game_obj.Ledy ledy, Game_obj.Capitalist the_capitalist, Game_obj.Capitan capitan,
                   Game_obj.Boatswain boatswain, Game_obj.Frenchie frenchie, Game_obj.Kid kid) { // здесь создаются изначальные карты
        navigation_list = new ArrayList<>();

        // прописываем списки с возможными комбинациями персонажей, которые ощущают жажду
        ArrayList<Game_obj.GamePerson> persons = new ArrayList<>(Arrays.asList(ledy, the_capitalist, capitan,
                boatswain, frenchie, kid));
        ArrayList<Game_obj.GamePerson> cf = new ArrayList<>(Arrays.asList(capitan, frenchie));
        ArrayList<Game_obj.GamePerson> tcb = new ArrayList<>(Arrays.asList(the_capitalist, capitan, boatswain));
        ArrayList<Game_obj.GamePerson> cbf = new ArrayList<>(Arrays.asList(capitan, boatswain, frenchie));
        ArrayList<Game_obj.GamePerson> lc = new ArrayList<>(Arrays.asList(ledy, capitan));
        ArrayList<Game_obj.GamePerson> cb = new ArrayList<>(Arrays.asList(capitan, boatswain));
        ArrayList<Game_obj.GamePerson> lcbf = new ArrayList<>(Arrays.asList(ledy, capitan, boatswain, frenchie));
        ArrayList<Game_obj.GamePerson> ck = new ArrayList<>(Arrays.asList(capitan, kid));
        ArrayList<Game_obj.GamePerson> cbk = new ArrayList<>(Arrays.asList(capitan, boatswain, kid));
        ArrayList<Game_obj.GamePerson> lcb = new ArrayList<>(Arrays.asList(ledy, capitan, boatswain));
        ArrayList<Game_obj.GamePerson> tc = new ArrayList<>(Arrays.asList(the_capitalist, capitan));
        ArrayList<Game_obj.GamePerson> ltcbf = new ArrayList<>(Arrays.asList(ledy, the_capitalist, capitan, boatswain, frenchie));
        ArrayList<Game_obj.GamePerson> tcbfk = new ArrayList<>(Arrays.asList(the_capitalist, capitan, boatswain, frenchie, kid));
        ArrayList<Game_obj.GamePerson> tcbf = new ArrayList<>(Arrays.asList(the_capitalist, capitan, boatswain, frenchie));

        // добавляем карты в колоду чё
        putNavigation(new Navigation(capitan, frenchie, false, false, false, false));
        putNavigation(new Navigation(new ArrayList<>(), new ArrayList<>(), true, false, false, false));
        putNavigation(new Navigation(persons, persons, false, true, false, false));
        putNavigation(new Navigation(boatswain, cf, false, true, true, false));
        putNavigation(new Navigation(frenchie, tcb, true, false, false, false));
        putNavigation(new Navigation(the_capitalist, cbf, false, true, true, false));
        putNavigation(new Navigation(ledy, persons, false, false, false, true));
        putNavigation(new Navigation(the_capitalist, lc, false, true, false, false));
        putNavigation(new Navigation(boatswain, cb, false, false, true, false));
        putNavigation(new Navigation(kid, lcbf, false, false, false, false));
        putNavigation(new Navigation(the_capitalist, ck, true, false, true, false));
        putNavigation(new Navigation(frenchie, cbk, false, false, true, false));
        putNavigation(new Navigation(frenchie, lcb, false, true, false, false));
        putNavigation(new Navigation(the_capitalist, tc, false, false, false, false));
        putNavigation(new Navigation(persons, persons, true, true, true, false));
        putNavigation(new Navigation(kid, ltcbf, false, false, true, false));
        putNavigation(new Navigation(kid, tcbfk, false, true, false, false));
        putNavigation(new Navigation(kid, tcbfk, true, true, true, false));
        putNavigation(new Navigation(capitan, the_capitalist, false, true, true, false));
        putNavigation(new Navigation(capitan, capitan, false, false, false, false));
        putNavigation(new Navigation(frenchie, tcbf, false, true, true, false));
        putNavigation(new Navigation(capitan, boatswain, true, true, false, false));
        putNavigation(new Navigation(boatswain, kid, true, true, false, false));
        putNavigation(new Navigation(boatswain, ledy, false, false, false, false));

        Collections.shuffle(navigation_list); // перемешивание
    }

    public Navigation getNavigation() {
        return navigation_list.remove(0);
    }

    public void putNavigation(Navigation navigation) {
        navigation_list.add(navigation);
    }

    ArrayList<Navigation> navigation_list; // нулевой элемент - верхний, последний - нижний
}
