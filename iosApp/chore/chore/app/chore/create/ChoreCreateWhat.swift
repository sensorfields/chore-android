import App
import SwiftUI

struct ChoreCreateWhat: View {

    @Binding var name: String

    var body: some View {
        TextField(
            text: $name,
        ) {
            Text("Name")
        }.textFieldStyle(.roundedBorder)
    }
}

#Preview {
    ChoreCreateWhat(
        name: Binding.constant(""),
    )
}

#Preview {
    ChoreCreateWhat(
        name: Binding.constant("Some name"),
    )
}
