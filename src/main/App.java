package main;

import java.util.Arrays;
import java.util.Stack;

public class App
{
    public static void main(String[] args) 
    {
        var tower1 = new Stack<Integer>();
        tower1.addAll(Arrays.asList(5, 4, 3, 2, 1));
        var tower2 = new Stack<Integer>();
        var tower3 = new Stack<Integer>();
        solve(tower1, tower2, tower3, tower1.size());
        System.out.println(tower2);
        System.out.println(tower3);
    }

    static void solve(Stack<Integer> tower1, Stack<Integer> tower2, Stack<Integer> tower3, int initalSize)
    {
        if (tower2.size() == initalSize || tower3.size() == initalSize)
            return;
        var peek1 = tower1.peek();
        var peek2 = tower2.peek();
        var peek3 = tower3.peek();
        if (peek1 != null)
        {
            if (peek2 == null)
            {
                tower2.add(tower1.pop());
                solve(tower1, tower2, tower3, initalSize);
            }
            else if (peek2 > peek1)
            {
                tower2.add(tower1.pop());
                solve(tower1, tower2, tower3, initalSize);
            }
        }
        if (peek2 != null)
        {
            if (peek3 == null)
            {
                tower3.add(tower2.pop());
                solve(tower1, tower2, tower3, initalSize);
            }
            else if (peek3 > peek2)
            {
                tower3.add(tower2.pop());
                solve(tower1, tower2, tower3, initalSize);
            }
        }
        if (peek3 != null)
        {
            if (peek1 == null)
            {
                tower2.add(tower1.pop());
                solve(tower1, tower2, tower3, initalSize);
            }
            else if (peek1 > peek3)
            {
                tower1.add(tower3.pop());
                solve(tower1, tower2, tower3, initalSize);
            }
        }
    }

}
