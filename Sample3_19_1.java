import java.io.BufferedReader;                                   // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入
import java.io.InputStreamReader;                                // 匯入 InputStreamReader：將位元組輸入流轉成字元輸入流
import java.io.IOException;                                      // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;                        // 匯入 StandardCharsets：指定 UTF-8 編碼避免跨平台亂碼

public class Sample3_19_1 {                                        // 宣告公開類別 Sample3_19（檔名需與類別同名）
    public static void main(String[] args) {                     // 主程式進入點（JVM 從這裡開始執行）
        System.out.print("請輸入字串：");                         // 互動式提示（print 不換行，游標停在同一行）
        try (BufferedReader br = new BufferedReader(             // 使用 try-with-resources：確保用畢自動關閉資源
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼避免亂碼
            String text = readNonEmptyLine(br);                  // 讀取非空白的目標字串（若為空會持續要求重試）

            System.out.print("請輸入要搜尋的文字（取第 1 個字元）："); // 提示輸入搜尋字元（若輸入多字只取第一個碼點）
            String query = readNonEmptyLine(br);                 // 讀取非空白的搜尋輸入
            int codePoint = query.codePointAt(0);                // 取得搜尋字元的「第一個 Unicode 碼點」（支援表情/代理對）
                                                                 // 若用 charAt(0) 可能截斷代理對，故改用 codePointAt(0)

            int unitIndex = text.indexOf(codePoint);             // 在目標字串中尋找該碼點首次出現的位置（回傳 UTF-16 單元索引，-1 表示找不到）
            if (unitIndex != -1) {                               // 若找到位置
                int cpIndex = text.codePointCount(0, unitIndex)  // 計算從開頭到該位置之前共有幾個「碼點」
                               + 1;                              // 轉為人類可讀的第幾個（1 起算）
                String found = new String(Character.toChars(codePoint)); // 將碼點還原成字串（正確處理代理對）
                System.out.printf("%s 的第 %d 個字是「%s」%n",       // 以格式化輸出結果（%n 為跨平台換行）
                                  text, cpIndex, found);         // 代入：原字串、第幾個、找到的字元
            } else {                                             // 若找不到
                String want = new String(Character.toChars(codePoint)); // 將欲找的碼點還原為字串以正確顯示
                System.out.printf("%s 中沒有「%s」%n", text, want); // 輸出找不到的訊息
            }                                                    // if-else 結束
        } catch (IOException e) {                                // 捕捉 I/O 相關例外（例如裝置中斷或 EOF）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 顯示錯誤訊息到標準錯誤輸出（便於除錯）
        }                                                        // try-catch 區塊結束
    }                                                            // main 方法結束

    private static String readNonEmptyLine(BufferedReader br)    // 私有工具方法：反覆讀取直到取得「非空白」的一行
            throws IOException {                                 // 宣告可能拋出 IOException
        while (true) {                                           // 迴圈：直到取得合法輸入才結束
            String line = br.readLine();                         // 讀取一行輸入；若為 null 表示輸入來源已結束（EOF）
            if (line == null) {                                  // 檢查 EOF 狀態
                throw new IOException("未讀到任何輸入（EOF）。"); // 無法繼續讀取時拋出例外
            }                                                    // EOF 檢查結束
            line = line.trim();                                  // 去除前後空白，避免只有空白被當成有效輸入
            if (!line.isEmpty()) {                               // 若非空白字串
                return line;                                     // 回傳取得的內容
            }                                                    // 非空檢查結束
            System.out.print("未輸入內容，請重新輸入：");         // 提示使用者重新輸入（維持同一行互動）
        }                                                        // while 迴圈結束
    }                                                            // readNonEmptyLine 方法結束
}                                                                // 類別 Sample3_19 結束

