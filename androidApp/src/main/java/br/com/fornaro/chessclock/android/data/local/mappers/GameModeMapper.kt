package br.com.fornaro.chessclock.android.data.local.mappers

import br.com.fornaro.chessclock.android.domain.models.GameModeModel
import br.com.fornaro.chessclock.model.GameMode

typealias GameModeMapperAlias = Mapper<GameMode, GameModeModel>

object GameModeMapper : GameModeMapperAlias {
    override fun map(input: GameMode) = with(input) {
        GameModeModel(
            text = text,
            isSelected = isSelected,
        )
    }
}
