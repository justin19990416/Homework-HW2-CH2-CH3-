import java.io.BufferedReader;                                   // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入
import java.io.InputStreamReader;                                // 匯入 InputStreamReader：把位元組輸入流轉為字元輸入流
import java.io.IOException;                                      // 匯入 IOException：處理輸入/輸出相關例外
import java.nio.charset.StandardCharsets;                        // 匯入 StandardCharsets：指定 UTF-8 編碼避免亂碼
import java.util.Locale;                                         // 匯入 Locale：使用 Locale.ROOT 進行大小寫轉換以避免地區性差異

public class Sample3_18_1 {                                        // 宣告公開類別 Sample3_18（檔名需與類別同名）
    public static void main(String[] args) {                     // 主程式進入點（JVM 從這裡開始執行）
        System.out.print("請輸入英文字母：");                     // 互動式提示（print 不換行，游標停在同一行）
        try (BufferedReader br = new BufferedReader(             // 使用 try-with-resources：確保用畢自動關閉資源
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼避免跨平台亂碼
            String str = readNonEmptyLine(br);                   // 讀取非空白的一行字串（會要求重試直到合法）
            String upper = str.toUpperCase(Locale.ROOT);         // 轉大寫：使用 Locale.ROOT 避免土耳其語等地區性問題
            String lower = str.toLowerCase(Locale.ROOT);         // 轉小寫：同樣採用 Locale.ROOT 確保一致結果
            System.out.printf("轉換成大寫時為：%s%n", upper);      // 輸出大寫結果（%n 為跨平台換行）
            System.out.printf("轉換成小寫時為：%s%n", lower);      // 輸出小寫結果
        } catch (IOException e) {                                // 捕捉 I/O 相關例外（例如裝置中斷或 EOF）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 顯示錯誤訊息到標準錯誤輸出（便於除錯）
        }                                                        // try-catch 區塊結束
    }                                                            // main 方法結束

    private static String readNonEmptyLine(BufferedReader br) throws IOException { // 工具方法：讀取非空白輸入，否則持續提示
        while (true) {                                           // 迴圈：直到取得合法的非空白字串才結束
            String line = br.readLine();                         // 讀取一行輸入（可能為 null，代表 EOF）
            if (line == null) {                                  // 若為 null，表示資料來源已結束
                throw new IOException("未讀到任何輸入（EOF）。"); // 拋出例外：無法再繼續讀取
            }                                                    // EOF 檢查結束
            line = line.trim();                                  // 去除前後空白，避免只有空白卻被當成有效內容
            if (!line.isEmpty()) {                               // 若不是空字串，代表已取得有效輸入
                return line;                                     // 回傳取得的內容
            }                                                    // 非空檢查結束
            System.out.print("未輸入內容，請重新輸入英文字母："); // 提示使用者重新輸入（保持同一行互動）
        }                                                        // while 迴圈結束
    }                                                            // readNonEmptyLine 方法結束
}                                                                // 類別 Sample3_18 結束

