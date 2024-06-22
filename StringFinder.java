import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StringFinder {
  public static final String file = "https://norvig.com/big.txt";
  public static void main(String[] args) throws IOException {
    long startTime = System.currentTimeMillis();
    System.out.println("Start time "+ startTime);
    int lines = 1000;

    String each_line;
    StringBuilder str = new StringBuilder();
    int count=0;
    List<String> finder =  new ArrayList<>(Arrays.asList("john"));
    URL file_url = new URL(file);
    BufferedReader bf = new BufferedReader(new InputStreamReader(file_url.openStream()));
    String st;
    try {
      while((st = bf.readLine()) != null) {
        each_line = st;

        str.append(each_line).append('\n');
        count++;
        if(count%lines==0) {
          String overall_string = str.toString();
          Map<String,List<OffsetData>> matches = Matchers.match(overall_string, finder);
          Aggregators.aggregatePrinter(matches);

        }
      }
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
