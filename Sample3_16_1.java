import java.io.BufferedReader;                                   // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入
import java.io.InputStreamReader;                                // 匯入 InputStreamReader：把位元組輸入流轉成字元輸入流
import java.io.IOException;                                      // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;                        // 匯入 StandardCharsets：指定 UTF-8 編碼避免跨平台亂碼

public class Sample3_16_1 {                                      // 宣告公開類別 Sample3_16_1（檔名需與類別同名）
    public static void main(String[] args) {                     // 主程式進入點（JVM 從這裡開始執行）
        System.out.print("請輸入一個整數：");                     // 互動式提示（print 不換行，游標停在同一行）

        try (BufferedReader br = new BufferedReader(             // try-with-resources：使用完會自動關閉資源
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼避免亂碼
            int num = readIntWithRetry(br);                      // 呼叫方法：反覆讀取直到取得合法整數
            System.out.println("您輸入的數字是：" + num);         // 成功讀到整數後輸出結果
        } catch (IOException e) {                                // 捕捉 I/O 例外（例如輸入裝置中斷）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 顯示錯誤訊息到標準錯誤輸出
        }                                                        // try-catch 區塊結束
    }                                                            // main 方法結束

    private static int readIntWithRetry(BufferedReader br) throws IOException { // 工具方法：帶重試機制讀取整數
        while (true) {                                           // 迴圈：直到回傳合法整數才結束
            String line = br.readLine();                         // 讀取一行字串（可能為 null 代表 EOF）
            if (line == null) {                                  // 若為 null，表示輸入來源已結束
                throw new IOException("未讀到任何輸入（EOF）。"); // 拋出例外：無法再繼續讀取
            }                                                    // EOF 檢查結束
            line = line.trim();                                  // 去除前後空白，避免影響解析
            if (line.isEmpty()) {                                // 檢查是否為空字串（只按 Enter）
                System.out.print("未輸入內容，請重新輸入整數："); // 提示使用者重新輸入
                continue;                                        // 回到迴圈開頭重新讀取
            }                                                    // 空字串檢查結束
            try {                                                // 嘗試將字串轉為整數
                return Integer.parseInt(line);                   // 成功解析為整數則直接回傳
            } catch (NumberFormatException ex) {                 // 捕捉非整數格式的情況
                System.out.print("格式錯誤，請輸入有效的整數："); // 提示正確格式並要求重試
            }                                                    // 解析失敗處理結束
        }                                                        // while 迴圈結束
    }                                                            // readIntWithRetry 方法結束
}                                                                // 類別 Sample3_16_1 結束

