import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats{
public static void main(String[] args) {
final int n = 20;
        final int t = 20;
        double time = 0;
  Stopwatch timer = new Stopwatch();
            PercolationStats stats = new PercolationStats(n, t);
            time = timer.elapsedTime();
            StdOut.println("mean                    = " + stats.mean());
            StdOut.println("stddev                  = " + stats.stddev());
            StdOut.println("95% confidence interval = " + "[" + stats.confidenceLo() + ", " + stats.confidenceHi() +"]");
            StdOut.println("time : " + time);
  }
}
