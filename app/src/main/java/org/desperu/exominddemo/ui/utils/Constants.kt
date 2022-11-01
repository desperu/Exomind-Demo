package org.desperu.exominddemo.ui.utils

// --- FOR WEATHER REQUEST ---

// City list

const val RENNES = "Rennes"
const val PARIS = "Paris"
const val NANTES = "Nantes"
const val BORDEAUX = "Bordeaux"
const val LYON = "Lyon"

val cityList = listOf(RENNES, PARIS, NANTES, BORDEAUX, LYON)

// --- FOR UTILS ---

// For Loading Utils

const val ONE_MIN_MILLIS = 60 * 1000L

// For Weather Utils

const val CLEAR_SKY = 1 // for "01d"
const val FEW_CLOUDS = 2 // for "02d"
const val SCATTERED_CLOUDS = 3 // for "03d"
const val BROKEN_CLOUDS = 4 // for "04d"
const val SHOWER_RAIN = 9 // for "09d"
const val RAIN = 10 // for "10d"
const val THUNDERSTORM = 11 // for "11d"
const val SNOW = 13 // for "13d"
const val MIST = 50 // for "50d"


// --- FOR HELPER ---

// For SnackBarHelper

const val DURATION = 2000
