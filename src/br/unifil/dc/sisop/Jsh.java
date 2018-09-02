package br.unifil.dc.sisop;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
      executarComando(comandoEntrado);
    }
  }

  /**
   * Escreve o prompt na saida padrao para o usuário reconhecê-lo e saber que o
   * terminal está pronto para receber o próximo comando como entrada.
   */
  public static void exibirPrompt() throws IOException, InterruptedException {
    String nomeUsuario = System.getProperty("user.name");
    String pwd = System.getProperty("user.dir");
    System.out.print(nomeUsuario + "#: " + recuperarIdUsuario(nomeUsuario) + " " + pwd + " ");
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
    ArrayList<String> comandosShell = new ArrayList<String>();
    comandosShell.add("la");
    comandosShell.add("cd");
    comandosShell.add("encerrar");
    comandosShell.add("mdt");
    comandosShell.add("mesg_do_dia");
    comandosShell.add("relogio");
    comandosShell.add("falha_arbitraria");
    Scanner scanner = new Scanner(System.in);
    String comando = scanner.nextLine();
    boolean isValid = false;
    File file = new File(System.getProperty("user.dir"));
    File afile[] = file.listFiles();
    if(!comandosShell.contains(comando)) {
      for (int i = 0; i < afile.length; i++) {
        if(comando == afile[i].getName()) {
          isValid = true;
        }
      }
      if (!isValid) {
        System.out.println("Não existe esse comando");
      }
    }
    if (comando.equals("la")) {
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

    if (comando.contains("mdt")) {
      int nomeDir = comando.indexOf(" ");
      String nomeDiretorio = comando.substring(nomeDir + 1, comando.length());
      try {
        String diretorioAtual = System.getProperty("user.dir");
        File diretorioNovo = new File(diretorioAtual + "/" + nomeDiretorio);
        diretorioNovo.mkdir();
        System.out.println("Diretório " + diretorioNovo + " criado");
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
        File diretorioRemovido = new File(nomeDiretorio);
        if ((diretorioRemovido.exists()) && (diretorioRemovido.isDirectory())) {
          diretorioRemovido.delete();
          System.out.println("Diretório " + diretorioRemovido + " removido");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if(comando.equals("encerrar")) {
      System.exit(0);
    }

    if(comando.equals("mesg_do_dia")) {
      System.out.println("The only way around is through.");
    }

    if(comando.equals("falha_arbitraria")) {
      System.out.println("Invalid arguments. Please, RTFM.");
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

      while ((line = reader.readLine()) != null) {
        id = Integer.valueOf(line);
      }

    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return id;
  }

  private static Integer recuperarIdUsuario(String userName) {
    Integer id = null;
    Process process;
    try {
      process = Runtime.getRuntime().exec("id -u " + userName);
      process.waitFor();
      id = recuperarCodigoRetornoPrograma(process.getInputStream());
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return id;
  }

  private static int recuperarCodigoRetornoPrograma(InputStream is) {
    int codigoRetorno = 0;
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
      String line;

      while ((line = reader.readLine()) != null) {
        codigoRetorno = Integer.valueOf(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return codigoRetorno;
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
