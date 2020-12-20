public class Game_obj {

      public class GamePerson
     {
          public  String name_person;
          public int size_life;
          public int size_bonuslife;
          public  boolean exaustion=false;
          public GamePerson [] location_ship=new GamePerson[5];
          public  Items [] items_person;

          GamePerson(String name_person)
          {
             this.name_person=name_person;
             if(name_person=="Kid")
             {
                 size_life=3;
                 size_bonuslife=9;
                 location_ship[5]=GamePerson.this;
             }
              if(name_person=="Frenchy")
              {
                  size_life=6;
                  size_bonuslife=6;
                  location_ship[4]=GamePerson.this;
              }
              if(name_person=="Boatswain")
              {
                  size_life=8;
                  size_bonuslife=3;
                  location_ship[3]=GamePerson.this;
              }
              if(name_person=="Capitan")
              {
                  size_life=7;
                  size_bonuslife=5;
                  location_ship[2]=GamePerson.this;
              }
              if(name_person=="Snob")
              {
                  size_life=5;
                  size_bonuslife=7;
                  location_ship[1]=GamePerson.this;
              }
              if(name_person=="Ledy")
              {
                  size_life=4;
                  size_bonuslife=8;
                  location_ship[0]=GamePerson.this;
              }

          }

     }
    public class Ship
    {

    }
     public class Items
     {
         public  Items all_items[]= new Items[42];
         String name_items;
         boolean used=false;
         boolean visible=false;
          Items(String name_items) {
              this.name_items = name_items;
          }

     }
class Water extends Items
{
    Water()
    {
        super("Water");
    }

}
     public class Navigation
     {

     }
}
