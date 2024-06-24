import java.util.*;
import java.util.concurrent.Callable;

public class Matchers implements Callable<Map<String, Set<OffsetData>>> {
    public static  String str;
    public static  List<String> finder;
  public Matchers(String str, List<String> finder){
    this.str = str;
    this.finder = finder;
  }

  @Override
  public Map<String, Set<OffsetData>> call() {
    Map<String, Set<OffsetData>> mp = new HashMap<>();
    for (String data : finder) {
      String[] lined_string = str.split("\n");

      for (int i = 0; i < lined_string.length; i++) {
        List<String> sub_array_string = List.of(lined_string[i].split(" "));

        for (int j = 0; j < sub_array_string.size(); j++) {
          if (trimSpecialChar(sub_array_string.get(j)).equalsIgnoreCase(data)) {

            mp.computeIfAbsent(data.toLowerCase(), k -> new HashSet<>()).add(new OffsetData(i + 1, j + 1));
          }
        }
      }
    }
    return mp;
  }

  private String trimSpecialChar(String str_char) {
    return str_char.replaceAll("^[^a-zA-Z:!]+|[^a-zA-Z:!]+$", "");
  }
}
