package domain;

public class Cliente {
    protected String nombre;
    protected String telefono;

    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public void mostrarInfoCliente() {
        System.out.println("Cliente: " + nombre);
        System.out.println("Teléfono: " + telefono);
    }
}
