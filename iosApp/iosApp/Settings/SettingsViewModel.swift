import Foundation
import shared

class SettingsViewModel: ObservableObject {
    
    @Published private(set) var gameModes: [GameMode] = []
    
    private let gameModeRepository: GameModeRepository
    init(gameModeRepository: GameModeRepository) {
        self.gameModeRepository = gameModeRepository
        self.gameModeRepository.gameModes.collect(
            collector: Collector<[GameMode]>() { gameModes in self.gameModes = gameModes }
        ) { result, error in print("compleion") }
    }
    
    func onGameModeButtonClicked(index: Int32) {
        gameModeRepository.selectGameMode(index: index)
    }
}
