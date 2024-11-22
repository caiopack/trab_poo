import java.io.*;
import java.util.ArrayList;

public class Genero {
    private int id;
    private String desc;
    private String status;

    public Genero(int id, String desc, String status) {
        this.id = id;
        this.desc = desc;
        this.status = status;
    }

    public boolean inserir(Genero genero) {
        try (
            FileWriter fw = new FileWriter("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\trab poo\\bd\\genero.txt", true);
            BufferedWriter writer = new BufferedWriter(fw)) {
            writer.write(genero.getId()+ ";" + genero.getDesc() + ";" + genero.getStatus());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Genero> listar() {
        ArrayList<Genero> generos = new ArrayList<>();
        try (
            FileReader fr = new FileReader("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\trab poo\\bd\\genero.txt");
            BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String desc = dados[1];
                String status = dados[2];
                generos.add(new Genero(id, desc, status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generos;
    }

    public Genero consultar(String desc) {
        for (Genero genero : listar()) {
            if (genero.desc.equalsIgnoreCase(desc)) {
                return genero;
            }
        }
        return null;
    }

    public boolean editar(int id, String novaDesc, String novoStatus) {
        ArrayList<Genero> generos = listar();
        boolean encontrado = false;

        for (Genero genero : generos) {
            if (genero.id == id) {
                genero.desc = novaDesc;
                genero.status = novoStatus;
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (
                FileWriter fw = new FileWriter("C:\\Users\\ccaio\\OneDrive\\Área de Trabalho\\trab poo\\bd\\genero.txt");
                BufferedWriter writer = new BufferedWriter(fw)) {
                for (Genero genero : generos) {
                    writer.write(genero.id + ";" + genero.desc + ";" + genero.status);
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
