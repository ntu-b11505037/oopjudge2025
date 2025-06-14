package util;

public class EmailUtil {

    /**
     * 模擬發送 Email，僅顯示寄出成功訊息。
     *
     * @param to      收件人 Email（參數保留，但不會印出）
     * @param subject 主旨（參數保留，但不會印出）
     * @param content 內文（參數保留，但不會印出）
     */
    public static void send(String to, String subject, String content) {
        System.out.println("[系統訊息] 已成功發送一封通知信件。");
    }
}
