import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Gerenciar Gêneros");
            System.out.println("2. Gerenciar Filmes");
            System.out.println("3. Gerenciar Atores");
            System.out.println("4. Gerenciar Salas");
            System.out.println("5. Gerenciar Assentos");
            System.out.println("6. Gerenciar Tipos de Assento");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int menuOption = scanner.nextInt();
            scanner.nextLine();

            switch (menuOption) {
                case 1 -> gerenciarGenero(scanner);
                case 2 -> gerenciarFilme(scanner);
                case 3 -> gerenciarAtor(scanner);
                case 4 -> gerenciarSala(scanner);
                case 5 -> gerenciarAssento(scanner);
                case 6 -> gerenciarTipoAssento(scanner);
                case 7 -> running = false;
                default -> System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
    private static void gerenciarGenero(Scanner scanner) {
        boolean generoMenu = true;
        while (generoMenu) {
            System.out.println("\nGerenciamento de Gêneros:");
            System.out.println("1. Cadastrar Gênero");
            System.out.println("2. Listar Gêneros");
            System.out.println("3. Consultar Gênero");
            System.out.println("4. Editar Gênero");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int generoOption = scanner.nextInt();
            scanner.nextLine(); 

            Genero generoHandler = new Genero(0, "", "");
            switch (generoOption) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();

                    System.out.print("Status: ");
                    String status = scanner.nextLine();

                    Genero novoGenero = new Genero(id, desc, status);
                    if (generoHandler.inserir(novoGenero)) {
                        System.out.println("Gênero cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar o gênero.");
                    }
                }
                case 2 -> {
                    System.out.println("\nLista de Gêneros:");
                    for (Genero g : generoHandler.listar()) {
                        System.out.println("ID: " + g.getId() + ", Descrição: " + g.getDesc() + ", Status: " + g.getStatus());
                    }
                }
                case 3 -> {
                    System.out.print("Descrição do gênero para consulta: ");
                    String consultaDesc = scanner.nextLine();
                    Genero genero = generoHandler.consultar(consultaDesc);

                    if (genero != null) {
                        System.out.println("ID: " + genero.getId() + ", Descrição: " + genero.getDesc() + ", Status: " + genero.getStatus());
                    } else {
                        System.out.println("Gênero não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do gênero a editar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nova descrição: ");
                    String novaDesc = scanner.nextLine();

                    System.out.print("Novo status: ");
                    String novoStatus = scanner.nextLine();

                    if (generoHandler.editar(id, novaDesc, novoStatus)) {
                        System.out.println("Gênero editado com sucesso!");
                    } else {
                        System.out.println("Erro ao editar o gênero.");
                    }
                }
                case 5 -> generoMenu = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void gerenciarFilme(Scanner scanner) {
        boolean filmeMenu = true;
        while (filmeMenu) {
            System.out.println("\nGerenciamento de Filmes:");
            System.out.println("1. Cadastrar Filme");
            System.out.println("2. Listar Filmes");
            System.out.println("3. Consultar Filme");
            System.out.println("4. Editar Filme");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int filmeOption = scanner.nextInt();
            scanner.nextLine(); 

            Filme filmeHandler = new Filme(0, "", 0, null, "");
            Genero generoHandler = new Genero(0, "", "");

            switch (filmeOption) {
                case 1 -> {
                    System.out.print("ID do filme: ");
                    int idFilme = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Título do filme: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Classificação: ");
                    int classificacao = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Gênero do filme: ");
                    String descGenero = scanner.nextLine();

                    Genero genero = generoHandler.consultar(descGenero);
                    if (genero == null) {
                        System.out.println("Gênero não encontrado. Filme não cadastrado.");
                        break;
                    }

                    System.out.print("Status: ");
                    String status = scanner.nextLine();

                    Filme filme = new Filme(idFilme, titulo, classificacao, genero, status);
                    if (filmeHandler.cadastrar(filme)) {
                        System.out.println("Filme cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar o filme.");
                    }
                }
                case 2 -> {
                    System.out.println("\nLista de Filmes:");
                    for (Filme f : filmeHandler.listar()) {
                        System.out.println("ID: " + f.getIdFilme() + ", Título: " + f.getTitulo() + ", Classificação: " + f.getClassificacao() +
                                ", Gênero: " + f.getGenero().getDesc() + ", Status: " + f.getStatus());
                    }
                }
                case 3 -> {
                    System.out.print("ID do filme para consulta: ");
                    int idFilme = scanner.nextInt();
                    scanner.nextLine();

                    Filme filme = filmeHandler.consultar(idFilme);

                    if (filme != null) {
                        System.out.println("ID: " + filme.getIdFilme() + ", Título: " + filme.getTitulo() + ", Classificação: " + filme.getClassificacao() +
                                ", Gênero: " + filme.getGenero().getDesc() +", Status: " + filme.getStatus());
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

                    System.out.print("Nova classificação: ");
                    int novaClassificacao = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo status: ");
                    String novoStatus = scanner.nextLine();

                    Filme filme = new Filme(idFilme, novoTitulo, novaClassificacao, null, novoStatus);
                    if (filmeHandler.editar(filme)) {
                        System.out.println("Filme editado com sucesso!");
                    } else {
                        System.out.println("Erro ao editar o filme.");
                    }
                }
                case 5 -> filmeMenu = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void gerenciarAtor(Scanner scanner) {
        boolean atorMenu = true;
        while (atorMenu) {
            System.out.println("\nGerenciamento de Atores:");
            System.out.println("1. Cadastrar Ator");
            System.out.println("2. Listar Atores");
            System.out.println("3. Consultar Ator");
            System.out.println("4. Editar Ator");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int atorOption = scanner.nextInt();
            scanner.nextLine();  

            Ator atorHandler = new Ator("", "", "", 0);  

            switch (atorOption) {
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
                    System.out.println("\nLista de Atores:");
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
                case 5 -> atorMenu = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void gerenciarSala(Scanner scanner) {
        boolean salaMenu = true;
        while (salaMenu) {
            System.out.println("\nGerenciamento de Salas:");
            System.out.println("1. Cadastrar Sala");
            System.out.println("2. Listar Salas");
            System.out.println("3. Consultar Sala");
            System.out.println("4. Editar Sala");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int salaOption = scanner.nextInt();
            scanner.nextLine(); 

            Sala salaHandler = new Sala(0, 0, "", "");

            switch (salaOption) {
                case 1 -> {
                    System.out.print("ID da Sala: ");
                    int idSala = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Capacidade da Sala: ");
                    int capacidadeSala = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.print("Descrição da Sala: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Status da Sala: ");
                    String status = scanner.nextLine();

                    Sala novaSala = new Sala(idSala, capacidadeSala, descricao, status);
                    if (salaHandler.cadastrar(novaSala)) {
                        System.out.println("Sala cadastrada com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar a sala.");
                    }
                }
                case 2 -> {
                    System.out.println("\nLista de Salas:");
                    for (Sala sala : salaHandler.listar()) {
                        System.out.println("ID: " + sala.getIdSala() + ", Capacidade: " + sala.getCapacidadeSala() +
                                ", Descrição: " + sala.getDescricao() + ", Status: " + sala.getStatus());
                    }
                }
                case 3 -> {
                    System.out.print("Digite o ID da Sala para consulta: ");
                    int idSala = scanner.nextInt();
                    scanner.nextLine();  

                    Sala salaConsultada = salaHandler.consultar(idSala);

                    if (salaConsultada != null) {
                        System.out.println("ID: " + salaConsultada.getIdSala() + ", Capacidade: " + salaConsultada.getCapacidadeSala() +
                                ", Descrição: " + salaConsultada.getDescricao() + ", Status: " + salaConsultada.getStatus());
                    } else {
                        System.out.println("Sala não encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("ID da Sala a editar: ");
                    int idSala = scanner.nextInt();
                    scanner.nextLine();  
                
                    System.out.print("Nova capacidade: ");
                    int novaCapacidade = scanner.nextInt();
                    scanner.nextLine();
                
                    System.out.print("Nova descrição: ");
                    String novaDescricao = scanner.nextLine();
                
                    System.out.print("Novo status: ");
                    String novoStatus = scanner.nextLine();
                
                    if (salaHandler.editar(idSala, novaCapacidade, novaDescricao, novoStatus)) {
                        System.out.println("Sala editada com sucesso!");
                    } else {
                        System.out.println("Erro ao editar a sala.");
                    }
                }
                
                case 5 -> salaMenu = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    private static void gerenciarAssento(Scanner scanner) {
        boolean assentoMenu = true;
        while (assentoMenu) {
            System.out.println("\nGerenciamento de Assentos:");
            System.out.println("1. Cadastrar Assento");
            System.out.println("2. Listar Assentos");
            System.out.println("3. Consultar Assento");
            System.out.println("4. Editar Assento");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int assentoOption = scanner.nextInt();
            scanner.nextLine(); 
    
            Assento assentoHandler = new Assento(0, new TipoAssento(0, "", ""));
            
            switch (assentoOption) {
                case 1 -> {
                    System.out.print("ID do Assento: ");
                    int idAssento = scanner.nextInt();
                    scanner.nextLine();
    
                    System.out.print("Descrição do Tipo de Assento: ");
                    String descricaoTipoAssento = scanner.nextLine();
    
                    System.out.print("Status do Tipo de Assento: ");
                    String statusTipoAssento = scanner.nextLine();
    
                    TipoAssento tipoAssento = new TipoAssento(0, descricaoTipoAssento, statusTipoAssento);
                    Assento novoAssento = new Assento(idAssento, tipoAssento);
                    
                    if (assentoHandler.cadastrar(novoAssento)) {
                        System.out.println("Assento cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar o assento.");
                    }
                }
                case 2 -> {
                    System.out.println("\nLista de Assentos:");
                    for (Assento a : assentoHandler.listar()) {
                        System.out.println(a);
                    }
                }
                case 3 -> {
                    System.out.print("Digite o ID do Assento para consulta: ");
                    int idAssento = scanner.nextInt();
                    scanner.nextLine();
    
                    Assento assentoConsultado = assentoHandler.consultar(idAssento);
                    if (assentoConsultado != null) {
                        System.out.println(assentoConsultado);
                    } else {
                        System.out.println("Assento não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do Assento a editar: ");
                    int idAssento = scanner.nextInt();
                    scanner.nextLine();
    
                    Assento assentoExistente = assentoHandler.consultar(idAssento);
                    if (assentoExistente == null) {
                        System.out.println("Assento não encontrado.");
                        break;
                    }
    
                    System.out.println("Assento atual: " + assentoExistente);
    
                    System.out.print("Nova descrição do tipo de assento: ");
                    String novaDescricao = scanner.nextLine();
    
                    System.out.print("Novo status do tipo de assento: ");
                    String novoStatus = scanner.nextLine();
    
                    TipoAssento novoTipoAssento = new TipoAssento(0, novaDescricao, novoStatus);
                    Assento assentoEditado = new Assento(idAssento, novoTipoAssento);
                    
                    if (assentoHandler.editar(assentoEditado)) {
                        System.out.println("Assento editado com sucesso!");
                    } else {
                        System.out.println("Erro ao editar o assento.");
                    }
                }
                case 5 -> assentoMenu = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void gerenciarTipoAssento(Scanner scanner) {
        boolean tipoAssentoMenu = true;
        while (tipoAssentoMenu) {
            System.out.println("\nGerenciamento de Tipo de Assento:");
            System.out.println("1. Inserir Tipo de Assento");
            System.out.println("2. Listar Tipos de Assento");
            System.out.println("3. Consultar Tipo de Assento");
            System.out.println("4. Editar Tipo de Assento");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int tipoAssentoOption = scanner.nextInt();
            scanner.nextLine(); 
    
            TipoAssento tipoAssentoHandler = new TipoAssento(0, "", "");
    
            switch (tipoAssentoOption) {
                case 1 -> {
                    System.out.print("ID do Tipo de Assento: ");
                    int idTipoAssento = scanner.nextInt();
                    scanner.nextLine();
    
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
    
                    System.out.print("Status: ");
                    String status = scanner.nextLine();
    
                    TipoAssento novoTipoAssento = new TipoAssento(idTipoAssento, descricao, status);
                    if (tipoAssentoHandler.cadastrar(novoTipoAssento)) {
                        System.out.println("Tipo de Assento cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar Tipo de Assento.");
                    }
                }
                case 2 -> {
                    System.out.println("\nLista de Tipos de Assento:");
                    for (TipoAssento ta : tipoAssentoHandler.listar()) {
                        System.out.println(ta);
                    }
                }
                case 3 -> {
                    System.out.print("ID do Tipo de Assento para consulta: ");
                    int idTipoAssento = scanner.nextInt();
                    scanner.nextLine();
    
                    TipoAssento tipoAssentoConsultado = tipoAssentoHandler.consultar(idTipoAssento);
                    if (tipoAssentoConsultado != null) {
                        System.out.println(tipoAssentoConsultado);
                    } else {
                        System.out.println("Tipo de Assento não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do Tipo de Assento a editar: ");
                    int idTipoAssento = scanner.nextInt();
                    scanner.nextLine();
    
                    TipoAssento tipoAssentoExistente = tipoAssentoHandler.consultar(idTipoAssento);
                    if (tipoAssentoExistente == null) {
                        System.out.println("Tipo de Assento não encontrado.");
                        break;
                    }
    
                    System.out.println("Tipo de Assento atual: " + tipoAssentoExistente);
    
                    System.out.print("Nova descrição (ou Enter para manter): ");
                    String novaDescricao = scanner.nextLine();
                    novaDescricao = novaDescricao.isEmpty() ? tipoAssentoExistente.getDescricao() : novaDescricao;
    
                    System.out.print("Novo status (ou Enter para manter): ");
                    String novoStatus = scanner.nextLine();
                    novoStatus = novoStatus.isEmpty() ? tipoAssentoExistente.getStatus() : novoStatus;
    
                    TipoAssento tipoAssentoEditado = new TipoAssento(idTipoAssento, novaDescricao, novoStatus);
                    if (tipoAssentoHandler.editar(tipoAssentoEditado)) {
                        System.out.println("Tipo de Assento editado com sucesso!");
                    } else {
                        System.out.println("Erro ao editar Tipo de Assento.");
                    }
                }
                case 5 -> tipoAssentoMenu = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    
}

