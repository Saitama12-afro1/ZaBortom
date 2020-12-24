package Main;

import Items.Item;
import Items.Paddle;

import java.util.ArrayList;
import java.util.Random;

public class Game_obj {

    public void run() { // просто функция для тестов штук
        try {
            new NavigationDeck(new Ledy(), new Capitalist(), new Capitan(), new Boatswain(), new Frenchie(), new Kid());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public GamePerson [] location_ship=new GamePerson[6];
    public class GamePerson {
        public String name_person;
        public int size_life;
        public int size_bonuslife;
        public int damage_marker;
        public boolean thirst = false;
        public int damage_in_sea = 1;
        public int count_items = 2;
        boolean exhaustion_raw = false;//усталость от гребли
        public ArrayList<Item>items_person = new ArrayList<Item>(count_items);
        public String lover;
        public String enemy;
        public int score;

        GamePerson(String name_person) {
            this.name_person = name_person;
        }
        public void visible_item(GamePerson ob,int i)
        {
            ob.items_person.get(i).visible=true;

        }

        public ArrayList <Navigation> row(GamePerson ob,NavigationDeck o)//  метод для гребли
        {
           ArrayList <Navigation> arr= new ArrayList<Navigation>(2);

            arr.add(0,o.getNavigation());
            arr.add(1,o.getNavigation());
            for(int i=0, j=2;i<count_items;i++) {
                if (ob.items_person.get(i)instanceof Paddle) {
                    arr.add(j,o.getNavigation());
                    j++;
                }
            }
            return arr;
        }
        public void rpg_row(GamePerson ob,NavigationDeck o)//метод не доделан-(с)Никита
        {
            ArrayList <Navigation> arr= new ArrayList<Navigation>(3);
            arr.add(0,o.getNavigation());
            arr.add(1,o.getNavigation());
            arr.add(2,o.getNavigation());
            for(int i=0;i<3;i++)
            {
                if(arr.get(i).has_seagull)//прибавить к общему числу чаек в мэйне
                {

                }
            }
        }

        public boolean fight()//метод для битвы
        {
            boolean exhaustion_fight = false;//усталость от битвы
            return false;
        }

        public Item use_items()// метод для использования предмета
        {
            return new Item("f");
        }
    }
           public class Kid extends GamePerson
          {
              Kid()
              {
                  super("Kid");
                  size_life=3;
                  size_bonuslife=9;
                  location_ship[5]=this;
              }
              public Item thievery(GamePerson name_enemy)
              {
                  Random random=new Random();
                  int i= random.nextInt(name_enemy.count_items);
                  this.items_person.add(name_enemy.items_person.remove(i));//настроить  работу с полем видимости
                  return  this.items_person.get(count_items);
              }
              }


    public class Frenchie extends GamePerson
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
            damage_in_sea=0;
        }

    }
    public class Boatswain extends GamePerson
    {
        Boatswain()
        {
            super("Boatswain");
            size_life=8;
            size_bonuslife=4;
            location_ship[3]=this;
        }
    }
    public class Capitan extends GamePerson
    {
        Capitan()
        {
            super("Capitan");
            size_life=7;
            size_bonuslife=5;
            location_ship[2]=this;
        }
        public int bonus_many()
        {
            return 1;
        }
    }
    public class Capitalist extends GamePerson
    {
        Capitalist()
        {
            super("Capitalist");
            size_life=5;
            size_bonuslife=7;
            location_ship[1]=this;
        }
        public int bonus_paintings()
        {
            return 1;
        }
    }
    public class Ledy extends GamePerson
    {
        public Ledy()
        {
            super("Ledy");
            size_life=4;
            size_bonuslife=8;
            location_ship[0]=this;
        }
        public int bonus_jewelry()
        {
            return 1;
        }
    }

}

