package br.unifil.dc.sisop;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Write a description of class Jsh here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class Jsh {

  ComandosInternos comandosInternos;


  /**
   * Funcao principal do Jsh.
   */
  public static void promptTerminal() throws Exception {

    while (true) {
      exibirPrompt();
      ComandoPrompt comandoEntrado = lerComando();
//    		executarComando(comandoEntrado);
    }
  }

  /**
   * Escreve o prompt na saida padrao para o usuário reconhecê-lo e saber que o
   * terminal está pronto para receber o próximo comando como entrada.
   */
  public static void exibirPrompt() throws IOException, InterruptedException {
    String nomeUsuario = System.getProperty("user.name");
    String pwd = System.getProperty("user.dir");
    System.out.print(nomeUsuario + "#: " + getUserId(nomeUsuario) + pwd + " ");
  }

  /**
   * Preenche as strings comando e parametros com a entrada do usuario do terminal.
   * A primeira palavra digitada eh sempre o nome do comando desejado. Quaisquer
   * outras palavras subsequentes sao parametros para o comando. A palavras sao
   * separadas pelo caractere de espaco ' '. A leitura de um comando eh feita ate
   * que o usuario pressione a tecla <ENTER>, ou seja, ate que seja lido o caractere
   * EOL (End Of Line).
   *
   * @return
   */
  public static ComandoPrompt lerComando() throws Exception {
    Scanner scanner = new Scanner(System.in);
    String comando = scanner.nextLine();
    if (comando.equals("ls")) {
      File file = new File(System.getProperty("user.dir"));
      File afile[] = file.listFiles();
      int i = 0;
      for (int j = afile.length; i < j; i++) {
        File arquivos = afile[i];
        System.out.println(arquivos.getName());
      }
    }

    if (comando.contains("cd")) {
      int nomeDir = comando.indexOf(" ");
      String nomeDiretorio = comando.substring(nomeDir + 1, comando.length());
      System.setProperty("user.dir", nomeDiretorio);

    }

    if (comando.contains("mkdir")) {
      int nomeDir = comando.indexOf(" ");
      String nomeDiretorio = comando.substring(nomeDir + 1, comando.length());
      try {
        String diretorioAtual = System.getProperty("user.dir");
        File file = new File(diretorioAtual + "/" + nomeDiretorio);
        file.mkdir();
      } catch (Exception e) {
        throw new Exception("Não foi possivel criar o diretório");
      }
    }

    if (comando.equals("relogio")) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
      System.out.println("Hoje é: " + dateFormat.format(new Date()));
    }

    if (comando.contains("ad")) {
      int nomeDir = comando.indexOf(" ");
      String nomeDiretorio = comando.substring(nomeDir + 1, comando.length());
      try {
        File file = new File(nomeDiretorio);
        if ((file.exists()) && (file.isDirectory())) {
          file.delete();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  /**
   * Recebe o comando lido e os parametros, verifica se eh um comando interno e,
   * se for, o executa.
   * <p>
   * Se nao for, verifica se é o nome de um programa terceiro localizado no atual
   * diretorio de trabalho. Se for, cria um novo processo e o executa. Enquanto
   * esse processo executa, o processo do uniterm deve permanecer em espera.
   * <p>
   * Se nao for nenhuma das situacoes anteriores, exibe uma mensagem de comando ou
   * programa desconhecido.
   */
  public static void executarComando(ComandoPrompt comando) {
    if (comando.equals("ls")) {
      File file = new File(System.getProperty("user.dir"));
      File afile[] = file.listFiles();
      int i = 0;
      for (int j = afile.length; i < j; i++) {
        File arquivos = afile[i];
        System.out.println(arquivos.getName());
      }
    }

    if (comando.equals("cd")) {
      File file = new File(System.getProperty("user.dir"));
      System.getProperty("user.dir", file.getParent());

    }

    throw new RuntimeException("Método ainda não implementado.");
  }

  public static int executarPrograma(ComandoPrompt comando) {
    throw new RuntimeException("Método ainda não implementado.");
  }

  public static Integer getUserId(String username) throws IOException, InterruptedException {
    Integer id = null;
    Process process;
    try {
      process = Runtime.getRuntime().exec("id -u" + username);
      process.waitFor();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;

      while((line = reader.readLine()) != null) {
        id = Integer.valueOf(line);
      }

    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return id;
  }


  /**
   * Entrada do programa. Provavelmente você não precisará modificar esse método.
   */
  public static void main(String[] args) throws Exception {
    promptTerminal();
  }


  /**
   * Essa classe não deve ser instanciada.
   */
  private Jsh() {
  }
}
