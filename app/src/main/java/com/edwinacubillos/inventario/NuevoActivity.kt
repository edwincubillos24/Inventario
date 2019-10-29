package com.edwinacubillos.inventario

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edwinacubillos.inventario.model.Producto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_nuevo.*
import kotlinx.android.synthetic.main.content_nuevo.*

class NuevoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo)
        setSupportActionBar(toolbar)

        val database = FirebaseDatabase.getInstance()
        val myRef =database.getReference("productos")

        val idProducto = myRef.push().key

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        val idUsuario= currentUser?.uid

        bt_guardar.setOnClickListener {
            val producto = Producto(idProducto!!,
                et_nombre.text.toString(),
                et_descripcion.text.toString(),
                et_cantidad.text.toString().toInt(),
                idUsuario!!,
                ""
                )
            myRef.child(idProducto).setValue(producto)
            onBackPressed()
        }


    }
}
