// File: src/service/TicketService.java
package service;

import model.Member;
import model.Seat;
import model.Showtime;
import model.Ticket;

import java.util.*;

public class TicketService {
    private Map<String, Ticket> ticketMap = new HashMap<>();
    private int ticketCounter = 1;

    /**
     * 建立一張票（失敗時回傳 null）
     */
    public Ticket createTicket(Member member, Showtime showtime, List<Seat> selectedSeats) {
        // 檢查是否有重複的座位（已被訂走）
        Set<Seat> booked = getBookedSeatsByShowtime(showtime);
        for (Seat seat : selectedSeats) {
            if (booked.contains(seat)) {
                return null; // 有座位已被訂，無法建立
            }
        }

        String ticketId = "T" + ticketCounter++;
        Ticket ticket = new Ticket(ticketId, member, showtime, selectedSeats);
        ticketMap.put(ticketId, ticket);
        return ticket;
    }

    /**
     * 回傳某場次已被訂的座位集合
     */
    public Set<Seat> getBookedSeatsByShowtime(Showtime showtime) {
        Set<Seat> booked = new HashSet<>();
        for (Ticket ticket : ticketMap.values()) {
            if (ticket.getShowtime().getId().equals(showtime.getId())) {
                booked.addAll(ticket.getSeats());
            }
        }
        return booked;
    }

    /**
     * 回傳所有票
     */
    public Collection<Ticket> getAllTickets() {
        return ticketMap.values();
    }
}
