package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class App
{
    public static void main(String[] args) 
    {
        var tower1 = new Stack<Integer>();
        tower1.addAll(Arrays.asList(2, 1));

        var tower2 = new Stack<Integer>();
        var tower3 = new Stack<Integer>();
        solve(tower1, tower2, tower3, tower1.size());
        System.out.println(tower2);
        // System.out.println(tower3);
    }

    static Random rng = new Random();

    static void solve(Stack<Integer> from, Stack<Integer> to, Stack<Integer> spare, int toMove)
    {
        if (toMove == 1)
        {
            to.add(from.pop());
            return;
        }
        solve(from, spare, to, toMove-1);
        to.add(from.pop());
        solve(spare, to, from, toMove-1);
    }

    // Stack overflows half the time lmfao
    static void solveBogus(Stack<Integer> tower1, Stack<Integer> tower2, Stack<Integer> tower3, int initalSize)
    {
        if (tower2.size() == initalSize)
            return;

        var peeks = new int[] {
            tower1.isEmpty() ? Integer.MAX_VALUE : tower1.peek(),
            tower2.isEmpty() ? Integer.MAX_VALUE : tower2.peek(),
            tower3.isEmpty() ? Integer.MAX_VALUE : tower3.peek()
        };

        var towers = Arrays.asList(tower1, tower2, tower3);

        var validX = new ArrayList<Integer>();
        var validY = new ArrayList<Integer>();

        for (var i = 0; i < 3; ++i)
            for (var j = 0; j < 3; ++j)
                if (peeks[i] < peeks[j])
                {
                    validX.add(i);
                    validY.add(j);
                }

        var getMove = rng.nextInt(validX.size());
        towers.get(validY.get(getMove)).push(towers.get(validX.get(getMove)).pop());

        solveBogus(tower1, tower2, tower3, initalSize);
    }
}
