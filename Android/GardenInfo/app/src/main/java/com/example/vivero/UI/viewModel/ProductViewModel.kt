package com.example.vivero.UI.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.vivero.domain.model.PlantasState
import com.example.vivero.domain.model.Productos
import com.example.vivero.data.ProductRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ProductViewModel expone la interracion de los datos y la logica de presentacion.
 *
 * El ViewModel es inyectado por dagger hilt, se encarga de la autenticacion, la obtecion de los datos de
 * firebase y room
 *
 * @param repository  Proporciona los metodos para interactuar con la capa de datos
 */
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository : ProductRepository
): ViewModel() {
    //Instacia de autenticacionl
    private val auth =  repository.auth()
    private val currentUser = mutableStateOf(auth?.currentUser)
    val current : State<FirebaseUser?> = currentUser

    private val _products = mutableStateOf(listOf(Productos("Loading","","","","")))
    val products: State<List<Productos>> get() = _products

    var state by mutableStateOf(PlantasState())
        private set

    /**
     * Inicializacion del viewModel  y actualiza el usuario actual
     */
    init{
        Log.d("ViewModel", "ProductViewModel created")
        //Observa los cambios de autenticacion
        auth?.addAuthStateListener{ firebaseAuth->
            //Log.d("UserFirebase","${firebaseAuth.currentUser?.uid}")
            currentUser.value = firebaseAuth.currentUser
        }
        observeFirebaseAndRoom()
        getProductRoom()
    }


    /**
     * Inicia sesion anonima utilizando Firebase Authentication
     *
     * @return [FirebaseUser] representa al usuario que inicio sesion, o null si hay error
     */
    suspend fun signInAnonymously(): FirebaseUser? {
        return try{
            //inicia sesion anonima utilizando el repositorio
             repository.signInAnonymously()
        }catch (e : Exception){
            //manejo de Exception en caso de un error
            e.printStackTrace()
            null
        }
    }

    /***
     * Observa los cambios entre firebase y room para actualizar la lista de productos
     */
    private fun observeFirebaseAndRoom(){
        try{
            repository.getProduct().observeForever {list->
                _products.value = list //?: emptyList()
                if(list!=null){
                    viewModelScope.launch {
                        //Inserta los datos a Room
                        repository.insertDataRoom(list)
                    }
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    /**
     * Obtiene la lista de product en Room  y actualiza el estado de la aplicaión
     */
    private fun getProductRoom(){
        try{
            viewModelScope.launch {
                repository.getProductRoom().collectLatest{productList->
                    state = state.copy(
                        listPlantas = productList
                    )
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }


    /**
     * Inserta un nuevo producto en la base de datos de firebase, con la funcion onComplete con el resultado
     *
     * @param product  El producto que va a insertar
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    fun insertProduct(product : Productos, onComplete : (Boolean)->Unit){
        try{
            viewModelScope.launch {
                repository.insertProduct(product,onComplete)
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    /**
     * Actualiza la informacion de producto de la base de datos.
     *
     * @param product  El producto con la informacion actualizada
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    fun updatePoduct(product: Productos, onComplete: (Boolean) -> Unit){
        try{
            viewModelScope.launch {
                repository.updateProduct(product,onComplete)
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    /**
     * Actualiza la imagen de la base de datos.
     *
     * @param product  El producto con la nueva imagen
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    fun updateImage(product: Productos, onComplete: (Boolean) -> Unit){
        try {
            viewModelScope.launch {
                repository.updateImage(product,onComplete)
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    /**
     * Elimina el producto de la base de datos de firebase.
     *
     * Ademas, elimina el producto de la base de dato de room
     *
     * @param id  El id  que va a eliminar de la base de datos.
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    fun deleteProduct(id:String,onComplete: (Boolean) -> Unit){
        try{
            //Utiliza viewModelScope para ejecuta en un hilo secundario
            viewModelScope.launch {
                repository.deleteProduct(id){isDeleted->
                    if(isDeleted){
                        //Si la operacion delete fue exitosa de la base de datos remoto
                        onComplete(true)
                        viewModelScope.launch (Dispatchers.IO){
                            repository.deleteRoom(id)
                        }
                    }else{
                        onComplete(false)
                    }
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    /**
     * Cierra sesion actual del usuario
     */
    fun signOut(){
        try {
            //Cierra sesion utlizando el repositorio
            repository.signOut()
        }catch (e : Exception){
            //manejo de Exception en caso de un error
            e.printStackTrace()
        }
    }
}