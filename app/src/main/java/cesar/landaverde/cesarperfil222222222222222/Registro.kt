package cesar.landaverde.cesarperfil222222222222222

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import java.util.UUID

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtcorreoRegis = findViewById<EditText>(R.id.txtUserRegistro)
        val txtcontraRegis = findViewById<EditText>(R.id.txtContraRegistro)
        val btnRegistrar = findViewById<Button>(R.id.btnRegis)

        btnRegistrar.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val objConexion = ClaseConexion().cadenaConexion()

                    // Verifica que objConexion no sea nulo
                    if (objConexion == null) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@Registro, "Error en la conexión a la base de datos", Toast.LENGTH_SHORT).show()
                        }
                        return@launch
                    }

                    val crearUsuario = objConexion.prepareStatement("Insert into Usuarios (UUID,nombre_user,contra_user) Values (?,?,?)")

                    // Verifica que los campos de texto no sean nulos o vacíos
                    val correo = txtcorreoRegis.text?.toString()

                    if (correo.isNullOrEmpty()) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@Registro, "Por favor, ingrese el correo", Toast.LENGTH_SHORT).show()
                        }
                        return@launch
                    }

                    crearUsuario.setString(1, UUID.randomUUID().toString())
                    crearUsuario.setString(2, correo)
                    crearUsuario.setString(3, correo)
                    crearUsuario.executeUpdate()

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@Registro, "Usuario Creado", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@Registro, "Error al crear el usuario: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

       val imgvolve = findViewById<ImageView>(R.id.imgVolverrr)
        imgvolve.setOnClickListener{

            val PRegreso = Intent(this,Login::class.java)
            startActivity(PRegreso)
        }



    }

}