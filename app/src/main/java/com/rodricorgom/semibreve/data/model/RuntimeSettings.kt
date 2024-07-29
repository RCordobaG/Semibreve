package com.rodricorgom.semibreve.data.model

object RuntimeSettings {

    //Counts the number of times the user has answered correctly
    var correctAnswers : Int = 0
    //Counts the number of times the user answered incorrectly
    var incorrectAnswers : Int = 0
    //Controls which scales the app will display (European vs American)
    var scale : Boolean = false
    //Configurable number of rounds the user wants to play
    var rounds : Int = 0
    var currentRound: Int = 0
}