package request;

public class StartBattleRequest extends BaseRequest {

  public StartBattleRequest(Object data) {
    action = "NewCharacter";
    this.data = data;
  }

}
