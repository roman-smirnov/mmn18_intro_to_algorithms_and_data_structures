package com.company.miscellaneous;

/**
 * Created by Сергей on 18.02.2017.
 */
public class Pair<L, R> {
    private final L left;
    private final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }
    public R getRight() {
        return right;
    }
    public String toString() {
        return ("Pair[" + left + ", " + right + "]");
    }
}