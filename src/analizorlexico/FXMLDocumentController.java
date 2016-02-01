/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizorlexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import leerarchivo.AllLines;

/**
 *
 * @author Ernesto
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    Button btn_evaluar;
    @FXML
    TextArea resultado;
    @FXML
    TextField entrada;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         String path = "../AnalizadorLexico/src/analizorlexico//Lexer.flex";
        generarLexer(path);
    }  
    
    public void generarLexer(String path){
        
        File file = new File(path);
        jflex.Main.generate(file);
    }
    
    @FXML
    private void accion(ActionEvent e){
        
        try{
            probarLexerFile();
        }catch(IOException a){
            System.out.println(a.getMessage());
        }
    }
    
    public void probarLexerFile() throws IOException{
        AllLines al = new AllLines(entrada.getText());
        File fichero = new File("fichero.txt");
        PrintWriter writer;
        
        try{
            System.out.println("numero de lineas*******" + al.getLines());
            String[] x = al.AllLines(al.getLines());
            writer = new PrintWriter(fichero);
            for (int i = 0; i < al.getLines(); i++) {
                writer.append(x[i]);
            }
            writer.close();
        }
        catch(FileNotFoundException ex){
            
        }
        
        Reader reader;
        reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer(reader);
        String Resultados="";
        Token token = lexer.yylex();
        
        while(token != null){
         
            switch(token){
                case ERROR:
                    Resultados = Resultados+"Error, el simbolo no coincide \n";
                    break;
                case Identificador:
                case Entero:
                    Resultados = Resultados+"Token:"+token+" "+lexer.lexeme+"\n";
                   break;
                default:
                    Resultados = Resultados+"Token:"+token+"\n";
            }
            token = lexer.yylex();
        }
        Resultados = Resultados+"FIN";
        resultado.setText(Resultados);
    }
    
}
