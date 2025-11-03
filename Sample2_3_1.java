import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入
import java.io.InputStreamReader;              // 匯入 InputStreamReader：將位元組輸入流轉為字元輸入流
import java.io.IOException;                    // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：使用標準字元集常數（如 UTF-8）

public class Sample2_3_1 {                       // 宣告公開類別 Sample2_3（檔名需與類別同名）

    public static void main(String[] args) {   // 主程式進入點；改為內部 try-catch 處理例外而非在方法簽名丟出
        System.out.print("請輸入整數：");      // 輸出提示訊息（print 不換行，互動更自然）

        try (BufferedReader br =               // 使用 try-with-resources：確保使用完自動關閉資源
                 new BufferedReader(           // 建立帶緩衝的讀取器，降低 I/O 呼叫次數
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 避免跨平台亂碼

            String line = br.readLine();       // 讀取一行使用者輸入（可能為 null 表示 EOF）
            if (line == null) {                // 檢查是否為 EOF（沒有任何輸入）
                System.err.println("未讀到任何輸入（EOF）。"); // 提示未收到輸入
                System.out.println("結束處理"); // 一致性輸出結束訊息
                return;                        // 結束程式，不再往下解析
            }

            line = line.trim();                // 去除前後空白，避免空白影響解析
            if (line.isEmpty()) {              // 若為空字串，代表使用者未輸入有效內容
                System.err.println("未輸入內容。"); // 友善錯誤提示
                System.out.println("結束處理"); // 一致性輸出結束訊息
                return;                        // 結束程式
            }

            int num = Integer.parseInt(line);  // 將字串轉為整數（可能拋出 NumberFormatException）

            if (num == 1) {                    // 當輸入值等於 1
                System.out.println("輸入的是1"); // 輸出對應訊息
            } else {                            // 否則（輸入值不是 1）
                System.out.println("選擇的是1以外的數字（實際輸入：" + num + "）"); // 顯示非 1 並回報實際數值
            }

        } catch (NumberFormatException e) {     // 捕捉數字格式錯誤（如輸入非整數）
            System.err.println("格式錯誤：請輸入有效的整數。"); // 明確告知如何修正
        } catch (IOException e) {               // 捕捉 I/O 讀取過程中可能發生的例外
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 顯示詳細錯誤訊息以利除錯
        }

        System.out.println("結束處理");         // 程式統一尾端輸出，表示流程結束
    }                                           // main 方法結束
}                                               // 類別 Sample2_3 結束
