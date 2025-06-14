// File: src/ui/RegisterPage.java
package ui;

import service.MemberService;
import service.MovieService;
import service.ShowtimeService;
import service.TicketService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RegisterPage extends JFrame {
    private MemberService memberService;
    private MovieService movieService;
    private ShowtimeService showtimeService;
    private TicketService ticketService;

    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;
    private JTextField birthField;

    public RegisterPage(MemberService memberService, MovieService movieService,
                        ShowtimeService showtimeService, TicketService ticketService) {
        this.memberService = memberService;
        this.movieService = movieService;
        this.showtimeService = showtimeService;
        this.ticketService = ticketService;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("17CINEMA 註冊會員");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel background = new JLabel(new ImageIcon("images/login_welcome.png"));
        background.setLayout(new BorderLayout());
        add(background);

        JPanel registerPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        registerPanel.setOpaque(false);
        registerPanel.setBorder(BorderFactory.createEmptyBorder(80, 180, 80, 180));

        emailField = new JTextField();
        passwordField = new JPasswordField();
        confirmField = new JPasswordField();
        birthField = new JTextField("yyyy-mm-dd");
        birthField.setForeground(Color.GRAY);
        birthField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (birthField.getText().equals("yyyy-mm-dd")) {
                    birthField.setText("");
                    birthField.setForeground(Color.BLACK);
                }
            }
        });

        registerPanel.add(new JLabel("Email："));
        registerPanel.add(emailField);
        registerPanel.add(new JLabel("密碼："));
        registerPanel.add(passwordField);
        registerPanel.add(new JLabel("再次輸入密碼："));
        registerPanel.add(confirmField);
        registerPanel.add(new JLabel("出生年月日："));
        registerPanel.add(birthField);

        JButton submitButton = new JButton("送出註冊");
        JButton backButton = new JButton("返回登入");
        registerPanel.add(submitButton);
        registerPanel.add(backButton);

        background.add(registerPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> register());
        backButton.addActionListener(e -> {
            dispose();
            new LoginPage(memberService, movieService, showtimeService, ticketService).setVisible(true);
        });
    }

    private void register() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String confirm = new String(confirmField.getPassword()).trim();
        String birthStr = birthField.getText().trim();

        if (email.isEmpty() || password.isEmpty() || confirm.isEmpty() || birthStr.isEmpty()
                || birthStr.equals("yyyy-mm-dd")) {
            JOptionPane.showMessageDialog(this, "請完整填寫所有欄位", "錯誤", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (email.equalsIgnoreCase("admin@admin.com")) {
            JOptionPane.showMessageDialog(this, "此帳號為系統管理員專用，請更換其他 Email", "錯誤", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "兩次密碼不一致", "錯誤", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalDate.parse(birthStr);

            boolean success = memberService.register(email, password, birthStr);
            if (success) {
                JOptionPane.showMessageDialog(this, "註冊成功！請登入", "成功", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LoginPage(memberService, movieService, showtimeService, ticketService).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "註冊失敗，該帳號已存在", "失敗", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "出生年月日格式錯誤（應為 yyyy-mm-dd）", "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }
}
