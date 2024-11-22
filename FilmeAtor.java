public class FilmeAtor {
    private int Idfilme;
    private Ator ator;
    private Filme filme;
    private String personagem;
    private boolean principal;
    
    
    public FilmeAtor(int idfilme, Ator ator, Filme filme, String personagem, boolean principal) {
        Idfilme = idfilme;
        this.ator = ator;
        this.filme = filme;
        this.personagem = personagem;
        this.principal = principal;
    }
    public int getIdfilme() {
        return Idfilme;
    }
    public void setIdfilme(int idfilme) {
        Idfilme = idfilme;
    }
    public Ator getAtor() {
        return ator;
    }
    public void setAtor(Ator ator) {
        this.ator = ator;
    }
    public Filme getFilme() {
        return filme;
    }
    public void setFilme(Filme filme) {
        this.filme = filme;
    }
    public String getPersonagem() {
        return personagem;
    }
    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }
    public boolean isPrincipal() {
        return principal;
    }
    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}
