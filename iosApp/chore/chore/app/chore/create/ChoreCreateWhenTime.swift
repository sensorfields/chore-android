import SwiftUI

struct ChoreCreateWhenTime: View {

    @State private var date: Date = Date()

    var body: some View {
        DatePicker(selection: $date, displayedComponents: [.hourAndMinute], label: {})
            .datePickerStyle(.wheel)
    }
}

#Preview {
    ChoreCreateWhenTime()
}
