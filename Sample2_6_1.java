import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入
import java.io.InputStreamReader;              // 匯入 InputStreamReader：將位元組輸入流轉成字元輸入流
import java.io.IOException;                    // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：指定 UTF-8 等標準字元集避免亂碼

public class Sample2_6_1 {                       // 宣告公開類別 Sample2_6（檔名需與類別同名）
    public static void main(String[] args) {   // 主程式進入點；改以內部 try-catch 處理例外提升健壯性
        System.out.print("請輸入 a 或 b：");   // 提示使用者輸入（print 不換行，互動更自然）

        try (BufferedReader br =               // 使用 try-with-resources：確保用完會自動關閉資源
                 new BufferedReader(           // 建立帶緩衝的文字讀取器，降低底層 I/O 次數提升效能
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼避免跨平台亂碼

            String line = br.readLine();       // 讀取一行使用者輸入；若為 null 代表輸入來源已結束（EOF）
            if (line == null) {                // 檢查是否為 EOF（沒有任何輸入可讀）
                System.err.println("未讀到任何輸入（EOF）。"); // 於標準錯誤輸出提示訊息
                System.out.println("結束處理"); // 一致性輸出結尾訊息
                return;                        // 無法繼續解析，提前結束程式
            }

            line = line.trim();                // 移除前後空白，以避免空白影響字元判斷
            if (line.isEmpty()) {              // 檢查是否為空字串（例如只按 Enter）
                System.err.println("未輸入內容。"); // 友善提示使用者尚未輸入有效內容
                System.out.println("結束處理"); // 一致性輸出結尾訊息
                return;                        // 提前結束程式
            }

            char letter = Character.toLowerCase(line.charAt(0)); // 取第一個字元並轉成小寫，兼容 'A'/'B' 與 'a'/'b'

            switch (letter) {                  // 根據輸入字元進行分支判斷
                case 'a':                      // 當第一個字元為 'a'
                    System.out.println("輸入的是 a"); // 輸出對應訊息：輸入的是 a
                    break;                     // 結束本分支，避免繼續往下匹配
                case 'b':                      // 當第一個字元為 'b'
                    System.out.println("輸入的是 b"); // 輸出對應訊息：輸入的是 b
                    break;                     // 結束本分支
                default:                       // 非 'a' 與 'b' 的任何其他字元
                    System.out.println("輸入的是 a 或 b 以外的字元（實際輸入：" + letter + "）"); // 顯示實際字元以利除錯
                    System.out.println("請僅輸入 a 或 b"); // 再次提示有效輸入範圍
                    break;                     // 結束 default 分支
            }                                   // switch 區塊結束

        } catch (IOException e) {               // 捕捉 I/O 讀取過程中可能拋出的例外
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 輸出具體錯誤訊息協助定位問題
        }

        System.out.println("結束處理");         // 程式最後統一輸出結束訊息，讓使用者知道流程已結束
    }                                           // main 方法結束
}                                               // 類別 Sample2_6 結束
