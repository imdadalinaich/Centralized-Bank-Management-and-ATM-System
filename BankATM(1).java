import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankATM extends JFrame {

    static final int ACC_DIGITS = 10;
    static final int PIN_RETRY = 3;
    static final String ADMIN_PIN = "9999";

    static final String ACC_FILE = "accounts.dat";
    static final String TRANS_FILE = "transactions.txt";
    static final String RECEIPT_FILE = "receipt.txt";

    static final Color NAVY =
            new Color(15, 38, 71);

    static final Color BLUE =
            new Color(35, 104, 180);

    static final Color LIGHT =
            new Color(244, 247, 251);

    static final Color GREEN =
            new Color(32, 135, 83);

    static final Color RED =
            new Color(190, 55, 55);

    static final Color YELLOW =
            new Color(180, 130, 20);

    static class Account implements Serializable {

        private static final long serialVersionUID = 1L;

        String name;
        String account;
        String pin;
        double balance;
        boolean blocked;

        Account(
                String name,
                String account,
                String pin) {

            this.name = name;
            this.account = account;
            this.pin = pin;

            this.balance = 50000.0;

            this.blocked = false;
        }
    }

    CardLayout cardLayout =
            new CardLayout();

    JPanel mainPanel =
            new JPanel(cardLayout);

    Account loggedInUser = null;

    public BankATM() {

        setTitle(
                "National Bank of Pakistan - Bank Management System & ATM");

        setSize(900, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        mainPanel.add(
                welcomeScreen(),
                "HOME");

        mainPanel.add(
                createAccountScreen(),
                "CREATE");

        mainPanel.add(
                loginScreen(),
                "LOGIN");

        mainPanel.add(
                adminLoginScreen(),
                "ADMIN_LOGIN");

        mainPanel.add(
                adminScreen(),
                "ADMIN");

        add(mainPanel);

        cardLayout.show(
                mainPanel,
                "HOME");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            BankATM bank =
                    new BankATM();

            bank.setVisible(true);
        });
    }

    JPanel welcomeScreen() {

        JPanel panel =
                createBasePanel();

        JLabel title =
                createTitle(
                        "WELCOME TO NATIONAL BANK OF PAKISTAN",
                        28);

        panel.add(
                title,
                BorderLayout.NORTH);

        JPanel center =
                new JPanel();

        center.setOpaque(false);

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS));

        JLabel subtitle =
                createTitle(
                        "Bank Management System & ATM Machine",
                        20);

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        center.add(
                Box.createVerticalStrut(25));

        center.add(subtitle);

        center.add(
                Box.createVerticalStrut(30));

        JButton createAccount =
                createButton(
                        "1. Create New Account");

        JButton login =
                createButton(
                        "2. Login to ATM");

        JButton admin =
                createButton(
                        "3. Admin Panel");

        JButton exit =
                createButton(
                        "4. Exit");

        createAccount.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        login.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        admin.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        exit.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        center.add(createAccount);
        center.add(
                Box.createVerticalStrut(12));

        center.add(login);
        center.add(
                Box.createVerticalStrut(12));

        center.add(admin);
        center.add(
                Box.createVerticalStrut(12));

        center.add(exit);

        createAccount.addActionListener(
                e -> cardLayout.show(
                        mainPanel,
                        "CREATE"));

        login.addActionListener(
                e -> cardLayout.show(
                        mainPanel,
                        "LOGIN"));

        admin.addActionListener(
                e -> cardLayout.show(
                        mainPanel,
                        "ADMIN_LOGIN"));

        exit.addActionListener(
                e -> {

                    showMessage(
                            "Thank you for using ATM!");

                    System.exit(0);
                });

        panel.add(
                center,
                BorderLayout.CENTER);

        return panel;
    }

    void mainMenu() {

        cardLayout.show(
                mainPanel,
                "HOME");
    }

    JPanel createAccountScreen() {

        JPanel panel =
                createBasePanel();

        panel.add(
                createTitle(
                        "CREATE NEW ACCOUNT",
                        27),
                BorderLayout.NORTH);

        JPanel form =
                new JPanel(
                        new GridBagLayout());

        form.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        12,
                        12,
                        12,
                        12);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JTextField nameField =
                new JTextField(22);

        JTextField accountField =
                new JTextField(22);

        JPasswordField pinField =
                new JPasswordField(22);

        addFormRow(
                form,
                gbc,
                0,
                "Enter Full Name:",
                nameField);

        addFormRow(
                form,
                gbc,
                1,
                "10-Digit Account Number:",
                accountField);

        addFormRow(
                form,
                gbc,
                2,
                "Set 4-Digit PIN:",
                pinField);

        JLabel startingBalance =
                new JLabel(
                        "Starting Balance: Rs. 50,000.00");

        startingBalance.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15));

        JPanel buttons =
                new JPanel();

        buttons.setOpaque(false);

        JButton create =
                createButton(
                        "CREATE ACCOUNT");

        JButton back =
                createButton(
                        "BACK");

        buttons.add(create);
        buttons.add(back);

        JPanel bottom =
                new JPanel(
                        new BorderLayout());

        bottom.setOpaque(false);

        bottom.add(
                startingBalance,
                BorderLayout.NORTH);

        bottom.add(
                buttons,
                BorderLayout.SOUTH);

        create.addActionListener(e -> {

            String name =
                    nameField
                            .getText()
                            .trim();

            String accountNumber =
                    accountField
                            .getText()
                            .trim();

            String pin =
                    new String(
                            pinField
                                    .getPassword())
                            .trim();

            if (name.isEmpty()) {

                showError(
                        "Please enter your full name.");

                return;
            }

            if (!accountNumber.matches(
                    "\\d{10}")) {

                showError(
                        "Account number must be exactly "
                                + ACC_DIGITS
                                + " digits!");

                return;
            }

            if (accountExists(
                    accountNumber)) {

                showError(
                        "Account number already exists!");

                return;
            }

            if (!pin.matches(
                    "\\d{4}")) {

                showError(
                        "PIN must be exactly 4 digits!");

                return;
            }

            Account account =
                    new Account(
                            name,
                            accountNumber,
                            pin);

            if (saveNewAccount(
                    account)) {

                showMessage(
                        "Account Created Successfully!\n\n"
                                + "Your Account Number is: "
                                + accountNumber
                                + "\n\n"
                                + "Starting Balance: Rs. 50,000.00");

                nameField.setText("");
                accountField.setText("");
                pinField.setText("");

                cardLayout.show(
                        mainPanel,
                        "HOME");

            } else {

                showError(
                        "Error creating/opening account file!");
            }
        });

        back.addActionListener(
                e -> cardLayout.show(
                        mainPanel,
                        "HOME"));

        panel.add(
                form,
                BorderLayout.CENTER);

        panel.add(
                bottom,
                BorderLayout.SOUTH);

        return panel;
    }

    boolean saveNewAccount(
            Account account) {

        List<Account> accounts =
                loadAllAccounts();

        accounts.add(account);

        return saveAllAccounts(
                accounts);
    }

    JPanel loginScreen() {

        JPanel panel =
                createBasePanel();

        panel.add(
                createTitle(
                        "ATM LOGIN",
                        27),
                BorderLayout.NORTH);

        JPanel form =
                new JPanel(
                        new GridBagLayout());

        form.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        12,
                        12,
                        12,
                        12);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JTextField accountField =
                new JTextField(22);

        JPasswordField pinField =
                new JPasswordField(22);

        addFormRow(
                form,
                gbc,
                0,
                "Account Number:",
                accountField);

        addFormRow(
                form,
                gbc,
                1,
                "PIN:",
                pinField);

        JButton login =
                createButton(
                        "LOGIN");

        JButton back =
                createButton(
                        "BACK");

        JPanel buttons =
                new JPanel();

        buttons.setOpaque(false);

        buttons.add(login);
        buttons.add(back);

        login.addActionListener(e -> {

            String accountNumber =
                    accountField
                            .getText()
                            .trim();

            String pin =
                    new String(
                            pinField
                                    .getPassword())
                            .trim();

            if (!accountNumber.matches(
                    "\\d{10}")) {

                showError(
                        "Account number must be exactly "
                                + ACC_DIGITS
                                + " digits!");

                return;
            }

            Account user =
                    loadAccountByNumber(
                            accountNumber);

            if (user == null) {

                showError(
                        "Account not found!");

                return;
            }

            if (user.blocked) {

                showError(
                        "Account is BLOCKED!");

                return;
            }

            if (user.pin.equals(pin)) {

                loggedInUser =
                        user;

                showMessage(
                        "Login Successful!");

                accountField.setText("");
                pinField.setText("");

                atmMenu();

                return;
            }

            int attempts = 1;

            while (attempts < PIN_RETRY) {

                showError(
                        "Wrong PIN!\n"
                                + "Attempts left: "
                                + (PIN_RETRY - attempts));

                String retryPin =
                        JOptionPane.showInputDialog(
                                this,
                                "Enter PIN again:");

                if (retryPin == null) {

                    return;
                }

                if (user.pin.equals(
                        retryPin.trim())) {

                    loggedInUser =
                            user;

                    showMessage(
                            "Login Successful!");

                    accountField.setText("");
                    pinField.setText("");

                    atmMenu();

                    return;
                }

                attempts++;
            }

            user.blocked = true;

            updateAccount(user);

            showError(
                    "Account Blocked due to "
                            + PIN_RETRY
                            + " wrong attempts!");
        });

        back.addActionListener(
                e -> cardLayout.show(
                        mainPanel,
                        "HOME"));

        panel.add(
                form,
                BorderLayout.CENTER);

        panel.add(
                buttons,
                BorderLayout.SOUTH);

        return panel;
    }

    boolean login() {

        cardLayout.show(
                mainPanel,
                "LOGIN");

        return false;
    }

    void atmMenu() {

        JPanel panel =
                createBasePanel();

        panel.add(
                createTitle(
                        "ATM MENU",
                        27),
                BorderLayout.NORTH);

        JLabel welcome =
                new JLabel(
                        "Welcome, "
                                + loggedInUser.name,
                        SwingConstants.CENTER);

        welcome.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18));

        JPanel center =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                15,
                                15));

        center.setOpaque(false);

        JButton balance =
                createButton(
                        "1. Balance Inquiry");

        JButton deposit =
                createButton(
                        "2. Deposit Money");

        JButton withdraw =
                createButton(
                        "3. Withdraw Money");

        JButton transfer =
                createButton(
                        "4. Transfer Funds");

        JButton receipt =
                createButton(
                        "5. Open Receipt");

        JButton logout =
                createButton(
                        "6. Logout");

        center.add(balance);
        center.add(deposit);
        center.add(withdraw);
        center.add(transfer);
        center.add(receipt);
        center.add(logout);

        balance.addActionListener(
                e -> checkBalance(
                        loggedInUser));

        deposit.addActionListener(
                e -> depositMoney(
                        loggedInUser));

        withdraw.addActionListener(
                e -> withdrawMoney(
                        loggedInUser));

        transfer.addActionListener(
                e -> fundTransfer(
                        loggedInUser));

        receipt.addActionListener(
                e -> openReceipt());

        logout.addActionListener(e -> {

            loggedInUser = null;

            showMessage(
                    "Logged out successfully!");

            cardLayout.show(
                    mainPanel,
                    "HOME");
        });

        JPanel top =
                new JPanel(
                        new BorderLayout());

        top.setOpaque(false);

        top.add(
                welcome,
                BorderLayout.CENTER);

        panel.add(
                top,
                BorderLayout.CENTER);

        panel.add(
                center,
                BorderLayout.SOUTH);

        mainPanel.add(
                panel,
                "ATM");

        cardLayout.show(
                mainPanel,
                "ATM");
    }

    void checkBalance(
            Account user) {

        showMessage(
                String.format(
                        "Current Balance: Rs. %.2f",
                        user.balance));
    }

    void depositMoney(
            Account user) {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter amount to deposit:");

        if (input == null) {
            return;
        }

        try {

            double amount =
                    Double.parseDouble(
                            input);

            if (amount <= 0) {

                showError(
                        "Invalid amount!");

                return;
            }

            user.balance += amount;

            updateAccount(user);

            saveTransaction(
                    user,
                    "Deposit",
                    amount);

            generateReceipt(
                    user,
                    "Deposit",
                    amount);

            showMessage(
                    String.format(
                            "Deposit Successful!\n\n"
                                    + "Amount: Rs. %.2f\n"
                                    + "New Balance: Rs. %.2f",
                            amount,
                            user.balance));

        } catch (
                NumberFormatException e) {

            showError(
                    "Invalid input!");
        }
    }

    void withdrawMoney(
            Account user) {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter amount to withdraw:");

        if (input == null) {
            return;
        }

        try {

            double amount =
                    Double.parseDouble(
                            input);

            if (amount <= 0) {

                showError(
                        "Invalid amount!");

                return;
            }

            if (amount >
                    user.balance) {

                showError(
                        "Insufficient balance!");

                return;
            }

            user.balance -= amount;

            updateAccount(user);

            saveTransaction(
                    user,
                    "Withdraw",
                    amount);

            generateReceipt(
                    user,
                    "Withdraw",
                    amount);

            showMessage(
                    String.format(
                            "Please collect your cash!\n\n"
                                    + "Amount: Rs. %.2f\n"
                                    + "Remaining Balance: Rs. %.2f",
                            amount,
                            user.balance));

        } catch (
                NumberFormatException e) {

            showError(
                    "Invalid input!");
        }
    }

    void fundTransfer(
            Account user) {

        String targetAccount =
                JOptionPane.showInputDialog(
                        this,
                        "Enter target "
                                + ACC_DIGITS
                                + "-digit Account Number:");

        if (targetAccount == null) {
            return;
        }

        targetAccount =
                targetAccount.trim();

        if (!targetAccount.matches(
                "\\d{10}")) {

            showError(
                    "Invalid account number length!");

            return;
        }

        if (targetAccount.equals(
                user.account)) {

            showError(
                    "Cannot transfer to the same account!");

            return;
        }

        Account target =
                loadAccountByNumber(
                        targetAccount);

        if (target == null) {

            showError(
                    "Target account not found!");

            return;
        }

        if (target.blocked) {

            showError(
                    "Target account is blocked!");

            return;
        }

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter amount to transfer:");

        if (input == null) {
            return;
        }

        try {

            double amount =
                    Double.parseDouble(
                            input);

            if (amount <= 0) {

                showError(
                        "Invalid amount!");

                return;
            }

            if (amount >
                    user.balance) {

                showError(
                        "Insufficient balance!");

                return;
            }

            user.balance -= amount;

            target.balance += amount;

            updateAccount(user);

            updateAccount(target);

            saveTransaction(
                    user,
                    "Transfer Out",
                    amount);

            saveTransaction(
                    target,
                    "Transfer In",
                    amount);

            generateReceipt(
                    user,
                    "Transfer Out",
                    amount);

            showMessage(
                    String.format(
                            "Transfer Successful!\n\n"
                                    + "Sent: Rs. %.2f\n"
                                    + "To Account: %s",
                            amount,
                            target.account));

        } catch (
                NumberFormatException e) {

            showError(
                    "Invalid input!");
        }
    }

    @SuppressWarnings("unchecked")
    List<Account> loadAllAccounts() {

        File file =
                new File(ACC_FILE);

        if (!file.exists()) {

            return new ArrayList<>();
        }

        try {

            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream(
                                    file));

            List<Account> accounts =
                    (List<Account>)
                            input.readObject();

            input.close();

            return accounts;

        } catch (
                Exception e) {

            return new ArrayList<>();
        }
    }

    boolean saveAllAccounts(
            List<Account> accounts) {

        try {

            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    ACC_FILE));

            output.writeObject(
                    accounts);

            output.close();

            return true;

        } catch (
                IOException e) {

            showError(
                    "Failed to save account file.");

            return false;
        }
    }

    boolean accountExists(
            String accountNumber) {

        List<Account> accounts =
                loadAllAccounts();

        for (
                Account account :
                accounts) {

            if (account.account.equals(
                    accountNumber)) {

                return true;
            }
        }

        return false;
    }

    Account loadAccountByNumber(
            String accountNumber) {

        List<Account> accounts =
                loadAllAccounts();

        for (
                Account account :
                accounts) {

            if (account.account.equals(
                    accountNumber)) {

                return account;
            }
        }

        return null;
    }

    void updateAccount(
            Account user) {

        List<Account> accounts =
                loadAllAccounts();

        boolean found = false;

        for (
                int i = 0;
                i < accounts.size();
                i++) {

            if (accounts
                    .get(i)
                    .account
                    .equals(user.account)) {

                accounts.set(
                        i,
                        user);

                found = true;

                break;
            }
        }

        if (found) {

            saveAllAccounts(
                    accounts);

        } else {

            showError(
                    "Account not found during update.");
        }
    }

    void saveTransaction(
            Account user,
            String type,
            double amount) {

        String date =
                LocalDateTime
                        .now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyy-MM-dd HH:mm:ss"));

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(
                                    TRANS_FILE,
                                    true));

            writer.write(
                    user.account
                            + " | "
                            + type
                            + " | "
                            + String.format(
                                    "%.2f",
                                    amount)
                            + " | "
                            + date);

            writer.newLine();

            writer.close();

        } catch (
                IOException e) {

            showError(
                    "Failed to open transaction file.");
        }
    }

    void generateReceipt(
            Account user,
            String type,
            double amount) {

        String date =
                LocalDateTime
                        .now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyy-MM-dd HH:mm:ss"));

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(
                                    RECEIPT_FILE));

            writer.println(
                    "=========== ATM RECEIPT ===========");

            writer.println(
                    "Account Holder   : "
                            + user.name);

            writer.println(
                    "Account No       : "
                            + user.account);

            writer.println(
                    "Transaction Type : "
                            + type);

            writer.printf(
                    "Amount           : %.2f%n",
                    amount);

            writer.printf(
                    "Balance          : %.2f%n",
                    user.balance);

            writer.println(
                    "Date/Time        : "
                            + date);

            writer.println(
                    "===================================");

            writer.close();

        } catch (
                IOException e) {

            showError(
                    "Failed to create receipt.");
        }
    }

    JPanel adminLoginScreen() {

        JPanel panel =
                createBasePanel();

        panel.add(
                createTitle(
                        "ADMIN LOGIN",
                        27),
                BorderLayout.NORTH);

        JPanel form =
                new JPanel(
                        new GridBagLayout());

        form.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        12,
                        12,
                        12,
                        12);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JPasswordField pinField =
                new JPasswordField(20);

        addFormRow(
                form,
                gbc,
                0,
                "Enter Admin PIN:",
                pinField);

        JButton login =
                createButton(
                        "LOGIN");

        JButton back =
                createButton(
                        "BACK");

        JPanel buttons =
                new JPanel();

        buttons.setOpaque(false);

        buttons.add(login);
        buttons.add(back);

        login.addActionListener(e -> {

            String pin =
                    new String(
                            pinField
                                    .getPassword());

            if (pin.equals(
                    ADMIN_PIN)) {

                pinField.setText("");

                adminMenu();

            } else {

                showError(
                        "Access Denied!");
            }
        });

        back.addActionListener(
                e -> cardLayout.show(
                        mainPanel,
                        "HOME"));

        panel.add(
                form,
                BorderLayout.CENTER);

        panel.add(
                buttons,
                BorderLayout.SOUTH);

        return panel;
    }

    void adminMenu() {

        cardLayout.show(
                mainPanel,
                "ADMIN");
    }

    JPanel adminScreen() {

        JPanel panel =
                createBasePanel();

        panel.add(
                createTitle(
                        "ADMIN PANEL",
                        27),
                BorderLayout.NORTH);

        JPanel grid =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                15,
                                15));

        grid.setOpaque(false);

        JButton unblock =
                createButton(
                        "1. Unblock Account");

        JButton delete =
                createButton(
                        "2. Delete Account");

        JButton list =
                createButton(
                        "3. List All Accounts");

        JButton reset =
                createButton(
                        "4. Reset All Accounts");

        JButton back =
                createButton(
                        "5. Back");

        grid.add(unblock);
        grid.add(delete);
        grid.add(list);
        grid.add(reset);
        grid.add(back);

        unblock.addActionListener(
                e -> unblockAccount());

        delete.addActionListener(
                e -> deleteAccount());

        list.addActionListener(
                e -> listAllAccounts());

        reset.addActionListener(
                e -> clearAllAccounts());

        back.addActionListener(
                e -> cardLayout.show(
                        mainPanel,
                        "HOME"));

        panel.add(
                grid,
                BorderLayout.CENTER);

        return panel;
    }

    void unblockAccount() {

        String accountNumber =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Account Number to unblock:");

        if (accountNumber == null) {
            return;
        }

        accountNumber =
                accountNumber.trim();

        Account account =
                loadAccountByNumber(
                        accountNumber);

        if (account == null) {

            showError(
                    "Account not found!");

            return;
        }

        account.blocked = false;

        updateAccount(account);

        showMessage(
                "Account Unblocked Successfully!");
    }

    void deleteAccount() {

        String accountNumber =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Account Number to delete:");

        if (accountNumber == null) {
            return;
        }

        accountNumber =
                accountNumber.trim();

        List<Account> accounts =
                loadAllAccounts();

        boolean deleted = false;

        for (
                int i = 0;
                i < accounts.size();
                i++) {

            if (accounts
                    .get(i)
                    .account
                    .equals(accountNumber)) {

                accounts.remove(i);

                deleted = true;

                break;
            }
        }

        if (!deleted) {

            showError(
                    "Account not found. Nothing deleted.");

            return;
        }

        saveAllAccounts(
                accounts);

        showMessage(
                "Account deleted successfully.");
    }

    void listAllAccounts() {

        List<Account> accounts =
                loadAllAccounts();

        if (accounts.isEmpty()) {

            showError(
                    "No accounts to show.");

            return;
        }

        StringBuilder output =
                new StringBuilder();

        output.append(
                "--- ALL ACCOUNTS ---\n\n");

        for (
                Account account :
                accounts) {

            output.append(
                    "Name: ")
                    .append(
                            account.name)
                    .append("\n");

            output.append(
                    "Account: ")
                    .append(
                            account.account)
                    .append("\n");

            output.append(
                    String.format(
                            "Balance: Rs %.2f%n",
                            account.balance));

            output.append(
                    "Status: ")
                    .append(
                            account.blocked
                                    ? "BLOCKED"
                                    : "ACTIVE")
                    .append("\n\n");
        }

        JTextArea area =
                new JTextArea(
                        output.toString(),
                        18,
                        50);

        area.setEditable(false);

        area.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14));

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(area),
                "All Accounts",
                JOptionPane.INFORMATION_MESSAGE);
    }

    void clearAllAccounts() {

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "This will delete ALL accounts "
                                + "and transactions.\n\n"
                                + "Are you sure?",
                        "Confirm Reset",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

        if (confirm !=
                JOptionPane.YES_OPTION) {

            showMessage(
                    "Cancelled.");

            return;
        }

        File accountFile =
                new File(ACC_FILE);

        File transactionFile =
                new File(TRANS_FILE);

        if (accountFile.exists()) {
            accountFile.delete();
        }

        if (transactionFile.exists()) {
            transactionFile.delete();
        }

        showMessage(
                "All records cleared.");
    }

    void openReceipt() {

        File receipt =
                new File(
                        RECEIPT_FILE);

        if (!receipt.exists()) {

            showError(
                    "No receipt generated yet.");

            return;
        }

        try {

            Desktop
                    .getDesktop()
                    .open(receipt);

        } catch (
                Exception e) {

            showError(
                    "Could not open receipt.txt");
        }
    }

    void pauseConsole() {

        showMessage(
                "Press OK to continue...");
    }

    JPanel createBasePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15));

        panel.setBackground(
                LIGHT);

        panel.setBorder(
                new EmptyBorder(
                        30,
                        45,
                        30,
                        45));

        return panel;
    }

    JButton createButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15));

        button.setForeground(
                Color.WHITE);

        button.setBackground(
                BLUE);

        button.setFocusPainted(
                false);

        button.setBorderPainted(
                false);

        button.setPreferredSize(
                new Dimension(
                        260,
                        48));

        return button;
    }

    JLabel createTitle(
            String text,
            int size) {

        JLabel label =
                new JLabel(
                        text,
                        SwingConstants.CENTER);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        size));

        label.setForeground(
                NAVY);

        return label;
    }

    void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label,
            JComponent field) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;

        panel.add(
                new JLabel(label),
                gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(
                field,
                gbc);
    }

    void showMessage(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "National Bank",
                JOptionPane.INFORMATION_MESSAGE);
    }

    void showError(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "National Bank - Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
