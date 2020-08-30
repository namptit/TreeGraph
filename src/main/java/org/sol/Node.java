package org.sol;

import java.util.List;

public class Node {

    private int id;
    private String name;
    private List<Integer> children;
    private int parent;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getChildren() {
        return children;
    }

    public int getParent() {
        return parent;
    }
}
