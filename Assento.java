import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Assento {
    private int idAssento;
    private TipoAssento tipoAssento;

    public Assento(int idAssento, TipoAssento tipoAssento) {
        this.idAssento = idAssento;
        this.tipoAssento = tipoAssento;
    }

 
    public boolean cadastrar(Assento assento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\cinema\\trab_poo\\bd\\assento.txt", true))) {
            writer.write(assento.getIdAssento() + ";" + assento.getTipoAssento().getDescricao() + ";" + assento.getTipoAssento().getStatus());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
    return "Assento{idAssento=" + idAssento + ", tipoAssento=" + tipoAssento + "}";
}

    

    public ArrayList<Assento> listar() {
        ArrayList<Assento> assentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\cinema\\trab_poo\\bd\\assento.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idAssento = Integer.parseInt(dados[0]);
                String descricaoTipoAssento = dados[1];
                String statusTipoAssento = dados[2];

                TipoAssento tipoAssento = new TipoAssento(0, descricaoTipoAssento, statusTipoAssento);
                assentos.add(new Assento(idAssento, tipoAssento));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assentos;
    }

    public Assento consultar(int idAssento) {
        for (Assento assento : listar()) {
            if (assento.getIdAssento() == idAssento) {
                return assento;
            }
        }
        return null; 
    }

    public boolean editar(Assento assento) {
        ArrayList<Assento> assentos = listar();
        boolean encontrado = false;

        for (Assento a : assentos) {
            if (a.getIdAssento() == assento.getIdAssento()) {
                a.setTipoAssento(assento.getTipoAssento());
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\cinema\\trab_poo\\bd\\assento.txt"))) {
                for (Assento a : assentos) {
                    writer.write(a.getIdAssento() + ";" + a.getTipoAssento().getDescricao() + ";" + a.getTipoAssento().getStatus());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int getIdAssento() {
        return idAssento;
    }

    public void setIdAssento(int idAssento) {
        this.idAssento = idAssento;
    }

    public TipoAssento getTipoAssento() {
        return tipoAssento;
    }

    public void setTipoAssento(TipoAssento tipoAssento) {
        this.tipoAssento = tipoAssento;
    }
}
