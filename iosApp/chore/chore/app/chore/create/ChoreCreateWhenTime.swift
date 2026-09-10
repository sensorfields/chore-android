import SwiftUI

struct ChoreCreateWhenTime: View {

    @Binding var time: Date

    var body: some View {
        DatePicker(selection: $time, displayedComponents: [.hourAndMinute], label: {})
            .datePickerStyle(.wheel)
    }
}

#Preview {
    ChoreCreateWhenTime(
        time: Binding.constant(Date()),
    )
}
