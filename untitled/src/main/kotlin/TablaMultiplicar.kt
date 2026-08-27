package org.example

fun main(){
    var numero: Int = 0
    var tablasPendientes = 3

    //ciclo para ir armando las tablas
    while (tablasPendientes > 0) {
        //solicitamos el numero al usuario
        println("Ingrese un numero del 1 al 12:")
        numero = readLine()?.toIntOrNull() ?:0

        //validar si el numero esta en el rango
        if(numero < 1 || numero > 12){
            println("Numero fuera de Rango. Debe ingresar un numero en el rango solicitado")
        }else{
            println("Tabla de Multiplicar del numero: $numero")
            println("-".repeat(15))
            //creo la tabla de mult con el ciclo for
            for(j in 1 .. 10){
                var resultado = numero * j
                //verificar si es multiplo de 5
                var marca = if(resultado % 5 == 0) "*" else ""
                println(" $numero * $j = $resultado$marca")
            }
            tablasPendientes--
        }

    }
}