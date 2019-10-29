package com.edwinacubillos.inventario

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edwinacubillos.inventario.model.Producto
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var productosList = ArrayList<Producto>()
    private lateinit var productosAdapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        cargarProductos()

        productosAdapter = ProductoAdapter(productosList)

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        recyclerview.adapter = productosAdapter


        fab.setOnClickListener { view ->
            val intent = Intent(this, NuevoActivity::class.java)
            startActivity(intent);
        }
    }

    private fun cargarProductos() {
        val database = FirebaseDatabase.getInstance()
        val myRef =database.getReference("productos")

        myRef.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot: DataSnapshot in dataSnapshot.children){
                    val producto: Producto? = snapshot.getValue(Producto::class.java)
                    productosList.add(producto!!)
                }
                productosAdapter.notifyDataSetChanged()
            }
        })
    }
}
















