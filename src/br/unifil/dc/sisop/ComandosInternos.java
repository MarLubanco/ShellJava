package br.unifil.dc.sisop;

import java.io.File;
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
        File file = new File(System.getProperty("user.dir"));
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            System.out.println(arquivos.getName());
        }
        return 1;
    }
    
    public static int criarNovoDiretorio(String nomeDire) throws Exception { //TODO FEITOOO
        int nomeDir = nomeDire.indexOf(" ");
        String nomeDiretorio = nomeDire.substring(nomeDir + 1, nomeDire.length());
        try {
            String diretorioAtual = System.getProperty("user.dir");
            File file = new File(diretorioAtual + "/" + nomeDiretorio);
            file.mkdir();
            return  1;
        } catch (Exception e) {
            throw new Exception("Não foi possivel criar o diretório");
        }
    }
    
    public static int apagarDiretorio(String nomeDire) { //TODO FEITOOO
        int nomeDir = nomeDire.indexOf(" ");
        String nomeDiretorio = nomeDire.substring(nomeDir + 1, nomeDire.length());
        try {
            File file = new File(nomeDiretorio);
            if ((file.exists()) && (file.isDirectory())) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    
    public static int mudarDiretorioTrabalho(String nomeDire){ //TODO FEITOOO
        int nomeDir = nomeDire.indexOf(" ");
        String nomeDiretorio = nomeDire.substring(nomeDir + 1, nomeDire.length());
        System.setProperty("user.dir", nomeDiretorio);
        return 1;
    }
    
    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {}
}
