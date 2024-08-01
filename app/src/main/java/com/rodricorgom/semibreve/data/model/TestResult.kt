package com.rodricorgom.semibreve.data.model

import java.util.Date

data class TestResult(
    val id: String? = null,
    val date: String? = null,
    val correctAnswers: Int = 0,
    val incorrectAnswers: Int = 0,
    val score: Double = 0.0
)
