import parcs.AM;
import parcs.AMInfo;

import java.util.List;

public class VectorMutiple implements AM {
    public int run_test(List<List<Integer>> vector_list){
        int res = 0;
        int i = 0;
        for (Integer v_1: vector_list.get(0))
        {
            res += v_1 * vector_list.get(1).get(i);
            i++;
        }
        return res;
    }

    public void run(AMInfo info){
        Pair pair = (Pair) info.parent.readObject();
        int res = 0;
        int i = 0;
        List<Integer> v_2 = (List<Integer>)pair.getMatrix1();

        for (Integer v_1: (List<Integer>)pair.getMatrix1())
        {
            res += v_1 * v_2.get(i);
            i++;
        }
        info.parent.write(res);
    }

}
