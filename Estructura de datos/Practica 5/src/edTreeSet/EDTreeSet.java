package edTreeSet;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.Iterator;


public class EDTreeSet<E extends Comparable <E>> implements Set<E>{

    protected class BinaryNode {
        protected E data;
        protected BinaryNode left;
        protected BinaryNode right;
        
        BinaryNode(E data){
            this.data = data;
        }
        BinaryNode(E data, BinaryNode lnode, BinaryNode rnode) {
            this.data = data;
            this.left = lnode;
            this.right = rnode;
        }
    }
    
    private BinaryNode root;
    private Comparator<? super E> comparator;
    private int size; //number of elements
    protected boolean insertReturn; //Return value for the public insert method
    protected E removeReturn; //Return value for the public remove method

    

    public EDTreeSet() {
        root = null;
        comparator = null;
        size = 0;
    }
    
    public EDTreeSet(Comparator<? super E> comp) {
        root = null;
        comparator = comp;
        size = 0;
    }
    
    public EDTreeSet(Collection<? extends E> c) {
        this();
        addAll(c);
    }
    
    public EDTreeSet(SortedSet<E> s) {
        this(s.comparator());
        addAll(s);
    }
    
    private int compare(E left, E right) {
        if (comparator != null) { //A comparator is defined
            return comparator.compare(left,right);
        }
        else {
            return (((Comparable<E>) left).compareTo(right));
        }
    }
    
    
    public E first() {
        BinaryNode aux = root,data = null;
        while (aux != null ) {
            data = aux;
            aux = aux.left;
        }
        return data.data;
    }
    
    public E last() {
        BinaryNode aux = root, data = null;
        while (aux != null) {
            data = aux;
            aux = aux.right;
        }
        return data.data;
    }

    @Override
    public boolean add(E item) {
        if (size == 0) {
            root = new BinaryNode(item);
            size++;
            return true;
        }
        return add(root, item);
    }

    private boolean add(BinaryNode n, E item) {
        if (n.left == null) {
            if (compare(n.data, item) > 0) {
                n.left = new BinaryNode(item);
                size++;
                return true;
            }
        }
        if (n.right == null) {
            if (compare(n.data, item) < 0) {
                n.right = new BinaryNode(item);
                size++;
                return true;
            }
        }

        if (compare(n.data, item) == 0)
            return false;
        if (compare(n.data, item) > 0)
            return add(n.left, item);
        else
            return add(n.right, item);
    }


    @Override
    public boolean addAll(Collection<? extends E> arg0) {
        if (arg0==null) throw new NullPointerException();
        boolean changed = false;
        for (E e: arg0) {
            boolean res=add(e);
            if (!changed && res) changed = true;
        }
        return (changed);
    }

    @Override
    public void clear() {
        root = null; 
        size = 0;
    }

    @Override
    public boolean contains(Object arg0) {
        if (contains(root, (E) arg0) == null)
            return false;
        return true;
    }

    private BinaryNode contains(BinaryNode n, E item) {
        if (compare(n.data, item) == 0)
            return n;

        if (compare(n.data, item) > 0) {
            if (n.left != null) {
                return contains(n.left, item);
            }
        }
        if (compare(n.data, item) < 0) {
            if (n.right != null)
                return contains(n.right, item);
        }
        return null;
    }


    @Override
    public boolean containsAll(Collection<?> arg0) {
        if (arg0 == null) throw new NullPointerException();
        boolean cont=true;
        Iterator<?> it=arg0.iterator();
        while (it.hasNext() && cont) {
            cont = contains(it.next());
        }
        return cont;
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public boolean remove(Object arg0) {
        //TODO
        int old_size = this.size;
        if(this.contains(arg0)){
            System.out.println("Existe" + arg0);
        }
        System.out.println("Borrando" + arg0);
        remove((BinaryNode) this.root,(E)arg0);
        return this.size()<old_size?true:false;
    }

    private BinaryNode remove (BinaryNode n, E item){
        if(n == null){
            return n;
        }
        switch (this.compare(item,n.data)){
            case 1: n.right = remove(n.right,item);break;
            case -1:  n.left = remove(n.left,item);break;
            case 0:
                if(n.left == null && n.right == null){              //NODO HOJA
                    n = null;
                    size--;
                }else
                    if(n.left == null){                           //SE SUSTITUYE POR HIJO
                        n = n.right;
                        size--;
                    }else
                        if(n.right == null){
                            n = n.left;
                            size--;
                        }else{                                  //SE BUSCA MINIMO HIJO DERECHO
                            BinaryNode aux = n.right, min = aux;
                            while (aux != null) {
                                if(aux.left != null)
                                    min = aux.left;
                                aux = aux.left;
                            }
                            E old_data = n.data;
                            n.data = min.data;
                            min.data = old_data;
                            n.right = remove(n.right,min.data);
                        }
        }
        return n;
    }


    public E ceiling(E e) {
        BinaryNode n = root;
        E data = null;
        while(n!=null){
            if(this.size()>0){
                switch (compare(e,n.data)){
                    case 0: return n.data;
                    case 1:
                        if(n.right != null)
                            n = n.right;
                        else
                            return data;
                        break;
                    case -1: data = n.data;
                        if(n.left != null)
                            n = n.left;
                        else
                            return data;
                }
            }
        }
        return data;
    }
    
    @Override
    public boolean removeAll(Collection<?> arg0) {
        if (arg0==null) throw new NullPointerException();
        int originalSize=size();
        int newSize = originalSize;
        Object [] v = this.toArray();
        for (int i=0; i<v.length; i++) {
            if (arg0.contains(v[i])) {
                remove(v[i]);
                newSize--;
            }
        }
        return (originalSize!=newSize);
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {
        if (arg0==null) throw new NullPointerException();
        int originalS = size();
        int newS = originalS;
        Object[] v = this.toArray();
        for (int i=0; i<v.length; i++) {
            if (!arg0.contains(v[i])) {
                remove(v[i]);
                newS--;
            }
        }
        return (originalS!=newS);
        
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object[] toArray() {
        
        Object[] v = new Object[size()];
        toArray(0,root,v);
        
        return v;
    }

    private int toArray (int pos, BinaryNode r, Object[] v) {
        if (r!=null) {
            if (r.left!=null) pos = toArray(pos,r.left, v);
            //System.out.println("toArray pos-> "+pos +" data--> "+r.data);
            v[pos] = r.data;
            pos++;
            if (r.right!=null) pos =toArray(pos,r.right,v);
        }
        return pos;
    }
    
    @Override
    public <T> T[] toArray(T[] arg0) {
        if (arg0 == null) throw new NullPointerException();
        int n=size();
        if (n > arg0.length) 
            arg0=(T[]) new Object[n];
        toArray(0,root,arg0);
        
        return arg0;
    }

    
    /**Returns an String with the data in the nodes
     * in inorder
     */
     public String toString() {
         return toString(root) + " - size:" + size;
     }
     
     private String toString(BinaryNode r) {
         String s="";
         if (r != null) {
             String sl=toString(r.left);
             String sd=toString(r.right);
             if (sl.length() >0) 
                 s = sl + ", ";
             s = s + r.data;
             if (sd.length() > 0)
                 s = s + ", " + sd;
         }
         return s;
     }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

}
