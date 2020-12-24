package Items;

import Main.Game_obj;

public class Medkit extends Item implements IUsable {
    public Medkit() {
        super(0);

    }

    public void use(Game_obj.GamePerson ob) {
        ob.damage_marker--;
    }
}
