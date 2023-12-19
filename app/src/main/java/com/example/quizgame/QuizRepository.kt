package com.example.quizgame

object QuizRepository {
    val quizzes = listOf(
        Quiz("room23", "How much tables in room 23?", listOf("30", "36", "20", "25"), "20"),
        Quiz("standart", "Year of foundation of VPU 3", listOf("1991", "899", "2023", "1986"), "1986"),
        Quiz("war", "Where USS was established?", listOf("1921", "1932", "1917", "1802"), "1917")
    )
}