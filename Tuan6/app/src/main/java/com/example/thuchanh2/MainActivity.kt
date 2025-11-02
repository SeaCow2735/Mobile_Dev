package com.example.thuchanh2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.thuchanh2.data.Product
import com.example.thuchanh2.data.ProductUiState
import com.example.thuchanh2.data.ProductViewModel
import com.example.thuchanh2.ui.theme.ThucHanh2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThucHanh2Theme {
                ProductScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(44.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF2196F3)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }


            Text(
                text = "Product detail",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(8.dp))


        Box(Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(R.drawable.img),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(36.dp))
                    .align(Alignment.Center)
                    .height(300.dp)
            )
        }

        Spacer(Modifier.height(16.dp))


        Text(
            "Giày Nike Nam Nữ Chính Hãng - Nike Air Force 1\n" +
                    "'07 LV8 - Màu Trắng | JapanSport HF2898-100",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Justify
        )

        Spacer(Modifier.height(12.dp))


        Text(
            "Giá: 4.000.000₫",
            fontSize = 20.sp,
            color = Color(0xFFD32F2F),
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))


        Text(
            text = "Với giày chạy bộ, từng gram đều quan trọng. Đó là lý do tại sao đế giữa LIGHTSTRIKE PRO mới nhẹ hơn so với phiên bản trước. " +
                    "Mẫu upper ôm gọn giúp siết nhẹ vào mu bàn chân và lớp đệm đàn hồi được thiết kế để hạn chế hao năng lượng. " +
                    "Trong các mùa chạy tập luyện, công nghệ này được thiết kế nhằm hỗ trợ các bạn vận động viên tiếp cận được nhịp chạy hiệu năng hơn giữa các cuộc đua.",
            modifier = Modifier.padding(12.dp),
            fontSize = 13.sp,
            textAlign = TextAlign.Justify,
            color = Color(0xFF333333)
        )

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun ProductScreen(
    productId: String? = null,
    vm: ProductViewModel = viewModel()
) {
    LaunchedEffect(productId) {
        if (productId == null) vm.loadProduct() else vm.loadProductById(productId)
    }

    val state by vm.state.collectAsState()

    when (val s = state) {
        is ProductUiState.Loading -> LoadingView()
        is ProductUiState.Error -> ErrorView(
            message = s.message,
            onRetry = {
                if (productId == null) vm.loadProduct() else vm.loadProductById(productId)
            }
        )
        is ProductUiState.Success -> ProductContent(product = s.data)
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { CircularProgressIndicator() }
}

@Composable
private fun ErrorView(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lỗi: $message")
        Spacer(Modifier.height(12.dp))
        Button(onClick = onRetry) { Text("Thử lại") }
    }
}

@Composable
private fun ProductContent(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            model = product.imgURL,
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 180.dp)
        )

        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = formatPrice(product.price),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = product.des,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis
        )
    }
}

private fun formatPrice(price: Double): String {
    return "%,.2f".format(price)
}