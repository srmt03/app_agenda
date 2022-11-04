package br.senai.sp.jandira.agenda.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.databinding.ActivityMainBinding
import br.senai.sp.jandira.agenda.databinding.ActivityNewContactBinding
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.repository.ContactRepository
import java.time.LocalDate

class NewContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewContactBinding
    lateinit var contactRepository: ContactRepository
    lateinit var contato: Contact
    private var id = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contato = Contact()

        binding.buttonSave.setOnClickListener {
            save()
        }
        binding.buttonDelete.setOnClickListener {
            excluir()
        }
        id = intent.getIntExtra("id", 0)
        if (id > 0) {
            binding.buttonDelete.visibility = View.VISIBLE
            binding.buttonSave.text = "Atualizar"
            carregarContato()
        }
    }

    private fun excluir() {
        //confirmacao
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exclusão")
        builder.setMessage("Confirma a exclusão do(a) ${contato.nome} ?")
        builder.setPositiveButton("Sim"){_,_ ->
            contactRepository.delete(contato)
            finish()
        }
        builder.setNegativeButton("Não"){_,_ ->}

        builder.show()
    }

    private fun carregarContato() {
        contactRepository = ContactRepository(this)
        contato  = contactRepository.getContactById(id)

        binding.editTextName.setText(contato.nome)
        binding.editTextEmail.setText(contato.email)
        binding.editTextPhone.setText(contato.telefone)

    }

    private fun save() {

        contato.dataNascimento = binding.editTextDate.text.toString()
        contato.email = binding.editTextEmail.text.toString()
        contato.telefone = binding.editTextPhone.text.toString()
        contato.nome = binding.editTextName.text.toString()

        //Instancia do repositorio
        contactRepository = ContactRepository(this)
        if (id > 0){
            contactRepository.update(contato)
        } else {
            val id = contactRepository.save(contato)
        }
        Toast.makeText(this, "ID: $id", Toast.LENGTH_LONG).show()

        finish()
    }
}