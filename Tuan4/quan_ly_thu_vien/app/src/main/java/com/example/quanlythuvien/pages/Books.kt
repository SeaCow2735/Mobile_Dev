package com.example.quanlythuvien.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quanlythuvien.NavItem
import com.example.quanlythuvien.Routes
import com.example.quanlythuvien.data.SharedVM

@Composable
fun BooksPage(navController: NavController, vm: SharedVM) {
    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home, Routes.Home),
        NavItem("Books", Icons.Default.Book, Routes.Books),
        NavItem("SV", Icons.Default.Person, Routes.Students),
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.routes,
                        onClick = {
                            if (currentRoute != item.routes) {
                                navController.navigate(item.routes) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .statusBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hệ thống\nQuản lý Thư viện",
                    fontSize = 32.sp,
                    lineHeight = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(30.dp))

                //NHẬP TÊN SÁCH (cho SV đang chọn)
                var newBook by rememberSaveable { mutableStateOf("") }

                Text(
                    text = if (vm.selectedStudent.isNotBlank())
                        "Sách của ${vm.selectedStudent}"
                    else
                        "Sách",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = newBook,
                        onValueChange = { newBook = it },
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        placeholder = { Text("Tên sách") }
                    )

                    Spacer(Modifier.width(12.dp))

                    Button(
                        onClick = {
                            vm.addBookForSelected(newBook)
                            newBook = ""
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1766C4)),
                        modifier = Modifier.height(44.dp),
                        enabled = newBook.trim().isNotEmpty() && vm.selectedStudent.isNotBlank()
                    ) {
                        Text("Thêm", color = Color.White)
                    }
                }

                //DANH SÁCH SÁCH (theo SV hiện tại
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE6E6E6))
                        .padding(vertical = 12.dp)
                ) {
                    val books = vm.booksForSelected()

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        if (books.isEmpty()) {
                            item {
                                Text(
                                    if (vm.selectedStudent.isBlank())
                                        "Chưa chọn sinh viên"
                                    else
                                        "Chưa có sách nào",
                                    color = Color.DarkGray,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            itemsIndexed(books) { index, name ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp)
                                        .clip(RoundedCornerShape(24.dp))
                                        .background(Color.White)
                                        .padding(horizontal = 12.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${index + 1}. $name",
                                        fontSize = 16.sp,
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(onClick = { vm.removeBookAtForSelected(index) }) {
                                        Icon(Icons.Default.Delete, contentDescription = "Xóa sách")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


