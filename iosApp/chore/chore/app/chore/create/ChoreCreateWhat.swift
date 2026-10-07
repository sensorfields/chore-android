import App
import SwiftUI

struct ChoreCreateWhat: View {

    @Binding var name: String
    @FocusState var focused: Bool

    var body: some View {
        ScrollView(.vertical) {
            TextField(Res.string.shared.chore_create_name.format(), text: $name)
                .textFieldStyle(.roundedBorder)
                .focused($focused)
        }
        .onAppear {
            focused = true
        }
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
