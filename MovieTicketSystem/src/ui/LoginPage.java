// File: src/ui/LoginPage.java
package ui;

import model.Member;
import service.MemberService;
import service.MovieService;
import service.ShowtimeService;
import service.TicketService;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private MemberService memberService;
    private MovieService movieService;
    private ShowtimeService showtimeService;
    private TicketService ticketService;

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPage(MemberService memberService, MovieService movieService,
                     ShowtimeService showtimeService, TicketService ticketService) {
        this.memberService = memberService;
        this.movieService = movieService;
        this.showtimeService = showtimeService;
        this.ticketService = ticketService;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("17CINEMA 登入");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel background = new JLabel(new ImageIcon("images/login_welcome.png"));
        background.setLayout(new BorderLayout());
        add(background);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setOpaque(false);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(100, 180, 100, 180));

        emailField = new JTextField();
        passwordField = new JPasswordField();

        loginPanel.add(new JLabel("Email："));
        loginPanel.add(emailField);
        loginPanel.add(new JLabel("密碼："));
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("登入");
        JButton registerButton = new JButton("註冊");
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        background.add(loginPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> attemptLogin());
        registerButton.addActionListener(e -> {
            dispose();
            new RegisterPage(memberService, movieService, showtimeService, ticketService).setVisible(true);
        });
    }

    private void attemptLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "請輸入帳號與密碼", "錯誤", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Member loginUser = memberService.login(email, password);

        if (loginUser == null) {
            JOptionPane.showMessageDialog(this, "登入失敗，請檢查帳號或密碼", "錯誤", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (loginUser.getRole().equals("admin")) {
            dispose();
            new AdminPanel(movieService, showtimeService).setVisible(true);
        } else {
            dispose();
            new MovieSelectionPage(movieService, showtimeService, ticketService).setVisible(true);
        }
    }
}
