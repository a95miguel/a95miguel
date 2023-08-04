package com.medel.notas.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.medel.notas.R
import com.medel.notas.adapter.TaskAdapter
import com.medel.notas.databinding.FragmentHomeBinding
import com.medel.notas.model.TaskData
import com.medel.notas.network.NightModeHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.runBlocking

class HomeFragment : Fragment(), AddTaskFragment.DialogNextBtnClickListener,
    TaskAdapter.TaskAdapterClickAdapter {
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding
    private var addTaskFragment: AddTaskFragment? = null
    private lateinit var adapter: TaskAdapter
    private lateinit var mlist: MutableList<TaskData>
    private var valor = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nightMode(requireContext())
        init(view)
        registerEvents()
        checkInternet()


    }

    /**
     * Método que utiliza ReactiveNetwork para observar cambios en la conectividad de red.
     * Este método se ejecuta cuando el estado de la red cambia y se actualizan las vistas en función del estado.
     */
    private fun checkInternet() {
        ReactiveNetwork.observeNetworkConnectivity(requireContext())
            .subscribeOn(Schedulers.io()) // Observar en el hilo de background (Schedulers.io()).
            .observeOn(AndroidSchedulers.mainThread())// Observar en el hilo principal (AndroidSchedulers.mainThread()).
            .subscribe { connectivity ->
                //Toast.makeText(context,it.state().name,Toast.LENGTH_SHORT).show()
                // Obtiene el estado de la conectividad actual y lo guarda en la variable 'valor'.
                valor = connectivity.state().name
                if (valor == "DISCONNECTED") {
                    binding.network.isVisible = true
                    binding.progressBar.isVisible = false
                    binding.recyclerview.isVisible = false
                    binding.btnSetting.isVisible = false
                    binding.btnAddNote.isVisible = false
                } else {
                    binding.network.isVisible = false
                    binding.recyclerview.isVisible = true
                    getDataFirebase()
                }
            }
    }


    private fun nightMode(context: Context) {
        // Para obtener el estado actual del modo nocturno desde las preferencias compartidas
        val isNightMode = NightModeHelper.getNightMode(context)
        // Si el modo nocturno está activado, se desactiva; de lo contrario, se activa.
        if (isNightMode) {
            NightModeHelper.setNightMode(context, true)
        } else {
            NightModeHelper.setNightMode(context, false)
        }
    }


    private fun getDataFirebase() {
        runBlocking {
            databaseRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    mlist.clear()
                    // Verifica si existe algún dato en la referencia de la base de datos.
                    if (snapshot.exists()) {
                        binding.btnAddNote.isVisible = true
                        binding.btnSetting.isVisible = true
                        // Itera a través de los datos en la referencia de la base de datos.
                        for (taskSnapshot in snapshot.children) {
                            // Obtiene la clave (key) y el valor (value) del DataSnapshot actual y crea un objeto TaskData.
                            val task = taskSnapshot.key?.let {
                                TaskData(it, taskSnapshot.value.toString())
                            }
                            // Si el objeto TaskData no es nulo, agrega el objeto a la lista 'mlist'.
                            if (task != null) {
                                binding.progressBar.isVisible = false
                                mlist.add(task)
                            }
                        }
                        // Notifica al adaptador que los datos de la lista han cambiado.
                        adapter.notifyDataSetChanged()
                    } else {
                        binding.btnAddNote.isVisible = true
                        binding.progressBar.isVisible = false
                        binding.btnSetting.isVisible = true
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })

        }
    }

    private fun registerEvents() {
        binding.btnAddNote.setOnClickListener {
            if (addTaskFragment != null)
                childFragmentManager.beginTransaction().remove(addTaskFragment!!).commit()
            addTaskFragment = AddTaskFragment()
            addTaskFragment!!.setListener(this)
            addTaskFragment!!.show(
                childFragmentManager,
                AddTaskFragment.TAG
            )
        }
        binding.btnSetting.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_settingFragment)
        }

    }

    private fun init(view: View) {
        // Configuración del controlador de navegación
        navController = Navigation.findNavController(view)
        // Inicialización de Firebase Authentication
        auth = FirebaseAuth.getInstance()
        // Inicialización de la referencia a Firebase Realtime Database con la ruta "Tasks" para el usuario actual
        databaseRef = FirebaseDatabase.getInstance().reference.child("Tasks")
            .child(auth.currentUser?.uid.toString())

        // Fija el tamaño del RecyclerView para mejorar el rendimiento
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        mlist = mutableListOf()
        // Creación de una instancia del adaptador personalizado TaskAdapter y se le pasa la lista mlist
        adapter = TaskAdapter(mlist)
        adapter.setListener(this)
        // Asigna el adaptador al RecyclerView
        binding.recyclerview.adapter = adapter
    }

    override fun onSaveTask(task: String, etTask: TextInputEditText) {
        // Utiliza la referencia 'databaseRef' para insertar un nuevo dato en la base de datos.
        databaseRef.push().setValue(task).addOnCompleteListener {
            // Comprueba si la tarea de guardar datos fue exitosa.
            if (it.isSuccessful) {
                Toast.makeText(context, R.string.task_save, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
            // Limpia el contenido del campo de texto después de guardar.
            etTask.text = null
            // Cierra el fragmento
            addTaskFragment!!.dismiss()
        }
    }

    override fun onEditTask(taskData: TaskData, etTask: TextInputEditText) {
        // Crea un HashMap para actualizar los datos en Firebase Realtime Database.
        val map = HashMap<String, Any>()
        map[taskData.id] = taskData.task
        // Utiliza la referencia 'databaseRef' para actualizar los datos de la tarea con el HashMap.
        databaseRef.updateChildren(map).addOnCompleteListener {
            // Comprueba si la tarea de actualización de datos fue exitosa.
            if (it.isSuccessful) {
                Toast.makeText(context, R.string.update_data, Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
            etTask.text = null
            addTaskFragment!!.dismiss()
        }
    }

    override fun onDeleteTaskBtn(taskData: TaskData) {
        // Muestra el mensaje de alerta utilizando un AlertDialog.
        val alertDialog = AlertDialog.Builder(context)
            .setTitle(R.string.confirm_delete)
            .setMessage("Eliminar: ${taskData.task}")
            .setPositiveButton("Si, eliminar") { _, _ ->
                // Utiliza 'databaseRef.child(taskData.id)' para apuntar al nodo específico de ID  y eliminar su valor.
                databaseRef.child(taskData.id).removeValue().addOnCompleteListener {
                    // Comprueba si la tarea de eliminación de datos fue exitosa.
                    if (it.isSuccessful) {
                        Toast.makeText(context, R.string.delete_data, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }

            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        alertDialog.show()
        // Obtiene el botón negativo del AlertDialog y cambia el color del texto.
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.text))
        // Obtiene el botón positivo del AlertDialog y cambia el color del texto.
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.color_delete))
    }

    override fun onEditTaskBtn(taskData: TaskData) {
        // Verifica si el  diálogo de edición existe y está en uso.
        if (addTaskFragment != null)
        // Si existe, elimina el dialogo
            childFragmentManager.beginTransaction().remove(addTaskFragment!!).commit()
        // Crea una nueva instancia del diálogo de edición
        addTaskFragment = AddTaskFragment.newIntance(taskData.id, taskData.task)
        // Establece el listener (escuchador) para el diálogo de edición.
        addTaskFragment!!.setListener(this)
        // Muestra el diálogo
        addTaskFragment!!.show(childFragmentManager, AddTaskFragment.TAG)
    }

}