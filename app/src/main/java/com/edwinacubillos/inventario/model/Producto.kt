package com.edwinacubillos.inventario.model

import java.io.Serializable

class Producto(
    val idProducto: String,
    val nombre: String,
    val descripcion: String,
    val cantidad: Int,
    val idUsuario: String,
    val urlFoto: String
) : Serializable {
    constructor() : this(
        "",
        "",
        "",
        0,
        "",
        ""
    )
}