package com.example.thuchanh2.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thuchanh2.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface ProductUiState {
    data object Loading : ProductUiState
    data class Success(val data: Product) : ProductUiState
    data class Error(val message: String) : ProductUiState
}

class ProductViewModel(
    private val repo: ProductRepository = ProductRepository()
) : ViewModel() {

    private val _state = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val state: StateFlow<ProductUiState> = _state

    fun loadProduct() {
        _state.value = ProductUiState.Loading
        viewModelScope.launch {
            val result = repo.fetchProduct()
            _state.value = result.fold(
                onSuccess = { ProductUiState.Success(it) },
                onFailure = { ProductUiState.Error(it.message ?: "Đã có lỗi xảy ra") }
            )
        }
    }

    fun loadProductById(id: String) {
        _state.value = ProductUiState.Loading
        viewModelScope.launch {
            val result = repo.fetchProductById(id)
            _state.value = result.fold(
                onSuccess = { ProductUiState.Success(it) },
                onFailure = { ProductUiState.Error(it.message ?: "Đã có lỗi xảy ra") }
            )
        }
    }
}
