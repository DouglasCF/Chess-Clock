import Foundation
import shared

class SettingsViewModel: ObservableObject {
    private let gameModeRepository = GameModeRepository()
    
    @Published private(set) var gameModes: [GameMode] = []
    
    init() {
        gameModeRepository.gameModes.collect(
            collector: Collector<[GameMode]>() { gameModes in self.gameModes = gameModes }
        ) { result, error in print("compleion") }
    }
    
    func onGameModeButtonClicked(index: Int32) {
        gameModeRepository.selectGameMode(index: index)
    }
}
