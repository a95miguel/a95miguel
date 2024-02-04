package com.example.vivero.data

import androidx.lifecycle.LiveData
import com.example.vivero.data.local.ProductDao
import com.example.vivero.domain.model.ProductLocal
import com.example.vivero.domain.model.Productos
import com.example.vivero.data.remote.FirebaseDaoImpl
import com.example.vivero.data.remote.FirebaseProductDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Proporciona los metodos para interactuar con los datos relacionados con Productos.
 *
 * @param firebase Instancia de [FirebaseProductDao] para interactuar con Frebase Realtime.
 *
 * @param dao Instancia de [ProductDao] para interactuar con la base de datos local Room.
 */
class ProductRepository @Inject constructor(
    private val firebase : FirebaseDaoImpl,
    private val dao : ProductDao
) {
    /**
     * Obtinen la instancia de autenticacion de firebase.
     *
     * @return La instancia de [FirebaseAuth] para gestionar la autenticacion, o null si hay un error
     */
    fun auth () : FirebaseAuth?{
        return try {
            firebase.auth()
        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }

    /**
     * Inicia sesion anonima utilizando firebase Autenticacion
     *
     * @return El objeto [FirebaseUser] representa al usuario que inicio sesion , o null si hay un error.
     */
    suspend fun signInAnonymously():FirebaseUser?{
        return try{
            firebase.signInAnonymously()
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    /**
     * Obtine la lista de productos desde Firebase con LiveData
     *
     * @return [LiveData] que obtine la lista de [Productos]
     */
    fun getProduct(): LiveData<List<Productos>> {
        return firebase.getProduct()
    }

    /**
     * Obtiene la lista de productos almacenados localmente en ROOM con un Flow
     *
     * @return [Flow] que emite la lista de [ProductLocal]
     */
    fun getProductRoom():Flow<List<ProductLocal>>{
        return dao.getProduct()
    }

    /**
     * Inserta el nuevo producto  de la base de datos de Firebase Realtime.
     *
     * @param product El Producto que va a insertar
     *
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    fun insertProduct(product: Productos, onComplete: (Boolean) -> Unit){
        try {
            firebase.insertProduct(product,onComplete)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    /**
     * Inserta datos en la base de datos local de ROOM
     *
     * @param product La lista de [Productos] que se va a insertar en la base de datos local.
     */
    suspend fun insertDataRoom(product: List<Productos>){
         try {
             //Mapea la lista de productos a la lista de room y realiza la insercion.
             val productList = product.map {
                 ProductLocal(
                     id = it.id,
                     name = it.name,
                     detail = it.detail,
                     price = it.price
                 )
             }
             dao.insertProduct(productList)
         }catch (e:Exception){
             e.printStackTrace()
         }
    }

    /**
     * Actualiza la informacion de productos en la base de datos de Firebase Realtime.
     *
     * @param product El Producto con la informacion actualizada.
     *
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    fun updateProduct(product: Productos, onComplete: (Boolean) -> Unit){
        try {
            firebase.updateProduct(product,onComplete)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    /**
     * Actualiza la imagen de un producto en la base de datos de firebase Storage
     *
     * @param product El producto con la nueva imagen
     *
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    fun updateImage(product: Productos, onComplete: (Boolean) -> Unit){
        try {
            firebase.updateImage(product,onComplete)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    /**
     * Elimina un producto de la base de dato de Firebase Realtime
     *
     * @param id El id del producto que se va a eliminar.
     *
     * @param onComplete retorna un booleano si la operacion fue exitosa o no
     */
    fun deleteProduct(id : String,onComplete: (Boolean) -> Unit){
         try {
             firebase.deleteProduct(id,onComplete)
         }catch (e:Exception){
             e.printStackTrace()
         }
    }

    /**
     * Elimina un producto de la base de datos de Room
     *
     * @param id El id del producto que se va a eliminar.
     */
    suspend fun deleteRoom(id: String){
        try {
            dao.deleteProduct(id)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    /**
     * Cierra la sesion actual del usuario utilizado.
     */
    fun signOut(){
        try {
            return firebase.signOut()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}