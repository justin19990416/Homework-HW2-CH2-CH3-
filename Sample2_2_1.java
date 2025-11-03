import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入
import java.io.IOException;                    // 匯入 IOException：處理輸入輸出相關例外
import java.io.InputStreamReader;              // 匯入 InputStreamReader：把位元組輸入流轉成字元輸入流
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：使用標準字元集（例如 UTF-8）

public class Sample2_2_1 {                     // 宣告公開類別 Sample2_2_1（檔名需與類別同名）
    public static void main(String[] args) {   // 主程式進入點；移除 throws，改由內部 try-catch 處理例外
        System.out.print("請輸入整數：");      // 提示使用者輸入整數（print 不換行，互動較自然）

        try (BufferedReader br =               // 使用 try-with-resources：確保讀取完自動關閉資源
             new BufferedReader(               // 建立帶緩衝的讀取器，減少 I/O 次數提升效能
                 new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼避免跨平台亂碼

            String line = br.readLine();       // 讀取一行使用者輸入（可能為 null 代表 EOF）
            if (line == null) {                // 若為 null 表示沒有任何輸入（EOF 狀態）
                System.err.println("未讀到任何輸入（EOF）。"); // 輸出錯誤訊息到標準錯誤
                return;                        // 無法繼續解析，直接結束主程式
            }

            line = line.trim();                // 去除前後空白，避免空白影響整數解析
            if (line.isEmpty()) {              // 檢查是否為空字串（使用者按 Enter 但未輸入內容）
                System.err.println("未輸入內容。"); // 友善提示使用者
                return;                        // 不再繼續往下執行
            }

            int num = Integer.parseInt(line);  // 嘗試將字串轉為整數（若格式不合法會丟 NumberFormatException）

            if (num == 1) {                    // 當輸入值為 1 的情況
                System.out.println("輸入的是1");  // 輸出對應訊息（輸入的是 1）
                System.out.println("選擇的是1");  // 只有在數值為 1 時才輸出「選擇的是1」（修正原程式總是輸出的問題）
            } else {                            // 其他數值的情況
                System.out.println("你輸入的是 " + num); // 回報實際輸入的數值
            }

        } catch (NumberFormatException e) {     // 捕捉字串轉整數時的格式錯誤
            System.err.println("格式錯誤：請輸入有效的整數。"); // 提供可採取行動的錯誤訊息
        } catch (IOException e) {               // 捕捉 I/O 讀取過程中的例外
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 顯示詳細錯誤資訊以便除錯
        }

        System.out.println("結束處理");         // 程式最後統一輸出結束訊息
    }                                           // main 方法結束
}                                               // 類別 Sample2_2_1 結束
