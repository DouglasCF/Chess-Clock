package br.com.fornaro.chessclock.android.data.local.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}
