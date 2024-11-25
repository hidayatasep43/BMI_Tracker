//
//  Untitled.swift
//  iosApp
//
//  Created by Asep hidayat on 23/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import Foundation
import Combine
import shared

class GrowthHistoryViewModel: ObservableObject {
    
    @Published var growthHistory: [UserGrowthState] = []
    @Published var errorMessage: String = ""
    @Published var showAlert: Bool = false
    
    let growthRepository: GrowthRepository
    
    init(growthRepository: GrowthRepository = GrowthRepositoryHelper().getGrowthRepository()) {
        self.growthRepository = growthRepository
    }
    
    func fetchGrowthRecords() async {
        do {
            let records = try await growthRepository.getAllGrowthRecords()
            // Convert the retrieved data into UserGrowth models
            let userGrowthList = records.map { record in
                UserGrowthState(height: record.height, weight: record.weight, timeInSeconds: record.date)
            }
            // Update the published property to notify the UI
            DispatchQueue.main.async {
                self.growthHistory = userGrowthList
            }
        } catch {
            // Handle errors and update error message
            DispatchQueue.main.async {
                self.showAlert = true
                self.errorMessage = "Failed to fetch records: \(error.localizedDescription)"
            }
        }
    }
    
    
}


