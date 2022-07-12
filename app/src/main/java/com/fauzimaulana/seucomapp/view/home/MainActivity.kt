package com.fauzimaulana.seucomapp.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzimaulana.seucomapp.core.ui.ProjectAdapter
import com.fauzimaulana.seucomapp.core.vo.Resource
import com.fauzimaulana.seucomapp.databinding.ActivityMainBinding
import com.fauzimaulana.seucomapp.view.add.AddActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var projectAdapter: ProjectAdapter
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        projectAdapter = ProjectAdapter()
        mainViewModel.getAllProject().observe(this) { project ->
            when (project) {
                is Resource.Loading -> binding.viewLoading.root.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.viewLoading.root.visibility = View.GONE
                    projectAdapter.submitList(project.data)
                }
                is Resource.Error -> {
                    binding.viewLoading.root.visibility = View.GONE
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
        with(binding.rvProject) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = projectAdapter
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}