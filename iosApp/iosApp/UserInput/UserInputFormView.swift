//
//  UserInputFormView.swift
//  iosApp
//
//  Created by Asep hidayat on 23/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI

struct UserInputFormView: View {
    @StateObject private var viewModel = UserInputViewModel()
    
    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                TextField("Enter your age", text: $viewModel.userInput.age)
                    .keyboardType(.numberPad)
                    .padding()
                    .background(Color(.systemGray6))
                    .cornerRadius(8)
                
                TextField("Enter your height (cm)", text: $viewModel.userInput.height)
                    .keyboardType(.decimalPad)
                    .padding()
                    .background(Color(.systemGray6))
                    .cornerRadius(8)
                
                TextField("Enter your weight (kg)", text: $viewModel.userInput.weight)
                    .keyboardType(.decimalPad)
                    .padding()
                    .background(Color(.systemGray6))
                    .cornerRadius(8)
                
                Button(action: viewModel.calculateBMI) {
                    Text("Calculate BMI")
                        .frame(maxWidth: .infinity)
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                }
                
                if !viewModel.bmiResult.isEmpty {
                    Text("Your BMI is \(viewModel.bmiResult)")
                        .font(.headline)
                        .foregroundColor(.green)
                }
                if let message = viewModel.bmiResultMessage {
                    Text(message)
                        .font(.subheadline)
                        .foregroundColor(.green)
                        .multilineTextAlignment(.center)
                        .padding()
                }
                Spacer()
            }
            .padding()
            .navigationTitle("User Input")
            .alert(isPresented: $viewModel.showAlert) {
                Alert(
                    title: Text("Invalid Input"),
                    message: Text(viewModel.alertMessage),
                    dismissButton: .default(Text("OK"))
                )
            }
        }
    }
}

