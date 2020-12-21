package Items;

import Main.Game_obj;

public class Item {
    String name_items;
    boolean visible=false;
     int bonus_score=0;
    public Item(String name_items) {
        this.name_items = name_items;
    }
    public Item(int bonus_score){this.bonus_score=bonus_score;}
     public boolean visible_item(Game_obj.GamePerson ob, Item item)
    {
        int i=0;
        while(i<30)
        {
            if(ob.items_person[i]!=null) {}//доделать
        }
        return false;
    }
    public class Water extends Item
    {
        Water()
        {
            super("Water");
        }
        public  void use(Game_obj.GamePerson ob)
        {
            ob.thirst=false;
        }

    }
    public class Many extends Item
    {
        Many(Game_obj.GamePerson ob)
        {
            super(1);
        }
    }
   public class Picture_1 extends Item
    {
        Picture_1()
        {
            super(3);

        }
    }
     public class Picture_2 extends Item
    {
        Picture_2()
        {
            super(2);
        }

    }
    public class Jewelry extends Item//доделать, но хз как
    {
        Jewelry(Game_obj.GamePerson ob)
        {
            super(1);
        }
    }
     public class Weapon extends Item
    {
        int attack;
        String name_weapon;
        Weapon(int attack,String name_weapon)
        {
            super(0);
            this.attack=attack;
            this.name_weapon=name_weapon;
        }
    }
    public class Bucket extends Item implements IUsable
    {
        Bucket()
        {
            super(0);
        }
         public void  use(Game_obj.GamePerson ob)// проверять фазу
        {
            ob.damage_in_sea=ob.damage_in_sea+1;

        }

    }
    public   class Medkit extends Item implements IUsable
    {
        Medkit()
        {
            super(0);

        }
          public void use(Game_obj.GamePerson ob)
        {
            ob.damage_marker--;
        }
    }
    public  class Rpg extends Weapon implements IUsable
    {
        Rpg()
        {
            super(8,"RPG");
        }
        public  void use(Game_obj.GamePerson ob)//реализовать взятие из колоды навигации
        {

        }

    }




}
