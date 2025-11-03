import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入
import java.io.InputStreamReader;              // 匯入 InputStreamReader：將位元組輸入流轉換為字元輸入流
import java.io.IOException;                    // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：使用 UTF-8 等標準字元集避免亂碼

public class Sample2_14_1 {                    // 宣告公開類別 Sample2_14_1（檔名需與類別同名）
    private static final int MIN_IDX = 1;      // 定義常數：可接受的最小次數（下限 1）
    private static final int MAX_IDX = 10;     // 定義常數：可接受的最大次數（上限 10）

    public static void main(String[] args) {   // 主程式進入點；改在方法內部自行處理例外以提高健壯性
        System.out.printf("要跳過第幾次的處理？（%d~%d）%n", MIN_IDX, MAX_IDX); // 友善提示輸入範圍（使用格式化輸出與跨平台換行）
        System.out.print("請輸入整數：");      // 顯示輸入提示（print 不換行，互動更自然）

        try (BufferedReader br =               // 使用 try-with-resources：確保使用完自動關閉資源
                 new BufferedReader(           // 建立帶緩衝的文字讀取器，降低底層 I/O 次數
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼避免跨平台亂碼

            Integer skipTarget = null;         // 用來儲存要被跳過的「第幾次」；null 代表尚未取得合法輸入
            while (skipTarget == null) {       // 反覆讀取直到取得合法且在範圍內的整數
                String line = br.readLine();   // 讀取一行使用者輸入；可能為 null（EOF）
                if (line == null) {            // 若為 null，代表輸入來源已結束（無法再讀）
                    System.err.println("未讀到任何輸入（EOF）。"); // 提示錯誤：無可用輸入
                    System.out.println("結束處理"); // 一致性輸出收尾訊息
                    return;                    // 提前結束程式
                }

                line = line.trim();            // 去除前後空白，避免空白影響解析與判斷
                if (line.isEmpty()) {          // 檢查是否為空字串（例如只按 Enter）
                    System.out.print("未輸入內容，請輸入 " + MIN_IDX + " 到 " + MAX_IDX + " 的整數："); // 要求重新輸入
                    continue;                  // 回到迴圈開頭再讀一次
                }

                int val;                       // 宣告暫存變數，用來保存解析後的整數值
                try {                          // 嘗試將輸入字串轉為整數
                    val = Integer.parseInt(line); // 解析字串為整數，若格式不正確會丟出 NumberFormatException
                } catch (NumberFormatException ex) { // 捕捉非整數或格式錯誤的情況
                    System.out.print("格式錯誤，請輸入 " + MIN_IDX + " 到 " + MAX_IDX + " 的整數："); // 提供可採取行動的提示
                    continue;                  // 重新讀取輸入
                }

                if (val < MIN_IDX || val > MAX_IDX) { // 檢查是否超出允許範圍
                    System.out.print("超出範圍，請輸入 " + MIN_IDX + " 到 " + MAX_IDX + " 的整數："); // 提示合法範圍
                    continue;                  // 要求重新輸入
                }

                skipTarget = val;              // 通過所有檢查：設定要跳過的目標次數
            }                                   // while 迴圈結束（此時 skipTarget 一定合法）

            for (int i = MIN_IDX; i <= MAX_IDX; i++) { // 由 1 執行到 10 的處理流程
                if (i == skipTarget) {         // 當迭代次數等於要跳過的次數
                    System.out.printf("（已跳過第%d次的處理）%n", i); // 可觀測性：明確告知哪一次被跳過
                    continue;                  // 使用 continue 跳過此次迴圈後續處理
                }
                System.out.printf("第%d次的處理。%n", i); // 正常情況：輸出目前的處理次數
            }                                   // for 迴圈結束

        } catch (IOException e) {               // 捕捉 I/O 相關例外（例如讀取失敗）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 顯示具體錯誤訊息以利除錯
        }                                       // try-with-resources 區塊結束

        System.out.println("結束處理");         // 程式最後統一輸出結束訊息（無論是否提前中止）
    }                                           // main 方法結束
}                                               // 類別 Sample2_14_1 結束
