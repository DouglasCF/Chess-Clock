import Foundation
import shared

class HomeViewModel: ObservableObject {
    @Published private(set) var game: Game = Game(totalTime: 1*60*1000, increment: 2, isPlaying: false, isWhiteMove: true, whiteTimeRemaining:1*60*1000, blackTimeRemaining: 1*60*1000)
    
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
}
