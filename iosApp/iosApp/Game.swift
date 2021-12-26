import Foundation

private let miliConst = 100

struct Game {
    var totalTime: Int
    var increment: Int
    var whiteTimeRemaining: TimeInterval
    var blackTimeRemaining: TimeInterval
    var isPlaying = false
    var isWhiteMove = true
    
    let whiteTimer = Timer.publish(every: 1/100, on: .main, in: .common).autoconnect()
    let blackTimer = Timer.publish(every: 1/100, on: .main, in: .common).autoconnect()
    
    init(totalTime: Int, increment: Int, isPlaying: Bool = false) {
        self.totalTime = totalTime*miliConst
        self.increment = increment
        self.whiteTimeRemaining = TimeInterval(self.totalTime)
        self.blackTimeRemaining = TimeInterval(self.totalTime)
    }
    
    mutating func onWhitePressClock() {
        if whiteCanPlay() {
            changeTurn()
            whiteTimeRemaining += Double(increment*miliConst)
        }
    }
    
    mutating func onBlackPressClock() {
        if blackCanPlay() {
            changeTurn()
            blackTimeRemaining += Double(increment*miliConst)
        }
    }
    
    mutating func decrementWhiteTime() {
        if !isWhiteMove || !isPlaying { return }
        if whiteTimeRemaining > 0 { whiteTimeRemaining -= 1 }
        else { cancelTimers() }
    }
    
    mutating func decrementBlackTime() {
        if isWhiteMove || !isPlaying { return }
        if blackTimeRemaining > 0 { blackTimeRemaining -= 1 }
        else { cancelTimers() }
    }
    
    mutating func changeTurn() {
        isWhiteMove = !isWhiteMove
    }
    
    mutating func changePlayPause() {
        isPlaying = !isPlaying
    }
    
    func blackCanPlay() -> Bool {
        return !isWhiteMove && isPlaying
    }
    
    func whiteCanPlay() -> Bool {
        return isWhiteMove && isPlaying
    }
    
    func whiteLost() -> Bool {
        return whiteTimeRemaining <= 0
    }
    
    func blackLost() -> Bool {
        return blackTimeRemaining <= 0
    }
    
    private func cancelTimers() {
        blackTimer.upstream.connect().cancel()
        whiteTimer.upstream.connect().cancel()
    }
}
