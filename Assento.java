public class Assento {
    private int idAssento;
    private TipoAssento tipoAssento;
    
    public Assento(int idAssento, TipoAssento tipoAssento) {
        this.idAssento = idAssento;
        this.tipoAssento = tipoAssento;
    }
    
    public int getIdAssento() {
        return idAssento;
    }
    
    public void setIdAssento(int idAssento) {
        this.idAssento = idAssento;
    }
    public TipoAssento getTipoAssento() {
        return tipoAssento;
    }
    public void setTipoAssento(TipoAssento tipoAssento) {
        this.tipoAssento = tipoAssento;
    }
    
        
}
