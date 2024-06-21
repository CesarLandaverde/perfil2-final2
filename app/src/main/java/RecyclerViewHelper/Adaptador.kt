package RecyclerViewHelper

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import cesar.landaverde.cesarperfil222222222222222.InformacionDetalle
import cesar.landaverde.cesarperfil222222222222222.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion
import modelo.Tickets
import java.text.FieldPosition
import java.util.UUID

class Adaptador(var Datos:List<Tickets>):RecyclerView.Adapter<ViewHolder>() {

    fun actualizarListado(nuevaLista:List<Tickets>){
        Datos =nuevaLista
        notifyDataSetChanged()
    }


    fun EliminarDatos(tituloTick : String , position: Int){


        val listDatos = Datos.toMutableList()
        listDatos.removeAt(position)

        GlobalScope.launch(Dispatchers.IO) {

            val objConexion = ClaseConexion().cadenaConexion()

            val eliminarticket = objConexion?.prepareStatement("Delete tickets where titulo_ticket = ?")!!

            eliminarticket.setString(1,tituloTick)
            eliminarticket.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()


        }

        Datos = listDatos.toList()
        notifyItemRemoved(position)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vistaa = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card,parent,false)

        return ViewHolder(vistaa)
    }

    override fun getItemCount() = Datos.size

    fun ActualizarDatos(estadonuevo: String, uuid:String){
        GlobalScope.launch(Dispatchers.IO) {

            val objConexion = ClaseConexion().cadenaConexion()

            val actuTicket =objConexion?.prepareStatement("Update tickets Set estado_ticket = ? Where uuid = ?")!!
            actuTicket.setString(1,estadonuevo)
            actuTicket.setString(2,uuid)
            actuTicket.executeUpdate()


        }


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Datos[position]

        holder.txtTicket.text = item.titulo

        holder.txtEstatus.text = item.estado

        holder.imgEliminar.setOnClickListener{

            val context = holder.itemView.context

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Confirmacion")
            builder.setMessage("¿Desear eliminar ticket?")
            builder.setPositiveButton("Si"){
                dialog, which->
                EliminarDatos(item.titulo,position)
            }

            builder.setNegativeButton("No"){dialog, which ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()




        }

        holder.imgEdit.setOnClickListener{

            val context = holder.itemView.context
            val builder = AlertDialog.Builder(context)

            builder.setTitle("Editar Estado")

            val cuadradito = EditText(context)

            cuadradito.setHint(item.estado)

            builder.setView(cuadradito)

            builder.setPositiveButton("Guardar") { dialog, which->
                ActualizarDatos(cuadradito.text.toString(),item.uuid)
            }

            builder.setNegativeButton("Cancelar") {dialog , wich->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()

        }

        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val pantallaInfo = Intent(context,InformacionDetalle::class.java)

        }
    }
    }
