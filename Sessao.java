import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Sessao {
    private int idSessao;
    private LocalDateTime dataHoraSessao;
    private Filme filme;
    private Sala sala;
    private Funcionario funcionario;
    private String status;

    public Sessao(int idSessao, LocalDateTime dataHoraSessao, Filme filme, Sala sala, Funcionario funcionario, String status) {
        this.idSessao = idSessao;
        this.dataHoraSessao = dataHoraSessao;
        this.filme = filme;
        this.sala = sala;
        this.funcionario = funcionario;
        this.status = status;
    }

    public boolean cadastrar(Sessao sessao) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd//sessao.txt", true))) {
            writer.write(sessao.getIdSessao() + ";" + sessao.getDataHoraSessao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ";" + 
                         sessao.getFilme().getIdFilme() + ";" + sessao.getSala().getIdSala() + ";" + sessao.getFuncionario().getMatricula() + ";" + sessao.getStatus());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<Sessao> listar() {
        ArrayList<Sessao> sessoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("bd//sessao.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idSessao = Integer.parseInt(dados[0]);
                LocalDateTime dataHoraSessao = LocalDateTime.parse(dados[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                int idFilme = Integer.parseInt(dados[2]);
                int idSala = Integer.parseInt(dados[3]);
                int matriculaFuncionario = Integer.parseInt(dados[4]);
                String status = dados[5];

                
                Filme filme = new Filme(idFilme, null, 0, null, null);
                Sala sala = new Sala(idSala, 0, null, null);
                Funcionario funcionario = new Funcionario(null, null, null, matriculaFuncionario, null);

                sessoes.add(new Sessao(idSessao, dataHoraSessao, filme, sala, funcionario, status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessoes;
    }

    public Sessao consultar(int idSessao) {
        for (Sessao sessao : listar()) {
            if (sessao.getIdSessao() == idSessao) {
                return sessao;
            }
        }
        return null; 
    }

    public boolean editar(Sessao sessao) {
        ArrayList<Sessao> sessoes = listar();
        boolean encontrado = false;

        for (Sessao s : sessoes) {
            if (s.getIdSessao() == sessao.getIdSessao()) {
                s.setDataHoraSessao(sessao.getDataHoraSessao());
                s.setFilme(sessao.getFilme());
                s.setSala(sessao.getSala());
                s.setFuncionario(sessao.getFuncionario());
                s.setStatus(sessao.getStatus());
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd//sessao.txt"))) {
                for (Sessao s : sessoes) {
                    writer.write(s.getIdSessao() + ";" + s.getDataHoraSessao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ";" + 
                                 s.getFilme().getIdFilme() + ";" + 
                                 s.getSala().getIdSala() + ";" + s.getFuncionario().getMatricula() + ";" + s.getStatus());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public LocalDateTime getDataHoraSessao() {
        return dataHoraSessao;
    }

    public void setDataHoraSessao(LocalDateTime dataHoraSessao) {
        this.dataHoraSessao = dataHoraSessao;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
