package domain;

public class ServicioImpresion {
    protected double precioBN;
    protected double precioBN_Mayor;
    protected double precioColor;
    protected double precioColor_Mayor;
    protected double precioAnillado;


    public ServicioImpresion() {
        this.precioBN = 200;
        this.precioBN_Mayor = 150;
        this.precioColor = 500;
        this.precioColor_Mayor = 400;
        this.precioAnillado = 3000;
    }

    public static void mostrarOpciones() {
        System.out.println("Catálogo de servicios:");
        System.out.println("1. Impresión B/N: $200 (o $150 si son 100 o más)");
        System.out.println("2. Impresión Color: $500 (o $400 si son 50 o más)");
        System.out.println("3. Anillado: $3000");
    }
}
