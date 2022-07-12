package com.fauzimaulana.seucomapp.view.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.fauzimaulana.seucomapp.core.vo.Resource
import com.fauzimaulana.seucomapp.databinding.ActivityAddBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AddActivity : AppCompatActivity() {

    private var _binding: ActivityAddBinding? = null
    private val binding get() = _binding!!

    private val addViewModel: AddViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addViewModel.getLocationType().observe(this) { type ->
            when (type) {
                is Resource.Loading -> {
                    //DO NOTHING
                }
                is Resource.Success -> {
                    val locationType = arrayOf(type.data?.pR, type.data?.bD, type.data?.fL, type.data?.rO)
                    val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationType)
                    binding.spinner.adapter = locationAdapter

                    binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            val selected = locationType[position]
                            when (selected) {
                                "Project" -> {
                                    binding.projectLayout.visibility = View.VISIBLE
                                    binding.buildingLayout.visibility = View.GONE
                                    binding.floorLayout.visibility = View.GONE
                                    binding.roomLayout.visibility = View.GONE
                                    createNewProject()
                                }
                                "Building" -> {
                                    binding.projectLayout.visibility = View.GONE
                                    binding.buildingLayout.visibility = View.VISIBLE
                                    binding.floorLayout.visibility = View.GONE
                                    binding.roomLayout.visibility = View.GONE
                                    createNewBuilding()
                                }
                                "Floor" -> {
                                    binding.projectLayout.visibility = View.GONE
                                    binding.buildingLayout.visibility = View.GONE
                                    binding.floorLayout.visibility = View.VISIBLE
                                    binding.roomLayout.visibility = View.GONE
                                }
                                "Room" -> {
                                    binding.projectLayout.visibility = View.GONE
                                    binding.buildingLayout.visibility = View.GONE
                                    binding.floorLayout.visibility = View.GONE
                                    binding.roomLayout.visibility = View.VISIBLE
                                }
                            }
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun createNewBuilding() {
        addViewModel.getAllProject().observe(this) { project ->
            when (project) {
                is Resource.Loading -> {
                    //Do nothing
                }
                is Resource.Success -> {
                    val projectName = mutableListOf<String>()
                    for (i in 0 until project.data!!.size) {
                        projectName.add(project.data[i].locName)
                    }
                    val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, projectName)
                    binding.spinnerProject.adapter = locationAdapter

                    binding.spinnerProject.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            createNewBuildingToServer(project.data[position].locCode)
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }
                }
                is Resource.Error -> {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun createNewBuildingToServer(projectCode: String) {
        binding.buttonSaveBuilding.setOnClickListener {
            val name = binding.buildingNameEditText.text.toString()
            val lat = binding.buildingLatEditText.text.toString()
            val lon = binding.buildingLonEditText.text.toString()
            val dis = binding.buildingDisEditText.text.toString()

            when {
                name.isEmpty() -> {
                    binding.buildingNameEditText.error = "Name cannot be empty"
                }
                lat.isEmpty() -> {
                    binding.buildingLatEditText.error = "Latitude cannot be empty"
                }
                lon.isEmpty() -> {
                    binding.buildingLonEditText.error = "Longitude cannot be empty"
                }
                dis.isEmpty() -> {
                    binding.buildingDisEditText.error = "Dispensation cannot be empty"
                }
                else -> {
                    addViewModel.createBuilding(name, "BD", lat.toDouble(), lon.toDouble(), dis.toDouble(), projectCode).observe(this@AddActivity) { result ->
                        when(result) {
                            is Resource.Loading -> {
                                //DO nothing
                            }
                            is Resource.Success -> {
                                Toast.makeText(this@AddActivity, "Building Created", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            is Resource.Error -> {
                                Toast.makeText(this@AddActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun createNewProject() {
        binding.buttonSaveProject.setOnClickListener {
            val name = binding.projectNameEditText.text.toString()
            val lat = binding.projectLatEditText.text.toString()
            val lon = binding.projectLonEditText.text.toString()
            val dis = binding.projectDisEditText.text.toString()

            when {
                name.isEmpty() -> {
                    binding.projectNameEditText.error = "Name cannot be empty"
                }
                lat.isEmpty() -> {
                    binding.projectLatEditText.error = "Latitude cannot be empty"
                }
                lon.isEmpty() -> {
                    binding.projectLonEditText.error = "Longitude cannot be empty"
                }
                dis.isEmpty() -> {
                    binding.projectDisEditText.error = "Dispensation cannot be empty"
                }
                else -> {
                    addViewModel.createProject(name, "PR", lat.toDouble(), lon.toDouble(), dis.toDouble()).observe(this@AddActivity) { result ->
                        when(result) {
                            is Resource.Loading -> {
                                //DO nothing
                            }
                            is Resource.Success -> {
                                Toast.makeText(this@AddActivity, "Project Created", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            is Resource.Error -> {
                                Toast.makeText(this@AddActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}