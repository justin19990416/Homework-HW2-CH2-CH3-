import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入
import java.io.InputStreamReader;              // 匯入 InputStreamReader：把位元組輸入流轉成字元輸入流
import java.io.IOException;                    // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：指定 UTF-8 編碼以避免跨平台亂碼
import java.util.Arrays;                       // 匯入 Arrays：使用陣列工具（排序等）
import java.util.Collections;                  // 匯入 Collections：提供 reverseOrder 以做降冪排序

public class Sample2_21_1 {                    // 宣告公開類別 Sample2_21_1（檔名需與類別同名）
    private static final int N = 5;            // 定義常數 N：需輸入的分數筆數，避免魔術數字便於維護

    public static void main(String[] args) {   // 主程式進入點；在方法內處理例外提高健壯性
        System.out.printf("請輸入 %d 人的分數（每行一個整數）：%n", N); // 友善提示輸入規則與筆數

        try (BufferedReader br =               // 使用 try-with-resources：讀取完自動關閉資源
                 new BufferedReader(           // 建立帶緩衝的文字讀取器，降低底層 I/O 次數
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 避免亂碼

            Integer[] scores = new Integer[N]; // 使用 Integer（物件型別）而非 int，才能搭配 Collections.reverseOrder 做降冪排序

            for (int i = 0; i < N; ) {         // 讀分數迴圈：成功解析有效整數後才遞增 i
                System.out.printf("請輸入第 %d 位分數：", i + 1); // 提示目前要輸入第幾位
                String line = br.readLine();   // 讀取一行使用者輸入；若為 null 表示輸入來源已結束（EOF）
                if (line == null) {            // 檢查是否遇到 EOF（無可用輸入）
                    System.err.println("未讀到任何輸入（EOF）。"); // 於標準錯誤輸出提示訊息
                    System.out.println("結束處理"); // 一致性輸出結尾訊息
                    return;                    // 提前結束程式
                }

                line = line.trim();            // 去除前後空白，避免影響數字解析
                if (line.isEmpty()) {          // 若為空字串（例如只按 Enter）
                    System.out.println("未輸入內容，請重新輸入有效的整數分數。"); // 要求重新輸入
                    continue;                  // 繼續下一輪讀取（不遞增 i）
                }

                try {                           // 嘗試將字串解析成整數
                    int val = Integer.parseInt(line); // 將輸入字串轉成 int，若格式錯誤會拋出 NumberFormatException
                    scores[i] = val;           // 寫入陣列第 i 個位置（自動裝箱成 Integer）
                    i++;                       // 僅在成功解析後才前進到下一位
                } catch (NumberFormatException e) { // 捕捉非整數或格式不正確的情況
                    System.out.println("格式錯誤，請輸入合法的整數（例如：75）。"); // 友善提示並要求重試
                }                               // try-catch（NumberFormatException）結束
            }                                   // 分數讀取迴圈結束

            Arrays.sort(scores, Collections.reverseOrder()); // 使用 Arrays.sort + reverseOrder 進行由大到小排序（降冪）

            for (int rank = 0; rank < scores.length; rank++) { // 依排序後結果輸出名次（0 為第 1 名）
                System.out.printf("第%d名的分數是 %d 分%n", rank + 1, scores[rank]); // 顯示排名與分數，%n 為跨平台換行
            }                                   // 輸出名次迴圈結束

        } catch (IOException e) {               // 捕捉 I/O 相關例外（讀取過程可能發生）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 顯示具體錯誤訊息以利除錯
        }                                       // try-with-resources 區塊結束

        System.out.println("結束處理");         // 程式最後統一輸出結束訊息，提升使用者體驗
    }                                           // main 方法結束
}                                               // 類別 Sample2_21_1 結束

