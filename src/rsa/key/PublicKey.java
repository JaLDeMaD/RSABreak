package rsa.key;

import java.math.BigInteger;

public class PublicKey {
    protected BigInteger n, e;

    public PublicKey() {

    }

    public PublicKey(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public BigInteger getE() {
        return this.e;
    }

    public BigInteger getN() {
        return this.n;
    }
}