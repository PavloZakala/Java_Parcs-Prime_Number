import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Pair implements Serializable {

    private List<List<Integer>> vector1;
    private List<List<Integer>> vector2;

    private List<List<Integer>> res;
    private Pair next;

    public Pair(List<List<Integer>> vector1, List<List<Integer>> vector2) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.res = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            this.res.add(new ArrayList<>());
        this.next = null;
    }

    public List<List<Integer>> getVector1() {
        return vector1;
    }

    public List<List<Integer>> getVector2() {
        return vector2;
    }

    public Pair getNext() {
        return next;
    }

    public List<List<Integer>> getRes() {
        return res;
    }

    public void setRes(List<List<Integer>> res) {
        this.res = res;
    }

    public void setNext(Pair next) {
        this.next = next;
    }
}
