package br.unifil.dc.sisop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Write a description of class ComandosInternos here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public class ComandosInternos {
    
    public static int exibirRelogio() {  //TODO FEITOOO
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
        System.out.println("Hoje é: " + dateFormat.format( new Date() ));
        throw new RuntimeException("Método ainda não implementado");
    }
    
    public static int escreverListaArquivos(Optional<String> nomeDir) { //TODO FEITOOO
        throw new RuntimeException("Método ainda não implementado");
    }
    
    public static int criarNovoDiretorio(String nomeDir) { //TODO FEITOOO
        throw new RuntimeException("Método ainda não implementado");
    }
    
    public static int apagarDiretorio(String nomeDir) { //TODO FEITOOO

        throw new RuntimeException("Método ainda não implementado");
    }
    
    public static int mudarDiretorioTrabalho(String nomeDir){ //TODO FEITOOO
        throw new RuntimeException("Método ainda não implementado");
    }
    
    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {}
}
