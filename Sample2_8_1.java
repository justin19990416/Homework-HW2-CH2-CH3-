import java.io.BufferedReader;                 // 匯入 BufferedReader：提供緩衝以高效率讀取文字輸入（stdin）
import java.io.InputStreamReader;              // 匯入 InputStreamReader：將位元組輸入流轉為字元輸入流
import java.io.IOException;                    // 匯入 IOException：處理 I/O 相關例外
import java.nio.charset.StandardCharsets;      // 匯入 StandardCharsets：使用 UTF-8 等標準字元集避免亂碼

public class Sample2_8_1 {                     // 宣告公開類別 Sample2_8_1（檔名需與類別名稱一致）
    public static void main(String[] args) {   // 主程式進入點；改用內部 try-catch 處理例外以提升健壯性
        System.out.print("請選擇路線（輸入 1→A 路線，2→B 路線）："); // 提示使用者輸入（print 不換行，互動更自然）

        try (BufferedReader br =               // 使用 try-with-resources：確保讀取結束後自動關閉資源
                 new BufferedReader(           // 建立帶緩衝的文字讀取器，降低底層 I/O 呼叫次數
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) { // 指定 UTF-8 編碼，避免跨平台亂碼

            Character ans = null;              // 用來儲存最終選擇結果（A 或 B）；null 代表尚未取得有效輸入
            while (ans == null) {              // 迴圈：直到取得有效選項才離開
                String line = br.readLine();   // 讀取一行使用者輸入；可能為 null（表示 EOF）
                if (line == null) {            // 若為 EOF，代表沒有任何輸入可讀
                    System.err.println("未讀到任何輸入（EOF）。"); // 提示錯誤：無法從標準輸入取得資料
                    System.out.println("結束處理"); // 一致性輸出收尾訊息
                    return;                    // 結束程式（無法繼續）
                }

                line = line.trim();            // 去除前後空白，避免空白影響解析或判斷
                if (line.isEmpty()) {          // 若為空字串（例如只按 Enter）
                    System.out.print("未輸入內容，請輸入 1 或 2："); // 友善提示並請求重新輸入
                    continue;                  // 回到迴圈開頭重新讀取
                }

                int res;                       // 宣告暫存變數 res 以保存解析後的整數
                try {                          // 嘗試將字串轉為整數
                    res = Integer.parseInt(line); // 將輸入字串解析成整數，可能拋出 NumberFormatException
                } catch (NumberFormatException ex) { // 捕捉數字格式錯誤（輸入非數字）
                    System.out.print("格式錯誤，請輸入數字 1 或 2："); // 提供可採取行動的錯誤訊息
                    continue;                  // 回到迴圈開頭重新讀取
                }

                switch (res) {                 // 依據數字做選路線判斷
                    case 1:                    // 當輸入為 1
                        ans = 'A';             // 對應到 A 路線
                        break;                 // 結束此分支
                    case 2:                    // 當輸入為 2
                        ans = 'B';             // 對應到 B 路線
                        break;                 // 結束此分支
                    default:                   // 其他數字（非 1/2）一律視為無效
                        System.out.print("無效的數字，請輸入 1 或 2："); // 提示重新輸入有效選項
                        // ans 仍為 null，迴圈將繼續要求輸入
                }                               // switch 區塊結束
            }                                   // while 迴圈結束（代表已取得有效的 ans）

            System.out.println("選擇了 " + ans + " 路線"); // 顯示最後的路線選擇結果
        } catch (IOException e) {               // 捕捉 I/O 讀取過程中可能發生的例外
            System.err.println("讀取輸入發生錯誤：" + e.getMessage()); // 輸出具體錯誤訊息以利除錯
        }                                       // try-with-resources 區塊結束

        System.out.println("結束處理");         // 統一在程式尾端輸出結束訊息
    }                                           // main 方法結束
}                                               // 類別 Sample2_8_1 結束

