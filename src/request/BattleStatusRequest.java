package request;

public class BattleStatusRequest {

  public String action;
  public Double data;

  public BattleStatusRequest(Double damage) {
    this.action = "BattleRequest";
    this.data = damage;
  }

}
