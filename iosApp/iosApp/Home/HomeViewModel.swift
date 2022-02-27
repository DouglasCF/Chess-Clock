import shared

class HomeViewModel: ObservableObject {
    
    private var increment: Int64 = 0
    private var totalTime: Int64 = 5
    
    @Published private(set) var game: Game = Game(totalTime: 5*60*1000, increment: 0, isPlaying: false, isWhiteMove: true, whiteTimeRemaining:5*60*1000, blackTimeRemaining: 5*60*1000)
    
    private let gameModeRepository: GameModeRepository
    init(gameModeRepository: GameModeRepository) {
        self.gameModeRepository = gameModeRepository
        restartGame()
        observeGameMode()
    }
    
    private func observeGameMode() {
        gameModeRepository.gameModes.collect(
            collector: Collector<[GameMode]>() { gameModes in
                let gameMode = gameModes.first(where: { $0.isSelected == true })
                self.totalTime = gameMode?.time ?? 0
                self.increment = gameMode?.increment ?? 0
                print("gameMode updated: " + String(describing: gameMode))
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
        game = Game(totalTime: self.totalTime*60*1000, increment: self.increment*1000, isPlaying: false, isWhiteMove: true, whiteTimeRemaining: self.totalTime*60*1000, blackTimeRemaining: self.totalTime*60*1000)
    }
}
