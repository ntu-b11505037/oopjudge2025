// File: src/ui/MovieSelectionPage.java
package ui;

import model.Movie;
import service.MovieService;
import service.ShowtimeService;
import service.TicketService;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class MovieSelectionPage extends JFrame {
    private MovieService movieService;
    private ShowtimeService showtimeService;
    private TicketService ticketService;

    private JTextField searchField;
    private JPanel moviePanel;

    public MovieSelectionPage(MovieService movieService, ShowtimeService showtimeService, TicketService ticketService) {
        this.movieService = movieService;
        this.showtimeService = showtimeService;
        this.ticketService = ticketService;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("🎬 17CINEMA - 電影選擇");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ========== Top Panel ==========
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel titleLabel = new JLabel("目前上映中");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        topPanel.add(titleLabel, BorderLayout.WEST);

        // 搜尋欄區域
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchField = new JTextField("輸入電影關鍵字...");
        searchField.setForeground(Color.GRAY);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(250, 30));

        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("輸入電影關鍵字...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("輸入電影關鍵字...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        JButton searchBtn = new JButton("搜尋");
        searchBtn.setPreferredSize(new Dimension(80, 30));
        searchBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchBtn.addActionListener(e -> searchMovies());

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ========== Movie Panel ==========
        moviePanel = new JPanel();
        moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));
        moviePanel.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(moviePanel);
        add(scrollPane, BorderLayout.CENTER);

        // ========== Bottom Panel ==========
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton backBtn = new JButton("返回登入");
        backBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backBtn.addActionListener(e -> {
            dispose();
            new LoginPage(movieService.getMemberService(), movieService, showtimeService, ticketService).setVisible(true);
        });
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // ========== 初始資料載入 ==========
        refreshMovieList(movieService.getAllMovies());
    }

    private void searchMovies() {
        String keyword = searchField.getText().trim().toLowerCase();
        if (keyword.equals("輸入電影關鍵字...")) keyword = "";
        List<Movie> filtered = movieService.searchMovies(keyword);
        refreshMovieList(filtered);
    }

    private void refreshMovieList(List<Movie> movies) {
        moviePanel.removeAll();
        if (movies.isEmpty()) {
            JLabel noResult = new JLabel("沒有符合條件的電影。", JLabel.CENTER);
            noResult.setFont(new Font("SansSerif", Font.PLAIN, 16));
            noResult.setAlignmentX(Component.CENTER_ALIGNMENT);
            moviePanel.add(Box.createVerticalStrut(30));
            moviePanel.add(noResult);
        } else {
            for (Movie movie : movies) {
                moviePanel.add(createMovieCard(movie));
                moviePanel.add(Box.createVerticalStrut(10));
            }
        }
        moviePanel.revalidate();
        moviePanel.repaint();
    }

    private JPanel createMovieCard(Movie movie) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(10, 10, 10, 10)
        ));

        // 圖片區
        ImageIcon poster = new ImageIcon("images/" + movie.getPosterFilename());
        Image scaled = poster.getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);
        JLabel posterLabel = new JLabel(new ImageIcon(scaled));
        card.add(posterLabel, BorderLayout.WEST);

        // 資訊區
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(0, 10, 0, 0));

        JLabel titleLabel = new JLabel(movie.getTitleZh() + " (" + movie.getTitleEn() + ")");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        infoPanel.add(titleLabel);

        infoPanel.add(new JLabel("分級：" + movie.getClassification()));
        infoPanel.add(new JLabel("片長：" + movie.getLength() + " 分鐘"));

        JTextArea summary = new JTextArea(movie.getSummary());
        summary.setLineWrap(true);
        summary.setWrapStyleWord(true);
        summary.setEditable(false);
        summary.setOpaque(false);
        summary.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infoPanel.add(summary);

        card.add(infoPanel, BorderLayout.CENTER);

        // 右側按鈕
        JButton selectBtn = new JButton("查看場次 / 開始訂票");
        selectBtn.setFont(new Font("SansSerif", Font.PLAIN, 13));
        selectBtn.setPreferredSize(new Dimension(160, 30));
        selectBtn.addActionListener(e -> {
            dispose();
            new ShowtimeSelectionPage(movie, movieService, showtimeService, ticketService).setVisible(true);
        });
        card.add(selectBtn, BorderLayout.EAST);

        return card;
    }
}
