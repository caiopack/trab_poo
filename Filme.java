import java.io.*;
import java.util.ArrayList;

public class Filme {
    private int idFilme;
    private String titulo;
    private int classificacao;
    private Genero genero;
    private String status;
    
    public Filme(int idFilme, String titulo, int classificacao, Genero genero, String status) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.classificacao = classificacao;
        this.genero = genero;
        this.status = status;
    }

    public boolean cadastrar(Filme filme) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("\\bd\\filme.txt", true))) {
            writer.write(filme.getIdFilme() + ";" + filme.getTitulo() + ";" + filme.getClassificacao() + ";" + filme.getGenero() +  ";" + filme.getStatus() + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Filme filme){
       ArrayList<Filme> filmes = listar();
       boolean encontrado = false;

       try (BufferedWriter writer = new BufferedWriter(new FileWriter("\\bd\\filme.txt"))) {
           for(Filme f : filmes){
            if(f.getIdFilme() == filme.getIdFilme()){
                writer.write(filme.getIdFilme() + ";" + filme.getTitulo() + ";" + filme.getClassificacao() + ";" + filme.getGenero() +  ";" + filme.getStatus() + "\n");
                encontrado = true;
            } else {
                writer.write(f.getIdFilme() + ";" + f.getTitulo() + ";" + f.getClassificacao() + ";" + f.getGenero() +  ";" + f.getStatus() + "\n");
            }
           }
       } catch (Exception e) {
        e.printStackTrace();
        return false;
       }
       return encontrado;
    }

    public Filme consultar(int idFilme) {
        ArrayList<Filme> filmes = listar();

        for (Filme filme : filmes) {
            if (filme.getIdFilme() == idFilme) {
                return filme;
            }
        }

        return null; 
    }

    public ArrayList<Filme> listar() {
        ArrayList<Filme> filmes = new ArrayList<>();
    
        // Instância de Genero para consultar gêneros
        Genero generoObj = new Genero(idFilme, titulo, status);
    
        try (BufferedReader reader = new BufferedReader(new FileReader("\\bd\\filme.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idFilme = Integer.parseInt(dados[0]);
                String titulo = dados[1];
                int classificacao = Integer.parseInt(dados[2]);
                String descGenero = dados[3];
                String status = dados[4];
    
                // Consultando o gênero a partir da descrição
                Genero genero = generoObj.consultar(descGenero);
    
                if (genero != null) {
                    filmes.add(new Filme(idFilme, titulo, classificacao, genero, status));
                } else {
                    System.err.println("Gênero não encontrado para a descrição: " + descGenero);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return filmes;
    }
    

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

}
