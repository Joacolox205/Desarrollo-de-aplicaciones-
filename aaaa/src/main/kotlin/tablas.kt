package org.example

fun main(){
    var numero: Int = 0
    var tablasPendientes: Int = 5

    while (tablasPendientes > 0){
        print("ingrese un numero del 1 al 12")
        numero = readLine()?.toIntOrNull()?:0
        
    }
}