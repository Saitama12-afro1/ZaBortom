package Items;

public class Weapon extends Item
{
   int attack;
   String name_weapon;
    public Weapon(int attack,String name_weapon)
   {
       super(0);
       this.attack=attack;
       this.name_weapon=name_weapon;
   }
}
