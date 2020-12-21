package Main;

import Items.Item;

import javax.swing.plaf.basic.BasicListUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NavigationDeck  {
    // кидает исключение, если дают неправильное кол-во игроков. Вот такой костыль
    NavigationDeck(ArrayList<Game_obj.GamePerson> persons) throws Exception { // здесь создаются изначальные карты
        if (persons.size() != 6) {
            throw new Exception("Wrong number of persons provided");
        }

        navigation_list = new ArrayList<>();

        // Предполагается порядок - Леди СНоб Капитан Боцман Черпак Шкет
        Game_obj.GamePerson ledy = persons.get(0);
        Game_obj.GamePerson the_capitalist = persons.get(1);
        Game_obj.GamePerson capitan = persons.get(2);
        Game_obj.GamePerson boatswain = persons.get(3);
        Game_obj.GamePerson frenchie = persons.get(4);
        Game_obj.GamePerson kid = persons.get(5);

        // прописываем списки с возможными комбинациями персонажей, которые ощущают жажду
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
        putItem(new Navigation(capitan, frenchie, false, false, false, false));
        putItem(new Navigation(new ArrayList<>(), new ArrayList<>(), true, false, false, false));
        putItem(new Navigation(persons, persons, false, true, false, false));
        putItem(new Navigation(boatswain, cf, false, true, true, false));
        putItem(new Navigation(frenchie, tcb, true, false, false, false));
        putItem(new Navigation(the_capitalist, cbf, false, true, true, false));
        putItem(new Navigation(ledy, persons, false, false, false, true));
        putItem(new Navigation(the_capitalist, lc, false, true, false, false));
        putItem(new Navigation(boatswain, cb, false, false, true, false));
        putItem(new Navigation(kid, lcbf, false, false, false, false));
        putItem(new Navigation(the_capitalist, ck, true, false, true, false));
        putItem(new Navigation(frenchie, cbk, false, false, true, false));
        putItem(new Navigation(frenchie, lcb, false, true, false, false));
        putItem(new Navigation(the_capitalist, tc, false, false, false, false));
        putItem(new Navigation(persons, persons, true, true, true, false));
        putItem(new Navigation(kid, ltcbf, false, false, true, false));
        putItem(new Navigation(kid, tcbfk, false, true, false, false));
        putItem(new Navigation(kid, tcbfk, true, true, true, false));
        putItem(new Navigation(capitan, the_capitalist, false, true, true, false));
        putItem(new Navigation(capitan, capitan, false, false, false, false));
        putItem(new Navigation(frenchie, tcbf, false, true, true, false));
        putItem(new Navigation(capitan, boatswain, true, true, false, false));
        putItem(new Navigation(boatswain, kid, true, true, false, false));
        putItem(new Navigation(boatswain, ledy, false, false, false, false));

        Collections.shuffle(navigation_list); // перемешивание
    }

    public Navigation getNavigation() {
        return navigation_list.remove(0);
    }

    public void putItem(Navigation navigation) {
        navigation_list.add(navigation);
    }

    ArrayList<Navigation> navigation_list; // нулевой элемент - верхний, последний - нижний
}
