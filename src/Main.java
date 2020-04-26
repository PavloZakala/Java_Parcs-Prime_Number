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
import java.util.concurrent.BlockingDeque;


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
        int BLOCK_SIZE = 5;

        Scanner sc = new Scanner(new File("input"));

        List<List<List<Integer>>> pair_of_matrix = new ArrayList<>();
        pair_of_matrix.add(fromFileFirst(sc.nextLine()));
        pair_of_matrix.add(fromFileSecond(sc.nextLine()));

        task curtask = new task();
        curtask.addJarFile("VectorMutiple.jar");
        AMInfo info = new AMInfo(curtask, null);

        List<List<Pair>> res = new ArrayList<>();
        Pair start = null;
        Pair last = null;

        int size_m_1 = pair_of_matrix.get(0).size();
        int size_m_2 = pair_of_matrix.get(1).size();

        for (int i = 0; i < size_m_1; i += BLOCK_SIZE)
        {
            List<Pair> new_row = new ArrayList<>();
            for (int j = 0; j < size_m_2; j += BLOCK_SIZE)
            {
                Pair inp = new Pair(new ArrayList<>(pair_of_matrix.get(0).subList(i, Math.min(i+BLOCK_SIZE, size_m_1))),
                            new ArrayList<>(pair_of_matrix.get(1).subList(j, Math.min(j+BLOCK_SIZE, size_m_2))));
                if (start == null)
                {
                    start = inp;
                }
                if (last != null) {
                    last.setNext(inp);
                }
                last =  inp;

                new_row.add(inp);
            }
            res.add(new_row);
        }

        point p = info.createPoint();
        channel c = p.createChannel();
        p.execute("VectorMutiple");
        c.write(start);

        System.out.println("Waiting for result...");
        System.out.println("Result: " + c.readLong());
        try{

            int h = pair_of_matrix.get(0).size();
            int w = pair_of_matrix.get(1).size();

            List<List<Integer>> final_matrix = new ArrayList<>();
            for (int i = 0; i < h; i++)
                final_matrix.add(new ArrayList<>());


            int r_i = 0;
            for (List<Pair> row: res) {
                for (Pair pair : row) {
                    List<List<Integer>> submatrix = pair.getRes();
                    int i = 0;
                    System.out.println(submatrix.size());
                    for (List<Integer> r: submatrix) {
                        final_matrix.get(r_i * BLOCK_SIZE + i).addAll(r);
                        i++;
                    }
                }
                r_i++;
            }
            System.out.println(final_matrix.size());
            System.out.println(final_matrix.get(0).size());
            String name = String.format("M_%d_%d_res", w, h);
            PrintWriter out = new PrintWriter(new FileWriter(name));

            out.println(String.format("%d %d", w, h));
            for (List<Integer> row: final_matrix) {
                for (Integer num : row) {
                    out.print(num);
                    out.print(" ");
                }
                out.println();
            }
            out.close();
        } catch (IOException e) {e.printStackTrace(); return;}

//        curtask.end();
    }
//
//    public void run(List<List<List<Integer>>> pair_of_matrix) {
//        List<List<Pair>> res = new ArrayList<>();
//        Pair start = null;
//        Pair last = null;
//        int BLOCK_SIZE = 5;
//
//        int size_m_1 = pair_of_matrix.get(0).size();
//        int size_m_2 = pair_of_matrix.get(1).size();
//
//        for (int i = 0; i < size_m_1; i += BLOCK_SIZE)
//        {
//            List<Pair> new_row = new ArrayList<>();
//            for (int j = 0; j < size_m_2; j += BLOCK_SIZE)
//            {
//                Pair inp = new Pair(pair_of_matrix.get(0).subList(i, Math.min(i+BLOCK_SIZE, size_m_1)),
//                        pair_of_matrix.get(1).subList(j, Math.min(j+BLOCK_SIZE, size_m_2)));
//                if (start == null)
//                {
//                    start = inp;
//                }
//                if (last != null) {
//                    last.setNext(inp);
//                }
//                last =  inp;
//
//                new_row.add(inp);
//            }
//            res.add(new_row);
//        }
//        (new VectorMutiple()).run_test(start);
//        try{
//
//            int h = pair_of_matrix.get(0).size();
//            int w = pair_of_matrix.get(1).size();
//
//            List<List<Integer>> final_matrix = new ArrayList<>();
//            for (int i = 0; i < h; i++)
//                final_matrix.add(new ArrayList<>());
//
//
//            int r_i = 0;
//            for (List<Pair> row: res) {
//                for (Pair pair : row) {
//                    List<List<Integer>> submatrix = pair.getRes();
//                    int i = 0;
//                    for (List<Integer> r: submatrix) {
//                        final_matrix.get(r_i * BLOCK_SIZE + i).addAll(r);
//                        i++;
//                    }
//                }
//                r_i++;
//            }
//
//            String name = String.format("M_%d_%d", w, h);
//            PrintWriter out = new PrintWriter(new FileWriter(name));
//
//            out.println(String.format("%d %d", w, h));
//            for (List<Integer> row: final_matrix) {
//                for (Integer num : row) {
//                    out.print(num);
//                    out.print(" ");
//                }
//                out.println();
//            }
//            out.close();
//        } catch (IOException e) {e.printStackTrace(); return;}
//    }

}
