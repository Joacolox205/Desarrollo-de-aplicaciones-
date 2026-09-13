package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.model.AcademicLogic
import com.example.model.AcademicReport
import com.example.model.CinemaClassifierLogic
import com.example.model.MultiplicationRow
import com.example.model.MultiplicationTableLogic
import com.example.model.Song
import com.example.model.SpectatorTicket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CinemaUiState(
    val tickets: List<SpectatorTicket> = emptyList(),
    val totalRevenue: Int = 0,
    val spectatorCounter: Int = 1,
    val currentAgeInput: String = "",
    val errorMessage: String? = null
)

data class MultiplicationUiState(
    val currentNumber: Int = 7,
    val rows: List<MultiplicationRow> = MultiplicationTableLogic.generateTable(7),
    val remainingTablesGoal: Int = 3,
    val completedCount: Int = 1,
    val numberInput: String = "7",
    val errorMessage: String? = null
)

data class PlaylistUiState(
    val songs: List<Song> = listOf(
        Song(title = "Bohemian Rhapsody"),
        Song(title = "Hotel California"),
        Song(title = "Sweet Child O' Mine"),
        Song(title = "Billie Jean"),
        Song(title = "Smells Like Teen Spirit")
    ),
    val songTitleInput: String = "",
    val positionInput: String = "",
    val searchInput: String = "",
    val searchResultFeedback: String? = null,
    val operationFeedback: String? = null
)

data class AcademicUiState(
    val studentName: String = "Perking",
    val subject: String = "Programación",
    val gradeInput: String = "4.0",
    val report: AcademicReport = AcademicLogic.evaluate("Perking", "Programación", 4.0),
    val selectedDay: Int = 5,
    val dayResult: Pair<String, Boolean> = AcademicLogic.getDayOfWeek(5),
    val num1Input: String = "34",
    val num2Input: String = "5",
    val sumResult: Int = 39
)

class AppViewModel : ViewModel() {

    // --- CINEMA ---
    private val _cinemaState = MutableStateFlow(CinemaUiState())
    val cinemaState: StateFlow<CinemaUiState> = _cinemaState.asStateFlow()

    fun onCinemaAgeChanged(newAge: String) {
        _cinemaState.update { it.copy(currentAgeInput = newAge, errorMessage = null) }
    }

    fun addSpectator() {
        val age = _cinemaState.value.currentAgeInput.trim().toIntOrNull()
        if (age == null) {
            _cinemaState.update { it.copy(errorMessage = "Ingrese un número entero válido") }
            return
        }

        val (category, price) = CinemaClassifierLogic.classifyAge(age)
        if (age < 0) {
            _cinemaState.update {
                it.copy(
                    errorMessage = "Edad inválida ($age). No se puede cobrar entrada.",
                    currentAgeInput = ""
                )
            }
            return
        }

        val currentCount = _cinemaState.value.spectatorCounter
        val ticket = SpectatorTicket(
            spectatorNumber = currentCount,
            age = age,
            category = category,
            price = price
        )

        _cinemaState.update { state ->
            val updatedList = state.tickets + ticket
            state.copy(
                tickets = updatedList,
                totalRevenue = updatedList.sumOf { it.price },
                spectatorCounter = currentCount + 1,
                currentAgeInput = "",
                errorMessage = null
            )
        }
    }

    fun simulateStandardBatch() {
        // Simula la iteración de 5 espectadores del ejercicio original
        val sampleAges = listOf(8, 15, 26, 68, 42)
        var count = _cinemaState.value.spectatorCounter
        val newTickets = sampleAges.map { age ->
            val (category, price) = CinemaClassifierLogic.classifyAge(age)
            SpectatorTicket(
                spectatorNumber = count++,
                age = age,
                category = category,
                price = price
            )
        }

        _cinemaState.update { state ->
            val updated = state.tickets + newTickets
            state.copy(
                tickets = updated,
                totalRevenue = updated.sumOf { it.price },
                spectatorCounter = count,
                errorMessage = null
            )
        }
    }

    fun clearCinemaTickets() {
        _cinemaState.value = CinemaUiState()
    }

    // --- TABLAS DE MULTIPLICAR ---
    private val _multiplicationState = MutableStateFlow(MultiplicationUiState())
    val multiplicationState: StateFlow<MultiplicationUiState> = _multiplicationState.asStateFlow()

    fun onTableNumberInputChanged(value: String) {
        _multiplicationState.update { it.copy(numberInput = value, errorMessage = null) }
    }

    fun generateTableForNumber(number: Int) {
        if (number < 1 || number > 12) {
            _multiplicationState.update {
                it.copy(errorMessage = "Número fuera de rango (1-12). Ingrese un valor válido.")
            }
            return
        }

        val rows = MultiplicationTableLogic.generateTable(number)
        _multiplicationState.update { state ->
            val newRemaining = maxOf(0, state.remainingTablesGoal - 1)
            state.copy(
                currentNumber = number,
                numberInput = number.toString(),
                rows = rows,
                completedCount = state.completedCount + 1,
                remainingTablesGoal = newRemaining,
                errorMessage = null
            )
        }
    }

    fun generateTableFromInput() {
        val num = _multiplicationState.value.numberInput.trim().toIntOrNull()
        if (num == null) {
            _multiplicationState.update { it.copy(errorMessage = "Ingrese un número válido entre 1 y 12") }
            return
        }
        generateTableForNumber(num)
    }

    // --- PLAYLIST ---
    private val _playlistState = MutableStateFlow(PlaylistUiState())
    val playlistState: StateFlow<PlaylistUiState> = _playlistState.asStateFlow()

