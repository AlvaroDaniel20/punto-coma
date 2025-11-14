package app;

import domain.Cliente;
import domain.Pedido;
import domain.ServicioImpresion;

public class Main {
    public static void main(String[] args) {
        ServicioImpresion servicios = new ServicioImpresion();

        Cliente cliente = new Cliente("Juan Armando", "3101234567");

        int cantBN = 120;
        int cantColor = 30;
        int cantAnillado = 2;

        System.out.println();
        ServicioImpresion.mostrarOpciones();
        System.out.println();

        Pedido pedido = new Pedido(cliente, servicios, cantBN, cantColor, cantAnillado);

        pedido.sumarValorItems();
        pedido.calcularDesc();
        pedido.calcularTotal();

        pedido.mostrarPedido();
    }
}
