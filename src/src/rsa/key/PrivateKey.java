package rsa.key;


import rsa.RSA;

import java.math.BigInteger;
import java.util.logging.Logger;

/**
 * Created by User on 25/10/2015.
 */
public class PrivateKey extends PublicKey {
    private static Logger Log = Logger.getLogger("PrivateKey");
    private BigInteger p, q, d, phi;

    public PrivateKey(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
    }

    public PrivateKey(BigInteger n, BigInteger p,
                      BigInteger q, BigInteger phi, BigInteger e, BigInteger d) {
        super(n, e);
        this.p = p;
        this.q = q;
        this.phi = phi;
        this.d = d;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public void setPhi(BigInteger phi) {
        this.phi = phi;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getQ() {
        return this.q;
    }

    public BigInteger getPhi() {
        return this.phi;
    }

    public BigInteger getD() {
        return this.d;
    }

    public PrivateKey(int length) {

        this.p = BigInteger.probablePrime(length/2, RSA.random);
        this.q = BigInteger.probablePrime(length/2, RSA.random);

        this.n = this.p.multiply(this.q);
        this.phi =
                this.p.subtract(BigInteger.ONE).multiply(this.q.subtract(BigInteger.ONE));

        this.e = new BigInteger("65537");
        while (this.e.gcd(this.phi).compareTo(BigInteger.ONE) != 0) {
            Log.info("gcd(e, phi) != 1, choosing another value for e ...");
            this.e = BigInteger.probablePrime(17, RSA.random);
        }
        this.d = this.e.modInverse(this.phi);
        Log.config(this.toString());
    }

    @Override
    public String toString() {
        return String.format(
                "\t\t\t  [RSA Key of length %d]\np = \n%s\n\nq = \n%s\n\nn = \n%s\n\nphi = \n%s\n\ne = %s\n\nd = \n%s",
                this.n.bitLength(), indent(this.p), indent(this.q), indent(this.n), indent(this.phi), this.e, indent(this.d)
        );
    }

    public PublicKey extractPublic() {
        return new PublicKey(this.n, this.e);
    }

    public static String indent(BigInteger n, int length) {
        String s = n.toString();
        StringBuilder sb = new StringBuilder(s.length() + s.length() % length * 5 + 4);
        int iteration = s.length() / length;
        for (int i = 0; i < iteration; i++) {
            sb.append("    " + s.substring(i * length, (i + 1) * length) + "\n");
        }
        sb.append("    " + s.substring(iteration * length, s.length()));
        return sb.toString();
    }

    public static String indent(BigInteger n) {
        return indent(n, 50);
    }
}