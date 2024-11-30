import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ingresso {
    private int idIngresso;
    private double valorPago;
    private SalaAssento SalaAssento;
    private Sessao sessao;
    
    public Ingresso(int idIngresso, double valorPago, SalaAssento salaAssento, Sessao sessao) {
        this.idIngresso = idIngresso;
        this.valorPago = valorPago;
        SalaAssento = salaAssento;
        this.sessao = sessao;
    }
     public boolean cadastrar(Ingresso ingresso) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\cinema\\trab_poo\\bd\\ingresso.txt", true))) {
            writer.write(ingresso.getIdIngresso() + ";" + ingresso.getValorPago() + ";" + ingresso.getSalaAssento().getIdSalaAssento() + ";" +
                         ingresso.getSessao().getIdSessao());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
public String toString() {
    return "Ingresso{idIngresso=" + idIngresso + ", valorPago=" + valorPago + ", SalaAssentoID=" + SalaAssento.getIdSalaAssento() + 
            ", SessaoID=" + sessao.getIdSessao() + '}';
}


    public ArrayList<Ingresso> listar() {
        ArrayList<Ingresso> ingressos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\cinema\\trab_poo\\bd\\ingresso.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idIngresso = Integer.parseInt(dados[0]);
                double valorPago = Double.parseDouble(dados[1]);
                int idSalaAssento = Integer.parseInt(dados[2]);
                int idSessao = Integer.parseInt(dados[3]);

                SalaAssento salaAssento = new SalaAssento(idSalaAssento, new Assento(0, new TipoAssento(0, "", "")),
                    new Sala(0, 0, "", ""));
                    Sessao sessao = new Sessao(idSessao, null, null, null, null, "");

                ingressos.add(new Ingresso(idIngresso, valorPago, salaAssento, sessao));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingressos;
    }

    public Ingresso consultar(int idIngresso) {
        for (Ingresso ingresso : listar()) {
            if (ingresso.getIdIngresso() == idIngresso) {
                return ingresso;
            }
        }
        return null;
    }

    public boolean editar(Ingresso ingresso) {
        ArrayList<Ingresso> ingressos = listar();
        boolean encontrado = false;

        for (Ingresso i : ingressos) {
            if (i.getIdIngresso() == ingresso.getIdIngresso()) {
                i.setValorPago(ingresso.getValorPago());
                i.setSalaAssento(ingresso.getSalaAssento());
                i.setSessao(ingresso.getSessao());
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\cinema\\trab_poo\\bd\\ingresso.txt"))) {
                for (Ingresso i : ingressos) {
                    writer.write(i.getIdIngresso() + ";" + i.getValorPago() + ";" + i.getSalaAssento().getIdSalaAssento() + ";" + i.getSessao().getIdSessao());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public int getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(int idIngresso) {
        this.idIngresso = idIngresso;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public SalaAssento getSalaAssento() {
        return SalaAssento;
    }

    public void setSalaAssento(SalaAssento salaAssento) {
        SalaAssento = salaAssento;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    
}
