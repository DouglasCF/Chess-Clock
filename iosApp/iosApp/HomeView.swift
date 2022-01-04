import SwiftUI

struct HomeView: View {
    @ObservedObject var homeViewModel = HomeViewModel()
    let minHeight = CGFloat(80)
    
    let whiteTimer = Timer.publish(every: 1/10, on: .main, in: .common).autoconnect()
    let blackTimer = Timer.publish(every: 1/10, on: .main, in: .common).autoconnect()
    
    var body: some View {
        let game = homeViewModel.game
        
        ZStack {
            VStack(spacing: 0) {
                Button(action: { homeViewModel.onWhitePressedClock() }) {
                    TimeText(time: game.whiteTimeRemainingString)
                        .frame(maxWidth: .infinity, maxHeight: game.isWhiteMove ? .infinity : minHeight, alignment: .bottom)
                        .foregroundColor(.black)
                        .background(.white)
                        .rotationEffect(Angle(degrees: 180))
                        .onReceive(whiteTimer) { _ in
                            homeViewModel.decreaseWhiteTime()
                        }
                }
                
                HStack {
                    Spacer()
                    
                    Image(systemName: "gearshape.fill")
                        .resizable()
                        .frame(width: 30, height: 30)
                    
                    Spacer()
                    
                    Button(action: { homeViewModel.changePlayPause() }) {
                        Image(systemName: game.isPlaying ? "pause.fill" : "play.fill")
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
                
                Button(action: { homeViewModel.onBlackPressedClock() }) {
                    TimeText(time: game.blackTimeRemainingString)
                        .frame(maxWidth: .infinity, maxHeight: game.isWhiteMove ? minHeight : .infinity, alignment: .bottom)
                        .foregroundColor(.white)
                        .background(.black)
                        .onReceive(blackTimer) { _ in
                            homeViewModel.decreaseBlackTime()
                        }
                }
            }
            
            IncrementalText(value: game.incrementTimeString)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .foregroundColor(.black)
                .rotationEffect(Angle(degrees: 180))
            
            IncrementalText(value: game.incrementTimeString)
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
