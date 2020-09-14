package com.person.bean;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private int id;
    private String title;
    private List<TreeNode> children=new ArrayList<>();
    private boolean spread; //节点是否初始展开，默认 false
    private boolean checked;

    public TreeNode() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", children=" + children +
                ", spread=" + spread +
                ", checked=" + checked +
                '}';
    }
}
