package domain;

public class Pedido {
    protected int cantBN;
    protected int cantColor;
    protected int cantAnillado;

    protected double valorBN;
    protected double valorColor;
    protected double valorAnillado;
    protected double valorBruto;
    protected double valorDescuento;
    protected double valorTotal;

    boolean confirmado;

    ServicioImpresion servicios;
    Cliente cliente;

    public Pedido(Cliente cliente, ServicioImpresion servicios, int cantBN, int cantColor, int cantAnillado) {
        if (cantBN < 0 || cantColor < 0 || cantAnillado < 0) {
            throw new IllegalArgumentException("Las cantidades no pueden ser negativas.");
        }

        this.cliente = cliente;
        this.servicios = servicios;
        this.cantBN = cantBN;
        this.cantColor = cantColor;
        this.cantAnillado = cantAnillado;

        this.confirmado = false;
    }

    public void sumarValorItems() {
        if (cantBN >= 100) {
            valorBN = cantBN * servicios.precioBN_Mayor;
        } else {
            valorBN = cantBN * servicios.precioBN;
        }

        if (cantColor >= 50) {
            valorColor = cantColor * servicios.precioColor_Mayor;
        } else {
            valorColor = cantColor * servicios.precioColor;
        }

        valorAnillado = cantAnillado * servicios.precioAnillado;

        valorBruto = valorBN + valorColor + valorAnillado;
    }

    public void calcularDesc() {

        valorDescuento = 0;

        boolean condicion10 =
                cantAnillado > 0 &&
                (cantBN + cantColor) >= 30;

        if (condicion10) {
            valorDescuento = valorBruto * 0.10;
        } else if (valorBruto > 40000) {
            valorDescuento = valorBruto * 0.05;
        }
    }

    public void calcularTotal() {
        valorTotal = valorBruto - valorDescuento;
        confirmado = true;
    }

    public boolean estadoPedido() {
        return confirmado;
    }

    public void mostrarPedido() {

        cliente.mostrarInfoCliente();

        System.out.println("\n--- Detalle del Pedido ---");

        System.out.println("Impresión B/N (" + cantBN + "): $" + valorBN);
        System.out.println("Impresión Color (" + cantColor + "): $" + valorColor);
        System.out.println("Anillado (" + cantAnillado + "): $" + valorAnillado);

        System.out.println("\nTotal Bruto: $" + valorBruto);
        System.out.println("Descuento: $" + valorDescuento);
        System.out.println("Total Final: $" + valorTotal);

        System.out.println("\nEstado: " + (confirmado ? "Confirmado" : "Sin confirmar"));
    }
}
