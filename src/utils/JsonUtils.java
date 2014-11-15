package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import main.Constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by nick on 9/6/14.
 */
public class JsonUtils {

  private static Gson gson;

  public static Gson getGson() {
    if (gson == null) {
      gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmmmmm").create();
    }
    return gson;
  }

  public static <T> T fromHttpEntity(String entity, Class<T> type) {
    T object;
    object = getGson().fromJson(entity, type);
    return object;
  }

  public static String toJsonString(Object object) {
    return getGson().toJson(object);
  }

  private static void writeToSocket(PrintWriter out, Object object) {
    out.print(toJsonString(object));
    out.print(Constants.HTTP_SEPARATOR_STRING);
    out.flush();
    System.out.println("OUT: " + toJsonString(object) + "'");
  }

  public static <T> T makeRequest(PrintWriter out, Object object, BufferedReader in, Class<T> type) {
    writeToSocket(out, object);
    return readFromSocket(in, type);
  }

  private static <T> T readFromSocket(BufferedReader in, Class<T> type) {
    T object;
    StringBuilder builder = new StringBuilder();
    int curr;
    int[] search = {'\0', '\0', '\0', '\0'};

    try {
      while (!matches(search, Constants.HTTP_SEPARATOR)) {
        curr = in.read();
        shift(search, curr);
        if (curr == -1)
          return null;
        if (curr != '\n')
          builder.append((char) curr);

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    String output = builder.toString().replaceAll("[\r\n]", "").trim();
    System.out.println("IN: '" + output + "'");

    object = getGson().fromJson(output, type);
    return object;
  }

  public static boolean matches(int[] from, int[] to) {
    boolean match = true;
    if (from.length != to.length)
      return false;
    for (int i = 0; i < from.length && match; i++) {
      match = match && from[i] == to[i];
    }
    return match;
  }

  public static void shift(int[] shift, int next) {
    for (int i = shift.length - 1; i >= 0; i--) {
      int swap = shift[i];
      shift[i] = next;
      next = swap;
    }

  }

}
