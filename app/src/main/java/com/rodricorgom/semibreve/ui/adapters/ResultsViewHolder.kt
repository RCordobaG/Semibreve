package com.rodricorgom.semibreve.ui.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.rodricorgom.semibreve.data.model.TestResult
import com.rodricorgom.semibreve.databinding.ResultsViewBinding

class ResultsViewHolder (private var binding: ResultsViewBinding) : RecyclerView.ViewHolder(binding.root){

    //val context = context

    fun bind(result : TestResult){
        binding.idHolderTextView.text = result.id.toString()
        binding.dateHolderTextView.text = result.date.toString()
        binding.scoreHolderTextView.text = result.score.toString()
        binding.correctAnswerTextView.text = result.correctAnswers.toString()
        binding.incorrectAnswerTextView.text = result.incorrectAnswers.toString()


    }
}