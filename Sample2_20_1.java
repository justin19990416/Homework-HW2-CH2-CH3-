import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝機制以高效率讀取文字輸入
import java.io.InputStreamReader;              // 匯入 InputStreamReader：將位元組輸入流轉為字元輸入流
import java.io.IOException;                    // 匯入 IOException：處理輸入輸出相關例外
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：指定 UTF-8 等標準字元集以避免亂碼
import java.util.Arrays;                       // 匯入 Arrays：使用內建排序方法取代手寫氣泡排序（更簡潔高效）

public class Sample2_20_1 {                    // 宣告公開類別 Sample2_20_1（檔名需與類別同名）
    private static final int N = 5;            // 定義常數 N：需要讀取的分數筆數，避免魔術數字便於維護

    public static void main(String[] args) {   // 主程式進入點（JVM 從此開始執行）
        System.out.printf("請輸入 %d 個人的分數（每行一個整數）：%n", N); // 友善提示輸入規則與筆數

        try (BufferedReader br =               // 使用 try-with-resources：確保輸入流用畢自動關閉
                 new BufferedReader(           // 建立帶緩衝的文字讀取器以降低 I/O 次數
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼避免跨平台亂碼

            int[] scores = new int[N];         // 配置整數陣列儲存分數（長度為 N）
            for (int i = 0; i < N; ) {         // 以手動遞增的 for 迴圈：成功讀到合法整數才遞增 i
                System.out.printf("請輸入第 %d 位分數：", i + 1); // 提示目前要輸入的是第幾位的分數
                String line = br.readLine();   // 讀取一行使用者輸入（可能為 null 表示 EOF）
                if (line == null) {            // 若為 null 代表輸入來源已結束（EOF）
                    System.err.println("未讀到任何輸入（EOF），提早結束。"); // 以錯誤輸出告知無法繼續
                    return;                    // 結束主程式
                }

                line = line.trim();            // 去除前後空白，避免空白影響解析
                if (line.isEmpty()) {          // 若為空字串（只按 Enter）
                    System.out.println("未輸入內容，請重新輸入有效的整數分數。"); // 要求重新輸入
                    continue;                  // 回到迴圈起點，不遞增 i
                }

                try {                           // 嘗試將字串解析為整數
                    int val = Integer.parseInt(line); // 解析整數，若格式錯誤會拋出 NumberFormatException
                    scores[i] = val;           // 寫入到陣列的第 i 個位置
                    i++;                       // 僅在成功解析後才遞增 i（前進到下一位）
                } catch (NumberFormatException e) { // 捕捉非整數或格式不正確的狀況
                    System.out.println("格式錯誤，請輸入合法的整數（例如：75）。"); // 友善提示並要求重試
                }                               // try-catch（NumberFormatException）結束
            }                                   // for 讀取分數迴圈結束

            Arrays.sort(scores);                // 使用內建排序：由小到大排序（O(n log n)）取代手寫氣泡排序
            for (int rank = 1, idx = N - 1; idx >= 0; rank++, idx--) { // 由最大值開始輸出（降冪排名）
                System.out.printf("第%d名的分數是 %d 分%n", rank, scores[idx]); // 顯示排名與對應分數
            }                                   // 輸出排名迴圈結束

        } catch (IOException e) {               // 捕捉 I/O 相關例外（讀取過程可能發生）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 輸出詳細錯誤訊息以利除錯
        }                                       // try-with-resources 區塊結束

        System.out.println("結束處理");         // 統一在最後輸出結束訊息，提升使用者體驗
    }                                           // main 方法結束
}                                               // 類別 Sample2_20_1 結束

