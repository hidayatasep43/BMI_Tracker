//
//  GrowthHistoryRow.swift
//  iosApp
//
//  Created by Asep hidayat on 23/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI

struct GrowthHistoryRow: View {
    let growth: UserGrowthState

    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text("Height: \(String(format: "%.2f", growth.height)) cm")
                Text("Weight: \(String(format: "%.2f", growth.weight)) kg")
                Text("Date: \(growth.readableDate)")
            }
            .font(.body)
            Spacer()
            Text(formattedDate(growth.date))
                .font(.subheadline)
        }
    }

    private func formattedDate(_ date: Date) -> String {
        let formatter = DateFormatter()
        formatter.dateStyle = .medium
        return formatter.string(from: date)
    }
}

