import shared

class GameModeMapper {
    
    func map(gameMode: GameMode) -> GameModeModel {
        return GameModeModel(text: gameMode.text, isSelected: gameMode.isSelected)
    }
}
