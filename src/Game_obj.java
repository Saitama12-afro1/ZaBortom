import java.util.ArrayList;
import java.util.Random;

public class Game_obj {

      public class GamePerson
     {
          public  String name_person;
          public int size_life;
          public int size_bonuslife;
          public int damadge_market;
          public  boolean exhaustion_raw=false;//усталость от гребли
         public  boolean exhaustion_fight=false;//усталость от битвы
          public GamePerson [] location_ship=new GamePerson[5];
           public int count_items=2;
          public ArrayList<Items> [] items_person=new ArrayList[count_items];
          public String lover;
          public String enemy;
          public  int score;
          GamePerson(String name_person)
          {
             this.name_person=name_person;
          }
          public   boolean raw()//  метод для гребли
          {
              return false;

          }
          public boolean fight()//метод для битвы
          {
              return false;

          }
          public  Items use_items()// метод для использования предмета
          {
              return  new Items("f");

          }
          class Kid extends GamePerson
          {
              Kid()
              {
                  super("Kid");
                  size_life=3;
                  size_bonuslife=9;
                  location_ship[5]=this;
              }
              public ArrayList<Items> thievery(GamePerson name_enemy)
              {
                  Random random=new Random();
                  int i= random.nextInt(name_enemy.count_items);
                   this.items_person[count_items++]=name_enemy.items_person[i];//настроить  работу с полем видимости
                  name_enemy.items_person[i].remove(i);
                   return  this.items_person[count_items];
              }

          }
         class Frenchie extends GamePerson
         {
             Frenchie()
             {
                 super("Frenchie");
                 size_life=6;
                 size_bonuslife=6;
                 location_ship[4]=this;
             }
            public void ignor_damage_sea()
            {
            }

         }
         class Boatswain extends GamePerson
         {
             Boatswain()
             {
                 super("Boatswain");
                 size_life=8;
                 size_bonuslife=4;
                 location_ship[3]=this;
             }
         }
         class Capitan extends GamePerson
         {
             Capitan()
             {
                 super("Capitan");
                 size_life=7;
                 size_bonuslife=5;
                 location_ship[2]=this;
             }
         }
         class Capitalist extends GamePerson
         {
             Capitalist()
             {
                 super("Capitalist");
                 size_life=5;
                 size_bonuslife=7;
                 location_ship[1]=this;
             }
         }
         class Ledy extends GamePerson
         {
             Ledy()
             {
                 super("Ledy");
                 size_life=4;
                 size_bonuslife=8;
                 location_ship[0]=this;
             }
         }




     }

     public class Navigation
     {
         public  Navigation all_navigation[]= new Navigation[24];

     }
}
