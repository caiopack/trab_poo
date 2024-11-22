import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Funcionario extends Pessoa {
    private int matricula;
    private Date horarioTrabalho;
    private static final String FILE_PATH = "funcionario.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Funcionario(String cpf, String nome, String email, int matricula, Date horarioTrabalho) {
        super(cpf, nome, email);
        this.matricula = matricula;
        this.horarioTrabalho = horarioTrabalho;
    }

    public Date getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(Date horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public boolean cadastrar(Funcionario funcionario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(funcionario.getMatricula() + ";" + DATE_FORMAT.format(funcionario.getHorarioTrabalho()) + ";" +
                         funcionario.getCpf() + ";" + funcionario.getNome() + ";" + funcionario.getEmail() + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Funcionario funcionario) {
        ArrayList<Funcionario> funcionarios = listar();
        boolean encontrado = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Funcionario f : funcionarios) {
                if (f.getMatricula() == funcionario.getMatricula()) {
                    writer.write(funcionario.getMatricula() + ";" +
                                 DATE_FORMAT.format(funcionario.getHorarioTrabalho()) + ";" +
                                 funcionario.getCpf() + ";" +
                                 funcionario.getNome() + ";" +
                                 funcionario.getEmail() + "\n");
                    encontrado = true;
                } else {
                    writer.write(f.getMatricula() + ";" +
                                 DATE_FORMAT.format(f.getHorarioTrabalho()) + ";" +
                                 f.getCpf() + ";" +
                                 f.getNome() + ";" +
                                 f.getEmail() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return encontrado;
    }

    public Funcionario consultar(int matricula) {
        ArrayList<Funcionario> funcionarios = listar();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }

        return null; 
    }

    public static ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int matricula = Integer.parseInt(dados[0]);
                Date horarioTrabalho = DATE_FORMAT.parse(dados[1]);
                String cpf = dados[2];
                String nome = dados[3];
                String email = dados[4];
                funcionarios.add(new Funcionario(cpf, nome, email, matricula, horarioTrabalho));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }
}
