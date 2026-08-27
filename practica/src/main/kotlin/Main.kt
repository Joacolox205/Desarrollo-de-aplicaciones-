package org.example

fun main() {
    //notese es inmutable
    val name = "Perking"
    //mutables
    //String
    var asignatura: String = "Programacion"
    //Double
    var nota = 4.0
    //Float
    var numDecimal = 5.99f
    //Char
    var letra = 'S'
    //Int
    var num1 = 34
    var num2 = 5
    //Long
    var enteroLargo = 8L
    //Boolean
    var apruebo = false

    println("Estas cursando $asignatura  y tienes calificacion de: $nota")

    println("¿Apruebas?: ${if(nota >= 4.0) "aprobado" else "reprobado"}")

    println("La suma es ${num1 + num2}")

    var aprobado = if (nota >= 4.0) "Aprobado" else "Reprobado"

    println("Hello, " + name + "!")

    //operadores logicos "And" / "or" -> && / ||

    //para texto de mas lineas
    var reporte = """
        *** Asignatura : ${asignatura.uppercase()}
        ** Calificacion: $nota
        * Aprobo: $aprobado
    """.trimIndent()
    println(reporte)

    //parseo o conversion de variables

    var texto = "43"
    var numEntero = texto.toInt()
    //toInt, toFloat, toString

    var contador = 2

    contador +=3 //contador = contador + 3
    contador *=4 //contador = contador * 4

    var diaSemana = 5

    when(diaSemana){
        1 -> println("Lunes")
        2 -> println("Martes")
        3 -> println("Miercoles")
        4 -> println("Jueves")
        5 -> println("Viernes")
        6,7 -> println("Fin de Semana")
        else -> println("Numero invalido")
    }

    fun sumar(n1: Int, n2: Int): Int{
        var resultado = n1 + n2
        return  resultado
    }

    var res = sumar(8,89)
    println(res)


}