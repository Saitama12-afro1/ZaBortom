package Main;

import Items.*;
import java.util.ArrayList;
import java.util.Collections;

public class ItemDeck {
    ItemDeck() { // здесь создаются изначальные карты
        items_list = new ArrayList<>();

        // Добавление предметов в список

        Collections.shuffle(items_list); // перемешивание
    }

    public Item getItem() {
        return items_list.remove(0);
    }

    public void putItem(Item item) {
        items_list.add(item);
    }

    ArrayList<Item> items_list; // нулевой элемент - верхний, последний - нижний
}
