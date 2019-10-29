package com.edwinacubillos.inventario

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edwinacubillos.inventario.model.Producto
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detalle.*
import kotlinx.android.synthetic.main.content_detalle.*

class DetalleActivity : AppCompatActivity() {

    private lateinit var producto: Producto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        setSupportActionBar(toolbar)

        producto = intent?.getSerializableExtra("producto") as Producto

        et_nombre.setText(producto.nombre)
        et_descripcion.setText(producto.descripcion)
        et_cantidad.setText(producto.cantidad.toString())

        val database = FirebaseDatabase.getInstance()
        val myRef =database.getReference("productos")

        bt_borrar.setOnClickListener{
            myRef.child(producto.idProducto).removeValue()
        }
    }
}
