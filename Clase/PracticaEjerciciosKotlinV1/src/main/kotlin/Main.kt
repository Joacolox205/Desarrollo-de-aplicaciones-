package org.example

import kotlin.collections.plusAssign

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

//primero antes de empezar un ejercicio se declaran las variables segun lo que te diga el ejercicio

fun main() {
    //declaramos las variables primero
    var edad: Int = 0
    var totalRecaudado: Int = 0

    println("sistema clasificador de edades")
    //ahora declaramos el ciclo para preguntar por las edades
    for (i in 1..5)
    //solicitamos la edad del usuario
        println("ingrese edad del espectador$i:")

    var entrada = readLine()

    //convertir la edad a numero entero
    edad = entrada?.toIntOrNull() ?: -1
    //el if se usa mejor si son preguntas
    //usaremos el when

    val (categoria, precio) = when {
        edad < 0 -> Pair("edad invalida", 0)
        edad < 0..12 -> Pair("niño", 1500)
        edad < 13..17 -> Pair("adolecente", 2500)
        edad < 18..59 -> Pair("adulto", 4000)
        else -> Pair("adulto mayor", 2000)
    }

    //mostrar la informacion
    if (edad < 0){
        println("edad invalida. no se puede cobrar la entrada")
    }else{
        println("categoria: $categoria---- precio: $$precio")
        //aumentamos el acumulador
        totalRecaudado += precio
    }

    //mostrar total recaudado
    println("total recaudado: $$totalRecaudado")

}





