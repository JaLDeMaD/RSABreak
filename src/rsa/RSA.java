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


}
