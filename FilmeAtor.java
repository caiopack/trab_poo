
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FilmeAtor {
    private int idFilmeAtor;
    private Ator ator;
    private Filme filme;
    private String personagem;
    private boolean principal;
    
    
    public FilmeAtor(int idFilmeAtor, Ator ator, Filme filme, String personagem, boolean principal) {
        this.idFilmeAtor = idFilmeAtor;
        this.ator = ator;
        this.filme = filme;
        this.personagem = personagem;
        this.principal = principal;
    }

    public boolean cadastrar(FilmeAtor filmeAtor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd\\filmeator.txt", true))) {
            writer.write(filmeAtor.getIdFilmeAtor() + ";" + filmeAtor.getAtor().getRegistro() + ";" + filmeAtor.getFilme().getIdFilme() + ";" + filmeAtor.getPersonagem() + ";" +
                filmeAtor.isPrincipal() + "\n"
            );
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

     public boolean editar(FilmeAtor filmeAtor) {
        ArrayList<FilmeAtor> lista = listar();
        boolean encontrado = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd\\filmeator.txt"))) {
            for (FilmeAtor fa : lista) {
                if (fa.getIdFilmeAtor() == filmeAtor.getIdFilmeAtor()) {
                    writer.write(filmeAtor.getIdFilmeAtor() + ";" + filmeAtor.getAtor().getRegistro() + ";" + filmeAtor.getFilme().getIdFilme() + ";" + filmeAtor.getPersonagem() + ";" + filmeAtor.isPrincipal() + "\n"
                    );
                    encontrado = true;
                } else {
                    writer.write(fa.getIdFilmeAtor() + ";" + fa.getAtor().getRegistro() + ";" + fa.getFilme().getIdFilme() + ";" + fa.getPersonagem() + ";" +
                        fa.isPrincipal() + "\n"
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return encontrado;
    }

    public FilmeAtor consultar(int idFilmeAtor) {
        for (FilmeAtor filmeAtor : listar()) {
            if (filmeAtor.getIdFilmeAtor() == idFilmeAtor) {
                return filmeAtor;
            }
        }
        return null;
    }

    public ArrayList<FilmeAtor> listar() {
        ArrayList<FilmeAtor> filmeAtores = new ArrayList<>();
        Ator atorHandler = new Ator(null, null, null, 0); // Ajuste conforme o construtor da classe Ator
        Filme filmeHandler = new Filme(0, null, 0, null, null);

        try (BufferedReader reader = new BufferedReader(new FileReader("bd\\filmeator.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idFilmeAtor = Integer.parseInt(dados[0]);
                int registroAtor = Integer.parseInt(dados[1]);
                int idFilme = Integer.parseInt(dados[2]);
                String personagem = dados[3];
                boolean principal = Boolean.parseBoolean(dados[4]);

                Ator ator = atorHandler.consultar(registroAtor);
                Filme filme = filmeHandler.consultar(idFilme);

                if (ator != null && filme != null) {
                    filmeAtores.add(new FilmeAtor(idFilmeAtor, ator, filme, personagem, principal));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmeAtores;
    }

    public int getIdFilmeAtor() {
        return idFilmeAtor;
    }
    
    public Ator getAtor() {
        return ator;
    }
    public void setAtor(Ator ator) {
        this.ator = ator;
    }
    public Filme getFilme() {
        return filme;
    }
    public void setFilme(Filme filme) {
        this.filme = filme;
    }
    public String getPersonagem() {
        return personagem;
    }
    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }
    public boolean isPrincipal() {
        return principal;
    }
    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public void setIdFilmeAtor(int idFilmeAtor) {
        this.idFilmeAtor = idFilmeAtor;
    }
}
