public class Items {
    public Items all_items[]= new Items[42];
    String name_items;
    boolean used=false;
    boolean visible=false;
    Items(String name_items) {
        this.name_items = name_items;
    }
    boolean visible_item(Game_obj.GamePerson ob,Items item)
    {
        int i=0;
        while(i<30)
        {
            if(ob.items_person[i]!=)//доделать
        }

    }
    class Water extends Items
    {
        Water()
        {
            super("Water");
        }
        public  void destroy_thirst(Game_obj.GamePerson ob)
        {

        }

    }
    class Many extends Items
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
    class Picture_1 extends Items
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
    class Picture_2 extends Items
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
    class Jewelry extends Items//доделать, но хз как
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
