// File: src/Main.java
import model.*;
import service.*;
import ui.LoginPage;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 初始化服務元件
        MovieService movieService = new MovieService();
        MemberService memberService = new MemberService();
        TicketService ticketService = new TicketService();
        ShowtimeService showtimeService = new ShowtimeService(movieService); // ✅ 傳入 movieService 作為參數

        // 載入資料
        preloadMovies(movieService);
        preloadShowtimes(showtimeService, movieService);
        preloadTestMembers(memberService);

        // 啟動登入畫面
        SwingUtilities.invokeLater(() -> {
            LoginPage loginPage = new LoginPage(memberService, movieService, showtimeService, ticketService);
            loginPage.setVisible(true);
        });
    }

    private static void preloadMovies(MovieService movieService) {
        List<Movie> movieList = Arrays.asList(
            new Movie("美國隊長：無畏新世界", "Captain America: Brave New World", "poster_captain_america.png", "輔12", "新一代美國隊長的冒險即將展開", 130),
            new Movie("機動戰士Gundam GquuuuuuX", "Mobile Suit Gundam GquuuuuuX", "poster_gundam.png", "輔15", "高達系列最新作，戰火再臨", 120),
            new Movie("夜校女生", "Night School Girl", "poster_uniform.png", "普遍級", "意外揭開學校的神秘傳說", 105),
            new Movie("史蒂芬金之猴子", "Stephen King's The Monkey", "poster_monkey.png", "限制級", "邪靈猴子帶來的詛咒正在蔓延", 115),
            new Movie("殺人預言", "Nocturnal Prediction", "poster_nocturnal.png", "輔12", "預知夢揭露了驚人的連續殺人案", 125),
            new Movie("窒息倒數", "Last Breath", "poster_last_breath.png", "輔15", "氧氣耗盡前的人性考驗", 110),
            new Movie("火線追緝令", "Seven", "poster_seven.png", "限制級", "經典懸疑神探驚心動魄追兇之路", 127),
            new Movie("粗獷派建築師", "The Brutalist", "poster_brutalist.png", "普遍級", "一位建築師如何與時代對話", 100)
        );
        movieService.setMovies(movieList);
    }

    private static void preloadShowtimes(ShowtimeService showtimeService, MovieService movieService) {
        List<Movie> movies = movieService.getAllMovies();
        int count = 0;
        for (Movie movie : movies) {
            String hallType = (count < 4) ? "large" : "small";
            showtimeService.addShowtime(new Showtime(
                "S" + (count + 1) + "A",
                movie,
                "2025-06-15",
                "14:00",
                hallType,
                hallType.equals("large") ? 300 : 120
            ));
            showtimeService.addShowtime(new Showtime(
                "S" + (count + 1) + "B",
                movie,
                "2025-06-15",
                "19:00",
                hallType,
                hallType.equals("large") ? 300 : 120
            ));
            count++;
        }
    }

    private static void preloadTestMembers(MemberService memberService) {
        // 一般會員帳號
        memberService.register("test@example.com", "123456", "2000-01-01");

        // 管理員帳號（role 為 admin）
        memberService.register("admin@admin.com", "admin123", "1990-01-01");
    }
}
