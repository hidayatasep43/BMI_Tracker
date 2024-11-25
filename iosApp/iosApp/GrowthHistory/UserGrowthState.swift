
import Foundation

struct UserGrowthState: Identifiable {
    let id: UUID
    let height: Float
    let weight: Float
    let date: Date

    init(height: Float, weight: Float, timeInSeconds: Int64) {
        self.id = UUID()
        self.height = height
        self.weight = weight
        self.date = Date(timeIntervalSince1970: TimeInterval(timeInSeconds)) // Convert Int64 to Date
    }

    // Computed property to provide a human-readable date string
    var readableDate: String {
        let formatter = DateFormatter()
        formatter.dateStyle = .medium // For "Nov 21, 2024"
        formatter.timeStyle = .none  // No time included
        return formatter.string(from: date)
    }
}


