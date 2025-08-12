# Stock Trading Simulation System

A comprehensive Java console application that simulates a real-world stock trading environment with advanced features for market data, portfolio management, and transaction tracking.

## 🚀 **Features**

### ✅ **Core Trading Features**
- **Buy/Sell Operations** - Execute stock trades with real-time validation
- **Portfolio Management** - Track holdings, average prices, and total value
- **Market Data Display** - Real-time stock prices, changes, and volume
- **Transaction History** - Complete audit trail of all trading activities

### ✅ **User Management**
- **User Registration & Login** - Secure user authentication system
- **Portfolio Tracking** - Individual portfolio performance monitoring
- **Fund Management** - Deposit and withdraw funds
- **Net Worth Calculation** - Real-time portfolio + cash balance

### ✅ **Market Simulation**
- **Dynamic Pricing** - Simulate market movements and price changes
- **Stock Availability** - Track available shares for trading
- **Market Cap Calculation** - Real-time market capitalization
- **Price Change Tracking** - Monitor daily price movements

### ✅ **OOP Architecture**
- **User Class** - Manages user data, portfolio, and transactions
- **Stock Class** - Handles stock information and price updates
- **Transaction Class** - Records all buy/sell activities with timestamps

## 🏗️ **System Architecture**

### **Classes & Responsibilities**

#### **1. StockTradingSystem (Main Class)**
- Central application controller
- Menu system and user interface
- Data management and validation
- Market simulation engine

#### **2. User Class**
- User authentication and profile management
- Portfolio holdings and average price calculations
- Balance management (deposits/withdrawals)
- Stock purchase and sale operations

#### **3. Stock Class**
- Stock information storage (symbol, company, price)
- Price change tracking and updates
- Available shares management
- Market data calculations

#### **4. Transaction Class**
- Transaction record keeping
- Timestamp and audit trail
- Buy/sell operation details
- User activity tracking

## 📊 **Sample Data Included**

### **Pre-loaded Stocks**
- **AAPL** - Apple Inc. ($150.00)
- **GOOGL** - Alphabet Inc. ($2,800.00)
- **MSFT** - Microsoft Corporation ($300.00)
- **AMZN** - Amazon.com Inc. ($3,200.00)
- **TSLA** - Tesla Inc. ($800.00)
- **FB** - Meta Platforms Inc. ($350.00)
- **NVDA** - NVIDIA Corporation ($600.00)
- **NFLX** - Netflix Inc. ($500.00)

### **Sample Users**
- **admin** / admin123 - Admin User ($100,000)
- **john** / pass123 - John Doe ($50,000)
- **jane** / pass456 - Jane Smith ($75,000)

## 🎯 **How to Use**

### **1. Main Menu Options**
```
1. User Login          - Access user dashboard
2. Register New User   - Create new trading account
3. View Market Data    - Real-time stock information
4. View All Stocks     - Complete stock listing
5. View All Users      - User directory
6. View Transaction History - System-wide activity
7. Simulate Market Movement - Dynamic pricing
8. Exit               - Close application
```

### **2. User Dashboard Features**
```
1. View Portfolio     - Your holdings and performance
2. Buy Stock         - Purchase shares
3. Sell Stock        - Sell your shares
4. View Market Data  - Current market information
5. View Transaction History - Your trading activity
6. Deposit Funds     - Add money to account
7. Withdraw Funds    - Remove money from account
8. Logout           - Return to main menu
```

## 💰 **Trading Process**

### **Buying Stocks**
1. **Login** to your account
2. **Select "Buy Stock"** from dashboard
3. **Enter stock symbol** (e.g., AAPL)
4. **Specify number of shares** to purchase
5. **Confirm transaction** and see confirmation

### **Selling Stocks**
1. **Login** to your account
2. **Select "Sell Stock"** from dashboard
3. **Enter stock symbol** you own
4. **Specify number of shares** to sell
5. **Confirm transaction** and receive funds

## 📈 **Portfolio Tracking**

