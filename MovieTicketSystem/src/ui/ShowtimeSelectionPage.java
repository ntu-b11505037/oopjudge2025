// File: src/ui/ShowtimeSelectionPage.java
package ui;

import model.Movie;
import model.Showtime;
import service.MovieService;
import service.ShowtimeService;
import service.TicketService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ShowtimeSelectionPage extends JFrame {
    private Movie movie;
    private MovieService movieService;
    private ShowtimeService showtimeService;
    private TicketService ticketService;

    public ShowtimeSelectionPage(Movie movie, MovieService movieService,
                                 ShowtimeService showtimeService, TicketService ticketService) {
        this.movie = movie;
        this.movieService = movieService;
        this.showtimeService = showtimeService;
        this.ticketService = ticketService;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("17CINEMA - 選擇場次");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 標題區塊
        JLabel titleLabel = new JLabel("《" + movie.getTitleZh() + "》請選擇場次", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // 場次清單區塊
        JPanel showtimeListPanel = new JPanel();
        showtimeListPanel.setLayout(new BoxLayout(showtimeListPanel, BoxLayout.Y_AXIS));
        showtimeListPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        ButtonGroup group = new ButtonGroup();
        List<Showtime> showtimes = showtimeService.getShowtimesByMovie(movie);

        for (Showtime s : showtimes) {
            String label = String.format("%s %s（%s廳） 可用座位：%d",
                    s.getDate(), s.getTime(),
                    s.getHallType().equals("large") ? "大" : "小",
                    s.getRemainingSeats());
            JRadioButton btn = new JRadioButton(label);
            btn.setFont(new Font("SansSerif", Font.PLAIN, 16));
            btn.setActionCommand(s.getId());
            group.add(btn);
            showtimeListPanel.add(btn);
            showtimeListPanel.add(Box.createVerticalStrut(5));
        }

        JScrollPane scroll = new JScrollPane(showtimeListPanel);
        scroll.setPreferredSize(new Dimension(700, 350));
        add(scroll, BorderLayout.CENTER);

        // 按鈕區塊
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        JButton backBtn = new JButton("返回電影列表");
        JButton nextBtn = new JButton("開始訂票");

        backBtn.setPreferredSize(new Dimension(150, 40));
        nextBtn.setPreferredSize(new Dimension(150, 40));

        backBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        nextBtn.setFont(new Font("SansSerif", Font.BOLD, 14));

        backBtn.addActionListener(e -> {
            dispose();
            new MovieSelectionPage(movieService, showtimeService, ticketService).setVisible(true);
        });

        nextBtn.addActionListener((ActionEvent e) -> {
            ButtonModel selected = group.getSelection();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "請先選擇場次！", "錯誤", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String showtimeId = selected.getActionCommand();
            Showtime selectedShowtime = showtimeService.getShowtimeById(showtimeId);

            if (selectedShowtime == null) {
                JOptionPane.showMessageDialog(this, "無法找到所選場次，請重新選擇。", "錯誤", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedShowtime.getMovie() == null) {
                selectedShowtime.setMovie(movie);
            }

            dispose();
            new TheaterGUI(selectedShowtime, ticketService).createAndShowGUI();
        });

        buttonPanel.add(backBtn);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(nextBtn);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
