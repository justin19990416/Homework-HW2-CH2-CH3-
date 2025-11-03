import java.io.BufferedReader;                                   // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入
import java.io.InputStreamReader;                                // 匯入 InputStreamReader：將位元組輸入流轉成字元輸入流
import java.io.IOException;                                      // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;                        // 匯入 StandardCharsets：指定 UTF-8 編碼避免跨平台亂碼

public class Sample3_20 {                                        // 宣告公開類別 Sample3_20（檔名需與類別同名）
    public static void main(String[] args) {                     // 主程式進入點（JVM 從這裡開始執行）
        System.out.print("請輸入字串：");                         // 互動提示（print 不換行，游標停在同一行）
        try (BufferedReader br = new BufferedReader(             // try-with-resources：用畢自動關閉資源，避免記憶體/FD 泄漏
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8，避免在不同平台出現亂碼
            String str1 = readNonEmptyLine(br);                  // 讀取第一個非空白字串（若空白會要求重試）
            System.out.print("請輸入要新增的字串：");              // 互動提示：準備讀取要附加的字串
            String str2 = readNonEmptyLine(br);                  // 讀取第二個非空白字串（若空白會要求重試）

            // 以 StringBuilder 進行串接：單執行緒情境下比 StringBuffer 輕量，並預先配置容量減少擴容成本
            StringBuilder sb = new StringBuilder(str1.length() + str2.length()); // 預估容量＝兩者長度相加，降低 re-allocation
            sb.append(str1);                                     // 先放入原始字串（保留輸入順序）
            sb.append(str2);                                     // 再把第二個字串附加在尾端
            String result = sb.toString();                       // 轉回不可變字串供輸出或後續使用

            System.out.printf("在「%s」後新增「%s」的話，會變成「%s」。%n", // 使用格式化輸出（%n 跨平台換行）
                              str1, str2, result);               // 代入：原字串、要新增的字串、結果字串
        } catch (IOException e) {                                // 捕捉 I/O 相關例外（例如輸入中斷或 EOF）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 輸出錯誤訊息到標準錯誤（便於除錯）
        }                                                        // try-catch 區塊結束
    }                                                            // main 方法結束

    private static String readNonEmptyLine(BufferedReader br)    // 私有工具方法：讀取「非空白」的一行字串
            throws IOException {                                 // 宣告可能拋出 IOException 供呼叫端處理
        while (true) {                                           // 迴圈直到取得合法輸入才結束
            String line = br.readLine();                         // 讀取一行；若回傳 null 代表輸入來源已結束（EOF）
            if (line == null) {                                  // 檢查 EOF 狀況
                throw new IOException("未讀到任何輸入（EOF）。"); // 無法再讀取時拋出例外
            }                                                    // EOF 檢查結束
            line = line.trim();                                  // 去除首尾空白，避免把空白視為有效內容
            if (!line.isEmpty()) {                               // 若字串非空白即為合法
                return line;                                     // 回傳使用者輸入
            }                                                    // 非空白檢查結束
            System.out.print("未輸入內容，請重新輸入：");         // 提示使用者重新輸入（保持同一行互動體驗）
        }                                                        // while 迴圈結束
    }                                                            // readNonEmptyLine 方法結束
}                                                                // 類別 Sample3_20 結束

