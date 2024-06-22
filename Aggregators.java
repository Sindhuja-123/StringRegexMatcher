import java.util.List;
import java.util.Map;

public class Aggregators {

  public static void aggregatePrinter(Map<String , List<OffsetData>> result) {

      for(Map.Entry<String, List<OffsetData>> mp : result.entrySet()){
        System.out.println(mp.getKey() +"  "+ mp.getValue());
      }

  }
}
