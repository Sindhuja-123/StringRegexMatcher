import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StringFinder {
  public static final String file = "https://norvig.com/big.txt";

  public static void main(String[] args) throws IOException {
    long startTime = System.currentTimeMillis();
    System.out.println("Start time "+ startTime);
    List<Future<Map<String,Set<OffsetData>>>> matchers=new ArrayList<>();

    int lines = 10000;

    String each_line;
    StringBuilder str = new StringBuilder();
    int count=0;
    List<String> finder =  new ArrayList<>(Arrays.asList("can"));
    URL file_url = new URL(file);
    BufferedReader bf = new BufferedReader(new InputStreamReader(file_url.openStream()));
    String st;
    System.out.println("Available thread "+ Runtime.getRuntime().availableProcessors());
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    try {
      while((st = bf.readLine()) != null) {
        each_line = st;

        str.append(each_line).append('\n');
        count++;
        if(count%lines==0) {
          String overall_string = str.toString();
          str.setLength(0);
          Future<Map<String, Set<OffsetData>>> match = executorService.submit(new Matchers(overall_string, finder));
          //matchers = Matchers.match(overall_string, finder);

            matchers.add(match);
        }
      }
      executorService.shutdown();
      Aggregators.aggregatePrinter(matchers);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      bf.close();

    }

    long endTime = System.currentTimeMillis();
    System.out.println("Start time "+ endTime);
    long totalTime = endTime-startTime;
    System.out.println("total time taken  "+ totalTime);

  }
}
