# Comversor De Monedas App

¡Bienvenido a la **Comversor De Monedas App**! Este proyecto es una aplicación de consola escrita en Java que utiliza la API de **ExchangeRate-api** para realizar conversiones de monedas en tiempo real. La aplicación ofrece una experiencia interactiva para los usuarios, permitiéndoles consultar y convertir valores de diferentes monedas de manera fácil y rápida.

## Características

- **Consulta de divisas**: Puedes ver el valor actual de una moneda específica con respecto a otras.
- **Conversión directa**: Selecciona dos monedas y descubre el valor de una con respecto a la otra.
- **Conversión de cantidades**: Introduce una cantidad en una moneda y conviértela a otra.

## Cómo funciona

1. El programa inicia con un menú principal donde puedes elegir entre las siguientes opciones:
   - **Ver valor de una moneda respecto a otras**.
   - **Convertir una moneda en otra**.
   - **Convertir una cantidad específica de una moneda en otra**.
   - **Salir del programa**.

2. Dependiendo de la opción elegida, el programa te pedirá que selecciones monedas específicas de una lista y, en algunos casos, que ingreses una cantidad para convertir.

## Tecnologías utilizadas

- **Java**: Para la lógica de la aplicación y la programación orientada a objetos.
- **ExchangeRate-api**: API externa utilizada para obtener los valores de conversión de divisas en tiempo real.

## Instalación

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/tu-usuario/nombre-repositorio.git
   ```
2. Asegúrate de tener Java instalado en tu sistema.
3. Compila y ejecuta el programa desde tu terminal:
   ```bash
   javac Main.java
   java Main
   ```

## Notas importantes

- Para que la aplicación funcione correctamente, es necesario contar con una conexión a internet, ya que se hace uso de la API externa.
- La API ExchangeRate-api requiere una clave de acceso para funcionar. Asegúrate de tener tu propia clave y configurarla en la clase `Conexion`.

