package org.example


fun main() {
    var edad: Int = 0
    var totalRecaudados: Int = 0

    for (i in 1..5) {
        println("Ingrese la edad del espectador $i: ")
        val entrada = readLine()

        // Convertir a entero de forma segura
        edad = entrada?.toIntOrNull() ?: -1

        // Validar que la edad sea válida
        if (edad < 0) {
            println("Edad no válida. Intente nuevamente para este espectador.")
            continue // Salta a la siguiente iteración
        }

        // Condicional para clasificar categoría y precio según la edad
        val (categoria, precio) = when {
            edad < 5 -> "Menores (No ingresan / Gratis)" to 2500
            edad in 5..14 -> "Niños" to 4000
            edad in 15..19 -> "Adolescentes" to 7000
            edad in 20..64 -> "Adultos" to 0
            else -> "Adultos Mayores" to 6000
        }

        // Acumular el total recaudado
        totalRecaudados += precio
        println("-> Categoría: $categoria | Precio: \$$precio\n")
    }


    println("El total recaudado por las 5 entradas es: \$$totalRecaudados")

}