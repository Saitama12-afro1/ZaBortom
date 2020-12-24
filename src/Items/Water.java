package Items;

import Main.Game_obj;

public class Water extends Item {
     public Water() {
        super("Water");
    }

    public void use(Game_obj.GamePerson ob) {
        ob.thirst = false;
    }

}
