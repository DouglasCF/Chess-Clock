import SwiftUI

struct SettingsView: View {
    @EnvironmentObject private var settingsViewModel: SettingsViewModel
    
    var body: some View {
        let gameModes = settingsViewModel.gameModes
        
        HStack {
            VStack(alignment: .leading) {
                Text("Game Mode")
                    .fontWeight(.bold)
                    .padding([.leading, .top])
                
                ScrollView(.horizontal, showsIndicators: false) {
                    LazyHStack(spacing: 12) {
                        ForEach(gameModes.indices, id: \.self) { index in
                            GameModeItem(gameMode: gameModes[index]) {
                                settingsViewModel.onGameModeButtonClicked(index: Int32(index))
                            }
                        }
                    }
                }
                .frame(height: 70)

                Spacer()
            }
        }
        .navigationBarTitle("Settings")
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView()
    }
}
