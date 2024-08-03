package com.rodricorgom.semibreve.data.RuntimeData

object RuntimeSettings {

    //Enables the use of a manual button to go to next flashcard
    var manualMode: Boolean = false

    //Counts the number of times the user has answered correctly
    var correctAnswers : Int = 0
    //Counts the number of times the user answered incorrectly
    var incorrectAnswers : Int = 0
    //Controls which scales the app will display (European vs American)
    var scale : Boolean = false
    //Configurable number of rounds the user wants to play
    var rounds : Int = 0
    //The actual round, used to determine the remaining flashcards to display
    var currentRound: Int = 0
    //Save this number of entries in the results file
    var maxEntries: Int = 10
    //Time between the user selecting an option and the next flashcards appearing
    var newRoundTimer : Long = 1000
}