import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;

public class Matchers {
  public static Map<String, List<OffsetData>> match(String str, List<String> finder) {
    int index =-1;
    Map<String, List<OffsetData>> mp = new LinkedHashMap<>();
    for (String data : finder) {
      String[] lined_string = str.split("\n");

      for (int i = 0; i < lined_string.length; i++) {
        List<String> sub_array_string = List.of(lined_string[i].split(" "));

        for (int j = 0; j < sub_array_string.size(); i++) {
          if (sub_array_string.get(i).equals(data)) {
            if (mp.containsKey(data)) {
              mp.get(data).add(new OffsetData(i + 1, index + 1));
            } else {
              mp.put(data, (List<OffsetData>) new OffsetData(i + 1, index + 1));
            }
          }
        }
      }
    }
    return mp;
  }
}
