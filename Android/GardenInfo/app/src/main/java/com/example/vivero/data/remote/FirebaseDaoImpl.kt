package com.example.vivero.data.remote

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vivero.domain.model.Productos
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Implementacion de [FirebaseProductDao] que interactua con Firebase Realtime Database y Storage.
 */
class FirebaseDaoImpl @Inject constructor(
    private val database :  FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth,
    private val store : FirebaseStorage
): FirebaseProductDao {
    //Referencia de la ubicacion en Firebase Realtime Database
    private val dataRef = database.getReference("Plantas")
    //Referencia de la ubicacion de imagenes en Firebase Storage
    private val storeRef = store.getReference("Images")
    private var valueEventListener: ValueEventListener? = null

    /**
     * Inicia sesion anonima utilizando Firebase Authentication
     *
     * @return El objeto [FirebaseUser]  representa al usuario que inicio sesion, o null si hay un error.
     */
    override suspend fun signInAnonymously(): FirebaseUser? {
        return try {
            firebaseAuth.signInAnonymously().await().user
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Obtinen la instancia de autenticacion de firebase.
     *
     * @return La instancia de [FirebaseAuth] para gestionar la autenticacion, o null si hay un error
     */
    override  fun auth(): FirebaseAuth? {
        return try {
            firebaseAuth
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Obtine la lista de productos desde Firebase con LiveData
     *
     * @return [LiveData] que obtine la lista de [Productos]
     */
    override fun getProduct(): LiveData<List<Productos>> {
        //MutableLiveData almacena la lista de productos
        val liveDataProductos = MutableLiveData<List<Productos>>()
        //Verefica si hay un ValueEventListener existente.
        valueEventListener?.let {
            // Desvincular el ValueEventListener anterior si existe
            dataRef.removeEventListener(it)
        }
        //ValueEventListener escucha los cambios de los datos de Firebase.
        val newValueEvent = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //Mapea los elementos y  actualiza el liveData
                val item = snapshot.children.mapNotNull { dataSnapshot ->
                    dataSnapshot.getValue(Productos::class.java)
                }
                liveDataProductos.postValue(item)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseDao","Error: ${error.message}")
            }
        }
        valueEventListener = newValueEvent
        dataRef.addValueEventListener(newValueEvent)
        //Retorna el LiveData que sera observado para recibir actualizaciones
        return liveDataProductos
    }

    /**
     * Inserta el nuevo producto  de la base de datos de Firebase Realtime y la imagen en Firebase Storage.
     *
     * @param product El Producto que va a insertar
     *
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    override  fun insertProduct(product: Productos, onComplete: (Boolean) -> Unit) {
        try {
            //Genera un id unico para el producto
            val id = dataRef.push().key
            val uri = Uri.parse(product.imageUrl)
            //Obtiene la referencia en donde se almacenara la imagen
            val imageRef = storeRef.child(id.toString())
            //Sube la  imagen a Firebase Storage
            val upload = imageRef.putFile(uri)

            // Si la imagen se insertó correctamente
            upload.addOnSuccessListener {
                // Obtengo la URL de descarga de la imagen
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    val downloadUrlImagen = uri.toString()
                    // Agrego la URL a los datos
                    product.imageUrl = downloadUrlImagen
                    product.id = id.toString()
                    //Inserta el producto a Firebase Realtime Database
                    id?.let {
                        dataRef.child(it).setValue(product).addOnSuccessListener {
                            //Actualiza la base de datos de Firebase
                            updateProductDatabase(product,onComplete)
                        }
                    }
                }
            }.addOnFailureListener {
                it.printStackTrace()
                onComplete(false)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("FirebaseDao","Error al insertar: ${e.message}")
            onComplete(false)
        }
    }

    /**
     *Actualiza la imagen de Firebase Storage.
     *
     * @param product La imagen que sera actualizada
     *
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    override  fun updateImage(product: Productos, onComplete: (Boolean) -> Unit) {
        try {
            //Obtiene la referencia de la  ubicacion en Firebase Storage
            val imageRef = storeRef.child(product.id)
            val uri = Uri.parse(product.imageUrl)
            // Obtiene la url de la imagen
            val currentImageUrl = product.imageUrl
            val newImage = imageRef.path
            //Verefica si la url de la imagen actual es diferente a la nueva url
            if(currentImageUrl!=newImage){
                //Insertamos la nueva imagen a Firebase Storage
                val upload = imageRef.putFile(uri)
                //Si la carga de la imagen fue exitosa, obtiene la nueva URL.
                upload.addOnSuccessListener {
                    imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                        val dowloadUrl = downloadUri.toString()
                        //Actualiza la url
                        product.imageUrl = dowloadUrl
                        updateProductDatabase(product, onComplete)
                    }.addOnFailureListener {
                        it.printStackTrace()
                        onComplete(false)
                    }
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
            Log.e("FirebaseDao","Error al actualizar imagen: ${e.message}")
        }
    }

    /**
     * Actualiza la informacion de productos en la base de datos de Firebase Realtime.
     *
     * @param product El Producto con la informacion actualizada.
     *
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    override  fun updateProduct(product: Productos, onComplete: (Boolean) -> Unit) {
        try {
            //Con ValueEventListener vereficamos la existencia del producto en Firebase Realtime
            dataRef.child(product.id).addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    //Obtiene el producto de la base de datos
                    val existProduct = snapshot.getValue(Productos::class.java)
                    //Vereficamos si el producto existe.
                    if(existProduct==null){
                        onComplete(false)
                    }else{
                        //Actualiza los datos.
                        updateProductDatabase(product, onComplete)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    //En caso de un error, la operacion es false
                    onComplete(false)
                }
            })
        }catch (e:Exception){
            e.printStackTrace()
            Log.e("FirebaseDao","Error al actualizar datos: ${e.message}")
            onComplete(false)
        }
    }

    /**
     * Actualiza los  datos de Firebase Realtime
     *
     * @param product El producto con los datos actualizado
     *
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    fun updateProductDatabase(product: Productos, onComplete: (Boolean) -> Unit) {
        dataRef.child(product.id).setValue(product).addOnCompleteListener {
            if(it.isSuccessful){
                onComplete(true)
            }else{
                it.exception?.printStackTrace()
                onComplete(false)
            }
        }
    }

    /**
     * Elimina un producto de la base de dato de Firebase Realtime
     *
     * @param id El id del producto que se va a eliminar.
     *
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    override  fun deleteProduct(id: String, onComplete: (Boolean) -> Unit) {
        try {
            //Eimina el dato utilizando la clave proporcionada
            dataRef.child(id).removeValue()
            //Elimina la imagen de Store
            val imageDelete = storeRef.child(id)
            imageDelete.delete().addOnSuccessListener {
                onComplete(true)
            }.addOnFailureListener {
                onComplete(false)
            }
        }catch (e:Exception){
            e.printStackTrace()
            Log.e("FirebaseDao","Error al eliminar: ${e.message}")
        }
    }

    /**
     * Cierra la sesion actual del usuario utilizado.
     */
    override  fun signOut() {
        firebaseAuth.signInAnonymously()
    }


}