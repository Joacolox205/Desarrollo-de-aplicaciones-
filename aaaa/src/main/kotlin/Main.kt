package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var edades: Int = 0;
    var total: Int = 0;

    println("**************** Systema de calculo ******************")
    for(i in 1<= .. <=5){
        println("ingrese la edad del espectador $i:")
        var entrada = readLine();
        edades = entrada?.toIntOrNull() ?: -1

        val (categoria, precio) = when{
            edades in 0 <= .. <= 12 -> pair("Niño",1500)
            edades in 13 <= .. <= 17 -> pair("Adolecente",2500)
            edades in 18 <= .. <= 59 -> pair("Adulto",4000)
            edades < 0 -> pair("edad invalida",0)
            else -> pair("Adulto mayor",2000)
        }
        if(edades < 0){
            println("edad invalida, no se puede asignar categoria de precio");
        }
        else{
            print("categoria: $categoria --- precio: $precio");
            total +=precio;
        }
    }
    print("total recaudado: $$total")
}