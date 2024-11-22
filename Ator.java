import java.io.*;
import java.util.ArrayList;

public class Ator extends Pessoa {
    private int registro;

    public Ator(String cpf, String nome, String email, int registro) {
        super(cpf, nome, email);
        this.registro = registro;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public boolean cadastrar(Ator ator) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\trab poo\\bd\\genero.txt", true))) {
            writer.write(ator.getRegistro() + ";" + ator.getCpf() + ";" + ator.getNome() + ";" + ator.getEmail() + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Ator ator) {
        ArrayList<Ator> atores = listar();
        boolean encontrado = false;

        try (
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\trab poo\\bd\\genero.txt"))) {
            for (Ator a : atores) {
                if (a.getRegistro() == ator.getRegistro()) {
                    writer.write(ator.getRegistro() + ";" + ator.getCpf() + ";" + ator.getNome() + ";" + ator.getEmail() + "\n");
                    encontrado = true;
                } else {
                    writer.write(a.getRegistro() + ";" + a.getCpf() + ";" + a.getNome() + ";" + a.getEmail() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return encontrado;
    }

    public Ator consultar(int registro) {
        ArrayList<Ator> atores = listar();

        for (Ator ator : atores) {
            if (ator.getRegistro() == registro) {
                return ator;
            }
        }

        return null; 
    }

    public ArrayList<Ator> listar() {
        ArrayList<Ator> atores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\trab poo\\bd\\genero.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int registro = Integer.parseInt(dados[0]);
                String cpf = dados[1];
                String nome = dados[2];
                String email = dados[3];
                atores.add(new Ator(cpf, nome, email, registro));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return atores;
    }
}
