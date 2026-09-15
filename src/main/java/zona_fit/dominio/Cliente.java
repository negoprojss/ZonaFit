package zona_fit.dominio;

import java.util.Objects;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private int menbrecia;

    public Cliente(){}

    public  Cliente(int id){
        this.id = id;
    }

    public Cliente(String nombre, String apellido, int menbrecia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.menbrecia = menbrecia;
    }

    public void  Cliente(int id, String nombre, String apellido, int menbrecia){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.menbrecia = menbrecia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getMenbrecia() {
        return menbrecia;
    }

    public void setMenbrecia(int menbrecia) {
        this.menbrecia = menbrecia;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", menbrecia=" + menbrecia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && menbrecia == cliente.menbrecia && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, menbrecia);
    }

}
