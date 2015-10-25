package breaker;

import rsa.Status;
import rsa.key.PublicKey;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

public class Pollard {
    private static Logger logger = Logger.getLogger(Pollard.class.getName());

    public static Status breakKey(PublicKey k) {
        Instant ins = Instant.now();
        long counter = 0;

        BigInteger n = k.getN(),
                x = new BigInteger("2"),
                y = x,

        d = BigInteger.ONE;
        while (d.compareTo(BigInteger.ONE) == 0) {
            x = g(x, n);
            y = g(g(y, n), n);
            d = n.gcd(x.subtract(y).abs());
            logger.config(String.format("\tx= %s\n\ty= %s\n\td = %s",
                    x, y, d));
            counter++;
        }
        if (d.compareTo(n) == 0)
            logger.config("\t  [!] d equals n, try again... ");
        return new Status(counter, Duration.between(ins, Instant.now()), n.compareTo(d) != 0);
    }

    public static BigInteger g(BigInteger x, BigInteger n) {
        return x.pow(2).add(BigInteger.ONE).mod(n);
    }
}
