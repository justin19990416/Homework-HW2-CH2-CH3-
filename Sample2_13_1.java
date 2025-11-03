import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入
import java.io.InputStreamReader;              // 匯入 InputStreamReader：把位元組輸入流轉成字元輸入流
import java.io.IOException;                    // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：使用標準字元集（例如 UTF-8）避免亂碼

public class Sample2_13_1 {                      // 宣告公開類別 Sample2_13（檔名需與類別同名）
    private static final int MIN_ITER = 1;     // 定義常數：允許的最小迴圈次數（下限）
    private static final int MAX_ITER = 10;    // 定義常數：允許的最大迴圈次數（上限）

    public static void main(String[] args) {   // 主程式進入點；改在方法內部處理例外以提升健壯性
        System.out.printf("請問要在第幾次結束迴圈呢？（%d~%d）%n", MIN_ITER, MAX_ITER); // 友善提示輸入範圍（含換行）
        System.out.print("請輸入整數：");      // 提示使用者輸入（print 不換行，互動更自然）

        try (BufferedReader br =               // 使用 try-with-resources：確保讀取完畢自動關閉資源
                 new BufferedReader(           // 建立帶緩衝的讀取器，降低底層 I/O 次數、提升效能
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼避免跨平台亂碼

            Integer target = null;             // 儲存欲在第幾次中斷的目標次數；使用包裝型別以允許 null 表示尚未就緒
            while (target == null) {           // 反覆讀取直到取得合法數值才離開
                String line = br.readLine();   // 讀取一行輸入（可能為 null，代表 EOF）
                if (line == null) {            // 判斷是否讀到 EOF（無任何可讀輸入）
                    System.err.println("未讀到任何輸入（EOF）。"); // 於標準錯誤輸出提示訊息
                    System.out.println("結束處理"); // 一致性輸出收尾訊息
                    return;                    // 無法繼續解析，提前結束程式
                }

                line = line.trim();            // 去除前後空白，避免影響整數解析與範圍判斷
                if (line.isEmpty()) {          // 若為空字串（例如只按 Enter）
                    System.out.print("未輸入內容，請輸入 " + MIN_ITER + " 到 " + MAX_ITER + " 的整數："); // 要求重新輸入
                    continue;                  // 回到迴圈開頭重新讀取
                }

                int val;                       // 暫存解析後的整數值
                try {                          // 嘗試將字串轉為整數
                    val = Integer.parseInt(line); // 解析字串為整數，若格式錯誤會丟 NumberFormatException
                } catch (NumberFormatException ex) { // 捕捉非整數輸入的情況
                    System.out.print("格式錯誤，請輸入 " + MIN_ITER + " 到 " + MAX_ITER + " 的整數："); // 提示正確格式
                    continue;                  // 繼續要求使用者重新輸入
                }

                if (val < MIN_ITER || val > MAX_ITER) { // 檢查是否超出允許範圍
                    System.out.print("超出範圍，請輸入 " + MIN_ITER + " 到 " + MAX_ITER + " 的整數："); // 提示合法範圍
                    continue;                  // 重新讀取輸入
                }

                target = val;                  // 通過檢查：設定目標次數並跳出迴圈
            }                                   // while 迴圈結束（此時 target 一定是合法整數）

            for (int i = 1; i <= MAX_ITER; i++) { // 執行 1 到 MAX_ITER 的處理流程
                System.out.printf("第%d次的處理。%n", i); // 回報目前執行到第幾次（%n 為跨平台換行）
                if (i == target) {             // 當達到目標次數時
                    System.out.printf("在第%d次結束迴圈。%n", target); // 顯示在第幾次結束迴圈的訊息
                    break;                     // 結束 for 迴圈（符合題意的 break 行為）
                }
            }                                   // for 迴圈結束

        } catch (IOException e) {               // 捕捉 I/O 相關例外（例如讀取失敗）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 顯示具體錯誤訊息以利除錯
        }                                       // try-with-resources 區塊結束

        System.out.println("結束處理");         // 程式最後統一輸出結束訊息（無論是否提早中止）
    }                                           // main 方法結束
}                                               // 類別 Sample2_13 結束

