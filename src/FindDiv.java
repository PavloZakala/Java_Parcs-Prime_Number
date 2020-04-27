import parcs.AM;
import parcs.AMInfo;
import parcs.channel;
import parcs.point;

import java.util.ArrayList;
import java.util.List;

public class FindDiv implements AM {

    public long run_test(Range range){

        long a = range.getA();
        long b = range.getB();
        long res = 1;

        if (range.getNext() != null)
        {
            res = run_test(range.getNext());
        }

        for (long n = a; n < b; n++)
        {
            if (is_prime(n)) {
                if (res < n)
                    res = n;
            }
        }
        return res;
    }

    public void run(AMInfo info){
        Range range = (Range) info.parent.readObject();

        long a = range.getA();
        long b = range.getB();
        long res = 1;

        point p = null;
        channel c = null;

        long N = range.getN();

        if (range.getNext() != null)
        {
            System.out.println("NOT NULL");
            p = info.createPoint();
            c = p.createChannel();

            p.execute("FindDiv");
            c.write(range.getNext());
        }
        System.out.println("FOR");

        for (long n = a; n < b; n++)
        {
            if (is_prime(n)) {
                if (res < n)
                    res = n;
            }
        }
        System.out.println("Range (" + range.getA() + ", " +  range.getB() + ")" + res);
        if (range.getNext() != null) {
            res = Math.max(c.readLong(), res);
        }
        info.parent.write(res);
        System.out.println("END");
    }

    public boolean is_prime(long num)
    {
        for (int i = 2; i < Math.sqrt(num) + 1; i++)
        {
            if (num % i == 0)
                return false;
        }

        return true;
    }
}
