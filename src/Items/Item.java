package Items;

import Main.Game_obj;

public class Item {
    public Item all_items[]= new Item[42];
    String name_items;
    boolean used=false;
    boolean visible=false;
    public Item(String name_items) {
        this.name_items = name_items;
    }
    boolean visible_item(Game_obj.GamePerson ob, Item item)
    {
        int i=0;
        while(i<30)
        {
            if(ob.items_person[i]!=null) {}//доделать
        }
        return false;
    }
    class Water extends Item
    {
        Water()
        {
            super("Water");
        }
        public  void destroy_thirst(Game_obj.GamePerson ob)
        {

        }

    }
    class Many extends Item
    {
        Many(Game_obj.GamePerson ob)
        {
            super("Many");
            if(ob.name_person=="Capitan")
            {
                ob.score=ob.score+2;
            }else {
                ob.score = ob.score + 1;
            }
        }
        void score_collaboration(Game_obj.GamePerson ob)
        {
            if(ob.name_person=="Capitan")
            {
                ob.score=ob.score+2;

            }else {
                ob.score = ob.score + 1;
            }
        }
    }
    class Picture_1 extends Item
    {
        Picture_1()
        {
            super("Picture_1");

        }
        void score_collaboration(Game_obj.GamePerson ob)
        {
            if(ob.name_person=="Capitalist")
            {
                ob.score=ob.score+6;

            }else {
                ob.score = ob.score + 3;
            }
        }
    }
    class Picture_2 extends Item
    {
        Picture_2()
        {
            super("Picture_2");
        }
        void score_collaboration(Game_obj.GamePerson ob)
        {
            if(ob.name_person=="Capitalist")
            {
                ob.score=ob.score+4;

            }else {
                ob.score = ob.score + 2;
            }
        }
    }
    class Jewelry extends Item//доделать, но хз как
    {
        Jewelry(Game_obj.GamePerson ob)
        {
            super("Jewelry");
            if(ob.name_person=="Lady")
            {
                ob.score=ob.score+4;
            }else {
                ob.score = ob.score + 2;
            }
        }
    }


}
