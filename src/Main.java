import org.omg.PortableInterceptor.INACTIVE;
import parcs.AMInfo;
import parcs.channel;
import parcs.point;
import parcs.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static List<List<Integer>> fromFileFirst(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));

        int w = sc.nextInt();
        int h = sc.nextInt();
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < w; j++) {
                int num = sc.nextInt();
                row.add(num);
            }
            matrix.add(row);
        }
        return matrix;
    }

    public static List<List<Integer>> fromFileSecond(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));

        int w = sc.nextInt();
        int h = sc.nextInt();
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            matrix.add(new ArrayList<>());
        }
        for (int i = 0; i < h; i++) {
            for (List<Integer> col: matrix) {
                int num = sc.nextInt();
                col.add(num);
            }
        }

        return matrix;
    }

    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("VectorMutiple.jar");
        Scanner sc = new Scanner(new File("input"));

        List<List<Integer>> matrix1 = fromFileFirst(sc.nextLine());
        List<List<Integer>> matrix2 = fromFileSecond(sc.nextLine());

        Pair pair_of_matrix = new Pair(matrix1, matrix2);
        AMInfo info = new AMInfo(curtask, null);

        (new Main()).run(info, pair_of_matrix);
        curtask.end();
    }

    public void run(Pair pair_of_matrix) {
        List<List<Integer>> res = new ArrayList<>();

        for (List<Integer> row: (List<List<Integer>>)pair_of_matrix.getMatrix1())
        {
            List<Integer> new_row = new ArrayList<>();
            for (List<Integer> col: (List<List<Integer>>)pair_of_matrix.getMatrix2())
            {
                List<List<Integer>> inp = Arrays.asList(row, col);
                int r = (new VectorMutiple()).run_test(inp);
                new_row.add(r);
            }
            res.add(new_row);
        }

        try{
            int h = res.size();
            int w = res.get(0).size();

            String name = String.format("M_%d_%d", w, h);
            PrintWriter out = new PrintWriter(new FileWriter(name));

            out.println(String.format("%d %d", w, h));
            for (List<Integer> row: res) {
                for (Integer num : row) {
                    out.print(num);
                    out.print(" ");
                }
                out.println();
            }
            out.close();
        } catch (IOException e) {e.printStackTrace(); return;}
    }

    public void run(AMInfo info, Pair pair_of_matrix) {

        List<point> points = new ArrayList<>();
        List<List<channel>> chans = new ArrayList<>();
        for (List<Integer> row: (List<List<Integer>>)pair_of_matrix.getMatrix1())
        {
            List<channel> new_row = new ArrayList<>();
            for (List<Integer> col: (List<List<Integer>>)pair_of_matrix.getMatrix1())
            {
                Pair inp = new Pair(row, col);
                point p = info.createPoint();
                channel c = p.createChannel();
                p.execute("VectorMutiple");
                c.write(inp);
                new_row.add(c);
                points.add(p);
            }
            chans.add(new_row);
        }

        try{
            int h = chans.size();
            int w = chans.get(0).size();

            String name = String.format("M_%d_%d", w, h);
            PrintWriter out = new PrintWriter(new FileWriter(name));

            out.println(String.format("%d %d", w, h));
            for (List<channel> row: chans) {
                for (channel c : row) {
                    out.print(c.readLong());
                    out.print(" ");
                }
                out.println();
            }
            out.close();
        } catch (IOException e) {e.printStackTrace(); return;}
    }
}
