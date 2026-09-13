package com.example.model

/**
 * Representa la clasificación de edad y precio de entrada de cine según los ejercicios originales.
 */
data class SpectatorTicket(
    val id: Long = System.currentTimeMillis(),
    val spectatorNumber: Int,
    val age: Int,
    val category: String,
    val price: Int
)

object CinemaClassifierLogic {
    /**
     * Lógica exacta de los archivos Main.kt (Clase/PracticaEjerciciosKotlinV1 & untitled)
     */
    fun classifyAge(age: Int): Pair<String, Int> {
        return when {
            age < 0 -> Pair("Edad inválida", 0)
            age in 0..12 -> Pair("Niño", 1500)
            age in 13..17 -> Pair("Adolescente", 2500)
            age in 18..59 -> Pair("Adulto", 4000)
            else -> Pair("Adulto mayor", 2000)
        }
    }
}

/**
 * Representa una fila en la tabla de multiplicar con detección de múltiplos de 5.
 */
data class MultiplicationRow(
    val multiplier: Int,
    val factor: Int,
    val result: Int,
    val isMultipleOfFive: Boolean
)

object MultiplicationTableLogic {
    fun generateTable(number: Int, limit: Int = 10): List<MultiplicationRow> {
        return (1..limit).map { i ->
            val result = number * i
            MultiplicationRow(
                multiplier = number,
                factor = i,
                result = result,
                isMultipleOfFive = (result % 5 == 0)
            )
        }
    }
}

/**
 * Modelo para las canciones de la Playlist interactiva.
 */
data class Song(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String
)

/**
 * Reporte académico y evaluador de notas.
 */
data class AcademicReport(
    val studentName: String,
    val subject: String,
    val grade: Double,
    val isApproved: Boolean,
    val formattedReport: String
)

object AcademicLogic {
    fun evaluate(studentName: String, subject: String, grade: Double): AcademicReport {
        val approved = grade >= 4.0
        val approvedText = if (approved) "Aprobado" else "Reprobado"
        val report = """
            *** Asignatura : ${subject.uppercase()}
            ** Calificación: $grade
            * Aprobó: $approvedText
        """.trimIndent()
        return AcademicReport(
            studentName = studentName,
            subject = subject,
            grade = grade,
            isApproved = approved,
            formattedReport = report
        )
    }

    fun getDayOfWeek(dayNumber: Int): Pair<String, Boolean> {
        return when (dayNumber) {
            1 -> Pair("Lunes", false)
            2 -> Pair("Martes", false)
            3 -> Pair("Miércoles", false)
            4 -> Pair("Jueves", false)
            5 -> Pair("Viernes", false)
            6, 7 -> Pair("Fin de Semana", true)
            else -> Pair("Número inválido", false)
        }
    }

    fun addNumbers(n1: Int, n2: Int): Int {
        return n1 + n2
    }
}
