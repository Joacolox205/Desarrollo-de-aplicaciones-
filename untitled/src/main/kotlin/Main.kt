package org.example
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var edad: Int = 0
    var totalRecaudado: Int = 0
    //Ciclo para repetir las preguntas de las edades
    for(i in 1 .. 5){
        //solicitar la edad al usuario
        println("Ingrese la edad del espectador $i: ")
        var entrada = readLine()

        //Convertir la edad a un entero
        edad = entrada?.toIntOrNull() ?: -1

        //clasificar de acuerdo a la edad
        val(categoria, precio) = when{
            edad in 0 .. 12 -> Pair("Niño", 1500)
            edad in 13 .. 17 -> Pair("Adolescente", 2500)
            edad in 18 .. 59 -> Pair("Adulto", 4000)
            edad < 0 -> Pair("Edad invalida", 0)
            else -> Pair("Adulto mayor", 2000)
        }
        //mostrar categoria y precio a pagar por el espectador
        if(edad < 0){
            println("Edad invalida. No se puede asignar un precio ni categoria")
        }else{
            println("Categoria: $categoria --- precio: $precio")
            totalRecaudado += precio
        }

    }
    println("Total recaudado: $$totalRecaudado")
}