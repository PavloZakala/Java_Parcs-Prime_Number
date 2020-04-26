import parcs.AM;
import parcs.AMInfo;
import parcs.channel;
import parcs.point;

import java.util.List;

public class VectorMutiple implements AM {

    public int run_test(Pair pair){
        int res = 0;
        int i = 0;

        if (pair.getNext() != null) {
            run_test(pair.getNext());
        }
        List<Integer> v_2 = (List<Integer>)pair.getMatrix1();

        for (Integer v_1: (List<Integer>)pair.getMatrix2())
        {
            res += v_1 * v_2.get(i);
            i++;
        }
        pair.setRes(res);
        return res;
    }

    public void run(AMInfo info){
        Pair pair = (Pair) info.parent.readObject();
        int res = 0;
        int i = 0;
        point p = info.createPoint();
        channel c = p.createChannel();

        if (pair.getNext() != null) {
            System.out.println("null");

            p.execute("VectorMutiple");
            c.write(pair.getNext());
        }
        List<Integer> v_2 = (List<Integer>)pair.getMatrix1();

        for (Integer v_1: (List<Integer>)pair.getMatrix1())
        {
            res += v_1 * v_2.get(i);
            i++;
        }
        pair.setRes(res);
        if (pair.getNext() != null) {
            c.readLong();
        }
        info.parent.write(res);

    }

}
