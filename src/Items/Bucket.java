package Items;

import Main.Game_obj;

public class Bucket extends Item implements IUsable {
    public Bucket() {
        super(0);
    }

    public void use(Game_obj.GamePerson ob)// проверять фазу
    {
        ob.damage_in_sea = ob.damage_in_sea + 1;

    }

}
