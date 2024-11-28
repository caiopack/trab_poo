import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Sala {
    private int idSala;
    private int capacidadeSala;
    private String descricao;
    private String status;

    public Sala(int idSala, int capacidadeSala, String descricao, String status) {
        this.idSala = idSala;
        this.capacidadeSala = capacidadeSala;
        this.descricao = descricao;
        this.status = status;
    }

    public boolean cadastrar(Sala sala) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd//sala.txt", true))) {
            writer.write(sala.getIdSala() + ";" + sala.getCapacidadeSala() + ";" + sala.getDescricao() + ";" + sala.getStatus());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Sala> listar() {
        ArrayList<Sala> salas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("bd//sala.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idSala = Integer.parseInt(dados[0]);
                int capacidadeSala = Integer.parseInt(dados[1]);
                String descricao = dados[2];
                String status = dados[3];

                salas.add(new Sala(idSala, capacidadeSala, descricao, status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salas;
    }

    public Sala consultar(int idSala) {
        for (Sala sala : listar()) {
            if (sala.getIdSala() == idSala) {
                return sala;
            }
        }
        return null;
    }


    public boolean editar(int idSala, int novaCapacidade, String novaDescricao, String novoStatus) {
        ArrayList<Sala> salas = listar();
        boolean encontrado = false;

        for (Sala sala : salas) {
            if (sala.getIdSala() == idSala) {
                sala.setCapacidadeSala(novaCapacidade);
                sala.setDescricao(novaDescricao);
                sala.setStatus(novoStatus);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd//sala.txt"))) {
                for (Sala sala : salas) {
                    writer.write(sala.getIdSala() + ";" + sala.getCapacidadeSala() + ";" + sala.getDescricao() + ";" + sala.getStatus());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getCapacidadeSala() {
        return capacidadeSala;
    }

    public void setCapacidadeSala(int capacidadeSala) {
        this.capacidadeSala = capacidadeSala;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
