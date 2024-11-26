import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Instanciando a classe Genero
        Genero generoInstance = new Genero(0, "", "");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir novo gênero");
            System.out.println("2. Listar todos os gêneros");
            System.out.println("3. Consultar gênero por descrição");
            System.out.println("4. Editar gênero");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1 -> {
                    // Inserir novo gênero
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();

                    System.out.print("Status: ");
                    String status = scanner.nextLine();

                    Genero novoGenero = new Genero(id, desc, status);
                    if (generoInstance.inserir(novoGenero)) {
                        System.out.println("Gênero inserido com sucesso.");
                    } else {
                        System.out.println("Erro ao inserir o gênero.");
                    }
                }

                case 2 -> {
                    // Listar todos os gêneros
                    System.out.println("\nLista de gêneros:");
                    for (Genero genero : generoInstance.listar()) {
                        System.out.println("ID: " + genero.getId() + ", Descrição: " + genero.getDesc() + ", Status: " + genero.getStatus());
                    }
                }

                case 3 -> {
                    // Consultar gênero por descrição
                    System.out.print("Digite a descrição para consulta: ");
                    String consultaDesc = scanner.nextLine();
                    Genero generoConsultado = generoInstance.consultar(consultaDesc);

                    if (generoConsultado != null) {
                        System.out.println("ID: " + generoConsultado.getId() + ", Descrição: " + generoConsultado.getDesc() + ", Status: " + generoConsultado.getStatus());
                    } else {
                        System.out.println("Gênero não encontrado.");
                    }
                }

                case 4 -> {
                    // Editar gênero
                    System.out.print("ID do gênero a editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    System.out.print("Nova descrição: ");
                    String novaDesc = scanner.nextLine();

                    System.out.print("Novo status: ");
                    String novoStatus = scanner.nextLine();

                    if (generoInstance.editar(idEditar, novaDesc, novoStatus)) {
                        System.out.println("Gênero editado com sucesso.");
                    } else {
                        System.out.println("Erro ao editar o gênero.");
                    }
                }

                case 5 -> {
                    // Sair do programa
                    running = false;
                    System.out.println("Saindo do programa.");
                }

                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}
