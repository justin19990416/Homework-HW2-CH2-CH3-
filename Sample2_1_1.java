import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝機制以高效率讀取文字輸入
import java.io.IOException;                    // 匯入 IOException：處理輸入輸出時可能拋出的例外
import java.io.InputStreamReader;              // 匯入 InputStreamReader：將位元組輸入流轉為字元輸入流
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：提供標準字元集常數（例如 UTF-8）

public class Sample2_1_1 {                       // 宣告公開類別 Sample2_1（檔名需與類別名稱一致）

    public static void main(String[] args) {   // 主程式進入點（JVM 從這裡開始執行）
        System.out.println("請輸入整數：");    // 輸出提示訊息，請使用者輸入整數
        try (BufferedReader br = new BufferedReader( // 使用 try-with-resources：自動關閉資源避免記憶體/資源外洩
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 將標準輸入以 UTF-8 編碼包裝為字元流，再由 BufferedReader 緩衝

            int num = readIntWithRetry(br);    // 呼叫自訂方法反覆讀取直到取得合法整數
            handleSelection(num);              // 根據輸入的整數執行對應的處理邏輯（方便未來擴充）

        } catch (IOException e) {              // 捕捉並處理 I/O 層級的例外（例如讀取失敗或 EOF）
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 將錯誤訊息輸出到標準錯誤串流
        }                                       // try-catch 區塊結束
        System.out.println("結束處理");         // 程式最後輸出結束訊息
    }                                           // main 方法結束

    // 反覆讀取直到拿到合法整數（若遇 EOF 則拋出 IOException 交由呼叫端處理）
    private static int readIntWithRetry(BufferedReader br) throws IOException { // 宣告 readIntWithRetry，需傳入已開啟的 BufferedReader
        while (true) {                          // 使用無窮迴圈，直到成功解析為整數才 return 中斷
            String line = br.readLine();        // 一次讀取一行文字（含使用者按下 Enter 的分行）
            if (line == null) {                 // 若讀到 null 代表輸入來源結束（EOF）
                throw new IOException("未讀到任何輸入（EOF）。"); // 拋出 IOException 通知呼叫端無法繼續讀取
            }                                   // if 區塊結束
            line = line.trim();                 // 去除前後空白字元，避免空白影響解析
            if (line.isEmpty()) {               // 若為空字串表示使用者未輸入實質內容
                System.out.print("未輸入內容，請重新輸入整數："); // 提示使用者重新輸入（不換行以接續同一行）
                continue;                       // 跳回迴圈開頭等待下一次輸入
            }                                   // if 區塊結束
            try {                               // 嘗試將字串轉為整數
                return Integer.parseInt(line);  // 解析成功則直接回傳該整數並結束方法
            } catch (NumberFormatException ex) { // 若格式不正確（含有非數字等），捕捉並處理例外
                System.out.print("格式錯誤，請輸入有效的整數："); // 提示使用者再次輸入（不換行）
            }                                   // try-catch 區塊結束
        }                                       // while 迴圈結束（實際上在成功回傳前不會到達這行）
    }                                           // readIntWithRetry 方法結束

    // 用 switch 依輸入值分支，方便未來擴充更多選項
    private static void handleSelection(int num) { // 宣告 handleSelection 方法，接收使用者輸入的整數
        switch (num) {                         // 根據 num 進行分支
            case 1:                            // 當輸入數值為 1 的情況
                System.out.println("你輸入/選擇的是 1"); // 輸出對應訊息
                break;                         // 結束此分支，避免繼續執行到 default
            default:                           // 其他未特別處理的數值
                System.out.println("你輸入的是 " + num); // 直接顯示使用者輸入的數值
        }                                       // switch 區塊結束
    }                                           // handleSelection 方法結束
}                                               // 類別 Sample2_1 結束
