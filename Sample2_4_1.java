import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝區以高效率讀取文字輸入
import java.io.InputStreamReader;              // 匯入 InputStreamReader：將位元組輸入流轉為字元輸入流
import java.io.IOException;                    // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：提供標準字元集常數（如 UTF-8）

public class Sample2_4_1 {                     // 宣告公開類別 Sample2_4_1（檔名需與類別同名）
    public static void main(String[] args) {   // 程式進入點；改用內部 try-catch 處理例外以提高健壯性
        System.out.print("請輸入整數：");      // 提示使用者輸入整數（print 不換行，互動更自然）

        try (BufferedReader br =               // 使用 try-with-resources 確保資源自動關閉，避免記憶體/資源外洩
                 new BufferedReader(           // 建立帶緩衝的文字讀取器，減少 I/O 次數以提升效能
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼避免跨平台亂碼

            String line = br.readLine();       // 讀取一行輸入；若為 null 代表輸入來源已結束（EOF）
            if (line == null) {                // 檢查是否為 EOF 狀態
                System.err.println("未讀到任何輸入（EOF）。"); // 提示沒有任何可讀取的輸入
                System.out.println("結束處理"); // 一致性輸出結尾訊息
                return;                        // 提前結束程式
            }

            line = line.trim();                // 移除前後空白字元，避免影響整數解析
            if (line.isEmpty()) {              // 若是空字串，代表使用者未輸入有效內容
                System.err.println("未輸入內容。"); // 友善提示使用者
                System.out.println("結束處理"); // 一致性輸出結尾訊息
                return;                        // 提前結束程式
            }

            int num = Integer.parseInt(line);  // 嘗試將輸入字串轉為整數（可能丟出 NumberFormatException）

            switch (num) {                     // 使用 switch 取代多重 if-else，較易閱讀與擴充
                case 1:                        // 當輸入為 1 時
                    System.out.println("輸入的是1"); // 輸出對應訊息：輸入的是 1
                    break;                     // 結束此分支，避免落入後續 case
                case 2:                        // 當輸入為 2 時
                    System.out.println("輸入的是2"); // 輸出對應訊息：輸入的是 2
                    break;                     // 結束此分支
                default:                       // 其他非 1、2 的整數
                    System.out.println("輸入的是1或2以外的數字（實際輸入：" + num + "）"); // 回覆實際輸入，提升可觀測性
            }                                   // switch 區塊結束

        } catch (NumberFormatException e) {     // 捕捉字串轉整數時的格式錯誤
            System.err.println("格式錯誤：請輸入有效的整數。"); // 提供可採取行動的錯誤訊息
        } catch (IOException e) {               // 捕捉 I/O 相關錯誤（讀取失敗等）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 輸出錯誤細節以利除錯
        }

        System.out.println("結束處理");         // 在正常流程或例外處理後統一輸出結束訊息
    }                                           // main 方法結束
}                                               // 類別 Sample2_4_1 結束
