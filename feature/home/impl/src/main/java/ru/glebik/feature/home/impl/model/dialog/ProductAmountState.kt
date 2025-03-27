package ru.glebik.feature.home.impl.model.dialog

data class ProductAmountState(
    val isShow: Boolean,
    val productId: Int,
    val amount: Int,
)