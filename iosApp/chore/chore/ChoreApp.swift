import App
import SwiftUI

@main
struct ChoreApp: App {

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

let appGraph: IosAppGraph = createAppGraph()
