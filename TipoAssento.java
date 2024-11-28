import java.io.*;
import java.util.ArrayList;

public class TipoAssento {
    private int idTipoAssento;
    private String descricao;
    private String status;

    public TipoAssento(int idTipoAssento, String descricao, String status) {
        this.idTipoAssento = idTipoAssento;
        this.descricao = descricao;
        this.status = status;
    }

    public boolean cadastrar(TipoAssento tipoAssento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd//tipoassento.txt", true))) {
            writer.write(tipoAssento.getIdTipoAssento() + ";" + tipoAssento.getDescricao() + ";" + tipoAssento.getStatus());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<TipoAssento> listar() {
        ArrayList<TipoAssento> tipoAssentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("bd//tipoassento.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idTipoAssento = Integer.parseInt(dados[0]);
                String descricao = dados[1];
                String status = dados[2];

                tipoAssentos.add(new TipoAssento(idTipoAssento, descricao, status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tipoAssentos;
    }

    public TipoAssento consultar(int idTipoAssento) {
        for (TipoAssento tipoAssento : listar()) {
            if (tipoAssento.getIdTipoAssento() == idTipoAssento) {
                return tipoAssento;
            }
        }
        return null;
    }

    public boolean editar(TipoAssento tipoAssento) {
        ArrayList<TipoAssento> tipoAssentos = listar();
        boolean encontrado = false;

        for (TipoAssento ta : tipoAssentos) {
            if (ta.getIdTipoAssento() == tipoAssento.getIdTipoAssento()) {
                ta.setDescricao(tipoAssento.getDescricao());
                ta.setStatus(tipoAssento.getStatus());
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd//tipoassento.txt"))) {
                for (TipoAssento ta : tipoAssentos) {
                    writer.write(ta.getIdTipoAssento() + ";" + ta.getDescricao() + ";" + ta.getStatus());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int getIdTipoAssento() {
        return idTipoAssento;
    }

    public void setIdTipoAssento(int idTipoAssento) {
        this.idTipoAssento = idTipoAssento;
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
