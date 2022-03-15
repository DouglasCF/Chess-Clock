import SwiftUI

struct SettingsView: View {
    @EnvironmentObject private var settingsViewModel: SettingsViewModel
    
    @State private var totalTime: String = ""
    @State private var increment: String = ""
    
    var body: some View {
        let gameModes = settingsViewModel.gameModes
        let customGameModes = settingsViewModel.customGameModes
        
        HStack {
            VStack(alignment: .leading) {
                Text("Game Mode")
                    .fontWeight(.bold)
                    .padding([.leading, .top])
                
                GameModeList(list: gameModes, action: settingsViewModel.onGameModeButtonClicked(index:))
                GameModeList(list: customGameModes, action: settingsViewModel.onGameModeButtonClicked(index:))
                
                HStack {
                    Spacer()
                    VStack {
                        TextField("Total time", text: $totalTime)
                            .keyboardType(.numberPad)
                        TextField("Increment", text: $increment)
                            .keyboardType(.numberPad)
                    }
                    .frame(width: 120)
                    .textFieldStyle(.roundedBorder)
                    Button(action: {
                        settingsViewModel.onCreateCustomGameModeButtonClicked(totalTime: Int64(totalTime) ?? 0, increment: Int64(increment) ?? 0)
                        totalTime = ""
                        increment = ""
                    }) {
                        Text("Create a custom game")
                            .font(.headline)
                            .frame(height: 30)
                            .foregroundColor(.white)
                            .padding()
                            .background(Color("GameMode"))
                            .cornerRadius(8)
                    }
                    Spacer()
                }
                .padding(.horizontal)

                Spacer()
            }
        }
        .navigationBarTitle("Settings")
    }
    
    private func GameModeList(list: [GameModeModel], action: @escaping (Int32) -> Void) -> some View {
        ScrollView(.horizontal, showsIndicators: false) {
            LazyHStack(spacing: 12) {
                ForEach(list.indices, id: \.self) { index in
                    GameModeItem(gameMode: list[index]) {
                        action(Int32(index))
                    }
                }
            }
        }
        .frame(height: 70)
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView()
    }
}
