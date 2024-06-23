import java.util.*;
import java.util.concurrent.Future;

public class Aggregators {

  public static void aggregatePrinter(List<Future<Map<String, Set<OffsetData>>>> futures) {
      Map<String, Set<OffsetData>> aggregatedResults = new HashMap<>();
      for (Future<Map<String, Set<OffsetData>>> future : futures) {
          try {

              Map<String, Set<OffsetData>> result = future.get();
              for(Map.Entry<String, Set<OffsetData>> mp : result.entrySet()){
                  aggregatedResults.computeIfAbsent(mp.getKey(), k -> new HashSet<>()).addAll(mp.getValue());

                 // System.out.println(mp.getKey() +"  "+ mp.getValue());
                 }
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      for (Map.Entry<String, Set<OffsetData>> entry : aggregatedResults.entrySet()) {
          System.out.println(entry.getKey() + ": " + entry.getValue());
      }

  }
}
