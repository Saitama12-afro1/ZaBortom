package Items;

import Main.Game_obj;

public class Item {
    String name_items;
     public boolean visible = false;
     public int bonus_score = 0;

    public Item(String name_items) {
        this.name_items = name_items;
    }

    public Item(int bonus_score) {
        this.bonus_score = bonus_score;
    }

}


