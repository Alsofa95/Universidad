package polinomio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Polinomio {

	// La lista de monomios
	private List<Monomio> datos = new LinkedList<Monomio>();

	/**
	 * Constructor por defecto. La lista de monomios está vacía
	 */
	public Polinomio() {
	};

	/**
	 * Constructor a partir de un vector. Toma los coeficientes de los monomios
	 * de los valores almacenados en el vector, y los exponentes son las
	 * posiciones dentro del vector. Si <code>v[i]</code> contiene
	 * <code>a</code> el monomio contruido será aX^i. <br>
	 * 
	 * Por ejemplo: <br>
	 * 
	 * v = [-1, 0, 2] -> 2X^2 -1X^0
	 * 
	 * @param v
	 */
	public Polinomio(double v[]) {
		Cero comprueba=new Cero();
		for(int i=0;i<v.length;i++){			//Recorro el vector v
			if(!comprueba.esCero(v[i]))			//determina si es cero con un umbral especifico
				datos.add(new Monomio(v[i],i));	//añado
		}
	}

	/**
	 * Constructor copia
	 * 
	 * @param otro
	 * @throws <code>NullPointerException</code>
	 *             si el parámetro es nulo
	 */
	public Polinomio(Polinomio otro) {
		if (otro == null)
			throw new NullPointerException();

		for (Monomio item : otro.datos)
			datos.add(new Monomio(item));
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		

		boolean primero = true;

		for (int i = 0; i <datos.size(); i++) {
			Monomio item = datos.get(i);

			if (item.coeficiente < 0) {
				str.append('-');
				if (!primero)
					str.append(' ');
			} else if (!primero)
				str.append("+ ");

			str.append(Math.abs(item.coeficiente));
			if (item.exponente > 0)
				str.append('X');
			if (item.exponente > 1)
				str.append("^" + item.exponente);

			if (i < datos.size()-1)
				str.append(' ');

			primero = false;
		}
		if (primero)
			str.append("0.0");

		return str.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Polinomio other = (Polinomio) obj;

		if (this.datos.size() != other.datos.size())
			return false;

		Iterator<Monomio> iter1 = this.datos.iterator();
		Iterator<Monomio> iter2 = other.datos.iterator();

		while (iter1.hasNext())
			if (!(iter1.next().equals(iter2.next())))
				return false;

		return true;
	}

	/**
	 * Devuelve la lista de monomios
	 *
	 */
	public List<Monomio> monomios() {
		return datos;
	}

	/**
	 * Suma un polinomio sobre <code>this</code>, es decir, modificando el
	 * polinomio local. Debe permitir la auto autosuma, es decir,
	 * <code>polinomio.sumar(polinomio)</code> debe dar un resultado correcto.
	 *
	 * @param otro
	 * @return <code>this<\code>
	 * @throws <code>NullPointeExcepction</code> en caso de que el parámetro sea <code>null</code>.
	 */
	public void sumar(Polinomio otro) {
		ListIterator<Monomio> it = datos.listIterator();
		ListIterator<Monomio> itOtro = otro.datos.listIterator();
		Monomio m1,m2;

		while(it.hasNext() && itOtro.hasNext()){
			m1=it.next();
			m2=itOtro.next();
			if(m1.exponente == m2.exponente){				//Si ambos monominos tienen mismo exponente
				m1.coeficiente+=m2.coeficiente;				//los sumo y posteriormente compruebo si es cero.
				if(Cero.esCero(m1.coeficiente))
					it.remove();
			}
			if(m1.exponente<m2.exponente)					//Si miOtro monomio tiene un exponente mayo
				itOtro.previous();							//retrocedo hasta que miMonomio sea igual o mayor que este.
			if(m1.exponente>m2.exponente) {					//Si miMonomio tiene un exponente mayor añado miOtro monomio
				it.previous();
				it.add(m2);
			}
		}
		while(itOtro.hasNext()){							//Añado los restantes
			datos.add(itOtro.next());
		}
	}

	/** Multiplica el polinomio <code>this</code> por un monomio. 
	 * @param mono
	 */
	public void multiplicarMonomio(Monomio mono) {
		ListIterator<Monomio> it = this.datos.listIterator();
		Cero c=new Cero();
		if(c.esCero(mono.coeficiente)){
			this.datos.clear();
		}else{
			while(it.hasNext()){
				Monomio m=it.next();
				m.coeficiente*=mono.coeficiente;
				m.exponente+=mono.exponente;
			}
		}
	}
}
