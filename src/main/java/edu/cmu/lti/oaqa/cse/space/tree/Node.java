package edu.cmu.lti.oaqa.cse.space.tree;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Node<T> implements Visitable{

    public T data;
    public List<Node<T>> children;

    public Node() {
        super();
        children = new ArrayList<Node<T>>();
    }

    public Node(T data) {
        this();
        setData(data);
    }

    public List<Node<T>> getChildren() {
        return this.children;
    }

    public int getNumberOfChildren() {
        return getChildren().size();
    }

    public boolean hasChildren() {
        return (getNumberOfChildren() > 0);
    }

    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }

    public void addChild(Node<T> child) {
        children.add(child);
    }

    public void addChildAt(int index, Node<T> child) throws IndexOutOfBoundsException {
        children.add(index, child);
    }

    public void removeChildren() {
        this.children = new ArrayList<Node<T>>();
    }

    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

    public Node<T> getChildAt(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    public T getElement() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return getElement().toString();
    }

    public boolean equals(Node<T> node) {
        return node.getElement().equals(getElement());
    }

    public int hashCode() {
        return getElement().hashCode();
    }

    public String toStringVerbose() {
        String stringRepresentation = getElement().toString() + ":[";

        for (Node<T> node : getChildren()) {
            stringRepresentation += node.getElement().toString() + ", ";
        }

        //Pattern.DOTALL causes ^ and $ to match. Otherwise it won't. It's retarded.
        Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringRepresentation);

        stringRepresentation = matcher.replaceFirst("");
        stringRepresentation += "]";

        return stringRepresentation;
    }

	@Override
	public void accept(NodeVisitor visitor) {
		visitor.visit(this);
	}
}