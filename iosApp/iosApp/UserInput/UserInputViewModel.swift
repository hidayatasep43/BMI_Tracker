//
//  UserInputViewModel.swift
//  iosApp
//
//  Created by Asep hidayat on 23/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import Foundation
import Combine
import shared

class UserInputViewModel: ObservableObject {
    
    @Published var userInput = UserInput(age: "", height: "", weight: "")
    @Published var bmiResult: String = ""
    @Published var showAlert: Bool = false
    @Published var alertMessage: String = ""
    @Published var bmiResultMessage: String? = nil
    
    let growthRepository: GrowthRepository
    
    init(growthRepository: GrowthRepository = GrowthRepositoryHelper().getGrowthRepository()) {
        self.growthRepository = growthRepository
    }
    
    func calculateBMI() {
        // Validate inputs
        guard let age = Int(userInput.age), age >= 1, age <= 120 else {
            alertMessage = "Please enter a valid age between 1 and 120."
            showAlert = true
            return
        }
        
        guard let height = Float(userInput.height), height > 0 else {
            alertMessage = "Please enter a valid height."
            showAlert = true
            return
        }
        
        guard let weight = Float(userInput.weight), weight > 0 else {
            alertMessage = "Please enter a valid weight."
            showAlert = true
            return
        }
        
        // Calculate BMI
        let bmi = growthRepository.calculateBMI(weight: weight, height: height)
        bmiResult = String(format: "%.2f", bmi.bmi)
        bmiResultMessage = bmi.category
        
        Task {
            do {
                try await growthRepository.insertGrowth(weight: weight, height: height)
            } catch {
                alertMessage = "Error input"
                showAlert = true
            }
        }
    }
    
}

