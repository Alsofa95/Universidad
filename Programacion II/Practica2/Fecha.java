package ejercicio25;

import java.util.Calendar;

/**
 *
 * @author Daniel Moreno
 */
public class Fecha {
    int dia;
    int mes;
    int año;

    public Fecha(int dia, int mes, int año) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }
    
    public Fecha(Fecha otraFecha){
        this.dia=otraFecha.getDía();
        this.mes=otraFecha.getMes();
        this.año=otraFecha.getAño();
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + año;
    }
    
    public boolean equals(Fecha otraFecha){
        if(this.dia == otraFecha.getDía())
            if(this.mes == otraFecha.getMes())
                if(this.año == otraFecha.getAño())
                    return true;
       return false;
    }

    public int compareTo(Fecha otraFecha){
        if(this.equals(otraFecha))
            return 0;
        
        if(this.año>otraFecha.getAño()){
            return 1;
        }else{
            if(this.año == otraFecha.getAño()&this.mes>otraFecha.getMes()){
                return 1;
            }else{
                if(this.año == otraFecha.getAño() & this.mes == otraFecha.getMes() & this.dia>otraFecha.getDía())
                    return 1;
            }
        }
        return -1;
    }
            
    public int getDía() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAño() {
        return año;
    }
    
    public boolean añoBisiesto(int año){
        if(año%4==0 & año%100!=0 | año%400==0)
            return true;
        return false;
    }
    
    public int díasMes(int mes, int año){
        int[] diasMes={31,28,31,30,31,30,31,31,30,31,30,31};
        if(mes == 2 & añoBisiesto(año))
            return 29;
        return diasMes[mes-1];
    }
    
    public Fecha hoy(){
        Calendar calendario = Calendar.getInstance();
        int día = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int año = calendario.get(Calendar.YEAR);

        return new Fecha(día, mes, año);
    }
    
    public Fecha díaSiguiente(){
        int dia=this.dia,mes=this.mes,año=this.año;
        if(dia == this.díasMes(this.mes, this.año)){
            dia=1;
            if(this.mes == 12){
                año++;
                mes=1;
            }else{
                mes++;
            }
        }else{
            dia++;
        }  
        return new Fecha(dia, mes, año);
    }
}
