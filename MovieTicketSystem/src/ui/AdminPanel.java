package ui;

import model.Movie;
import model.Showtime;
import service.MovieService;
import service.ShowtimeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AdminPanel extends JFrame {
    private MovieService movieService;
    private ShowtimeService showtimeService;

    public AdminPanel(MovieService movieService, ShowtimeService showtimeService) {
        this.movieService = movieService;
        this.showtimeService = showtimeService;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("後台管理系統");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        String[] tabs = {"電影管理", "場次管理", "訂票狀態"};
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab(tabs[0], createMoviePanel());
        tabbedPane.addTab(tabs[1], createShowtimePanel());
        tabbedPane.addTab(tabs[2], createBookingStatusPanel());

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        setContentPane(mainPanel);
    }

    private JPanel createMoviePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<Movie> model = new DefaultListModel<>();
        for (Movie m : movieService.getAllMovies()) {
            model.addElement(m);
        }

        JList<Movie> movieList = new JList<>(model);
        movieList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getTitleZh() + " / " + value.getTitleEn());
            if (isSelected) label.setBackground(Color.LIGHT_GRAY);
            label.setOpaque(true);
            return label;
        });

        JButton removeBtn = new JButton("下架電影");
        removeBtn.addActionListener(e -> {
            Movie selected = movieList.getSelectedValue();
            if (selected != null) {
                movieService.removeMovie(selected);
                model.removeElement(selected);
            }
        });

        // ========== 新增電影表單 ==========
        JPanel addPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        addPanel.setBorder(BorderFactory.createTitledBorder("新增電影"));

        JTextField zhField = new JTextField();
        JTextField enField = new JTextField();
        JTextField posterField = new JTextField(); // 範例：poster_gundam.png
        JTextField classificationField = new JTextField(); // 範例：保護級12+
        JTextField summaryField = new JTextField();
        JTextField durationField = new JTextField(); // 以分鐘為單位

        addPanel.add(new JLabel("中文片名：")); addPanel.add(zhField);
        addPanel.add(new JLabel("英文片名：")); addPanel.add(enField);
        addPanel.add(new JLabel("海報檔名：")); addPanel.add(posterField);
        addPanel.add(new JLabel("電影分級：")); addPanel.add(classificationField);
        addPanel.add(new JLabel("電影簡介：")); addPanel.add(summaryField);
        addPanel.add(new JLabel("片長（分鐘）：")); addPanel.add(durationField);

        JButton addBtn = new JButton("新增電影");
        addBtn.addActionListener(e -> {
            String zh = zhField.getText().trim();
            String en = enField.getText().trim();
            String poster = posterField.getText().trim();
            String classification = classificationField.getText().trim();
            String summary = summaryField.getText().trim();
            String durationStr = durationField.getText().trim();

            if (zh.isEmpty() || en.isEmpty() || poster.isEmpty() || classification.isEmpty()
                    || summary.isEmpty() || durationStr.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "請完整填寫所有欄位", "錯誤", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int duration = Integer.parseInt(durationStr);
                Movie newMovie = new Movie(zh, en, poster, classification, summary, duration);
                movieService.addMovie(newMovie);
                model.addElement(newMovie);

                zhField.setText(""); enField.setText(""); posterField.setText("");
                classificationField.setText(""); summaryField.setText(""); durationField.setText("");

                JOptionPane.showMessageDialog(panel, "電影新增成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "片長請輸入整數分鐘", "格式錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        addPanel.add(new JLabel()); // 空欄位
        addPanel.add(addBtn);

        panel.add(new JScrollPane(movieList), BorderLayout.CENTER);
        panel.add(removeBtn, BorderLayout.SOUTH);
        panel.add(addPanel, BorderLayout.EAST);

        return panel;
    }

    private JPanel createShowtimePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultComboBoxModel<Movie> movieBoxModel = new DefaultComboBoxModel<>();
        for (Movie m : movieService.getAllMovies()) {
            movieBoxModel.addElement(m);
        }
        JComboBox<Movie> movieComboBox = new JComboBox<>(movieBoxModel);

        JTextArea showtimeArea = new JTextArea();
        showtimeArea.setEditable(false);

        JButton viewBtn = new JButton("查看場次");
        viewBtn.addActionListener(e -> {
            Movie selected = (Movie) movieComboBox.getSelectedItem();
            if (selected != null) {
                List<Showtime> list = showtimeService.getShowtimesByMovie(selected);
                StringBuilder sb = new StringBuilder();
                for (Showtime s : list) {
                    sb.append(s.getDate()).append(" ").append(s.getTime())
                      .append(" / ").append(s.getHallType())
                      .append(" / 可用位：").append(s.getRemainingSeats()).append("\n");
                }
                showtimeArea.setText(sb.toString());
            }
        });

        JTextField dateField = new JTextField(10);
        JTextField timeField = new JTextField(5);
        JComboBox<String> hallBox = new JComboBox<>(new String[]{"standard", "large"});
        JTextField seatCountField = new JTextField(5);

        JButton addBtn = new JButton("新增場次");
        addBtn.addActionListener(e -> {
            Movie selected = (Movie) movieComboBox.getSelectedItem();
            String date = dateField.getText().trim();
            String time = timeField.getText().trim();
            String hall = (String) hallBox.getSelectedItem();
            String seatsStr = seatCountField.getText().trim();

            if (selected == null || date.isEmpty() || time.isEmpty() || seatsStr.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "請填寫所有欄位", "錯誤", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int seatCount = Integer.parseInt(seatsStr);
                Showtime newShowtime = new Showtime(selected, date, time, hall, seatCount);
                showtimeService.addShowtime(newShowtime);
                JOptionPane.showMessageDialog(panel, "新增成功！", "成功", JOptionPane.INFORMATION_MESSAGE);

                dateField.setText("");
                timeField.setText("");
                seatCountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "座位數應為整數", "格式錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel top = new JPanel();
        top.add(new JLabel("選擇電影："));
        top.add(movieComboBox);
        top.add(viewBtn);

        JPanel addPanel = new JPanel();
        addPanel.setBorder(BorderFactory.createTitledBorder("新增場次"));
        addPanel.add(new JLabel("日期(yyyy-mm-dd)："));
        addPanel.add(dateField);
        addPanel.add(new JLabel("時間(HH:mm)："));
        addPanel.add(timeField);
        addPanel.add(new JLabel("影廳："));
        addPanel.add(hallBox);
        addPanel.add(new JLabel("座位數："));
        addPanel.add(seatCountField);
        addPanel.add(addBtn);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(showtimeArea), BorderLayout.CENTER);
        panel.add(addPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createBookingStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea statusArea = new JTextArea();
        statusArea.setEditable(false);

        JButton refreshBtn = new JButton("更新資料");
        refreshBtn.addActionListener((ActionEvent e) -> {
            StringBuilder sb = new StringBuilder();
            for (Showtime s : showtimeService.getAllShowtimes()) {
                sb.append("[").append(s.getId()).append("] ")
                  .append(s.getMovie().getTitleZh()).append(" / ")
                  .append(s.getDate()).append(" ").append(s.getTime()).append(" / ")
                  .append(s.getRemainingSeats()).append(" 座位剩餘\n");
            }
            statusArea.setText(sb.toString());
        });

        panel.add(new JScrollPane(statusArea), BorderLayout.CENTER);
        panel.add(refreshBtn, BorderLayout.SOUTH);
        return panel;
    }
}
