package responses;

import main.CharacterClass;

public class BattleStatusResponse {

  public Boolean game_ready;
  public Boolean your_turn;
  public double me_health;
  public double foe_health;
  public double me_maxhealth;
  public double foe_maxhealth;
  public String me;
  public String foe;
  public CharacterClass foe_class;

  public boolean isValid() {
    return game_ready != null && your_turn != null;
  }

}
