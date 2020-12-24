package Main;

import Items.*;
import java.util.ArrayList;
import java.util.Collections;

public class ItemDeck {
    ItemDeck() {
        items_list = new ArrayList<>();
        for (int i=0;i<17;i++) {
            putItem(new Water());
        }
        putItem(new Bucket());
        putItem(new Bucket());
        putItem(new Many());
        putItem(new Many());
        putItem(new Many());
        putItem(new Many());
        putItem(new Many());
        putItem(new Many());
        putItem(new Jewelry());
        putItem(new Jewelry());
        putItem(new Jewelry());
        putItem(new Medkit());
        putItem(new Medkit());
        putItem(new Medkit());
        putItem(new Picture_1());
        putItem(new Picture_1());
        putItem(new Picture_2());
        putItem(new Weapon(3,"Knife"));
        putItem(new Weapon(4,"boathook"));
        putItem(new Weapon(2,"baton"));
        putItem(new Rpg());
        putItem(new Umbrella());
        putItem(new Paddle());
        putItem(new Paddle());
        putItem(new Compass());

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
