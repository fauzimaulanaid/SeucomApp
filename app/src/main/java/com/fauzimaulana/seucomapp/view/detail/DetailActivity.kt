package com.fauzimaulana.seucomapp.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzimaulana.seucomapp.core.domain.model.BuildingModel
import com.fauzimaulana.seucomapp.core.domain.model.FloorModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectModel
import com.fauzimaulana.seucomapp.core.ui.BuildingAdapter
import com.fauzimaulana.seucomapp.core.ui.FloorAdapter
import com.fauzimaulana.seucomapp.core.vo.Resource
import com.fauzimaulana.seucomapp.databinding.ActivityDetailBinding
import com.fauzimaulana.seucomapp.view.edit.EditActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var buildingAdapter: BuildingAdapter
    private lateinit var floorAdapter: FloorAdapter
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buildingAdapter = BuildingAdapter()
        floorAdapter = FloorAdapter()

        val projectData = intent.getParcelableExtra<ProjectModel>(EXTRA_DATA)
        val buildingData = intent.getParcelableExtra<BuildingModel>(EXTRA_BUILDING)
        val floorData = intent.getParcelableExtra<FloorModel>(EXTRA_FLOOR)

        when {
            projectData != null -> {
                supportActionBar?.title = "Project"
                showDetailProject(projectData)

                detailViewModel.getAllBuildingByProject(projectData.locCode).observe(this) { building ->
                    when (building) {
                        is Resource.Loading -> {
                            //Do Nothing
                        }
                        is Resource.Success -> {
                            buildingAdapter.submitList(building.data)
                        }
                        is Resource.Error -> {
                            Toast.makeText(this, "There is no Building in ${projectData.locName}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                with(binding.rvBuilding) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = buildingAdapter
                }
                binding.fabEdit.setOnClickListener {
                    val intent = Intent(this, EditActivity::class.java)
                    intent.putExtra(EditActivity.EXTRA_PROJECT, projectData)
                    startActivity(intent)
                }
            }
            buildingData != null -> {
                supportActionBar?.title = "Building"
                binding.textViewName.text = "Building Name"
                binding.textViewBuilding.text = "Floor"
                showDetailBuilding(buildingData)

                detailViewModel.getAllFloorByBuilding(buildingData.locCode).observe(this) { floor ->
                    when (floor) {
                        is Resource.Loading -> {
                            //Do Nothing
                        }
                        is Resource.Success -> {
                            floorAdapter.submitList(floor.data)
                        }
                        is Resource.Error -> {
                            Toast.makeText(this, "There is no Floor in ${buildingData.locName}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                with(binding.rvBuilding) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = floorAdapter
                }

                binding.fabEdit.setOnClickListener {
                    val intent = Intent(this, EditActivity::class.java)
                    intent.putExtra(EditActivity.EXTRA_BUILDING, buildingData)
                    startActivity(intent)
                }
            }
            floorData != null -> {
                supportActionBar?.title = "Floor"
                binding.textViewName.text = "Floor Name"
                binding.textViewBuilding.text = "Room"
                showDetailFloor(floorData)
                binding.fabEdit.setOnClickListener {
                    val intent = Intent(this, EditActivity::class.java)
                    intent.putExtra(EditActivity.EXTRA_FLOOR, floorData)
                    startActivity(intent)
                }
            }
        }
    }

    private fun showDetailProject(project: ProjectModel) {
        with(binding) {
            textViewNameBody.text = project.locName
            textViewLatitudeBody.text = project.locLatitude.toString()
            textViewLongitudeBody.text = project.locLongitude.toString()
            textViewDispensationBody.text = project.locDispensation.toString()
        }
    }

    private fun showDetailBuilding(building: BuildingModel) {
        with(binding) {
            textViewNameBody.text = building.locName
            textViewLatitudeBody.text = building.locLatitude.toString()
            textViewLongitudeBody.text = building.locLongitude.toString()
            textViewDispensationBody.text = building.locDispensation.toString()
        }
    }

    private fun showDetailFloor(floor: FloorModel) {
        with(binding) {
            textViewNameBody.text = floor.locName
            textViewLatitudeBody.text = floor.locLatitude.toString()
            textViewLongitudeBody.text = floor.locLongitude.toString()
            textViewDispensationBody.text = floor.locDispensation.toString()
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
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_BUILDING = "extra_building"
        const val EXTRA_FLOOR = "extra_floor"
        const val EXTRA_ROOM = "extra_room"
    }
}