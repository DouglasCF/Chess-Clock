import SwiftUI

struct VictoryText: View {
    var isWinner: Bool
    
    var body: some View {
        VStack {
            Spacer()
            Text(isWinner ? "You win!" : "You lose!")
                .padding()
                .background(isWinner ? Color("winner") : Color("looser"))
                .foregroundColor(.white)
                .cornerRadius(100)
                .padding()
        }
    }
}

struct VictoryText_Previews: PreviewProvider {
    static var previews: some View {
        VictoryText(isWinner: false)
    }
}
