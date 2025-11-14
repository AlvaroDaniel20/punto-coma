
# Papelería “Punto & Coma”

En Punto & Coma, estudiantes e independientes hacen encargos de impresión y anillado para recoger el mismo día. El sistema registra nombre y teléfono del cliente y permite crear un pedido con ítems de este catálogo: Impresión B/N ($200 c/u, o $150 c/u si son 100 o más), Impresión Color ($500 c/u, o $400 c/u si son 50 o más), y Anillado ($3.000 c/u, sin precio por volumen). El cálculo funciona así: primero determinar subtotales por ítem aplicando precio por volumen cuando corresponda; luego sumar el total bruto; después aplicar un solo beneficio: si el pedido incluye al menos un anillado y la suma de impresiones (B/N + Color) es ≥ 30, se aplica 10% de descuento; de lo contrario, si el total bruto > $40.000, aplicar 5% de descuento; si ninguna condición se cumple, no hay descuento. No se aceptan cantidades ≤ 0. Tras confirmar el pedido, queda bloqueado y el sistema debe mostrar un resumen con detalle (precio aplicado por ítem), total bruto, descuento y total final. No se gestiona inventario ni pagos: solo el flujo de crear → calcular → confirmar → resumir.

---Analisis---


1. primer paso: Extraer requerimientos funcionales.

RF1: Registrar el nombre y teléfono del cliente al iniciar un pedido.

RF2: Permitir crear un pedido compuesto por varios ítems del catálogo.

RF3: Mantener un catálogo fijo con tres ítems:

Impresión Blanco y Negro (B/N): $200 c/u, o $150 c/u si son 100 o más.

Impresión Color: $500 c/u, o $400 c/u si son 50 o más.

Anillado: $3.000 c/u, sin descuento por volumen.

RF4: Calcular subtotales por ítem aplicando el precio unitario según volumen.

RF5: Calcular el total bruto del pedido (suma de subtotales).

RF6: Aplicar un único descuento según las siguientes condiciones:

Si el pedido tiene al menos un anillado y la suma de impresiones (B/N + Color) es igual o mayor a 30, aplicar 10% de descuento sobre el total bruto.

En caso contrario, si el total bruto es mayor a $40.000, aplicar 5% de descuento.

Si ninguna condición se cumple, no aplicar descuento.

RF7: Rechazar cualquier ítem con cantidad menor o igual a cero.

RF8: Una vez confirmado el pedido, debe quedar bloqueado y no permitir modificaciones.

RF9: Mostrar un resumen con detalle: cantidades, precios aplicados, subtotal por ítem, total bruto, descuento y total final.

RF10: Finalizar el flujo tras la confirmación del pedido.

RF11: No gestionar inventario, pagos ni base de datos.

RF12: Realizar toda la interacción mediante consola.
 



2. Segundo paso: Reglas del negocio.

Catálogo fijo y precios:

Impresión Blanco y Negro: precio normal $200, precio por volumen $150 (si son 100 o más).

Impresión Color: precio normal $500, precio por volumen $400 (si son 50 o más).

Anillado: precio fijo $3.000 (sin descuentos por cantidad).

Reglas de descuento:

Si el pedido tiene al menos un anillado y el total de impresiones (B/N + Color) es igual o superior a 30 unidades, se aplica un 10% de descuento sobre el total bruto.

Si no cumple la regla anterior, pero el total bruto supera los $40.000, se aplica un 5% de descuento.

Si ninguna condición se cumple, el descuento es 0%.

Solo se aplica una regla de descuento (no acumulables).

Estados y transiciones del pedido:

Estado “Nuevo”: se agregan ítems y datos del cliente.

Estado “Calculado”: se determinan totales y descuentos.

Estado “Confirmado”: el pedido queda bloqueado y no se puede modificar.

Restricciones técnicas:

No se usa base de datos.

No se maneja inventario.

No hay interfaz gráfica, solo consola.

No se integran pagos ni facturación.

Las cantidades deben ser mayores que cero.

El catálogo y sus precios están fijos en el código.



