package responses;

public class ConnectingResponse {

  public Boolean connected;
  public int position;
  public int total;

  public ConnectingResponse(Boolean connected, int position, int total) {
    this.connected = connected;
    this.position = position;
    this.total = total;
  }

}
