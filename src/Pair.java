import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Pair implements Serializable {

    private List matrix1;
    private List matrix2;

    private int res;
    private Pair next;

    public Pair(List matrix1, List matrix2) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.res = -42;
        this.next = null;
    }

    public List getMatrix1() {
        return matrix1;
    }

    public List getMatrix2() {
        return matrix2;
    }

    public Pair getNext() {
        return next;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public void setNext(Pair next) {
        this.next = next;
    }
}
