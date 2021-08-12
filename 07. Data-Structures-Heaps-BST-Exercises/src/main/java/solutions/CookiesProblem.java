package solutions;

import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {
    public Integer solve(int k, int[] cookies) {

        Queue<Integer> queue = new PriorityQueue<>();
        if (cookies.length == 0)
            return -1;

        for (int cookie : cookies)
            queue.offer(cookie);

        int count = 0;

        while (queue.size() > 1 && queue.peek() < k ){
            int least = queue.poll();
            int least2 = queue.poll();

            queue.add(least + 2 * least2);

            count++;
        }
        return queue.peek() > k ? count : -1;
    }
}
