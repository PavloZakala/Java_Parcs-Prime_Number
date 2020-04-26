import parcs.AM;
import parcs.AMInfo;
import parcs.channel;
import parcs.point;

import java.util.ArrayList;
import java.util.List;

public class VectorMutiple implements AM {

    public long run_test(Pair pair){
        int res = 0;
        int i = 0;

        if (pair.getNext() != null) {
            run_test(pair.getNext());
        }

        List<List<Integer>> m_1 = pair.getVector1();
        List<List<Integer>> m_2 = pair.getVector2();

        List<List<Integer>> res_matrix = pair.getRes();

        for (List<Integer> v_1 : m_1) {
            List<Integer> res_row = new ArrayList<>();
            for (List<Integer> v_2 : m_2) {
                res = 0;
                i = 0;
                for (int p_1: v_1)
                {
                    res += p_1 * v_2.get(i);
                    i++;
                }
                res_row.add(res);
            }
            res_matrix.add(res_row);
        }

        pair.setRes(res_matrix);
        return res;
    }

    public void run(AMInfo info){
        Pair pair = (Pair) info.parent.readObject();
        System.out.println("->" +  pair);
        long res = 0;
        int i = 0;

        point p = null;
        channel c = null;

        if (pair.getNext() != null) {
            System.out.println("NULL");
            p = info.createPoint();
            c = p.createChannel();

            p.execute("VectorMutiple");
            c.write(pair.getNext());
        }

        List<List<Integer>> m_1 = pair.getVector1();
        List<List<Integer>> m_2 = pair.getVector2();

        List<List<Integer>> res_matrix = pair.getRes();

        int k = 0;
        for (List<Integer> v_1 : m_1) {
            List<Integer> res_row = res_matrix.get(k);
            k++;
            System.out.println("Add row");
            for (List<Integer> v_2 : m_2) {
                res = 0;
                i = 0;
                for (int p_1 : v_1) {
                    res += p_1 * v_2.get(i);
                    i++;
                }
                res_row.add((int) res);
            }
            res_matrix.add(res_row);
            System.out.println("Row size: " + res_row.size());
        }

        if (pair.getNext() != null) {
            c.readLong();
        }
        System.out.println(res_matrix.size() +""+ res_matrix.get(0).size());
        pair.setRes(res_matrix);
        info.parent.write(res);
        System.out.println("END");
    }

}
