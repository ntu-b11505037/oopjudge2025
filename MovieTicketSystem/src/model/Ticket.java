package model;

import java.util.List;

public class Ticket {
    private String ticketId;
    private Member member;
    private Showtime showtime;
    private List<Seat> seats;

    public Ticket(String ticketId, Member member, Showtime showtime, List<Seat> seats) {
        this.ticketId = ticketId;
        this.member = member;
        this.showtime = showtime;
        this.seats = seats;
    }

    public String getTicketId() { return ticketId; }
    public Member getMember() { return member; }
    public Showtime getShowtime() { return showtime; }
    public List<Seat> getSeats() { return seats; }
}

