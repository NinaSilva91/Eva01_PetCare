# PetCare

Proyecto realizado en Kotlin para la evaluacion.

El programa simula el funcionamiento de una clinica veterinaria, 
permitiendo registrar pacientes, asignarlos a boxes y calcular el 
valor de la atencion al momento de su salida.

## Como ejecutar

1. Abrir el proyecto en IntelliJ IDEA.
2. Esperar a que Gradle termine de cargar.
3. Abrir `Main.kt`.
4. Ejecutar la funcion `main()`.

El programa realiza las pruebas automaticamente con los pacientes
que estan definidos en `Main.kt`.

## Que hace el programa

El programa permite:

- Registrar pacientes.
- Validar los codigos de atencion.
- Validar el tipo de dueno.
- Asignar un box disponible.
- Registrar la entrada y salida de los pacientes.
- Calcular el valor de la atencion.
- Aplicar descuentos segun el tipo de dueno.
- Generar tickets.
- Consultar los boxes disponibles.
- Consultar pacientes con convenio.
- Calcular la recaudacion.
- Mostrar un informe de cierre.

## Datos utilizados

Para probar el funcionamiento se utilizaron los siguientes pacientes:

- Max, canino, codigo CA12CD, convenio.
- Luna, canino, codigo CA99ZA, particular.
- Misi, felino, codigo FE22TO, particular.
- Loro, exotico, codigo EX44RG, municipal y silvestre.
- Iguana, exotico, codigo EX77RG, particular y no silvestre.

Tambien se prueba el caso de Misi con 18 minutos de atencion,
para comprobar la regla de los felinos que tienen menos de 20 minutos.

## Archivos principales

`Main.kt`  
Contiene las pruebas y ejecuta el programa.

`PetCare.kt`  
Contiene la logica principal para registrar entradas, salidas y
manejar los boxes.

`Paciente.kt`  
Clase principal de los pacientes.

`Canino.kt`, `Felino.kt` y `Exotico.kt`  
Clases que representan los distintos tipos de pacientes.

`Box.kt`  
Representa cada box de la clinica.

`EstadoBox.kt`  
Contiene los distintos estados que puede tener un box.

`Ticket.kt`  
Guarda la informacion de cada atencion terminada.

`Funciones.kt`  
Contiene las validaciones, consultas y calculos utilizados por
el programa.

## Tecnologias

- Kotlin
- IntelliJ IDEA
- Gradle
- Kotlin Coroutines
