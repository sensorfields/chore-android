import SwiftUI

struct ChoreCreateWhenDate: View {

    @Binding var date: Date

    var body: some View {
        ScrollView(.vertical) {
            DatePicker(selection: $date, displayedComponents: [.date], label: {})
                .datePickerStyle(.graphical)
        }
    }
}

#Preview {
    ChoreCreateWhenDate(
        date: Binding.constant(Date()),
    )
}
