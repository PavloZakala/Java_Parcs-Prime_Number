import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Pair implements Serializable {

    private List<Integer> vector1;
    private List<Integer> vector2;

    private long res;
    private Pair next;

    public Pair(List<Integer> vector1, List<Integer> vector2) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.res = -42;
        this.next = null;
    }

    public List<Integer> getVector1() {
        return vector1;
    }

    public List<Integer> getVector2() {
        return vector2;
    }

    public Pair getNext() {
        return next;
    }

    public long getRes() {
        return res;
    }

    public void setRes(long res) {
        this.res = res;
    }

    public void setNext(Pair next) {
        this.next = next;
    }
}
