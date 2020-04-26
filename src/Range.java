import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Range implements Serializable {
    long a, b;
    long N;
    int i;
    long res[];
    Range next;

    public Range(long N, long a, long b)
    {
        this.a = a;
        this.b = b;
        this.N = N;
        this.i = 0;
        this.res = new long[100];
        this.next = null;
    }

    public void setRes(long num) {
        this.res[i] = num;
        this.i++;
    }

    public List<Long> getRes() {
        List<Long> list_res = new ArrayList<>();

        for (int j = 0; j < i; j++)
        {
            list_res.add(this.res[j]);
        }
        return list_res;
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
