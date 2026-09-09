import App
import SwiftUI

struct ChoreCreateWhat: View {

    let name: String
    let onNameChange: (String) -> Void

    var body: some View {
        TextField(
            text: Binding(
                get: { name },
                set: onNameChange,
            )
        ) {
            Text("Name")
        }
    }
}

#Preview {
    ChoreCreateWhat(
        name: "",
        onNameChange: { _ in },
    )
}

#Preview {
    ChoreCreateWhat(
        name: "Some name",
        onNameChange: { _ in },
    )
}
