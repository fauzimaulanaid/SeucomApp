package com.fauzimaulana.seucomapp.view.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fauzimaulana.seucomapp.core.domain.model.BuildingModel
import com.fauzimaulana.seucomapp.core.domain.model.FloorModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectModel
import com.fauzimaulana.seucomapp.core.vo.Resource
import com.fauzimaulana.seucomapp.databinding.ActivityEditBinding
import com.fauzimaulana.seucomapp.view.home.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class EditActivity : AppCompatActivity() {

    private var _binding: ActivityEditBinding? = null
    private val binding get() = _binding!!

    private val editViewModel: EditViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val projectData = intent.getParcelableExtra<ProjectModel>(EXTRA_PROJECT)
        val buildingData = intent.getParcelableExtra<BuildingModel>(EXTRA_BUILDING)
        val floorData = intent.getParcelableExtra<FloorModel>(EXTRA_FLOOR)

        when {
            projectData != null -> {
                supportActionBar?.title = "Update Project"
                binding.locationNameEditText.setText(projectData.locName)
                binding.locationLatEditText.setText(projectData.locLatitude.toString())
                binding.locationLonEditText.setText(projectData.locLongitude.toString())
                binding.locationDisEditText.setText(projectData.locDispensation.toString())
                updateProject(projectData.locID)
            }
            buildingData != null -> {
                supportActionBar?.title = "Update Building"
                binding.locationNameEditText.setText(buildingData.locName)
                binding.locationLatEditText.setText(buildingData.locLatitude.toString())
                binding.locationLonEditText.setText(buildingData.locLongitude.toString())
                binding.locationDisEditText.setText(buildingData.locDispensation.toString())
                updateBuilding(buildingData.locID)
            }
            floorData != null -> {
                supportActionBar?.title = "Update Floor"
                binding.locationNameEditText.setText(floorData.locName)
                binding.locationLatEditText.setText(floorData.locLatitude.toString())
                binding.locationLonEditText.setText(floorData.locLongitude.toString())
                binding.locationDisEditText.setText(floorData.locDispensation.toString())
                updateFloor(floorData.locID)
            }
        }
    }

    private fun updateProject(projectId: String) {
        binding.buttonEdit.setOnClickListener {
            val name = binding.locationNameEditText.text.toString()
            val lat = binding.locationLatEditText.text.toString()
            val lon = binding.locationLonEditText.text.toString()
            val dis = binding.locationDisEditText.text.toString()

            when {
                name.isEmpty() -> {
                    binding.locationNameEditText.error = "Name cannot be empty"
                }
                lat.isEmpty() -> {
                    binding.locationLatEditText.error = "Latitude cannot be empty"
                }
                lon.isEmpty() -> {
                    binding.locationLonEditText.error = "Longitude cannot be empty"
                }
                dis.isEmpty() -> {
                    binding.locationDisEditText.error = "Dispensation cannot be empty"
                }
                else -> {
                    editViewModel.updateData(projectId, name, "PR", lat.toDouble(), lon.toDouble(), dis.toDouble()).observe(this) { result ->
                        when(result) {
                            is Resource.Loading -> {
                                //DO nothing
                            }
                            is Resource.Success -> {
                                Toast.makeText(this, "Project Updated", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            }
                            is Resource.Error -> {
                                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateBuilding(buildingId: String) {
        binding.buttonEdit.setOnClickListener {
            val name = binding.locationNameEditText.text.toString()
            val lat = binding.locationLatEditText.text.toString()
            val lon = binding.locationLonEditText.text.toString()
            val dis = binding.locationDisEditText.text.toString()

            when {
                name.isEmpty() -> {
                    binding.locationNameEditText.error = "Name cannot be empty"
                }
                lat.isEmpty() -> {
                    binding.locationLatEditText.error = "Latitude cannot be empty"
                }
                lon.isEmpty() -> {
                    binding.locationLonEditText.error = "Longitude cannot be empty"
                }
                dis.isEmpty() -> {
                    binding.locationDisEditText.error = "Dispensation cannot be empty"
                }
                else -> {
                    editViewModel.updateData(buildingId, name, "BD", lat.toDouble(), lon.toDouble(), dis.toDouble()).observe(this) { result ->
                        when(result) {
                            is Resource.Loading -> {
                                //DO nothing
                            }
                            is Resource.Success -> {
                                Toast.makeText(this, "Building Updated", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            }
                            is Resource.Error -> {
                                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateFloor(floorId: String) {
        binding.buttonEdit.setOnClickListener {
            val name = binding.locationNameEditText.text.toString()
            val lat = binding.locationLatEditText.text.toString()
            val lon = binding.locationLonEditText.text.toString()
            val dis = binding.locationDisEditText.text.toString()

            when {
                name.isEmpty() -> {
                    binding.locationNameEditText.error = "Name cannot be empty"
                }
                lat.isEmpty() -> {
                    binding.locationLatEditText.error = "Latitude cannot be empty"
                }
                lon.isEmpty() -> {
                    binding.locationLonEditText.error = "Longitude cannot be empty"
                }
                dis.isEmpty() -> {
                    binding.locationDisEditText.error = "Dispensation cannot be empty"
                }
                else -> {
                    editViewModel.updateData(floorId, name, "FL", lat.toDouble(), lon.toDouble(), dis.toDouble()).observe(this) { result ->
                        when(result) {
                            is Resource.Loading -> {
                                //DO nothing
                            }
                            is Resource.Success -> {
                                Toast.makeText(this, "Floor Updated", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            }
                            is Resource.Error -> {
                                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_PROJECT = "extra_project"
        const val EXTRA_BUILDING = "extra_building"
        const val EXTRA_FLOOR = "extra_floor"
        const val EXTRA_ROOM = "extra_room"
    }
}