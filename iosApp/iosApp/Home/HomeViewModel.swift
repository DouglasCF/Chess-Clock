import shared

class HomeViewModel: ObservableObject {
    
    @Published private(set) var game: Game = Game(totalTime: 5*60*1000, increment: 0, isPlaying: false, isWhiteMove: true, whiteTimeRemaining:5*60*1000, blackTimeRemaining: 5*60*1000)
    
    var gameMode: GameMode?
    
    private let gameModeRepository: GameModeRepository
    init(gameModeRepository: GameModeRepository) {
        self.gameModeRepository = gameModeRepository
        observeGameMode()
    }
    
    private func observeGameMode() {
        gameModeRepository.gameModes.collect(
            collector: Collector<[GameMode]>() { gameModes in
                self.gameMode = gameModes.first(where: { $0.isSelected == true })
                print("gameMode updated: " + String(describing: self.gameMode))
            }
        ) { result, error in print("compleion") }
    }
    
    private func updateGame() {
        game = game.copy() as! Game
    }
    
    func changePlayPause() {
        game.changePlayPause()
        updateGame()
    }
    
    func onWhitePressedClock() {
        game.onWhitePressedClock()
        updateGame()
    }
    
    func onBlackPressedClock() {
        game.onBlackPressedClock()
        updateGame()
    }
    
    func decreaseWhiteTime() {
        game.decreaseWhiteTime()
        updateGame()
    }
    
    func decreaseBlackTime() {
        game.decreaseBlackTime()
        updateGame()
    }
    
    func restartGame() {
        guard let gameMode = self.gameMode else { return }
        game = Game(totalTime: gameMode.time*60*1000, increment: gameMode.increment*1000, isPlaying: false, isWhiteMove: true, whiteTimeRemaining: gameMode.time*60*1000, blackTimeRemaining: gameMode.time*60*1000)
    }
}
