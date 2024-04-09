package com.medel.vivero_v1.home.data.repository

import android.net.Uri
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.medel.vivero_v1.home.data.local.ProductDao
import com.medel.vivero_v1.home.data.mapper.toDomain
import com.medel.vivero_v1.home.data.mapper.toEntity
import com.medel.vivero_v1.home.domain.models.Product
import com.medel.vivero_v1.home.domain.repository.HomeRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await

/**
 * Implementacion de [HomeRepository] que utiliza Room para el almacenamiento local y Firebase para el almacenamiento remoto
 *
 * @property dao Dao para acceder a la base de datos local
 */
class HomeRepositoryImpl(
    private val dao : ProductDao
) : HomeRepository {
    // Referencia de la ubicacion de los datos de Firebase Realtime
    private val dataRef = FirebaseDatabase.getInstance().getReference("Plantas")
    // Referencia de almacenamiento de imagenes en  Firebase Storage
    private val storeRef = FirebaseStorage.getInstance().reference.child("Images")

    /**
     * Obtiene los datos de firebase y los combina con room
     */
    override fun getAllProducts(): Flow<List<Product>> {
        // Obtener datos de la base de datos local
        val localProductsFlow = dao.getAllProducts().map { products ->
            products.map { it.toDomain() }
        }

        // Obtener datos de Firebase y realizar las operaciones necesarias
        val firebaseProductsFlow = getData().flatMapConcat { firebaseProducts ->
            // Insertar productos de Firebase en la base de datos local
            insertToDao(firebaseProducts)

            // Obtener productos locales después de la inserción
            val localProducts = dao.getAllProducts().first()

            // Filtrar los productos locales para eliminar los que no están en Firebase
            val firebaseProductIds = firebaseProducts.map { it.id }
            val productsToDelete = localProducts.filterNot { local ->
                firebaseProductIds.contains(local.id)
            }

            // Eliminar productos locales que no están en Firebase
            productsToDelete.forEach { product ->
                dao.deleteProduct(product.id)
            }

            // Devolver los productos de Firebase
            flowOf(firebaseProducts)
        }

        // Combinar el flujo de productos locales y de Firebase
        return localProductsFlow.combine(firebaseProductsFlow) { localProducts, _ ->
            localProducts
        }
    }

    /**
     * Obtiene los datos de Firebase Realtime
     */
    private fun getData():Flow<List<Product>> = callbackFlow {
        val valueEventListener = object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val product = snapshot.children.mapNotNull {
                    it.getValue(Product::class.java)
                }
                try{
                    trySend(product)
                }catch (e : Exception){
                    close(e)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        dataRef.addValueEventListener(valueEventListener)
        awaitClose { 
            dataRef.removeEventListener(valueEventListener)
        }
    }

    /**
     * Inserta productos en la base de datos local Room.
     */
    private suspend fun insertToDao(product: List<Product>){
        product.forEach {
            dao.insert(it.toEntity())
        }
    }

    /**
     * Inserta un nuevo producto en Firebase Realtime y almacena la imagen en Firebase Storage
     *
     *  @param product Producto a insertar
     *  @return Resultado de la operacion
     */
    override suspend fun insertProduct(product: Product): Result<Unit> {
          return try{
              //Genera un identificador unico
              val id = dataRef.push().key
              val uri  = Uri.parse(product.imageUrl)
              val imageRef = storeRef.child("$id")
              // Esperar a que se complete la carga del archivo
              val uploadTask = imageRef.putFile(uri).await()
              if (uploadTask.task.isSuccessful) {
                  // Esperar a que se obtenga la URL de descarga del archivo
                  val uriResult = imageRef.downloadUrl.await()
                  val urlImage = uriResult.toString()
                  product.imageUrl = urlImage
                  product.id = id.toString()
                  // Esperar a que se complete la escritura en la base de datos
                  dataRef.child(id!!).setValue(product).await()
                  //Inserta el producto en room
                  dao.insert(product.toEntity())
                  //Devuelve un resultado exitosoç
                  Result.success(Unit)
              } else {
                //Error al cargar la imagen
                Result.failure(RuntimeException("Error al cargar los datos, vuelva a intentar"))
            }
        }catch (e: Exception){
             e.printStackTrace()
              //Devuelve un resultado fallido con un mensaje de la excepcion
             Result.failure<Unit>(e)
        }
    }

    /**
     * Obtiene un producto de la lista por su id
     */
    override suspend fun getProductById(id: String): Product {
        return dao.getProductById(id).toDomain()
    }

    /**
     * Actualiza un producto y la imagen si ha cambiado.
     *
     * @param product Producto a insertar
     * @return Resultado de la operacion
     */
    override suspend fun updateProducts(product: Product): Result<Unit> {
        return try {

            val imageRef = storeRef.child(product.id)
            val currentImage = product.imageUrl

            val imageUrl = imageRef.downloadUrl.await()
            val imageUrlString = imageUrl.toString()

            if (imageUrlString != currentImage) {
                val uploadTask = imageRef.putFile(Uri.parse(currentImage)).await()

                val urlImage = imageRef.downloadUrl.await().toString()
                product.imageUrl = urlImage

                // Actualizar la URL de la imagen en la base de datos
                updateProductInDatabase(product)
            } else {
                // La imagen no ha cambiado, solo actualizamos otros campos en la base de datos
                updateProductInDatabase(product)
            }
            //Inserta el producto actualizado en room
            dao.insert(product.toEntity())
            //Devuelve el resultado exitoso
            Result.success(Unit)
        }catch (e : Exception){
            e.printStackTrace()
            //Devuelve el resultado fallido con el mensaje de la excepcion
            Result.failure<Unit>(e)
        }
    }

    /**
     * Actualiza el producto existete
     */
    private fun updateProductInDatabase(product: Product) {
        dataRef.child(product.id).setValue(product)
    }

    /**
     * Elimina un producto de Firebase Realtime y Storage
     *
     * @param id el producto a eliminar
     * @return Resultado de la operacion
     */
    override suspend fun deleteProduct(id: String):Result<Unit> {
        return try{
            //Elimina la imagen en firebase storage
            storeRef.child(id).delete().await()
            //Elimina en Firebase realtime
            dataRef.child(id).removeValue().await()
            //Elimina el producto en la room
            dao.deleteProduct(id)
            //Devuelve un resultado exitoso
            Result.success(Unit)
        }catch(e:Exception){
            e.printStackTrace()
            //Devuelve un resultado fallido
            Result.failure<Unit>(e)
        }
    }

}
