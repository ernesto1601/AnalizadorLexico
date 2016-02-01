package leerarchivo;

import java.io.*;

public class AllLines{
	static String name = "";
	static FileReader fr;
	static BufferedReader br;
	static String [] datos;

	public AllLines(String name){
		this.name = name;

	}
	

	public static String [] AllLines(int numlines){
            System.out.println("numero de lineas---" + numlines);
		try{
		  datos = new String [numlines];
          int c = 0;
          String bfread = "";
       	  fr = new FileReader(name);
          br = new BufferedReader(fr);

          while((bfread = br.readLine()) != null){
              datos[c] = bfread;
              System.out.println("datos----" + datos[c]);
              c++;
          }
      }catch(java.io.IOException ioex){
      }finally{
        try{
              fr.close();       //cierra el archivo
        }catch(java.io.IOException ioex){
        }
      }
      return datos;
  }

  public int getLines (){ // Método que devuelve número de líneas del archivo
      
      String cadena=""; //Cadena que sirve de auxiliar al método .readline
      int cont = 0;  //inicializamos un contador en cero para que nos cuente las lineas del archivo 
      String aux = name; //es una cadena auxiliar que nos sirve en el metodo replaceAll
    
      name = aux.replaceAll("[\n\r]",""); //Reasignamos al atributo el nombre del archivo pero descartamos cualquier "enter" si el nombre es ingresado por el usuario
    try{ 
      fr = new FileReader(name); // Creamos el objeto de la clase FileReader
      br = new BufferedReader(fr); // Creamos el objeto de la clase BufferedReader
        
      while((cadena=br.readLine()) != null){ //Un ciclo que nos leerá cada una de las lineas de nuestro archivo hasta que ya no haya nada.
           cont=cont+1; //nos lleva el conteo de las lineas
      }

    }catch(Exception e){ 
        System.out.println("excepcion---" + e);
      cont = -1; //en dado caso de que no pueda leer el archivo o que surja algun error en el bloque del try asignrá a contador el valor negativo de -1 
    }
    finally{ 
        try{
                  fr.close(); //finalmente cerramos el archivo
              } catch (Exception e){ 
                }
                  
            }                  
    
    return cont;  //nos regresa contador que contendrá el número de lineas o en caso de error un numero negativo
  }

 /* public static void main(String[] args) {
  	AllLines p = new AllLines("prueba.txt");

  	String [] datitos = p.AllLines(p.getLines());

  	for (int x = 0; x < datitos.length; x++){
  		System.out.println("datos: " + datitos[x]);
  	}

  	
  }*/
	
}