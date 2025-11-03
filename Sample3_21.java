import java.io.BufferedReader;                                   // 匯入 BufferedReader：高效率逐行讀取文字輸入
import java.io.InputStreamReader;                                // 匯入 InputStreamReader：將位元組流轉為字元流
import java.io.IOException;                                      // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;                        // 匯入 StandardCharsets：指定 UTF-8 編碼避免亂碼

public class Sample3_21 {                                        // 宣告公開類別 Sample3_21（檔名需與類別同名）
    public static void main(String[] args) {                     // 主程式進入點（JVM 從這裡開始執行）
        System.out.println("請輸入兩個整數（每行一個）：");         // 友善提示：請使用者各輸入一個整數（共兩行）

        try (BufferedReader br =                                 // 使用 try-with-resources：確保資源用畢自動關閉
                 new BufferedReader(                             // 建立具緩衝的讀取器以降低 I/O 次數
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 避免跨平台亂碼
            int num1 = readIntWithRetry(br, "請輸入第 1 個整數："); // 讀取第 1 個整數（含重試與錯誤訊息）
            int num2 = readIntWithRetry(br, "請輸入第 2 個整數："); // 讀取第 2 個整數（含重試與錯誤訊息）

            int ans = Math.max(num1, num2);                      // 使用標準函式 Math.max 取得較大值（避免重複判斷）
            System.out.printf("%d 與 %d 中較大的是 %d%n",          // 以格式化輸出結果（%n 為跨平台換行）
                              num1, num2, ans);                  // 代入兩個輸入與較大值

            if (num1 == num2) {                                  // 加值：若兩數相等，補充說明
                System.out.println("補充：兩個數字相等。");        // 告知使用者兩數相等（避免誤解）
            }                                                    // if 區塊結束
        } catch (IOException e) {                                // 捕捉 I/O 相關例外（例如輸入中斷或 EOF）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 輸出錯誤訊息到標準錯誤輸出
        }                                                        // try-catch 區塊結束
    }                                                            // main 方法結束

    private static int readIntWithRetry(BufferedReader br, String prompt) // 私有工具：帶提示與重試的整數讀取
            throws IOException {                                 // 由呼叫端（main 的 try 區塊）負責處理 IOException
        while (true) {                                           // 迴圈：直到成功解析出整數才結束
            System.out.print(prompt);                            // 顯示輸入提示（print 不換行，游標留在同一行）
            String line = br.readLine();                         // 讀取一行字串（可能為 null 代表 EOF）
            if (line == null) {                                  // 若讀到 EOF（無更多輸入）
                throw new IOException("未讀到任何輸入（EOF）。"); // 拋出例外：無法再取得資料
            }                                                    // EOF 檢查結束
            line = line.trim();                                  // 去除前後空白（避免空白影響解析）
            if (line.isEmpty()) {                                // 若只輸入空白或直接按 Enter
                System.out.println("未輸入內容，請重新輸入整數。"); // 友善提示並要求重試
                continue;                                        // 回到迴圈起點
            }                                                    // 空字串檢查結束
            try {                                                // 嘗試將字串解析為整數
                return Integer.parseInt(line);                   // 成功解析後立即回傳
            } catch (NumberFormatException ex) {                 // 若格式不正確（非整數）
                System.out.println("格式錯誤，請輸入有效的整數（例如：42）。"); // 提示正確格式並要求重試
            }                                                    // 解析失敗處理結束
        }                                                        // while 迴圈結束
    }                                                            // readIntWithRetry 方法結束
}                                                                // 類別 Sample3_21 結束

