package main;

public class CharacterInfo {

  public int character_level;
  public CharacterClass character_class;
  public int max_hp;
  public int max_ap;
  public int exp;
  public int attack;
  public double crit_chance;


  public static CharacterInfo makeThief() {
    CharacterInfo temp = new CharacterInfo();
    temp.character_level = 1;
    temp.character_class = CharacterClass.theif;
    temp.max_hp = 90;
    temp.max_ap = 12;
    temp.exp = 0;
    temp.attack = 10;
    temp.crit_chance = .7;
    return temp;
  }

  public static CharacterInfo makeWarrior() {
    CharacterInfo temp = new CharacterInfo();
    temp.character_level = 1;
    temp.character_class = CharacterClass.warrior;
    temp.max_hp = 160;
    temp.max_ap = 6;
    temp.exp = 0;
    temp.attack = 114;
    temp.crit_chance = .05;
    return temp;
  }

  public static CharacterInfo makeMage() {

    CharacterInfo temp = new CharacterInfo();
    temp.character_level = 1;
    temp.character_class = CharacterClass.mage;
    temp.max_hp = 80;
    temp.max_ap = 8;
    temp.exp = 0;
    temp.attack = 12;
    temp.crit_chance = .05;
    return temp;
  }



}
