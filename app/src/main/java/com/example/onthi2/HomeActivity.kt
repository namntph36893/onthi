package com.example.onthi2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.onthi2.ui.theme.OnThi2Theme

class HomeActivity : ComponentActivity() {

    data class Flower(val id: Int, val name: String, val price: String, val url: String)

    // tao danh sach du lieu
    val flowerList = listOf(
        Flower(
            1,
            "hoa loa ken 1",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
        Flower(
            2,
            "hoa loa ken 2",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
        Flower(
            3,
            "hoa loa ken 3",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
        Flower(
            4,
            "hoa loa ken 4",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
        Flower(
            5,
            "hoa loa ken 5",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
        Flower(
            6,
            "hoa loa ken 6",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
        Flower(
            7,
            "hoa loa ken 7",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
        Flower(
            8,
            "hoa loa ken 8",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
        Flower(
            9,
            "hoa loa ken 9",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
    )
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var DanhSach by remember { mutableStateOf(flowerList.toMutableList()) }
            var selectedFlower by remember {
                mutableStateOf<Flower?>(null)
            }
            var update by remember {
                mutableStateOf<Flower?>(null)
            }
            var newFlowerName by remember { mutableStateOf("") }
            var newFlowerPrice by remember { mutableStateOf("") }
            var newFlowerUrl by remember { mutableStateOf("") }

            // Function to update flower details
            fun updateFlower(updatedFlower: Flower) {
                val index = DanhSach.indexOfFirst { it.id == updatedFlower.id }
                if (index != -1) {
                    DanhSach[index] = updatedFlower
                }
            }


            LazyColumn(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
//                columns = GridCells.Fixed(3)
            ) {
                items(DanhSach.size) { viTri ->
                    val flower = DanhSach.get(viTri)
                    Column {
                        AsyncImage(
                            modifier = Modifier.size(410.dp, 300.dp),
                            contentScale = ContentScale.Crop,
                            model = flower.url, contentDescription = ""
                        )
                        Text(text = "ID: ${flower.id}")
                        Text(text = "Name: ${flower.name}")
                        Text(text = "Price: ${flower.price}")
                        Row {
                            Button(onClick = {
                                // 1 mo dialog
                                selectedFlower = flower
                                // 2 mo man hinh moi
                                // 3. hien thi thong tin bang Toast
                            }) {
                                Text(text = "Detail")
                            }
                            Button(onClick = {
                                DanhSach = DanhSach.toMutableList().apply { remove(flower) }
                            }) {
                                Text(text = "Delete")
                            }
                            Button(onClick = {
                                update = flower
                                newFlowerName = flower.name
                                newFlowerPrice = flower.price
                                newFlowerUrl = flower.url
                            }) {
                                Text(text = "Update")
                            }
                        }                                      
                    }
                }
            }
            // Dialog for updating flower details
            update?.let {
                AlertDialog(
                    onDismissRequest = { update = null },
                    title = { Text("Update Flower") },
                    confirmButton = {
                        Button(
                            onClick = {
                                val updatedFlower = Flower(
                                    id = it.id,
                                    name = newFlowerName,
                                    price = newFlowerPrice,
                                    url = newFlowerUrl
                                )
                                updateFlower(updatedFlower)
                                update = null
                                newFlowerName = ""
                                newFlowerPrice = ""
                                newFlowerUrl = ""
                            }
                        ) {
                            Text("Update")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                update = null
                                newFlowerName = ""
                                newFlowerPrice = ""
                                newFlowerUrl = ""
                            }
                        ) {
                            Text("Cancel")
                        }
                    },
                    text = {
                        Column {
                            TextField(
                                value = newFlowerName,
                                onValueChange = { newFlowerName = it },
                                label = { Text("Name") },
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            TextField(
                                value = newFlowerPrice,
                                onValueChange = { newFlowerPrice = it },
                                label = { Text("Price") },
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            TextField(
                                value = newFlowerUrl,
                                onValueChange = { newFlowerUrl = it },
                                label = { Text("Image URL") },
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                )
            }

            selectedFlower?.let {
                AlertDialog(
                    onDismissRequest = {
                        selectedFlower = null
                    },
                    title = {
                        Text(it.name)
                    },
                    text = {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(200.dp, 150.dp),
                                contentScale = ContentScale.Crop,
                                model = it.url, contentDescription = ""
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Name: ${it.name}")
                            Text("Price: ${it.price}")
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                selectedFlower = null
                            }
                        ) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}