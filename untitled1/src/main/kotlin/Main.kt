package org.example

fun main() {
    //Lista para la Playlist
    val playList = mutableListOf<>("","","") //Lista mutable String, String

    //Ciclo para mostrar el Menú
    var opcion = 0
    While(opcion != 8){
        println("**** Playlist de Canciones ****")
        println("1.- Agregar cancion al Final")
        println("2.- Agregar cancion en una posicion especifica")
        println("3.- Eliminar cancion de por su nombre")
        println("4.- Mostrar Playlist")
        println("5.- Buscar cancion")
        println("6.- Invertir orden")
        println("7.- Ordernar alfabeticamente")
        println("8.- salir")
        println("Selecione una opcion: ")
        opcion = readLine()?.toUIntOrNull()?: 0

        when(opcion){
            1 -> {
                println("Ingrese nombre de la cancion: ")
                val cancion = readLine() ?: ""
                playList.add(cancion)
                println("La cancion: $cancion ha sido agregado con exito")
            }
            2 -> {
                println("En que posicion desea agregar la cancion (0 a ${playList.size}): ")
                val pos = readLine()?.toIntOrNull()?: 0
                //Validamos que la posicion ingresado esta en el rango de la lista
                if(pos in 0..playList.size) {
                    println("Ingrese nombre de la cancion: ")
                    val cancion = readLine()?:""
                    playList.add(pos, cancion)
                    println("Cancion: $cancion ingresado conrrectamente")
                }else{
                    println("La posicion: $pos esta fuera del rango indicado")
                }
            }
            3 -> {
                println("Igrese el nombre de la cancion que dese eliminar: ")
                val cancion = readLine()?:""
                //eliminar cancion
                if(playList.remove(cancion))println("Cancion: $cancion eliminada")
                else println(" No se pudo encontrar la cancion: $cancion en la Playlist")
            }
            4 -> {
                println("**** Lista de canciones ****")
                playList.forEachIndexed { i, cancion -> println("${i+1}. $cancion") }
            }
            5 -> {
                println("Ingrese el nombre de la cancion a buscar: ")
                val cancion = readLine()?:""
                if (playList.contains(cancion))println("Cancion esta dentro de la Plalist")
                else println("La cancion no se encuentra dentro de la Playlist")
            }
            6->{
                val invertida = playList.reverse() //devuelve la lista invertida
                playList.clear() //Borra contenido de la lista
                playList.addAll(invertida) //agrega la lista invertida
                print("lista invertida de manera correcta")
            }
            7->{
                val alfabe= playList.sorted()
                playList.clear() //Borra contenido de la lista
                playList.addAll(alfabe) //agrega la lista invertida
                print("lista ordenada alfabeticamente de manera correcta")

            }
            8 ->{
                println("Gracias por usar el sistema")
            }
            else -> println("Opcion incorrecta Animal!!!")
        }
    }
}