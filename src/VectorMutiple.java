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
        List<Integer> v_2 = pair.getVector1();

        for (Integer v_1: pair.getVector2())
        {
            res += v_1 * v_2.get(i);
            i++;
        }
        pair.setRes(res);
        return res;
    }

    public void run(AMInfo info){
        Pair pair = (Pair) info.parent.readObject();
        long res = 0;
        int i = 0;

        point p = null;
        channel c = null;

        if (pair.getNext() != null) {
            System.out.println("Deeper");

            p = info.createPoint();
            c = p.createChannel();

            p.execute("VectorMutiple");
            c.write(pair.getNext());
        }

        System.out.println("Find sum");
        List<Integer> v_2 = pair.getVector1();

        for (Integer v_1: pair.getVector2())
        {
            res += v_1 * v_2.get(i);
            i++;
        }
        if (pair.getNext() != null) {
            System.out.println("readLoad() " + c.readLong());
        }
        System.out.println("Write " + res);
        pair.setRes(res);
        System.out.println("Return " + res);
        info.parent.write(res);
        System.out.println("End");
    }

}
