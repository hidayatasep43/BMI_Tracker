import SwiftUI
import shared

struct ContentView: View {
    
    var body: some View {
        TabView {
            UserInputFormView()
                .tabItem {
                    Label("Input", systemImage: "pencil")
                }
            
            GrowthHistoryScreen()
                .tabItem {
                    Label("History", systemImage: "list.bullet")
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
