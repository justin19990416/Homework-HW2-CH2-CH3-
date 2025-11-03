import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝以高效率讀取使用者輸入
import java.io.InputStreamReader;              // 匯入 InputStreamReader：將位元組輸入流轉為字元輸入流
import java.io.IOException;                    // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：指定 UTF-8 等標準字元集避免亂碼

public class Sample2_7_1 {                     // 宣告公開類別 Sample2_7_1（檔名需與類別名稱一致）
    public static void main(String[] args) {   // 主程式進入點；例外改於內部 try-catch 處理以提升健壯性
        System.out.println("請問你是男生嗎?");  // 顯示第一行提示問題
        System.out.print("請輸入 Y 或 N：");   // 顯示輸入提示（print 不換行，互動更自然）

        try (BufferedReader br =               // 使用 try-with-resources：確保資源自動關閉，避免外洩
                 new BufferedReader(           // 建立帶緩衝的文字讀取器，降低底層 I/O 次數
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼以避免跨平台亂碼

            String line = br.readLine();       // 讀取使用者輸入的一整行字串（可能為 null 代表 EOF）
            if (line == null) {                // 若為 null，表示輸入來源已結束（EOF）
                System.err.println("未讀到任何輸入（EOF）。"); // 輸出錯誤訊息到標準錯誤
                System.out.println("結束處理"); // 一致性輸出結尾訊息
                return;                        // 無法繼續處理，提前結束程式
            }

            line = line.trim();                // 去除前後空白，避免空白影響判斷
            if (line.isEmpty()) {              // 若為空字串，代表未輸入有效內容
                System.err.println("未輸入內容。"); // 友善提示使用者需輸入 Y 或 N
                System.out.println("結束處理"); // 一致性輸出結尾訊息
                return;                        // 提前結束程式
            }

            char letter = Character.toUpperCase(line.charAt(0)); // 取第一個字元並轉成大寫，統一處理 'y'/'n' 與 'Y'/'N'

            switch (letter) {                  // 依據輸入字元分支處理
                case 'Y':                      // 當輸入為 'Y'
                    System.out.println("你是男生啊!"); // 顯示對應回覆
                    break;                     // 結束此分支
                case 'N':                      // 當輸入為 'N'
                    System.out.println("你是女生啊?"); // 顯示對應回覆（保留原訊息語氣）
                    break;                     // 結束此分支
                default:                       // 其他非 Y/N 的字元
                    System.out.println("輸入的不是 Y 或 N（實際輸入：" + letter + "）"); // 顯示實際字元以利除錯
                    System.out.println("請僅輸入 Y 或 N"); // 再次提示有效輸入範圍
                    break;                     // 結束 default 分支
            }                                   // switch 區塊結束

        } catch (IOException e) {               // 捕捉 I/O 讀取過程中可能發生的例外
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 顯示詳細錯誤訊息以利除錯
        }

        System.out.println("結束處理");         // 程式最後統一輸出結束訊息，讓使用者明確知道流程結束
    }                                           // main 方法結束
}                                               // 類別 Sample2_7_1 結束

