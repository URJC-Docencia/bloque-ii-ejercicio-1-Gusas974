import material.Position;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * This class represents a tree data structure using a linked implementation.
 * It implements the NAryTree interface.
 *
 * @param <E> the type of element stored in the tree
 */
public class LinkedTree<E> implements NAryTree<E> {

    /**
     * This class represents a node in a tree data structure.
     * It implements the Position interface.
     *
     * @param <T> the type of element stored in the node
     */
    private class TreeNode<T> implements Position<T> {
        private List<TreeNode<T>>children;
        private T element;
        private TreeNode<T> parent;
        private List<TreeNode> children=new ArrayList<>();
        public TreeNode(T element){
            this.element = element;
        }

        public TreeNode(T element, TreeNode<T> parent) {
            this.element = element;
            this.parent = parent;
        }

        public List<TreeNode<T>> getChildren() {
            return children;
        }

        public TreeNode<T> getParent() {
            return parent;
        }

        @Override
        public T getElement() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
    private TreeNode<E> root;
    private int size;

    @Override
    public Position<E> addRoot(E e){
        if (!isEmpty()){
            throw new RuntimeException("Ya existe raiz");
        }
        TreeNode<E> nodo=new TreeNode<>(e);
        size++;
        return nodo;
    }
    private TreeNode<E> checkPosition(Position<E> p){
        if(!(p instanceof TreeNode)){
            throw new RuntimeException("Posicion invalida");
        }
        return (TreeNode<E>)p;
    }
    @Override
    public Position<E> add(E element, Position<E> p) {
         TreeNode<E> parent=checkPosition(p);
         TreeNode<E> newNode=new TreeNode<>(element, parent);
         parent.getChildren().add(newNode);
         size++;
         return newNode;
    }

    @Override
    public Position<E> add(E element, Position<E> p, int n) {
        TreeNode<E> parent=checkPosition(p);
        TreeNode<E> newNode=new TreeNode<>(element, parent);
        parent.getChildren().add(newNode);
        size++;
        return newNode;;
    }

    private static <E> void checkPositionOfChildrenList(int n,LinkedTree<E>,TreeNode<E> parent){
        if (n < 0 || n> parent.getChildren().size()){
            throw new RuntimeException("");
        }
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        TreeNode<E> node1=checkPosition(p1);
        TreeNode<E> node2=checkPosition(p2);
        E aux= node1.getElement();
        node1.element=node2.getElement();
        node2.element=node1.getElement();
    }

    @Override
    public E replace(Position<E> p, E e) {
        TreeNode<E> node= checkPosition(p);
        E old=node.getElement();
        node.element=e;
        return old;
    }

    @Override
    public void remove(Position<E> p) {
        TreeNode<E> node= checkPosition(p);
        if (node==root){
            root=null;
            size=0;
        }else{
            TreeNode<E> parent =node.getParent();
            parent.getChildren().remove(node);
            size-=computeSize(node);


        }
    }
    private int computeSize(TreeNode<E> node){
        int size=1;
        for(TreeNode<E> child: node.getChildren()){
            size+=computeSize(child);
        }
        return size;

    }


    @Override
    public NAryTree<E> subTree(Position<E> v) {
        TreeNode<E> node= checkPosition(v);
        LinkedTree<E> tree= new LinkedTree<>();
        tree.root=node;
        tree.size=computeSize(node);
        return tree;
    }

    @Override
    public void attach(Position<E> p, NAryTree<E> t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        return size=0;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isInternal(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isRoot(Position<E> v) {
        ;
    }

    @Override
    public Iterator<Position<E>> iterator() {
      //comprobar si esta vacio el arbol;
        List<Position<E>> positions= new ArrayList<>();
        breadthOrder(root,positions);
        return positions.iterator();

    }
    private void breadthOrder(TreeNode<E> root, List<Position<E>> positions){
        if (root !=null){
            List<TreeNode>queue=new ArrayList<>();
            queue.add(node);
            while(queue.isEmpty()){
                TreeNode<E> toExplore = queue.remove(index:0);
                positions.add(toExplore);
                queue.addAll(node.getChildren());
            }
        }
    }

    public int size() {
        return size;
    }

}