### **Real-time Metrics**
- **Cash Balance** - Available funds for trading
- **Portfolio Value** - Current value of all holdings
- **Total Net Worth** - Cash + Portfolio value
- **Average Purchase Price** - Cost basis for each stock
- **Individual Stock Performance** - Per-stock tracking

### **Portfolio Display**
```
Symbol    Company              Shares    Avg Price    Current Price    Total Value
AAPL      Apple Inc.          10        $150.00      $155.00         $1,550.00
GOOGL     Alphabet Inc.       5         $2,800.00    $2,850.00       $14,250.00
--------------------------------------------------------------------------------
TOTAL PORTFOLIO VALUE:                                    $15,800.00
Total Net Worth: $65,800.00
```

## 🔄 **Market Simulation**

### **Dynamic Pricing**
- **Automatic Updates** - Simulate real market movements
- **Price Fluctuations** - ±5% random price changes
- **Volume Tracking** - Monitor available shares
- **Market Cap Updates** - Real-time calculations

### **Market Data Display**
```
Symbol    Company              Current Price    Change        Change %    Volume
AAPL      Apple Inc.          $155.00         +$5.00        +3.33%      990,000
GOOGL     Alphabet Inc.       $2,850.00       +$50.00       +1.79%      495,000
MSFT      Microsoft Corp.     $315.00         +$15.00       +5.00%      795,000
```

## 🛡️ **Security & Validation**

### **Input Validation**
- **Stock Symbol Validation** - Ensures valid stock selection
- **Share Quantity Validation** - Positive numbers only
- **Fund Availability** - Prevents overspending
- **Stock Ownership** - Validates sell operations

### **Transaction Safety**
- **Duplicate Prevention** - No duplicate user registrations
- **Balance Protection** - Insufficient funds handling
- **Share Availability** - Stock supply validation
- **Audit Trail** - Complete transaction logging

## 📋 **Requirements**

- **Java 8 or higher**
- **No external dependencies**
- **Console/terminal access**
- **Basic understanding of stock trading concepts**

## 🚀 **Compilation & Execution**

### **Step 1: Compile**
```bash
javac StockTradingSystem.java
```

### **Step 2: Run**
```bash
java StockTradingSystem
```

## 🎮 **Sample Trading Session**

### **Quick Start Guide**
1. **Run the program**
2. **Login** with sample account: `john` / `pass123`
3. **View Portfolio** to see existing holdings
4. **Buy Stock** - Try purchasing some TSLA shares
5. **View Market Data** to see current prices
6. **Simulate Market Movement** to see price changes
7. **Check Portfolio** again to see updated values

### **Expected Results**
- **Portfolio Updates** - Real-time value calculations
- **Transaction Records** - Complete audit trail
- **Market Movements** - Dynamic pricing simulation
- **Performance Tracking** - Portfolio growth monitoring

## 🔮 **Future Enhancements**

- **Real-time Market Data** - API integration
- **Advanced Charts** - Price history visualization
- **Stop Loss Orders** - Automated trading rules
- **Dividend Tracking** - Income calculations
- **Tax Reporting** - Capital gains tracking
- **Mobile Interface** - Web/mobile applications
- **Multi-currency** - International trading
- **Options Trading** - Derivatives support

## 💡 **Trading Tips**

### **For Beginners**
- Start with small amounts
- Diversify your portfolio
- Monitor market movements
- Keep track of transaction history
- Understand average price calculations

### **Portfolio Management**
- Balance risk and reward
- Monitor portfolio performance
- Regular portfolio reviews
- Track net worth changes
- Maintain adequate cash reserves

## 🆘 **Troubleshooting**

### **Common Issues**
- **"Stock not found"** - Check symbol spelling
- **"Insufficient funds"** - Deposit more money
- **"Not enough shares"** - Check available quantity
- **"Invalid input"** - Follow prompt instructions

### **Getting Help**
- Use the demo script for guidance
- Check transaction history for errors
- Verify stock symbols and quantities
- Ensure sufficient account balance

---

**Note**: This is a simulation system for educational purposes. Real stock trading involves actual money and market risks. Always do thorough research before making real investment decisions.
