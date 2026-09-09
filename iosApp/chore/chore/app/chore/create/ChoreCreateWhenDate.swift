import SwiftUI

struct ChoreCreateWhenDate: View {

    @State private var date: Date = Date()

    var body: some View {
        DatePicker(selection: $date, displayedComponents: [.date], label: {})
            .datePickerStyle(.graphical)
    }
}

#Preview {
    ChoreCreateWhenDate()
}
