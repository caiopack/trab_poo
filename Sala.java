public class Sala {
    private int idSala;
    private int capacidadeSala;
    private String desc;
    private String status;
    
    public Sala(int idSala, int capacidadeSala, String desc, String status) {
        this.idSala = idSala;
        this.capacidadeSala = capacidadeSala;
        this.desc = desc;
        this.status = status;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getCapacidadeSala() {
        return capacidadeSala;
    }

    public void setCapacidadeSala(int capacidadeSala) {
        this.capacidadeSala = capacidadeSala;
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
