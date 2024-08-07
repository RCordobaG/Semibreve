package com.rodricorgom.semibreve

object Constants {

    val EU_notes = setOf("Do","Re","Mi","Fa","Sol","La","Si")
    val US_notes = setOf("C","D","E","F","G","A","B")

    val noteImageEU = mapOf(
        "Do" to R.drawable.do_c,
        "Re" to R.drawable.re_d,
        "Mi" to R.drawable.mi_e,
        "Fa" to R.drawable.fa_f,
        "Sol" to R.drawable.sol_g,
        "La" to R.drawable.la_a,
        "Si" to R.drawable.si_b)

    val noteImageUS = mapOf(
        "C" to R.drawable.do_c,
        "D" to R.drawable.re_d,
        "E" to R.drawable.mi_e,
        "F" to R.drawable.fa_f,
        "G" to R.drawable.sol_g,
        "A" to R.drawable.la_a,
        "B" to R.drawable.si_b)

}