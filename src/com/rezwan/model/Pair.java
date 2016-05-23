package com.rezwan.model;

/**
 * A simple model that returns a pair of something.
 */
public class Pair<X> {
    private X begin;
    private X end;

    public Pair(X begin, X end) {
        this.begin = begin;
        this.end = end;
    }

    public X getEnd() {
        return end;
    }

    public void setEnd(X end) {
        this.end = end;
    }

    public X getBegin() {
        return begin;
    }

    public void setBegin(X begin) {
        this.begin = begin;
    }

    @Override
    public boolean equals(Object obj) {
        Pair other = (Pair) obj;
        return (this.begin.equals(other.getBegin()) &&
                this.end.equals(other.getEnd())
        );
    }

    @Override
    public String toString() {
        return "begin: " + begin + " end:" + end;
    }
}