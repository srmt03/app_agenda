package br.senai.sp.jandira.agenda.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.ui.NewContactActivity

class ContactAdapter(var contactList: List<Contact>, var context: Context):RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    fun updateContactList(newContactList: List<Contact>){
        this.contactList = newContactList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_layout, parent, false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val contact = contactList[position]

        holder.textViewNome.text = contact.nome
        holder.textViewTelefone.text = contact.telefone
        holder.textViewEmail.text = contact.email
        holder.textViewInicial.text = contact.nome.substring(0,1)

        holder.cardViewContact.setOnClickListener{
            val intent = Intent(context, NewContactActivity::class.java)
            intent.putExtra("id", contact.id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class ContactHolder(view: View):RecyclerView.ViewHolder(view){
        val textViewNome: TextView = view.findViewById(R.id.textNome)
        val textViewTelefone: TextView = view.findViewById(R.id.textTelefone)
        val textViewEmail: TextView = view.findViewById(R.id.textEmail)
        val textViewInicial: TextView = view.findViewById(R.id.textInicial)
        val cardViewContact: CardView = view.findViewById(R.id.cardViewContato)
    }
}