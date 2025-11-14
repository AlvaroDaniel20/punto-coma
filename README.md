
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

**CA1: Catálogo y cliente registrado**
Dado que se ejecuta el programa con cliente "Juan Armando" (teléfono "3101234567"),

entonces debe mostrar en consola el catálogo completo y los datos del cliente.

![image](./asssets/pruebas%20de%20criterios/image.png)

**CA2: Cálculo de descuentos por volumen**
Dado que se ejecuta con 120 B/N, 30 color y 2 anillado,
entonces debe mostrar:

- B/N @ $150/u (descuento por ≥100) = $18000.0

- Color @ $500/u (sin descuento, <50) = $15000.0

- Anillado @ $3000/u = $6000.0

![image](./asssets/pruebas%20de%20criterios/CA2.png)

**CA3: Aplicación correcta del descuento 10% (anillado + impresiones ≥30)**
Dado que se ejecuta con 120 B/N, 30 color y 2 anillado,
entonces debe mostrar:

- Total Bruto: $39000.0

- Descuento: $3900.0 (10%)

- Total Final: $35100.0

![image](./asssets/pruebas%20de%20criterios/CA3.png)

**CA4: Estado del pedido confirmado**

Dado que se ejecuta el programa completo,

entonces la salida debe mostrar "Estado: Confirmado".

![image](./asssets/pruebas%20de%20criterios/CA4.png)


4. Cuarto paso: Limite de diseño. => 3 clases 

1. ServicioImprension: se encarga de mostrar catalogo y descuentos apicables

2. Cliente: Muestra información de los clientes (Nombre y telefono)

3. Pedido: Se encarga de agregar y calcular los items y valores subtotales y totales del pedido. 
Muestra un resumen del pedido


![image](./asssets/uml/WhatsApp%20Image%202025-11-13%20at%207.00.42%20PM.jpeg)

5. Quinto paso: Flujo de consola

Catálogo: muestra las tres entradas del servicio con sus precios y las reglas de precio por volumen.

Cliente: imprime nombre y teléfono del cliente que hizo el pedido (aquí: Juan Armando, 3101234567).

Detalle del pedido: lista cada ítem con cantidad y subtotal calculado:
B/N (120) usa el precio por volumen ($150) → subtotal $18000.0.
Color (30) usa precio normal ($500) → subtotal $15000.0.
Anillado (2) a $3000 c/u → subtotal $6000.0.

Cálculo de totales: suma los subtotales (Total Bruto $39000.0), aplica la regla de descuento correspondiente (10% → $3900.0) y muestra el Total Final ($35100.0).

Estado: marca el pedido como confirmado y lo imprime ("Estado: Confirmado").



6. Estructura modular
punto_coma/
├─ asssets/
│  ├─ uml/
│  └─ pruebas de criterios/
├─ bin/
├─ lib/
├─ src/
│  ├─ app/
│  │  └─ Main.java
│  ├─ domain/
│  │  ├─ Cliente.java
│  │  ├─ Pedido.java
│  │  └─ ServicioImpresion.java
│  └─ service/
└─ README.md


Descripción modular:

La carpeta domain contiene las clases del modelo de negocio: Cliente, Pedido y ServicioImpresion

La carpeta service no contiene archivos.

La carpeta app gestiona el flujo por consola (Main).

El archivo main.java es el punto de entrada del programa y lanza la aplicación.


