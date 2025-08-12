import java.util.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StockTradingSystem {
    private static final int MAX_USERS = 100;
    private static final int MAX_STOCKS = 50;
    private static final int MAX_TRANSACTIONS = 1000;
    
    private static User[] users = new User[MAX_USERS];
    private static Stock[] stocks = new Stock[MAX_STOCKS];
    private static Transaction[] transactions = new Transaction[MAX_TRANSACTIONS];
    
    private static int userCount = 0;
    private static int stockCount = 0;
    private static int transactionCount = 0;
    
    private static Scanner scanner = new Scanner(System.in);
    private static DecimalFormat df = new DecimalFormat("#.##");
    private static Random random = new Random();
    
    public static void main(String[] args) {
        System.out.println("=== STOCK TRADING SIMULATION SYSTEM ===");
        System.out.println("Welcome to the Stock Trading Simulator!");
        
        // Initialize with sample data
        initializeSampleData();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getValidChoice(1, 8);
            
            switch (choice) {
                case 1:
                    userLogin();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    viewMarketData();
                    break;
                case 4:
                    viewAllStocks();
                    break;
                case 5:
                    viewAllUsers();
                    break;
                case 6:
                    viewTransactionHistory();
                    break;
                case 7:
                    simulateMarketMovement();
                    break;
                case 8:
                    running = false;
                    System.out.println("Thank you for using Stock Trading Simulator!");
                    break;
            }
        }
        scanner.close();
    }
    
    private static void initializeSampleData() {
        // Initialize sample stocks
        stocks[0] = new Stock("AAPL", "Apple Inc.", 150.00, 1000000);
        stocks[1] = new Stock("GOOGL", "Alphabet Inc.", 2800.00, 500000);
        stocks[2] = new Stock("MSFT", "Microsoft Corporation", 300.00, 800000);
        stocks[3] = new Stock("AMZN", "Amazon.com Inc.", 3200.00, 600000);
        stocks[4] = new Stock("TSLA", "Tesla Inc.", 800.00, 400000);
        stocks[5] = new Stock("FB", "Meta Platforms Inc.", 350.00, 700000);
        stocks[6] = new Stock("NVDA", "NVIDIA Corporation", 600.00, 300000);
        stocks[7] = new Stock("NFLX", "Netflix Inc.", 500.00, 200000);
        stockCount = 8;
        
        // Initialize sample users
        users[0] = new User("admin", "admin123", "Admin User", 100000.00);
        users[1] = new User("john", "pass123", "John Doe", 50000.00);
        users[2] = new User("jane", "pass456", "Jane Smith", 75000.00);
        userCount = 3;
        
        // Add some initial holdings
        users[1].buyStock("AAPL", 10, 150.00);
        users[1].buyStock("GOOGL", 5, 2800.00);
        users[2].buyStock("MSFT", 15, 300.00);
        users[2].buyStock("TSLA", 8, 800.00);
    }
    
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("MAIN MENU");
        System.out.println("=".repeat(60));
        System.out.println("1. User Login");
        System.out.println("2. Register New User");
        System.out.println("3. View Market Data");
        System.out.println("4. View All Stocks");
        System.out.println("5. View All Users");
        System.out.println("6. View Transaction History");
        System.out.println("7. Simulate Market Movement");
        System.out.println("8. Exit");
        System.out.println("=".repeat(60));
        System.out.print("Enter your choice (1-8): ");
    }
    
    private static int getValidChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private static void userLogin() {
        System.out.println("\n--- USER LOGIN ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        
        User user = findUserByCredentials(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getName() + "!");
            userDashboard(user);
        } else {
            System.out.println("Invalid username or password!");
        }
    }
    
    private static void registerUser() {
        if (userCount >= MAX_USERS) {
            System.out.println("Maximum number of users reached!");
            return;
        }
        
        System.out.println("\n--- REGISTER NEW USER ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        
        if (findUserByUsername(username) != null) {
            System.out.println("Username already exists!");
            return;
        }
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine().trim();
        System.out.print("Enter initial balance: $");
        double initialBalance = getValidAmount();
        
        users[userCount] = new User(username, password, fullName, initialBalance);
        userCount++;
        
        System.out.println("User registered successfully!");
    }
    
    private static void userDashboard(User user) {
        boolean loggedIn = true;
        while (loggedIn) {
            displayUserMenu(user);
            int choice = getValidChoice(1, 8);
            
            switch (choice) {
                case 1:
                    viewPortfolio(user);
                    break;
                case 2:
                    buyStock(user);
                    break;
                case 3:
                    sellStock(user);
                    break;
                case 4:
                    viewMarketData();
                    break;
                case 5:
                    viewTransactionHistory(user);
                    break;
                case 6:
                    depositFunds(user);
                    break;
                case 7:
                    withdrawFunds(user);
                    break;
                case 8:
                    loggedIn = false;
                    System.out.println("Logged out successfully!");
                    break;
            }
        }
    }
    
    private static void displayUserMenu(User user) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("USER DASHBOARD - " + user.getName().toUpperCase());
        System.out.println("=".repeat(60));
        System.out.println("Balance: $" + df.format(user.getBalance()));
        System.out.println("Portfolio Value: $" + df.format(user.getPortfolioValue()));
        System.out.println("Total Net Worth: $" + df.format(user.getBalance() + user.getPortfolioValue()));
        System.out.println("=".repeat(60));
        System.out.println("1. View Portfolio");
        System.out.println("2. Buy Stock");
        System.out.println("3. Sell Stock");
        System.out.println("4. View Market Data");
        System.out.println("5. View Transaction History");
        System.out.println("6. Deposit Funds");
        System.out.println("7. Withdraw Funds");
        System.out.println("8. Logout");
        System.out.println("=".repeat(60));
        System.out.print("Enter your choice (1-8): ");
    }
    
    private static void viewPortfolio(User user) {
        System.out.println("\n--- PORTFOLIO ---");
        System.out.println("User: " + user.getName());
        System.out.println("Cash Balance: $" + df.format(user.getBalance()));
        System.out.println();
        
        if (user.getHoldings().isEmpty()) {
            System.out.println("No stocks in portfolio.");
            return;
        }
        
        System.out.printf("%-10s %-20s %-12s %-12s %-12s %-12s%n", 
                         "Symbol", "Company", "Shares", "Avg Price", "Current Price", "Total Value");
        System.out.println("-".repeat(80));
        
        double totalValue = 0;
        for (Map.Entry<String, Integer> entry : user.getHoldings().entrySet()) {
            String symbol = entry.getKey();
            int shares = entry.getValue();
            Stock stock = findStockBySymbol(symbol);
            if (stock != null) {
                double avgPrice = user.getAveragePrice(symbol);
                double currentPrice = stock.getCurrentPrice();
                double totalStockValue = shares * currentPrice;
                totalValue += totalStockValue;
                
                System.out.printf("%-10s %-20s %-12d %-12s %-12s %-12s%n",
                                 symbol, stock.getCompanyName(), shares,
                                 "$" + df.format(avgPrice), "$" + df.format(currentPrice),
                                 "$" + df.format(totalStockValue));
            }
        }
        
        System.out.println("-".repeat(80));
        System.out.printf("%-42s %-12s %-12s%n", "TOTAL PORTFOLIO VALUE:", "", "$" + df.format(totalValue));
        System.out.println("Total Net Worth: $" + df.format(user.getBalance() + totalValue));
    }
    
    private static void buyStock(User user) {
        System.out.println("\n--- BUY STOCK ---");
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().trim().toUpperCase();
        
        Stock stock = findStockBySymbol(symbol);
        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }
        
        System.out.println("Stock: " + stock.getCompanyName() + " (" + symbol + ")");
        System.out.println("Current Price: $" + df.format(stock.getCurrentPrice()));
        System.out.println("Available Shares: " + stock.getAvailableShares());
        System.out.println("Your Balance: $" + df.format(user.getBalance()));
        
        System.out.print("Enter number of shares to buy: ");
        int shares = getValidShares();
        
        if (shares <= 0) {
            System.out.println("Invalid number of shares!");
            return;
        }
        
        if (shares > stock.getAvailableShares()) {
            System.out.println("Not enough shares available!");
            return;
        }
        
        double totalCost = shares * stock.getCurrentPrice();
        if (totalCost > user.getBalance()) {
            System.out.println("Insufficient funds! Need: $" + df.format(totalCost));
            return;
        }
        
        // Execute the transaction
        user.buyStock(symbol, shares, stock.getCurrentPrice());
        stock.updateShares(-shares);
        
        // Record transaction
        recordTransaction(user, symbol, shares, stock.getCurrentPrice(), "BUY");
        
        System.out.println("Purchase successful! Bought " + shares + " shares of " + symbol + " for $" + df.format(totalCost));
    }
    
    private static void sellStock(User user) {
        System.out.println("\n--- SELL STOCK ---");
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().trim().toUpperCase();
        
        if (!user.getHoldings().containsKey(symbol)) {
            System.out.println("You don't own any shares of " + symbol + "!");
            return;
        }
        
        Stock stock = findStockBySymbol(symbol);
        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }
        
        int ownedShares = user.getHoldings().get(symbol);
        System.out.println("Stock: " + stock.getCompanyName() + " (" + symbol + ")");
        System.out.println("Current Price: $" + df.format(stock.getCurrentPrice()));
        System.out.println("Shares Owned: " + ownedShares);
        
        System.out.print("Enter number of shares to sell: ");
        int shares = getValidShares();
        
        if (shares <= 0 || shares > ownedShares) {
            System.out.println("Invalid number of shares!");
            return;
        }
        
        // Execute the transaction
        double totalValue = shares * stock.getCurrentPrice();
        user.sellStock(symbol, shares, stock.getCurrentPrice());
        stock.updateShares(shares);
        
        // Record transaction
        recordTransaction(user, symbol, shares, stock.getCurrentPrice(), "SELL");
        
        System.out.println("Sale successful! Sold " + shares + " shares of " + symbol + " for $" + df.format(totalValue));
    }
    
    private static void viewMarketData() {
        System.out.println("\n--- MARKET DATA ---");
        System.out.printf("%-10s %-20s %-15s %-15s %-15s %-15s%n",
                         "Symbol", "Company", "Current Price", "Change", "Change %", "Volume");
        System.out.println("-".repeat(90));
        
        for (int i = 0; i < stockCount; i++) {
            Stock stock = stocks[i];
            double change = stock.getPriceChange();
            double changePercent = (change / (stock.getCurrentPrice() - change)) * 100;
            
            String changeStr = change >= 0 ? "+" + df.format(change) : df.format(change);
            String changePercentStr = change >= 0 ? "+" + df.format(changePercent) + "%" : df.format(changePercent) + "%";
            
            System.out.printf("%-10s %-20s %-15s %-15s %-15s %-15s%n",
                             stock.getSymbol(), stock.getCompanyName(),
                             "$" + df.format(stock.getCurrentPrice()), changeStr,
                             changePercentStr, stock.getAvailableShares());
        }
    }
    
    private static void viewAllStocks() {
        System.out.println("\n--- ALL STOCKS ---");
        System.out.printf("%-10s %-20s %-15s %-15s %-15s%n",
                         "Symbol", "Company", "Current Price", "Available Shares", "Market Cap");
        System.out.println("-".repeat(75));
        
        for (int i = 0; i < stockCount; i++) {
            Stock stock = stocks[i];
            double marketCap = stock.getCurrentPrice() * stock.getAvailableShares();
            System.out.printf("%-10s %-20s %-15s %-15d %-15s%n",
                             stock.getSymbol(), stock.getCompanyName(),
                             "$" + df.format(stock.getCurrentPrice()), stock.getAvailableShares(),
                             "$" + df.format(marketCap / 1000000) + "M");
        }
    }
    
    private static void viewAllUsers() {
        System.out.println("\n--- ALL USERS ---");
        System.out.printf("%-15s %-20s %-15s %-15s %-15s%n",
                         "Username", "Name", "Balance", "Portfolio Value", "Net Worth");
        System.out.println("-".repeat(80));
        
        for (int i = 0; i < userCount; i++) {
            User user = users[i];
            double portfolioValue = user.getPortfolioValue();
            double netWorth = user.getBalance() + portfolioValue;
            
            System.out.printf("%-15s %-20s %-15s %-15s %-15s%n",
                             user.getUsername(), user.getName(),
                             "$" + df.format(user.getBalance()),
                             "$" + df.format(portfolioValue),
                             "$" + df.format(netWorth));
        }
    }
    
    private static void viewTransactionHistory() {
        System.out.println("\n--- TRANSACTION HISTORY ---");
        if (transactionCount == 0) {
            System.out.println("No transactions recorded.");
            return;
        }
        
        System.out.printf("%-20s %-15s %-10s %-10s %-12s %-10s%n",
                         "Timestamp", "User", "Symbol", "Shares", "Price", "Type");
        System.out.println("-".repeat(80));
        
        for (int i = 0; i < transactionCount; i++) {
            Transaction transaction = transactions[i];
            System.out.printf("%-20s %-15s %-10s %-10d %-12s %-10s%n",
                             transaction.getTimestamp(), transaction.getUsername(),
                             transaction.getSymbol(), transaction.getShares(),
                             "$" + df.format(transaction.getPrice()),
                             transaction.getType());
        }
    }
    
    private static void viewTransactionHistory(User user) {
        System.out.println("\n--- YOUR TRANSACTION HISTORY ---");
        System.out.printf("%-20s %-10s %-10s %-12s %-10s%n",
                         "Timestamp", "Symbol", "Shares", "Price", "Type");
        System.out.println("-".repeat(70));
        
        boolean found = false;
        for (int i = 0; i < transactionCount; i++) {
            Transaction transaction = transactions[i];
            if (transaction.getUsername().equals(user.getUsername())) {
                System.out.printf("%-20s %-10s %-10d %-12s %-10s%n",
                                 transaction.getTimestamp(), transaction.getSymbol(),
                                 transaction.getShares(), "$" + df.format(transaction.getPrice()),
                                 transaction.getType());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No transactions found.");
        }
    }
    
    private static void depositFunds(User user) {
        System.out.println("\n--- DEPOSIT FUNDS ---");
        System.out.println("Current Balance: $" + df.format(user.getBalance()));
        System.out.print("Enter amount to deposit: $");
        double amount = getValidAmount();
        
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        
        user.deposit(amount);
        System.out.println("Deposit successful! New balance: $" + df.format(user.getBalance()));
    }
    
    private static void withdrawFunds(User user) {
        System.out.println("\n--- WITHDRAW FUNDS ---");
        System.out.println("Current Balance: $" + df.format(user.getBalance()));
        System.out.print("Enter amount to withdraw: $");
        double amount = getValidAmount();
        
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        
        if (amount > user.getBalance()) {
            System.out.println("Insufficient funds!");
            return;
        }
        
        user.withdraw(amount);
        System.out.println("Withdrawal successful! New balance: $" + df.format(user.getBalance()));
    }
    
    private static void simulateMarketMovement() {
        System.out.println("\n--- SIMULATING MARKET MOVEMENT ---");
        
        for (int i = 0; i < stockCount; i++) {
            Stock stock = stocks[i];
            double oldPrice = stock.getCurrentPrice();
            
            // Simulate price movement (-5% to +5%)
            double changePercent = (random.nextDouble() - 0.5) * 0.1;
            double newPrice = oldPrice * (1 + changePercent);
            
            stock.updatePrice(newPrice);
            
            System.out.printf("%s: $%.2f -> $%.2f (%+.2f%%)\n",
                             stock.getSymbol(), oldPrice, newPrice, changePercent * 100);
        }
        
        System.out.println("Market simulation completed!");
    }
    
    private static void recordTransaction(User user, String symbol, int shares, double price, String type) {
        if (transactionCount >= MAX_TRANSACTIONS) {
            // Remove oldest transaction if limit reached
            for (int i = 0; i < transactionCount - 1; i++) {
                transactions[i] = transactions[i + 1];
            }
            transactionCount--;
        }
        
        transactions[transactionCount] = new Transaction(user.getUsername(), symbol, shares, price, type);
        transactionCount++;
    }
    
    private static User findUserByCredentials(String username, String password) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username) && users[i].getPassword().equals(password)) {
                return users[i];
            }
        }
        return null;
    }
    
    private static User findUserByUsername(String username) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username)) {
                return users[i];
            }
        }
        return null;
    }
    
    private static Stock findStockBySymbol(String symbol) {
        for (int i = 0; i < stockCount; i++) {
            if (stocks[i].getSymbol().equals(symbol)) {
                return stocks[i];
            }
        }
        return null;
    }
    
    private static int getValidShares() {
        while (true) {
            try {
                int shares = Integer.parseInt(scanner.nextLine().trim());
                if (shares > 0) {
                    return shares;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private static double getValidAmount() {
        while (true) {
            try {
                double amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount > 0) {
                    return amount;
                } else {
                    System.out.println("Please enter a positive amount.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }
}

class User {
    private String username;
    private String password;
    private String name;
    private double balance;
    private Map<String, Integer> holdings; // symbol -> shares
    private Map<String, Double> averagePrices; // symbol -> average price
    
    public User(String username, String password, String name, double initialBalance) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.balance = initialBalance;
        this.holdings = new HashMap<>();
        this.averagePrices = new HashMap<>();
    }
    
    public void buyStock(String symbol, int shares, double price) {
        double totalCost = shares * price;
        balance -= totalCost;
        
        if (holdings.containsKey(symbol)) {
            int currentShares = holdings.get(symbol);
            double currentAvgPrice = averagePrices.get(symbol);
            double newAvgPrice = ((currentShares * currentAvgPrice) + totalCost) / (currentShares + shares);
            
            holdings.put(symbol, currentShares + shares);
            averagePrices.put(symbol, newAvgPrice);
        } else {
            holdings.put(symbol, shares);
            averagePrices.put(symbol, price);
        }
    }
    
    public void sellStock(String symbol, int shares, double price) {
        if (!holdings.containsKey(symbol) || holdings.get(symbol) < shares) {
            return; // Should not happen with proper validation
        }
        
        double totalValue = shares * price;
        balance += totalValue;
        
        int currentShares = holdings.get(symbol);
        if (currentShares == shares) {
            holdings.remove(symbol);
            averagePrices.remove(symbol);
        } else {
            holdings.put(symbol, currentShares - shares);
        }
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }
    
    public double getPortfolioValue() {
        double totalValue = 0;
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            String symbol = entry.getKey();
            int shares = entry.getValue();
            // Note: In a real system, you'd get current price from market data
            // For simplicity, we'll use average price here
            double avgPrice = averagePrices.get(symbol);
            totalValue += shares * avgPrice;
        }
        return totalValue;
    }
    
    public double getAveragePrice(String symbol) {
        return averagePrices.getOrDefault(symbol, 0.0);
    }
    
    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public Map<String, Integer> getHoldings() { return holdings; }
}

class Stock {
    private String symbol;
    private String companyName;
    private double currentPrice;
    private double previousPrice;
    private int availableShares;
    
    public Stock(String symbol, String companyName, double initialPrice, int shares) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = initialPrice;
        this.previousPrice = initialPrice;
        this.availableShares = shares;
    }
    
    public void updatePrice(double newPrice) {
        this.previousPrice = this.currentPrice;
        this.currentPrice = newPrice;
    }
    
    public void updateShares(int change) {
        this.availableShares += change;
    }
    
    public double getPriceChange() {
        return currentPrice - previousPrice;
    }
    
    // Getters
    public String getSymbol() { return symbol; }
    public String getCompanyName() { return companyName; }
    public double getCurrentPrice() { return currentPrice; }
    public double getPreviousPrice() { return previousPrice; }
    public int getAvailableShares() { return availableShares; }
}

class Transaction {
    private String username;
    private String symbol;
    private int shares;
    private double price;
    private String type; // "BUY" or "SELL"
    private String timestamp;
    
    public Transaction(String username, String symbol, int shares, double price, String type) {
        this.username = username;
        this.symbol = symbol;
        this.shares = shares;
        this.price = price;
        this.type = type;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    // Getters
    public String getUsername() { return username; }
    public String getSymbol() { return symbol; }
    public int getShares() { return shares; }
    public double getPrice() { return price; }
    public String getType() { return type; }
    public String getTimestamp() { return timestamp; }
}
