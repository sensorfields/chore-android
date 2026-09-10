import App
import SwiftUI

struct ChoreCreateWhenWeek: View {

    var body: some View {
        ForEach(DayOfWeek.allCases, id: \.ordinal) { day in
            Toggle(day.name, isOn: Binding.constant(false))
        }
    }
}

#Preview {
    ChoreCreateWhenWeek()
}
