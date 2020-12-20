public class Items {
    public Items all_items[]= new Items[42];
    String name_items;
    boolean used=false;
    boolean visible=false;
    Items(String name_items) {
        this.name_items = name_items;
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
            ob.score=ob.score+1;
        }
    }
}
