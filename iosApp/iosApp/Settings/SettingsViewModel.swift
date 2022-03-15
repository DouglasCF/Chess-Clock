import Foundation
import shared

class SettingsViewModel: ObservableObject {
    
    @Published private(set) var gameModes: [GameModeModel] = []
    @Published private(set) var customGameModes: [GameModeModel] = []
    
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
        self.gameModeRepository.customGameModes.collect(
            collector: Collector<[GameMode]>() { gameModes in
                self.customGameModes = []
                gameModes.forEach { gameMode in
                    self.customGameModes.append(gameModeMapper.map(gameMode: gameMode))
                }
            }
        ) { result, error in print("compleion") }
    }
    
    func onGameModeButtonClicked(index: Int32) {
        gameModeRepository.selectGameMode(index: index)
    }
    
    func onCustomGameModeButtonClicked(index: Int32) {
        gameModeRepository.selectCustomGameMode(index: index)
    }
    
    func onCreateCustomGameModeButtonClicked(totalTime: Int64, increment: Int64) {
        if totalTime > 0 {
            gameModeRepository.addCustomGame(totalTime: totalTime, increment: increment)
        }
    }
}
