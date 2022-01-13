import SwiftUI
import shared

struct GameModeItem: View {
    var gameMode: GameMode
    var action: () -> Void
    
    var body: some View {
        Button(action: action) {
            Text(gameMode.text)
                .padding()
                .background(gameMode.isSelected ? Color("GameModeSelected") : Color("GameMode"))
                .cornerRadius(100)
                .foregroundColor(.white)
        }
    }
}

struct GameModeItem_Previews: PreviewProvider {
    static var previews: some View {
        GameModeItem(gameMode: GameMode(time: 1, increment: 1, isSelected: true), action: {})
    }
}
