import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        Genero generoHandler = new Genero(0, "", "");
        Filme filmeHandler = new Filme(0, "", 0, null, "");
        Ator atorHandler = new Ator("", "", "", 0); 

        while (running) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Gerenciar Gêneros");
            System.out.println("2. Gerenciar Filmes");
            System.out.println("3. Gerenciar Atores");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int menuOption = scanner.nextInt();
            scanner.nextLine(); 

            switch (menuOption) {
                case 1 -> gerenciarGenero(scanner, generoHandler);
                case 2 -> gerenciarFilme(scanner, filmeHandler, generoHandler);
                case 3 -> gerenciarAtor(scanner, atorHandler); 
                case 4 -> {
                    running = false;
                    System.out.println("Saindo do programa.");
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }


    private static void gerenciarGenero(Scanner scanner, Genero generoHandler) {
        boolean running = true;

        while (running) {
            System.out.println("\nGerenciamento de Gêneros:");
            System.out.println("1. Inserir novo gênero");
            System.out.println("2. Listar todos os gêneros");
            System.out.println("3. Consultar gênero por descrição");
            System.out.println("4. Editar gênero");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();

                    System.out.print("Status: ");
                    String status = scanner.nextLine();

                    Genero genero = new Genero(id, desc, status);
                    if (generoHandler.inserir(genero)) {
                        System.out.println("Gênero inserido com sucesso.");
                    } else {
                        System.out.println("Erro ao inserir o gênero.");
                    }
                }

                case 2 -> {
                    System.out.println("\nLista de gêneros:");
                    for (Genero genero : generoHandler.listar()) {
                        System.out.println("ID: " + genero.getId() + ", Descrição: " + genero.getDesc() + ", Status: " + genero.getStatus());
                    }
                }

                case 3 -> {
                    System.out.print("Digite a descrição para consulta: ");
                    String consultaDesc = scanner.nextLine();
                    Genero generoConsultado = generoHandler.consultar(consultaDesc);

                    if (generoConsultado != null) {
                        System.out.println("ID: " + generoConsultado.getId() + ", Descrição: " + generoConsultado.getDesc() + ", Status: " + generoConsultado.getStatus());
                    } else {
                        System.out.println("Gênero não encontrado.");
                    }
                }

                case 4 -> {
                    System.out.print("ID do gênero a editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nova descrição: ");
                    String novaDesc = scanner.nextLine();

                    System.out.print("Novo status: ");
                    String novoStatus = scanner.nextLine();

                    if (generoHandler.editar(idEditar, novaDesc, novoStatus)) {
                        System.out.println("Gênero editado com sucesso.");
                    } else {
                        System.out.println("Erro ao editar o gênero.");
                    }
                }

                case 5 -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

 
    private static void gerenciarFilme(Scanner scanner, Filme filmeHandler, Genero generoHandler) {
        boolean running = true;

        while (running) {
            System.out.println("\nGerenciamento de Filmes:");
            System.out.println("1. Inserir novo filme");
            System.out.println("2. Listar todos os filmes");
            System.out.println("3. Consultar filme por ID");
            System.out.println("4. Editar filme");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> {
                    System.out.print("ID do filme: ");
                    int idFilme = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Título do filme: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Classificação indicativa: ");
                    int classificacao = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Status do filme: ");
                    String status = scanner.nextLine();

                    System.out.print("Descrição do gênero do filme: ");
                    String descGenero = scanner.nextLine();

                    Genero genero = generoHandler.consultar(descGenero);
                    if (genero == null) {
                        System.out.println("Gênero não encontrado. Filme não será cadastrado.");
                        break;
                    }

                    Filme filme = new Filme(idFilme, titulo, classificacao, genero, status);
                    if (filmeHandler.cadastrar(filme)) {
                        System.out.println("Filme cadastrado com sucesso.");
                    } else {
                        System.out.println("Erro ao cadastrar o filme.");
                    }
                }

                case 2 -> {
                    System.out.println("\nLista de filmes:");
                    for (Filme filme : filmeHandler.listar()) {
                        System.out.println("ID: " + filme.getIdFilme() + ", Título: " + filme.getTitulo() + 
                                           ", Classificação: " + filme.getClassificacao() + 
                                           ", Gênero: " + filme.getGenero().getDesc() + 
                                           ", Status: " + filme.getStatus());
                    }
                }

                case 3 -> {
                    System.out.print("Digite o ID do filme para consulta: ");
                    int idFilme = scanner.nextInt();
                    scanner.nextLine();

                    Filme filmeConsultado = filmeHandler.consultar(idFilme);

                    if (filmeConsultado != null) {
                        System.out.println("ID: " + filmeConsultado.getIdFilme() + ", Título: " + filmeConsultado.getTitulo() + 
                                           ", Classificação: " + filmeConsultado.getClassificacao() + 
                                           ", Gênero: " + filmeConsultado.getGenero().getDesc() + 
                                           ", Status: " + filmeConsultado.getStatus());
                    } else {
                        System.out.println("Filme não encontrado.");
                    }
                }

                case 4 -> {
                    System.out.print("ID do filme a editar: ");
                    int idFilme = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo título: ");
                    String novoTitulo = scanner.nextLine();

                    System.out.print("Nova classificação indicativa: ");
                    int novaClassificacao = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo status: ");
                    String novoStatus = scanner.nextLine();

                    Filme filme = new Filme(idFilme, novoTitulo, novaClassificacao, null, novoStatus);
                    if (filmeHandler.editar(filme)) {
                        System.out.println("Filme editado com sucesso.");
                    } else {
                        System.out.println("Erro ao editar o filme.");
                    }
                }

                case 5 -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    
    private static void gerenciarAtor(Scanner scanner, Ator atorHandler) {
        boolean running = true;

        while (running) {
            System.out.println("\nGerenciamento de Atores:");
            System.out.println("1. Inserir novo ator");
            System.out.println("2. Listar todos os atores");
            System.out.println("3. Consultar ator por registro");
            System.out.println("4. Editar ator");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> {
                    System.out.print("Registro: ");
                    int registro = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    Ator ator = new Ator(nome, email, cpf, registro);
                    if (atorHandler.cadastrar(ator)) {
                        System.out.println("Ator cadastrado com sucesso.");
                    } else {
                        System.out.println("Erro ao cadastrar ator.");
                    }
                }

                case 2 -> {
                    System.out.println("\nLista de atores:");
                    for (Ator ator : atorHandler.listar()) {
                        System.out.println("Registro: " + ator.getRegistro() + ", Nome: " + ator.getNome() + 
                                           ", Email: " + ator.getEmail() + ", CPF: " + ator.getCpf());
                    }
                }

                case 3 -> {
                    System.out.print("Digite o registro para consulta: ");
                    int registro = scanner.nextInt();
                    scanner.nextLine();

                    Ator atorConsultado = atorHandler.consultar(registro);

                    if (atorConsultado != null) {
                        System.out.println("Registro: " + atorConsultado.getRegistro() + ", Nome: " + atorConsultado.getNome() + 
                                           ", Email: " + atorConsultado.getEmail() + ", CPF: " + atorConsultado.getCpf());
                    } else {
                        System.out.println("Ator não encontrado.");
                    }
                }

                case 4 -> {
                    System.out.print("Registro do ator a editar: ");
                    int registro = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine();

                    System.out.print("Novo CPF: ");
                    String novoCpf = scanner.nextLine();

                    Ator ator = new Ator(novoNome, novoEmail, novoCpf, registro);
                    if (atorHandler.editar(ator)) {
                        System.out.println("Ator editado com sucesso.");
                    } else {
                        System.out.println("Erro ao editar ator.");
                    }
                }

                case 5 -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
