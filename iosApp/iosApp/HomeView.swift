import SwiftUI

struct HomeView: View {
    @State var game = Game(totalTime: 1*60, increment: 2)
    let minHeight = CGFloat(80)
    
    var body: some View {
        ZStack {
            VStack(spacing: 0) {
                Button(action: { self.game.onWhitePressClock() }) {
                    TimeText(timer: self.game.whiteTimeRemaining)
                        .frame(maxWidth: .infinity, maxHeight: self.game.isWhiteMove ? .infinity : minHeight, alignment: .bottom)
                        .foregroundColor(.black)
                        .background(.white)
                        .rotationEffect(Angle(degrees: 180))
                        .onReceive(self.game.whiteTimer) { _ in
                            self.game.decrementWhiteTime()
                        }
                }
                
                HStack {
                    Spacer()
                    
                    Image(systemName: "gearshape.fill")
                        .resizable()
                        .frame(width: 30, height: 30)
                    
                    Spacer()
                    
                    Button(action: { self.game.changePlayPause() }) {
                        Image(systemName: self.game.isPlaying ? "pause.fill" : "play.fill")
                            .resizable()
                            .frame(width: 30, height: 30)
                    }
                    .buttonStyle(PlainButtonStyle())
                    
                    Spacer()
                    
                    Image(systemName: "stop.fill")
                        .resizable()
                        .frame(width: 30, height: 30)
                    
                    Spacer()
                }
                .frame(height: minHeight)
                .background(.gray)
                
                Button(action: { self.game.onBlackPressClock() }) {
                    TimeText(timer: self.game.blackTimeRemaining)
                        .frame(maxWidth: .infinity, maxHeight: self.game.isWhiteMove ? minHeight : .infinity, alignment: .bottom)
                        .foregroundColor(.white)
                        .background(.black)
                        .onReceive(self.game.blackTimer) { _ in
                            self.game.decrementBlackTime()
                        }
                }
            }
            
            IncrementalText(value: game.increment)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .foregroundColor(.black)
                .rotationEffect(Angle(degrees: 180))
            
            IncrementalText(value: game.increment)
                .frame(maxWidth: .infinity, alignment: .leading)
                .foregroundColor(.white)
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
