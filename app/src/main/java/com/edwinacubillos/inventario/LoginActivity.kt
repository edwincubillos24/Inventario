package com.edwinacubillos.inventario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    val ERROR_USUARIO_NO_EXISTE = "There is no user record corresponding to this identifier. The user may have been deleted."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        bt_iniciar.setOnClickListener {
            val correo = et_correo.text.toString()
            val contrasena = et_contrasena.text.toString()

            auth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        goToMainActivity()
                    } else {
                        // If sign in fails, display a message to the user.
                        if (task.exception?.message.toString() == ERROR_USUARIO_NO_EXISTE){
                            crearUsuario(correo, contrasena)
                        }
                    }
                }
        }
    }

    private fun crearUsuario(correo: String, contrasena: String) {
        auth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    goToMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("MSG", "createUserWithEmail:failure", task.exception)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null){
            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
