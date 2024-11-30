import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SalaAssento {
    private int idSalaAssento;
    private Assento assento;
    private Sala sala;

    public SalaAssento(int idSalaAssento, Assento assento, Sala sala) {
        this.idSalaAssento = idSalaAssento;
        this.assento = assento;
        this.sala = sala;
    }

    public boolean cadastrar(SalaAssento salaAssento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\cinema\\trab_poo\\SalaAssento.java", true))) {
            writer.write(salaAssento.getIdSalaAssento() + ";" + salaAssento.getAssento().getIdAssento() + ";" + salaAssento.getSala().getIdSala());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
public String toString() {
    return "SalaAssento{idSalaAssento=" + idSalaAssento + ", idAssento=" + assento.getIdAssento() + ", idSala=" + sala.getIdSala() + "}";
}


    public ArrayList<SalaAssento> listar() {
        ArrayList<SalaAssento> salaAssentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\cinema\\trab_poo\\SalaAssento.java"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idSalaAssento = Integer.parseInt(dados[0]);
                int idAssento = Integer.parseInt(dados[1]);
                int idSala = Integer.parseInt(dados[2]);

               
                TipoAssento tipoAssento = new TipoAssento(0, "", "Ativo"); 
                Assento assento = new Assento(idAssento, tipoAssento);
                Sala sala = new Sala(idSala, 0, "", "Ativo");

                salaAssentos.add(new SalaAssento(idSalaAssento, assento, sala));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salaAssentos;
    }

    public SalaAssento consultar(int idSalaAssento) {
        for (SalaAssento salaAssento : listar()) {
            if (salaAssento.getIdSalaAssento() == idSalaAssento) {
                return salaAssento;
            }
        }
        return null;
    }

    public boolean editar(SalaAssento salaAssento) {
        ArrayList<SalaAssento> salaAssentos = listar();
        boolean encontrado = false;

        for (SalaAssento sa : salaAssentos) {
            if (sa.getIdSalaAssento() == salaAssento.getIdSalaAssento()) {
                sa.setAssento(salaAssento.getAssento());
                sa.setSala(salaAssento.getSala());
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\cinema\\trab_poo\\SalaAssento.java"))) {
                for (SalaAssento sa : salaAssentos) {
                    writer.write(sa.getIdSalaAssento() + ";" + sa.getAssento().getIdAssento() + ";" + sa.getSala().getIdSala());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int getIdSalaAssento() {
        return idSalaAssento;
    }

    public void setIdSalaAssento(int idSalaAssento) {
        this.idSalaAssento = idSalaAssento;
    }

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
