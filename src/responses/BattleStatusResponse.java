package responses;

public class BattleStatusResponse {

  public BattleStatus mine;
  public BattleStatus foe;

}


class BattleStatus {
  public int current_hp;
  public int current_ap;
  public Character character_information;

}
