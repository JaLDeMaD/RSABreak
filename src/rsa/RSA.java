package rsa;

import rsa.key.PrivateKey;

import java.security.SecureRandom;
import java.util.logging.Logger;

final public class RSA {
    public static SecureRandom random;
    private static Logger logger = Logger.getLogger(RSA.class.getName());
    static {
        random = new SecureRandom();
        //logger.setLevel(Level.OFF);
    }

    public static PrivateKey makePrivateKey(int length) {
        return new PrivateKey(length);
    }

		public static BigInteger encrypt(PublicKey k, BigInteger m) {
        return m.modPow(k.getE(), k.getN());
    }

    public static BigInteger encrypt(PrivateKey k, BigInteger c) {
        return c.modPow(k.getD(), k.getN());
    }
}
