package ui;

import model.*;
import service.TicketService;
import util.AppSession;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TheaterGUI {
    private Showtime showtime;
    private TicketService ticketService;

    private JFrame frame;
    private JTextArea infoArea;
    private List<Seat> allSeats;
    private Map<Seat, JButton> buttonMap;

    public TheaterGUI(Showtime showtime, TicketService ticketService) {
        this.showtime = showtime;
        this.ticketService = ticketService;
        this.allSeats = new ArrayList<>();
        this.buttonMap = new HashMap<>();
    }

    public void createAndShowGUI() {
        frame = new JFrame("座位選擇 - " + showtime.getMovie().getTitleZh());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());

        // 頂部標示螢幕
        JPanel screenPanel = new JPanel();
        screenPanel.setPreferredSize(new Dimension(100, 40));
        screenPanel.setBackground(Color.DARK_GRAY);
        JLabel screenLabel = new JLabel("SCREEN", SwingConstants.CENTER);
        screenLabel.setForeground(Color.WHITE);
        screenPanel.add(screenLabel);
        frame.add(screenPanel, BorderLayout.NORTH);

        // 主體面板：左右分欄
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 左側座位區
        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(0, 1));

        boolean isLargeHall = showtime.getHallType().equals("large");
        int columns = isLargeHall ? 40 : 16;
        char maxRow = isLargeHall ? 'M' : 'I';

        for (char row = 'A'; row <= maxRow; row++) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            rowPanel.add(new JLabel(row + "  "));

            for (int i = 1; i <= columns; i++) {
                boolean isGap =
                        (isLargeHall && row >= 'A' && row <= 'K' && (i == 12 || i == 13 || i == 26 || i == 27)) ||
                        (isLargeHall && row == 'M' && (i >= 9 && i <= 30)) ||
                        (!isLargeHall && (i == 5 || i == 13));

                if (isGap) {
                    JLabel spacer = new JLabel("   ");
                    spacer.setPreferredSize(new Dimension(38, 25));
                    rowPanel.add(spacer);
                    continue;
                }

                Seat seat = new Seat(row, i);
                allSeats.add(seat);

                JButton button = new JButton(seat.toString());
                button.setPreferredSize(new Dimension(38, 25));
                button.setFont(new Font("SansSerif", Font.PLAIN, 8));
                button.setBackground(Color.LIGHT_GRAY);
                button.setOpaque(true);
                button.setBorderPainted(false);

                button.addActionListener(e -> {
                    if (!seat.isAvailable()) return;
                    seat.setSelected(!seat.isSelected());
                    button.setBackground(seat.isSelected() ? Color.BLUE : Color.LIGHT_GRAY);
                    updateInfo();
                });

                if (!seat.isAvailable()) {
                    button.setEnabled(false);
                    button.setBackground(Color.GRAY);
                }

                buttonMap.put(seat, button);
                rowPanel.add(button);
            }
            seatPanel.add(rowPanel);
        }

        JScrollPane seatScroll = new JScrollPane(seatPanel);
        seatScroll.setPreferredSize(new Dimension(800, 0));
        mainPanel.add(seatScroll, BorderLayout.CENTER);

        // 右側資訊區
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(350, 0));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel infoLabel = new JLabel("選擇資訊");
        infoLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        sidePanel.add(infoLabel);

        infoArea = new JTextArea(10, 30);
        infoArea.setEditable(false);
        JScrollPane infoScroll = new JScrollPane(infoArea);
        sidePanel.add(infoScroll);
        sidePanel.add(Box.createVerticalStrut(10));

        JButton confirmButton = new JButton("確認購票");
        JButton cancelButton = new JButton("取消選擇");

        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        confirmButton.addActionListener(e -> {
            List<Seat> selected = new ArrayList<>();
            for (Seat seat : allSeats) {
                if (seat.isSelected()) selected.add(seat);
            }

            if (selected.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "尚未選擇任何座位。", "提示", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Member current = AppSession.getCurrentMember();
            Ticket ticket = ticketService.createTicket(current, showtime, selected);

            if (ticket == null) {
                JOptionPane.showMessageDialog(frame, "訂票失敗，可能座位已被預訂。", "錯誤", JOptionPane.ERROR_MESSAGE);
                return;
            }

            showtime.setRemainingSeats(showtime.getRemainingSeats() - selected.size());

            StringBuilder sb = new StringBuilder("您已成功訂票，座位如下：\n");
            for (Seat s : selected) {
                sb.append(s.toString()).append(" ");
            }

            JOptionPane.showMessageDialog(frame, sb.toString(), "購票成功", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        });

        cancelButton.addActionListener(e -> {
            for (Seat seat : allSeats) {
                if (seat.isAvailable()) {
                    seat.setSelected(false);
                    buttonMap.get(seat).setBackground(Color.LIGHT_GRAY);
                }
            }
            updateInfo();
        });

        sidePanel.add(confirmButton);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(cancelButton);

        mainPanel.add(sidePanel, BorderLayout.EAST);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private void updateInfo() {
        StringBuilder sb = new StringBuilder("目前選擇：");
        for (Seat s : allSeats) {
            if (s.isSelected()) sb.append(s).append(" ");
        }
        infoArea.setText(sb.toString());
    }
}
