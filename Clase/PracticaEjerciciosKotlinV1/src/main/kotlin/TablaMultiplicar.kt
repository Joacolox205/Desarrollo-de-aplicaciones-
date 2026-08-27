package org.example

fun main() {
    var numero: Int = 0
    var tablasPendientes: Int = 3


    //ciclo para controlar la cantidad de tablas creadas

    while (tablasPendientes > 0) {
        println("ingrese un numero entre 1 y 12")
        numero = readLine()?.toIntOrNull() ?: 0


        //validar si esta dentro del rango
        when {
            numero < 1 || numero > 12 -> println("numero fuera de rango (1-12). intente de nuevo")
            else -> {
                println("tabla de multiplicar del numero $numero")
                println("-".repeat(25))

                //ciclo for para generar la tabla del 1 al 10
                for (i in 1..10) {
                    val resultado = numero * i

                    val marca = if (resultado % 5 == 0) "*" else ""
                    println("$numero *$i = $resultado($marca)")
                }
                //reducir contador de las tablas pendientes
                tablasPendientes--

            }
        }
    }
}
