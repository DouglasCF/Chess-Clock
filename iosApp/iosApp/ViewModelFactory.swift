import Foundation
import shared

class ViewModelFactory {
    let gameModeRepository = GameModeRepository()
    let gameModeMapper = GameModeMapper()
    
    func makeHomeViewModel() -> HomeViewModel {
        return HomeViewModel(gameModeRepository: gameModeRepository)
    }
    
    func makeSettingsViewModel() -> SettingsViewModel {
        return SettingsViewModel(gameModeRepository: gameModeRepository, gameModeMapper: gameModeMapper)
    }
}
