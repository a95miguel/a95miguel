package com.medel.convertidordivisas.Data.Database.Model

import com.google.gson.annotations.SerializedName
/**
 * Ejemplo de como usar RoboPojoGenerator -> https://github.com/robohorse/RoboPOJOGenerator
* */


data class ResponseBmx(
	@SerializedName("bmx")
	val bmx: Bmx? = null
)

data class Bmx(
	@SerializedName("series")
	val series: List<SeriesItem?>? = null
)

data class SeriesItem(
	@SerializedName("datos")
	val datos: List<DatosItem?>? = null,
	@SerializedName("idSerie")
	val idSerie: String? = null,
	@SerializedName("titulo")
	val titulo: String? = null
)

data class DatosItem(
	@SerializedName("fecha")
	val fecha: String? = null,
	@SerializedName("dato")
	val dato: String? = null
)

/*
{
	"bmx": {
	"series": [
	{
		"idSerie": "SF60632",
		"titulo": "Cotización de la divisa Respecto al peso mexicano Dólar Canadiense",
		"datos": [
		{
			"fecha": "28/04/2023",
			"dato": "13.2573"
		}
		]
	}
	]
}
}
* */