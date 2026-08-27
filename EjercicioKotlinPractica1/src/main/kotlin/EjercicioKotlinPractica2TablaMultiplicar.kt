package org.example

fun main(){
    var numero: Int = 0
    var tablasFaltantes = 3
    //ciclo para generar las 3 tablas solicitados por el ejercicio
    while (tablasFaltantes > 0){
        //preguntar por el numero a generar la tabla de multiplicar
        println("ingrese un numero del 1 al 12 por generar su tabla de multiplicar: ")

        // Cambio a readlnOrNull() para mayor seguridad
        numero = readlnOrNull()?.toIntOrNull() ?: 0

        //validar si el numero ingresado esta dentro del rango (Cambio de 'or' a '||')
        if(numero > 12 || numero < 1) {
            println("numero fuera de rango (1-12). Debe ingresar otro")
        }else{
            //mostrar la tabla de multiplicar
            println("Tabla de multiplicar del numero: $numero")

            // Cambio para enviar el número 18 directamente
            println("-".repeat(18))

            // Cambio al rango correcto con ..
            for(j in 1..10){
                var resultado = numero * j
                //VERIFICAR SI EL RESULTADO ES MULTIPLO DE 5
                var marca = if(resultado % 5 == 0) "*" else ""
                println("$numero * $j = $resultado$marca")
            }
            tablasFaltantes--
        }
    }
}