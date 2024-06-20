package RecyclerViewHelper

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cesar.landaverde.cesarperfil222222222222222.R

class ViewHolder (view: View):RecyclerView.ViewHolder(view){
    val txtTicket : TextView = view.findViewById(R.id.txtTicket)

    val txtEstatus : TextView = view.findViewById(R.id.txtEstatus)

    val imgEdit : TextView = view.findViewById(R.id.imgEdit)

    val imgEliminar : TextView = view.findViewById(R.id.imgEliminar)

}