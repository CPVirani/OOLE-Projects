package JavaGenerator;

import java.util.ArrayList;
import java.util.List;

public class NodeGen<T>{
    private T data = null;
    private List<NodeGen> children = new ArrayList<>();
    private NodeGen parent = null;

    public NodeGen(T data) {
        this.data = data;
    }


    public void addChild(T data) {
        NodeGen<T> newChild = new NodeGen<>(data);
        newChild.setParent(this);
        children.add(newChild);
    }


    public List<NodeGen> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }



    private void setParent(NodeGen parent) {
        this.parent = parent;
    }}