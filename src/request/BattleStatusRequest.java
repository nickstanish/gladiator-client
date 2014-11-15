package request;

public class BattleStatusRequest extends BaseRequest {

  public BattleStatusRequest(double damage) {
    this.action = "BattleRequest";
    this.data = damage;
  }

}
