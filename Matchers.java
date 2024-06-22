import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Matchers {
  public static Map<String, List<OffsetData>> match(String str, List<String> finder) {
    Map<String, List<OffsetData>> mp = new LinkedHashMap<>();
    for (String data : finder) {
      String[] lined_string = str.split("\n");

      for (int i = 0; i < lined_string.length; i++) {
        List<String> sub_array_string = List.of(lined_string[i].split(" "));

        for (int j = 0; j < sub_array_string.size(); j++) {
          if (sub_array_string.get(j).equals(data)) {
            if (mp.containsKey(data)) {
              mp.get(data).add(new OffsetData(i + 1, j + 1));
            } else {
              List<OffsetData> offsetData = new ArrayList<>();
              offsetData.add(new OffsetData(i+1, j+1));
              mp.put(data, offsetData);
            }
          }
        }
      }
    }
    return mp;
  }
}
