import java.io.Serializable;

public class Range implements Serializable {
    long a, b;
    long N;
    Range next;

    public Range(long N, long a, long b)
    {
        this.a = a;
        this.b = b;
        this.N = N;
        this.next = null;
    }

    public long getA() {
        return a;
    }

    public long getB() {
        return b;
    }

    public Range getNext() {
        return next;
    }

    public long getN() {
        return N;
    }

    public void setNext(Range next) {
        this.next = next;
    }
}
