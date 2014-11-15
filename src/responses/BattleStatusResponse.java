package responses;

public class BattleStatusResponse {

  public Boolean game_ready;
  public Boolean your_turn;

  public BattleStatusResponse(Boolean game_ready, Boolean your_turn) {
    this.game_ready = game_ready;
    this.your_turn = your_turn;
  }

  public boolean isValid() {
    return game_ready != null && your_turn != null;
  }

}
