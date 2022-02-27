import Foundation
import shared

class SettingsViewModel: ObservableObject {
    
    @Published private(set) var gameModes: [GameModeModel] = []
    
    private let gameModeRepository: GameModeRepository
    private let gameModeMapper: GameModeMapper
    init(gameModeRepository: GameModeRepository, gameModeMapper: GameModeMapper) {
        self.gameModeRepository = gameModeRepository
        self.gameModeMapper = gameModeMapper
        self.gameModeRepository.gameModes.collect(
            collector: Collector<[GameMode]>() { gameModes in
                self.gameModes = []
                gameModes.forEach { gameMode in
                    self.gameModes.append(gameModeMapper.map(gameMode: gameMode))
                }
            }
        ) { result, error in print("compleion") }
    }
    
    func onGameModeButtonClicked(index: Int32) {
        gameModeRepository.selectGameMode(index: index)
    }
}
