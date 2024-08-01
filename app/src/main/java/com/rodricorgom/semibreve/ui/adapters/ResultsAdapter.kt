package com.rodricorgom.semibreve.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodricorgom.semibreve.data.model.LocalResultsManager
import com.rodricorgom.semibreve.data.model.TestResult
import com.rodricorgom.semibreve.databinding.ResultsViewBinding

class ResultsAdapter (
    private val results : List<TestResult>,
    private val manager : LocalResultsManager,
    context: Context
) : RecyclerView.Adapter<ResultsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {

        val binding = ResultsViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ResultsViewHolder(binding)
    }

    override fun getItemCount(): Int = manager.elementCount()

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {

    }

}