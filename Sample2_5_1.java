import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入
import java.io.InputStreamReader;              // 匯入 InputStreamReader：把位元組輸入流轉成字元輸入流
import java.io.IOException;                    // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：提供 UTF-8 等標準字元集常數

public class Sample2_5_1 {                     // 宣告公開類別 Sample2_5_1（檔名需與類別同名）
    public static void main(String[] args) {   // 主程式進入點；移除 throws，改由內部 try-catch 處理例外
        System.out.print("請輸入整數：");      // 輸出提示訊息（print 不換行，互動更自然）

        try (BufferedReader br =               // 使用 try-with-resources：確保讀取完自動關閉資源
                 new BufferedReader(           // 建立帶緩衝的文字讀取器，減少底層 I/O 次數
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼避免跨平台亂碼

            String line = br.readLine();       // 讀取一行使用者輸入；若為 null 代表輸入來源結束（EOF）
            if (line == null) {                // 檢查是否為 EOF（沒有任何可讀取的輸入）
                System.err.println("未讀到任何輸入（EOF）。"); // 於標準錯誤輸出提示訊息
                System.out.println("結束處理"); // 一致性輸出收尾訊息
                return;                        // 無法繼續解析，提前結束程式
            }

            line = line.trim();                // 移除前後空白字元，避免影響整數解析
            if (line.isEmpty()) {              // 若使用者只按 Enter（空字串），視為無效輸入
                System.err.println("未輸入內容。"); // 友善提示未輸入內容
                System.out.println("結束處理"); // 一致性輸出收尾訊息
                return;                        // 提前結束程式
            }

            int num = Integer.parseInt(line);  // 嘗試將字串轉為整數（格式錯誤將拋出 NumberFormatException）

            switch (num) {                     // 使用 switch 分支：易讀、易擴充
                case 1:                        // 當輸入為 1
                    System.out.println("輸入的是1"); // 回饋使用者輸入的是 1
                    break;                     // 結束此分支，避免落入後續 case
                case 2:                        // 當輸入為 2
                    System.out.println("輸入的是2"); // 回饋使用者輸入的是 2
                    break;                     // 結束此分支
                default:                       // 其他非 1、2 的整數
                    System.out.println("輸入的是1或2以外的數字（實際輸入：" + num + "）"); // 顯示實際輸入值，提升可觀測性
            }                                   // switch 區塊結束

        } catch (NumberFormatException e) {     // 捕捉數字格式錯誤（例如輸入非整數）
            System.err.println("格式錯誤：請輸入有效的整數。"); // 提供可採取行動的錯誤訊息
        } catch (IOException e) {               // 捕捉 I/O 相關錯誤（讀取過程可能拋出）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 顯示詳細錯誤資訊以利除錯
        }                                       // try-catch 區塊結束

        System.out.println("結束處理");         // 程式最後統一輸出結束訊息
    }                                           // main 方法結束
}                                               // 類別 Sample2_5_1 結束

