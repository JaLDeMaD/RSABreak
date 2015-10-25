import breaker.Pollard;
import rsa.RSA;
import rsa.Status;
import rsa.key.PublicKey;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
  public static void main(String [] a) {
    Logger.getLogger("Main").setLevel(Level.INFO);

    loop(64, 104, 8, 3);
    loop(108, 128, 8, 1);

  }

  public static void loop(int start, int end, int step, int times) {
    for(int length = start; length <= end; length+= step) {
      System.out.println("  RSA key of : "+ length +" bits length:    ");
      Status[] statuses = new Status[times];
      for(int time = 0; time < times; time++) {
        PublicKey pub = RSA.makePrivateKey(length).extractPublic();
        statuses[time] = Pollard.breakKey(pub);
        System.out.println(String.format("\t[%d]: %s",time + 1,  statuses[time]));

      }
      if(times > 2) {
        System.out.println(
                String.format("\tAverage:  %s\n",
                        Status.avrageToString(statuses))
        );
      }
    }
  }
}