    fun onSongTitleChanged(title: String) {
        _playlistState.update { it.copy(songTitleInput = title, operationFeedback = null) }
    }

    fun onPositionInputChanged(pos: String) {
        _playlistState.update { it.copy(positionInput = pos, operationFeedback = null) }
    }

    fun onSearchInputChanged(query: String) {
        _playlistState.update { it.copy(searchInput = query, searchResultFeedback = null) }
    }

    fun addSongAtEnd() {
        val title = _playlistState.value.songTitleInput.trim()
        if (title.isEmpty()) {
            _playlistState.update { it.copy(operationFeedback = "El nombre de la canción no puede estar vacío") }
            return
        }
        val newSong = Song(title = title)
        _playlistState.update { state ->
            state.copy(
                songs = state.songs + newSong,
                songTitleInput = "",
                operationFeedback = "Canción '$title' agregada con éxito al final"
            )
        }
    }

    fun addSongAtPosition() {
        val title = _playlistState.value.songTitleInput.trim()
        val pos = _playlistState.value.positionInput.trim().toIntOrNull()

        if (title.isEmpty()) {
            _playlistState.update { it.copy(operationFeedback = "El nombre de la canción no puede estar vacío") }
            return
        }
        val currentSize = _playlistState.value.songs.size
        if (pos == null || pos !in 0..currentSize) {
            _playlistState.update {
                it.copy(operationFeedback = "La posición '$pos' está fuera de rango (0 a $currentSize)")
            }
            return
        }

        val updated = _playlistState.value.songs.toMutableList()
        updated.add(pos, Song(title = title))
        _playlistState.update {
            it.copy(
                songs = updated,
                songTitleInput = "",
                positionInput = "",
                operationFeedback = "Canción '$title' insertada correctamente en la posición $pos"
            )
        }
    }

    fun removeSongByName(title: String) {
        val current = _playlistState.value.songs
        val target = current.firstOrNull { it.title.equals(title.trim(), ignoreCase = true) }
        if (target != null) {
            val updated = current.toMutableList().apply { remove(target) }
            _playlistState.update {
                it.copy(
                    songs = updated,
                    operationFeedback = "Canción '${target.title}' eliminada de la playlist"
                )
            }
        } else {
            _playlistState.update {
                it.copy(operationFeedback = "No se pudo encontrar la canción '$title' en la Playlist")
            }
        }
    }

    fun removeSong(song: Song) {
        val updated = _playlistState.value.songs.toMutableList().apply { remove(song) }
        _playlistState.update {
            it.copy(
                songs = updated,
                operationFeedback = "Canción '${song.title}' eliminada"
            )
        }
    }

    fun searchSong() {
        val query = _playlistState.value.searchInput.trim()
        if (query.isEmpty()) {
            _playlistState.update { it.copy(searchResultFeedback = "Ingrese un nombre para buscar") }
            return
        }
        val found = _playlistState.value.songs.any {
            it.title.contains(query, ignoreCase = true)
        }
        val message = if (found) {
            "✓ La canción coincide y está dentro de la Playlist"
        } else {
            "✗ La canción no se encuentra dentro de la Playlist"
        }
        _playlistState.update { it.copy(searchResultFeedback = message) }
    }

    fun reversePlaylist() {
        val reversed = _playlistState.value.songs.reversed()
        _playlistState.update {
            it.copy(
                songs = reversed,
                operationFeedback = "Lista invertida de manera correcta"
            )
        }
    }

    fun sortPlaylistAlphabetically() {
        val sorted = _playlistState.value.songs.sortedBy { it.title.lowercase() }
        _playlistState.update {
            it.copy(
                songs = sorted,
                operationFeedback = "Lista ordenada alfabéticamente de manera correcta"
            )
        }
    }

    // --- ACADEMIC ---
    private val _academicState = MutableStateFlow(AcademicUiState())
    val academicState: StateFlow<AcademicUiState> = _academicState.asStateFlow()

    fun onStudentNameChanged(name: String) {
        _academicState.update { it.copy(studentName = name) }
        recalculateReport()
    }

    fun onSubjectChanged(subject: String) {
        _academicState.update { it.copy(subject = subject) }
        recalculateReport()
    }

    fun onGradeChanged(gradeStr: String) {
        _academicState.update { it.copy(gradeInput = gradeStr) }
        val parsed = gradeStr.toDoubleOrNull() ?: 0.0
        val clamped = parsed.coerceIn(0.0, 7.0)
        _academicState.update {
            it.copy(report = AcademicLogic.evaluate(it.studentName, it.subject, clamped))
        }
    }

    private fun recalculateReport() {
        val grade = _academicState.value.gradeInput.toDoubleOrNull() ?: 0.0
        val report = AcademicLogic.evaluate(
            studentName = _academicState.value.studentName,
            subject = _academicState.value.subject,
            grade = grade
        )
        _academicState.update { it.copy(report = report) }
    }

    fun onDaySelected(dayNumber: Int) {
        val dayInfo = AcademicLogic.getDayOfWeek(dayNumber)
        _academicState.update {
            it.copy(selectedDay = dayNumber, dayResult = dayInfo)
        }
    }

    fun onNum1Changed(value: String) {
        _academicState.update { it.copy(num1Input = value) }
        recalculateSum()
    }

    fun onNum2Changed(value: String) {
        _academicState.update { it.copy(num2Input = value) }
        recalculateSum()
    }

    private fun recalculateSum() {
        val n1 = _academicState.value.num1Input.toIntOrNull() ?: 0
        val n2 = _academicState.value.num2Input.toIntOrNull() ?: 0
        _academicState.update {
            it.copy(sumResult = AcademicLogic.addNumbers(n1, n2))
        }
    }
}
