package EDChainedHashSet;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

/**
 * Clase generica que implementa la interfaz Set<T> usando una tabla de
 * dispersión con resolución de colisiones mediante encadenamiento.
 * 
 * Admite elmentos null como validos
 * 
 * No implementa iteradores
 * 
 * @param <T> 
 */
public class EDChainedHashSet<T> implements Set<T> {
	/**
	 * Parte privada
	 * 
	 * DEFAULT_CAPACITY: tamaño por defecto de la tabla de dispersión
	 * DEFAULT_LOAD_FACTOR: factor de carga a aprtir del cual se realiza el
	 * rehashing. 1.0 es un 100%.
	 * 
	 * class node: el nodo 
	 * 
	 * table: la tabla de dispersión. 
	 * 
	 * size: numero de
	 * elementos de la tabla 
	 * 
	 * rehashThreshold: umbral a partir del cual se debe
	 * realizar el rehashing (size > rehashThreshold)
	 */
	static int DEFAULT_CAPACITY = 7;
	static double DEFAULT_LOAD_FACTOR = 3.0; // 300%

	private class Node {
		T data;
		Node next;

		public Node(T item) {
			data = item;
			next = null;
		}
	}

	private Node table[];
	private int size;
	private int rehashThreshold;

	// constructores
	@SuppressWarnings("unchecked")
	public EDChainedHashSet(int capacity, double loadFactor) {
		table = new EDChainedHashSet.Node[capacity];
		size = 0;
		rehashThreshold = (int) Math.floor(loadFactor * capacity);
	}

	public EDChainedHashSet(int capacity) {
		this(capacity, DEFAULT_LOAD_FACTOR);
	}

	public EDChainedHashSet(double loadFactor) {
		this(DEFAULT_CAPACITY, loadFactor);
	}

	public EDChainedHashSet() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Calcula el valor positivo de la funcion de dispersion para item ajustado
	 * al tamaño, y teniendo en cuenta que puede ser null
	 * 
	 * @param item
	 * @return Valor de dispersion
	 */
	private int hash(Object item) {
		if (item == null)
			return 0;

		return (item.hashCode() & Integer.MAX_VALUE) % table.length;
	}

	/**
	 * Compara si item1 y item2 son iguales teniendo en cuenta que ambos puede
	 * ser null
	 * 
	 * @param item1
	 * @param item2
	 * @return si item1 y item2 son iguales
	 */
	@SuppressWarnings("unchecked")
	private boolean compareNull(Object item1, Object item2) {
		if (item1 == null)
			return item1 == item2;
		else
			return ((T) item1).equals((T) item2);
	}
	/**
	 * Realiza el rehashing si es necesario, cuando (size > rehashThreshold). Al
	 * realizarlo dobla el tamaño de la tabla y recalcula el rehashThreshold
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		Node[] aux = table;
		this.table = new EDChainedHashSet.Node[(int)(this.getCapacity()*2.0)];
		this.rehashThreshold = (int)(getCapacity()*2.0);

		int index;
		for(int i=0;i<aux.length;i++){
			Node n= aux[i];
			while(n != null){
				index = hash(n.data);
				if(table[index] == null){					//Si no hay ningun elemento en ese indice lo añado
					table[index]= new Node(n.data);
				}else{
					Node oldNode = table[index];
					Node node=new Node(n.data);				//Si no esta esta lo añado a la tabla
					node.next = oldNode;
					table[index] = node;
				}
				n = n.next;
			}
		}
	}

	public int getCapacity() {
		return table.length;
	}
	
	@Override
	public boolean add(T item) {
		if(this.size >= rehashThreshold)
			this.rehash();

		int index = hash(item);
		Node oldNode = table[index];

		while(oldNode!=null){
			if(oldNode.data == item)
				return false;
			oldNode = oldNode.next;
		}
			Node node=new Node(item);				//Si no esta esta lo añado a la tabla
			node.next = table[index];
			table[index] = node;

		this.size++;
		return true;
	}

	@Override
	public boolean contains(Object item) {

		int index = this.hash(item);				//Posible indice del item a buscar
		Node n = table[index];
		while(n != null){
			if(compareNull(item,n.data)) return true;
			n = n.next;
		}
		return false;
	}

	@Override
	public boolean remove(Object item) {
		if(this.contains(item)){
			int index = hash(item);
			Node nodo = table[index];
			table[index] = null;

			while(nodo != null){
				if(!compareNull(nodo.data,item))
					this.add(nodo.data);
				this.size--;
				nodo = nodo.next;
			}
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		this.table= new EDChainedHashSet.Node[this.getCapacity()];
		this.size = 0;
	}

	// Los siguientes métodos asumen la existencia de iteradores.

	@Override
	public boolean containsAll(Collection<?> c) {
		if (c.isEmpty())
			return true;
		if (isEmpty())
			return false;
		for (Object item : c) {
			if (!contains(item))
				return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends T> c) {
		int oldSize = size;
		for (Object item : c)
			add((T) item);

		return (size != oldSize);
	}

	/* Conserva sólo los elementos de esta colección que están contenidos en la colección especificada (operación
	    opcional).

	    En otras palabras, se elimina de todos los elementos de esta colección que no están contenidos en la
	   colección especificada. 	  true  si esta colección cambia como resultado de la llamada
	*/
	@Override
	public boolean retainAll(Collection<?> c) {
		Iterator it=c.iterator();
		Collection conteins=new HashSet();

		while(it.hasNext()){
			Object e = it.next();
			if(this.contains(e))
				conteins.add(e);
		}
		if(conteins.size()<=0 || this.size == conteins.size()) return false;
		else{
			this.clear();
			it=conteins.iterator();
			while(it.hasNext()){
				this.add((T)it.next());
			}
		}
		return true;
	}
	
	//mirar cada coleccion y si es c borra toda la coleccion

	@Override
	public boolean removeAll(Collection<?> c) {
		int oldSize = size();
		for (Object item : c)
			remove(item);

		return size() != oldSize;
	}

	@Override
	public Object[] toArray() {
		Object ret[] = new Object[size];

		int i = 0;
		for (Object item : this) {
			ret[i] = item;
			i++;
		}
		return ret;
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> T[] toArray(T[] a) {
		T[] ret = null;

		if (a.length > size) {
			ret = a;
			for (int i = size; i < ret.length; i++)
				ret[i] = null;
		} else
			ret = (T[]) new Object[size];

		int i = 0;
		for (Object item : this) {
			ret[i] = (T) item;
			i++;
		}

		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(toArray());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EDChainedHashSet<T> other = (EDChainedHashSet<T>) obj;

		if (size() != other.size())
			return false;

		for (T item : other) {
			if (!contains(item))
				return false;
		}

		return true;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("[ ");
		for (int row = 0; row < table.length; row++) {
			if (table[row] != null) {
				str.append("{" + row + ": ");
				str.append(table[row].data);
				Node aux = table[row].next;
				while (aux != null) {
					str.append(", " + aux.data);
					aux = aux.next;
				}
				str.append("} ");
			}
		}

		str.append("] (size: " + size + ", capacity: " + table.length + ")");
		return str.toString();
	}

	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException();
	}

}
