import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class Funcionario extends Pessoa {
    private int matricula;
    private Date horarioTrabalho;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Date getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(Date horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }

    public Funcionario(String cpf, String nome, String email, int matricula, Date horarioTrabalho) {
        super(cpf, nome, email);
        this.matricula = matricula;
        this.horarioTrabalho = horarioTrabalho;
    }

    public boolean cadastrar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd\\funcionario.txt", true))) {
            writer.write(this.getMatricula() + "; " +
                         this.getCpf() + "; " +
                         this.getNome() + "; " +
                         this.getEmail() + "; " +
                         this.getHorarioTrabalho());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

  
    public ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try (
            FileReader fr = new FileReader("bd\\funcionario.txt");
            BufferedReader reader = new BufferedReader(fr)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int matricula = Integer.parseInt(dados[0]);
                String cpf = dados[1];
                String nome = dados[2];
                String email = dados[3];
                Date horarioTrabalho = Date.valueOf(dados[4]);  
                funcionarios.add(new Funcionario(cpf, nome, email, matricula, horarioTrabalho));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }


    public Funcionario consultar(int matricula) {
        for (Funcionario funcionario : listar()) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }
        return null;
    }

    
    public boolean editar(int matricula, String novoNome, String novoEmail, Date novoHorarioTrabalho) {
        ArrayList<Funcionario> funcionarios = listar();
        boolean encontrado = false;

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                funcionario.setNome(novoNome);
                funcionario.setEmail(novoEmail);
                funcionario.setHorarioTrabalho(novoHorarioTrabalho);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (
                FileWriter fw = new FileWriter("bd\\funcionario.txt");
                BufferedWriter writer = new BufferedWriter(fw)) {
                for (Funcionario funcionario : funcionarios) {
                    writer.write(funcionario.getMatricula() + ";" + funcionario.getCpf() + ";" + funcionario.getNome() + ";" + funcionario.getEmail() + ";" + funcionario.getHorarioTrabalho());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}