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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
                                    createNewFloor()
                                }
                                "Room" -> {
                                    binding.projectLayout.visibility = View.GONE
                                    binding.buildingLayout.visibility = View.GONE
                                    binding.floorLayout.visibility = View.GONE
                                    binding.roomLayout.visibility = View.VISIBLE
                                    createNewRoom()
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

    private fun createNewRoom() {
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
                    binding.spinnerProjectRoom.adapter = locationAdapter

                    binding.spinnerProjectRoom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            getAllBuildingForRoom(project.data[position].locCode, project.data[position].locName)
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

    private fun getAllBuildingForRoom(projectCode: String, projectName: String) {
        addViewModel.getAllBuildingByProject(projectCode).observe(this) { building ->
            when (building) {
                is Resource.Loading -> {
                    //Do nothing
                }
                is Resource.Success -> {
                    val buildingName = mutableListOf<String>()
                    for (i in 0 until building.data!!.size) {
                        buildingName.add(building.data[i].locName)
                    }
                    val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, buildingName)
                    binding.spinnerBuildingRoom.adapter = locationAdapter

                    binding.spinnerBuildingRoom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            getAllFloorForRoom(building.data[position].locCode, building.data[position].locName)
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }
                }
                is Resource.Error -> {
                    val buildingName = mutableListOf<String>()
                    val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, buildingName)
                    binding.spinnerBuilding.adapter = locationAdapter
                    Toast.makeText(this, "There is no building in $projectName, please create one first", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getAllFloorForRoom(buildingCode: String, buildingName: String) {
        addViewModel.getAllFloorByBuilding(buildingCode).observe(this) { floor ->
            when (floor) {
                is Resource.Loading -> {
                    //Do nothing
                }
                is Resource.Success -> {
                    val floorName = mutableListOf<String>()
                    for (i in 0 until floor.data!!.size) {
                        floorName.add(floor.data[i].locName)
                    }
                    val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, floorName)
                    binding.spinnerFloor.adapter = locationAdapter

                    binding.spinnerFloor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            createNewRoomToServer(floor.data[position].locCode)
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }
                }
                is Resource.Error -> {
                    val floorName = mutableListOf<String>()
                    val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, floorName)
                    binding.spinnerFloor.adapter = locationAdapter
                    Toast.makeText(this, "There is no floor in $buildingName, please create one first", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun createNewRoomToServer(floorCode: String) {
        binding.buttonSaveRoom.setOnClickListener {
            val name = binding.roomNameEditText.text.toString()
            val lat = binding.roomLatEditText.text.toString()
            val lon = binding.roomLonEditText.text.toString()
            val dis = binding.roomDisEditText.text.toString()

            when {
                name.isEmpty() -> {
                    binding.roomNameEditText.error = "Name cannot be empty"
                }
                lat.isEmpty() -> {
                    binding.roomLatEditText.error = "Latitude cannot be empty"
                }
                lon.isEmpty() -> {
                    binding.roomLonEditText.error = "Longitude cannot be empty"
                }
                dis.isEmpty() -> {
                    binding.roomDisEditText.error = "Dispensation cannot be empty"
                }
                else -> {
                    addViewModel.createRoom(name, "RO", lat.toDouble(), lon.toDouble(), dis.toDouble(), floorCode).observe(this@AddActivity) { result ->
                        when(result) {
                            is Resource.Loading -> {
                                //DO nothing
                            }
                            is Resource.Success -> {
                                Toast.makeText(this@AddActivity, "Room Created", Toast.LENGTH_SHORT).show()
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

    private fun createNewFloor() {
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
                    binding.spinnerProjectFloor.adapter = locationAdapter

                    binding.spinnerProjectFloor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            getAllBuildingForFloor(project.data[position].locCode, project.data[position].locName)
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

    private fun getAllBuildingForFloor(projectCode: String, projectName: String) {
        addViewModel.getAllBuildingByProject(projectCode).observe(this) { building ->
            when (building) {
                is Resource.Loading -> {
                    //Do nothing
                }
                is Resource.Success -> {
                    val buildingName = mutableListOf<String>()
                    for (i in 0 until building.data!!.size) {
                        buildingName.add(building.data[i].locName)
                    }
                    val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, buildingName)
                    binding.spinnerBuilding.adapter = locationAdapter

                    binding.spinnerBuilding.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            createNewFloorToServer(building.data[position].locCode)
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }
                }
                is Resource.Error -> {
                    val buildingName = mutableListOf<String>()
                    val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, buildingName)
                    binding.spinnerBuilding.adapter = locationAdapter
                    Toast.makeText(this, "There is no building in $projectName, please create one first", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun createNewFloorToServer(buildingCode: String) {
        binding.buttonSaveFloor.setOnClickListener {
            val name = binding.floorNameEditText.text.toString()
            val lat = binding.floorLatEditText.text.toString()
            val lon = binding.floorLonEditText.text.toString()
            val dis = binding.floorDisEditText.text.toString()

            when {
                name.isEmpty() -> {
                    binding.floorNameEditText.error = "Name cannot be empty"
                }
                lat.isEmpty() -> {
                    binding.floorLatEditText.error = "Latitude cannot be empty"
                }
                lon.isEmpty() -> {
                    binding.floorLonEditText.error = "Longitude cannot be empty"
                }
                dis.isEmpty() -> {
                    binding.floorDisEditText.error = "Dispensation cannot be empty"
                }
                else -> {
                    addViewModel.createFloor(name, "FL", lat.toDouble(), lon.toDouble(), dis.toDouble(), buildingCode).observe(this@AddActivity) { result ->
                        when(result) {
                            is Resource.Loading -> {
                                //DO nothing
                            }
                            is Resource.Success -> {
                                Toast.makeText(this@AddActivity, "Floor Created", Toast.LENGTH_SHORT).show()
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}