3. Tercer paso: Criterios de aceptación.


CA1: Registro básico de cliente
Dado que un usuario inicia un pedido,
cuando ingresa nombre “Ana Torres” y teléfono “987654321”,
entonces el sistema debe registrar al cliente y permitir agregar ítems.

CA2: Cálculo de precio por volumen (B/N ≥ 100)
Dado que el cliente agrega 120 impresiones B/N,
cuando se calcula el subtotal,
entonces el precio unitario debe ser $150 y el subtotal debe ser 120 × 150 = $18.000.

CA3: Cálculo de precio por volumen (Color ≥ 50)
Dado que el cliente agrega 60 impresiones color,
cuando se calcula el subtotal,
entonces el precio unitario debe ser $400 y el subtotal debe ser 60 × 400 = $24.000.

CA4: Pedido con anillado y impresiones ≥ 30 (10% descuento)
Dado que el cliente tiene 20 impresiones B/N ($4.000), 10 impresiones color ($5.000) y 1 anillado ($3.000),
cuando se calcula el total,
entonces el total bruto es $12.000, se aplica 10% de descuento ($1.200) y el total final es $10.800.

CA5: Pedido sin anillado, pero total bruto > $40.000 (5% descuento)
Dado que el cliente tiene 100 impresiones color (100 × $400 = $40.000),
cuando se calcula el total,
entonces no se aplica el 10% porque no hay anillado, pero sí un 5% de descuento ($2.000), quedando el total final en $38.000.

CA6: Pedido con valores inválidos
Dado que el usuario intenta agregar una cantidad de 0 unidades,
cuando confirma el ítem,
entonces el sistema debe mostrar “Cantidad inválida. Debe ser mayor que cero.” y no agregar el ítem.

CA7: Confirmación bloquea edición
Dado que el pedido está confirmado,
cuando el usuario intenta agregar un nuevo ítem,
entonces el sistema debe mostrar “Pedido ya confirmado. No se pueden agregar ítems.”


4. Cuarto paso: Limite de diseño. => 3 clases 

1. ServicioImprension: se encarga de mostrar catalogo y descuentos apicables
2. Cliente: Muestra información de los clientes (Nombre y telefono)
3. Pedido: Se encarga de agregar y calcular los items y valores subtotales y totales del pedido. 
Muestra un resumen del pedido



![image](./asssets/WhatsApp%20Image%202025-11-13%20at%207.00.42%20PM.jpeg)

5. Quinto paso: Flujo de consola

Mostrar mensaje de bienvenida: “Bienvenido a Punto & Coma”.

Solicitar el nombre del cliente.

Solicitar el teléfono del cliente.

Mostrar el catálogo con los tres tipos de ítems y sus precios.

Pedir al usuario que elija un tipo de ítem e ingrese la cantidad.

Si la cantidad es menor o igual a cero, mostrar error “Cantidad inválida” y volver a pedir.

Preguntar si desea agregar otro ítem (S/N).

Una vez terminado, calcular totales: subtotales, total bruto, descuento y total final.

Mostrar un resumen preliminar con todos los valores calculados.

Preguntar: “¿Confirmar pedido? (S/N)”.

Si confirma, cambiar el estado a “Confirmado”, mostrar mensaje “Pedido confirmado y bloqueado” y presentar el resumen final.

Si intenta modificar después de confirmar, mostrar mensaje de advertencia: “Pedido ya confirmado. No se pueden agregar ítems.”

Si el usuario intenta confirmar un pedido sin ítems, mostrar error: “Debe agregar al menos un ítem antes de confirmar.”

6. Estructura modular
punto_y_coma/
├── domain/
├── service/
├── app/
└── main.java


Descripción modular:

La carpeta domain contiene las clases del modelo de negocio: Cliente, ItemPedido y Pedido.

La carpeta service contiene las reglas de cálculo y descuentos (ReglaDescuentoService).

La carpeta app gestiona el flujo interactivo por consola (PedidoApp).

El archivo main.py es el punto de entrada del programa y lanza la aplicación.


