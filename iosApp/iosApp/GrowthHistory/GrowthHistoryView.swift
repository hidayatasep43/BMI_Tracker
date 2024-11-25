//
//  GrowthHistoryScreen.swift
//  iosApp
//
//  Created by Asep hidayat on 23/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI

struct GrowthHistoryScreen: View {
    @StateObject private var viewModel = GrowthHistoryViewModel()
    
    var body: some View {
        NavigationView {
            if viewModel.growthHistory.isEmpty {
                Text("No growth history available")
                    .font(.headline)
                    .foregroundColor(.gray)
                    .padding()
            } else {
                List(viewModel.growthHistory) { growth in
                    GrowthHistoryRow(growth: growth)
                }
            }
        }.navigationTitle("Growth Records")
            .onAppear {
                Task {
                    await viewModel.fetchGrowthRecords()
                }
            }
            .alert(isPresented: $viewModel.showAlert) {
                Alert(
                    title: Text("Error"),
                    message: Text(viewModel.errorMessage),
                    dismissButton: .default(Text("OK"))
                )
            }
    }
}

