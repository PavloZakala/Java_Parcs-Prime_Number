import parcs.AM;
import parcs.AMInfo;
import parcs.channel;
import parcs.point;

public class FindDiv implements AM {

    public long run_test(Range range){

        long a = range.getA();
        long b = range.getB();
        long res = -42;

        long N = range.getN();
        if (range.getNext() != null)
        {
            res = run_test(range.getNext());
        }
        for (long n = a; n < b; n++)
        {
            if (N % n == 0) {
                res = n;
                range.setRes(n);
            }
        }
        return res;
    }

    public void run(AMInfo info){
        Range range = (Range) info.parent.readObject();

        long a = range.getA();
        long b = range.getB();
        long res = -42;

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

        for (long n = a; n < b; n++)
        {
            if (N % n == 0)
                res = n;
            range.setRes(n);
        }

        info.parent.write(res);
        System.out.println("END");
    }
}
